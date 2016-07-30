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
    private LCLMap<IAsyncTaskObserver, ITask> m_OnFinishChecks;
    private IUpdate m_OnFinishChecksHandler;

    public LCLAsyncTaskExecutor()
    {
        m_Threads = new LCLPool<>(() -> new LCLThread(null), object -> {});
        m_OnFinishChecks = new LCLMap<>();
        m_OnFinishChecksHandler = () ->
        {
            //Loop thru all OnFinishChecks
            for (IAsyncTaskObserver observer : m_OnFinishChecks.keys())
            {
                //If a task finished and is not null execute it's
                if (m_OnFinishChecks.get(observer) != null)
                {
                    if (observer.finished())
                    {
                        m_OnFinishChecks.get(observer).task();
                        m_OnFinishChecks.replace(observer, null);
                    }
                }
            }

            //If a key is null remove the object
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

        t.setTask(task).start();

        m_OnFinishChecks.put(t::finished, () -> m_Threads.free(t));

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

        t.setTask(() -> { task.task(); }).start();

        m_OnFinishChecks.put(t::finished, () -> { onTaskFinish.task(); m_Threads.free(t); });

        return t::finished;
    }

    /** Removes a free thread. Use this in case there are too many threads. */
    public LCLAsyncTaskExecutor removeThread()
    {
        m_Threads.remove();

        return this;
    }

    public LCLAsyncTaskExecutor removeThreads(int numberOfThreads)
    {
        m_Threads.remove(numberOfThreads);

        return this;
    }

    public LCLAsyncTaskExecutor createThreads(int numberOfThreads)
    {
        while(numberOfThreads-- > 0) { LCLThread t = new LCLThread(null); m_Threads.addObject(t); }

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
