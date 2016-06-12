package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IReusable;
import com.sasluca.lcl.abstractions.functional.ITask;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLThread implements IReusable<LCLThread>, IDisposable
{
    private Thread m_Thread;
    private LCLAsyncTask m_Task;
    private boolean m_Running;

    public LCLThread(ITask task)
    {
        m_Task = new LCLAsyncTask(task);
        m_Thread = new Thread(() ->
        {
            while (m_Running)
            {
                if(!m_Task.finished()) m_Task.executeTask();
            }
        });
    }

    public final LCLThread reset()
    {
        if ((m_Task.started() && m_Task.finished()) || !m_Task.started()) m_Task = null;
        return this;
    }

    public LCLThread start()
    {
        if(m_Task == null)
        {
            //ERROR
            return this;
        }

        m_Thread.start();

        return this;
    }

    public LCLThread setTask(ITask task)
    {
        m_Task.setTask(task);

        return this;
    }

    public final synchronized boolean started() { return m_Task.started(); }
    public final synchronized boolean finished() { return m_Task.finished(); }

    @Override public void dispose()
    {
        if(m_Task.started())
        {
            if(m_Task.finished())
            {
                m_Thread = null;
                m_Task = null;
                m_Running = false;

                return;
            }
            //Thread can not be stopped until the task ended

            return;
        }

        m_Thread = null;
        m_Task = null;

        return;
    }
}
