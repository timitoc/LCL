package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.functional.ITask;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLAsyncTask
{
    private ITask m_Task;
    private boolean m_Started = false;
    private boolean m_Finished = false;

    public LCLAsyncTask(ITask task)
    {
        m_Task = task;
    }

    public void setTask(ITask task)
    {
        m_Task = task;
    }

    public synchronized final void executeTask()
    {
        m_Started = true;
        m_Task.task();
        m_Finished = true;
    }

    public final synchronized boolean started() { return m_Started; }
    public final synchronized boolean finished() { return m_Finished; }
}
