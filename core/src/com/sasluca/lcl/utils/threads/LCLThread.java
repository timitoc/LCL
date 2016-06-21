package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IReusable;
import com.sasluca.lcl.abstractions.functional.ITask;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLThread implements IReusable<LCLThread>, IDisposable
{
    private Thread m_Thread;
    private LCLAsyncTask m_Task;
    private AtomicBoolean m_IsAlive;

    public LCLThread(ITask task)
    {
        m_Task = new LCLAsyncTask(task);
        m_IsAlive = new AtomicBoolean();
        m_Thread = new Thread(() -> { while(m_IsAlive.get()) m_Task.executeTask(); });
    }

    @Override public final LCLThread reset()
    {
        return this;
    }

    public LCLThread start()
    {
        if(m_Task == null)
        {
            //TODO: ERROR
            return this;
        }

        if(!m_IsAlive.get())
        {
            m_Thread.start();
            m_IsAlive.getAndSet(true);
        }

        return this;
    }

    public synchronized LCLThread setTask(ITask task)
    {
        if(!m_Task.finished() && m_Task.started())
        {
            //TODO: ERROR
            return this;
        }

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
                m_IsAlive.getAndSet(false);
                m_Thread = null;
                m_Task = null;

                return;
            }
            //TODO: ERROR -> Thread can not be stopped until the task ended

            return;
        }

        m_IsAlive.getAndSet(false);
        m_Thread = null;
        m_Task = null;

        return;
    }
}
