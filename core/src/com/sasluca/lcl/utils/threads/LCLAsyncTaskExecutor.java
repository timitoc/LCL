package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IUpdate;
import com.sasluca.lcl.abstractions.functional.ITask;
import com.sasluca.lcl.utils.collections.LCLArray;
import com.sasluca.lcl.utils.collections.LCLMap;
import com.sasluca.lcl.utils.pools.LCLPool;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLAsyncTaskExecutor implements IDisposable
{
    private LCLPool<LCLThread> m_Threads;
    private LCLArray<LCLThread> m_FreeThreads;
    private LCLMap<IAsyncTaskObserver, ITask> m_OnFinishChecks;
    private IUpdate m_OnFinishChecksHandler;

    public LCLAsyncTaskExecutor()
    {
        m_Threads = new LCLPool<>(() -> new LCLThread(null), object -> {});
        m_FreeThreads = new LCLArray<>();
        m_OnFinishChecks = new LCLMap<>();
        m_OnFinishChecksHandler = () ->
        {
            for(IAsyncTaskObserver observer : m_OnFinishChecks.keys())
            {
                if(m_OnFinishChecks.get(observer) != null)
                {
                    if (observer.finished())
                    {
                        m_OnFinishChecks.get(observer).task();
                        m_OnFinishChecks.replace(observer, null);
                    }
                }
            }

            for(int i = 0; i < m_OnFinishChecks.getSize(); i++)
                if(m_OnFinishChecks.get(m_OnFinishChecks.getKey(i)) == null)
                    m_OnFinishChecks.remove(m_OnFinishChecks.getKey(i));

            return null;
        };

        LCL.SYS.AppSystem.addUpdateHandler(m_OnFinishChecksHandler);
    }

    public final IAsyncTaskObserver executeTaskAsync(ITask task)
    {
        LCLThread t = m_Threads.get();

        if(m_FreeThreads.contains(t)) m_FreeThreads.remove(t);

        t.setTask(() -> { task.task(); m_FreeThreads.add(t); m_Threads.free(t); }).start();

        return t::finished;
    }

    /**
     * Executes a task on a thread but then executes the onTaskFinish on the OpenGL thread.
     * @param task
     * @param onTaskFinish
     * @return
     */
    public final IAsyncTaskObserver executeTaskAsync(ITask task, ITask onTaskFinish)
    {
        LCLThread t = m_Threads.get();

        if(m_FreeThreads.contains(t)) m_FreeThreads.remove(t);

        t.setTask(() -> { task.task(); m_FreeThreads.add(t); m_Threads.free(t); }).start();

        m_OnFinishChecks.put(t::finished, onTaskFinish);

        return t::finished;
    }

    /** Removes a free thread. Use this in case there are too many threads. */
    public LCLAsyncTaskExecutor removeThread()
    {
        if(m_FreeThreads.isEmpty()) return this;
        m_Threads.remove(m_FreeThreads.get(m_FreeThreads.getSize() - 1));
        m_FreeThreads.remove(m_FreeThreads.get(m_FreeThreads.getSize() - 1));

        return this;
    }

    public LCLAsyncTaskExecutor createThreads(int numberOfThreads)
    {
        while(numberOfThreads-- > 0) { LCLThread t = new LCLThread(null); m_Threads.addObject(t); m_FreeThreads.add(t); }

        return this;
    }

    public int getNumberOfThreads() { return m_Threads.getNumberOfObjects(); }
    public int getNumberOfFreeThreads() { return m_Threads.getNumberOfFreeObjects(); }
    public int getNumberOfThreadsInUse() { return m_Threads.getNumberOfObjectsInUse(); }

    @Override public void dispose()
    {
        LCL.SYS.AppSystem.removeUpdateHandler(m_OnFinishChecksHandler);
        m_Threads.dispose();
    }
}
