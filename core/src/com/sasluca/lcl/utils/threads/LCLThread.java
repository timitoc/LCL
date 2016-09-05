package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IReusable;
import com.sasluca.lcl.abstractions.functional.ITask;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLThread implements IDisposable
{
    private boolean m_Started;
    private final LCLAsyncTask m_Task;
    private final Thread m_Thread;
    private final AtomicBoolean m_IsAlive;

    public LCLThread(ITask task)
    {
        m_Task = new LCLAsyncTask(task);
        m_IsAlive = new AtomicBoolean(true);
        m_Thread = new Thread(() -> { while(m_IsAlive.get()) m_Task.executeTask(); });
    }

    public LCLThread start()
    {
        if (m_Started) return this;
        m_Started = true;

        m_Thread.start();

        return this;
    }

    public LCLThread setTask(ITask task)
    {
        if (!m_Task.finished() && m_Task.started())
        {
            //TODO: ERROR
            return this;
        }

        m_Task.setTask(task);

        return this;
    }

    public final boolean isRunning() { return m_IsAlive.get(); }
    public final boolean currentTaskStarted() { return m_Task.started(); }
    public final boolean currentTaskFinished() { return m_Task.finished(); }

    @Override public void dispose()
    {
        m_Thread.interrupt();
        m_IsAlive.getAndSet(false);

        return;
    }
}
