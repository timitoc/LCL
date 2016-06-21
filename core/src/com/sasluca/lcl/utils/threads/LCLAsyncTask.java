package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.functional.ITask;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLAsyncTask
{
    private ITask m_Task;
    private final AtomicBoolean m_Started;
    private final AtomicBoolean m_Finished;

    public LCLAsyncTask(ITask task)
    {
        m_Task = task;
        m_Started = new AtomicBoolean(false);
        m_Finished = new AtomicBoolean(false);
    }

    public void setTask(ITask task)
    {
        m_Started.getAndSet(false);
        m_Finished.getAndSet(false);
        m_Task = task;
    }

    public final void executeTask()
    {
        if(m_Task == null || m_Finished.get()) return;

        m_Started.getAndSet(true);
        m_Task.task();
        m_Finished.getAndSet(true);
    }

    public final synchronized ITask getTask() { return m_Task; }
    public final synchronized boolean started() { return m_Started.get(); }
    public final synchronized boolean finished() { return m_Finished.get(); }
}
