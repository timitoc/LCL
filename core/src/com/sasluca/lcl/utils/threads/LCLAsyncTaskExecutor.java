package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.functional.ITask;
import com.sasluca.lcl.utils.pools.LCLPool;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLAsyncTaskExecutor
{
    private final LCLPool<LCLThread> m_Threads;

    public LCLAsyncTaskExecutor()
    {
        m_Threads = new LCLPool<LCLThread>() { @Override public LCLThread newInstance() { return new LCLThread(null); } };
    }

    public final IAsyncTaskObserver executeTaskAsync(ITask task)
    {
        LCLThread t = m_Threads.get();
        t.setTask(() -> { task.task(); if(t.finished()) m_Threads.free(t); }).start();

        return () -> { return t.finished(); };
    }
}
