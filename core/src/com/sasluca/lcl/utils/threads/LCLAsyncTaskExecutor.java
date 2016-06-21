package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.functional.ITask;
import com.sasluca.lcl.utils.pools.LCLPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLAsyncTaskExecutor implements IDisposable
{
    private LCLPool<LCLThread> m_Threads;
    private List<LCLThread> m_FreeThreads;

    public LCLAsyncTaskExecutor()
    {
        m_Threads = new LCLPool<>(() -> new LCLThread(null));
        m_FreeThreads = new ArrayList<>();
    }

    public final IAsyncTaskObserver executeTaskAsync(ITask task)
    {
        LCLThread t = m_Threads.get();

        if(m_FreeThreads.contains(t)) m_FreeThreads.remove(t);

        t.setTask(() -> { task.task(); m_FreeThreads.add(t); m_Threads.free(t); }).start();

        return t::finished;
    }

    /** Removes a free thread. Use this in case there are too many threads. */
    public LCLAsyncTaskExecutor removeThread()
    {
        if(m_FreeThreads.isEmpty()) return this;
        m_Threads.remove(m_FreeThreads.get(m_FreeThreads.size() - 1));
        m_FreeThreads.remove(m_FreeThreads.get(m_FreeThreads.size() - 1));

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

    @Override public void dispose() { m_Threads.dispose(); }
}
