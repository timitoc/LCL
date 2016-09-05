package com.sasluca.lcl.utils.threads;

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.ITransformable;
import com.sasluca.lcl.abstractions.functional.ITask;
import com.sasluca.lcl.utils.collections.LCLArray;
import com.sasluca.lcl.utils.collections.LCLMap;
import com.sasluca.lcl.utils.pools.LCLSynchronizedPool;

/*
 * Copyright 2016 Sas Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class LCLAsyncTaskExecutor
{
    static { init(); }

    private static boolean m_Init;
    private static LCLSynchronizedPool<LCLThread> m_Threads;
    private static LCLMap<IAsyncTaskObserver, ITask> m_OnFinishChecks;
    private static final LCLAsyncTaskExecutor METHOD_CHAIN = new LCLAsyncTaskExecutor();

    private LCLAsyncTaskExecutor() {}

    public static void init()
    {
        if(m_Init) return;
        m_Init = true;

        m_OnFinishChecks = new LCLMap<>();
        m_Threads = new LCLSynchronizedPool<>(() -> new LCLThread(null), EMPTY_LAMBDA -> {});

        LCL.getAppSystem().addUpdateHandler(LCLAsyncTaskExecutor::update);
    }

    private static void update()
    {
        //Loop thru all OnFinishChecks
        for (IAsyncTaskObserver observer : m_OnFinishChecks.getKeys())
        {
            //If a task currentTaskFinished and is not null execute it's
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
                m_OnFinishChecks.remove(m_OnFinishChecks.getKey(i--));
    }

    public static IAsyncTaskObserver executeTaskAsync(ITask task)
    {
        LCLThread t = m_Threads.get();

        t.setTask(task).start();

        m_OnFinishChecks.put(t::currentTaskFinished, () -> m_Threads.free(t));

        return t::currentTaskFinished;
    }

    /**
     * Executes a task on a thread but then executes the onTaskFinish on the OpenGL thread.
     * @param task
     * @param onTaskFinish
     * @return
     */
    public static IAsyncTaskObserver executeTaskAsync(ITask task, ITask onTaskFinish)
    {
        LCLThread t = m_Threads.get().setTask(task).start();

        m_OnFinishChecks.put(t::currentTaskFinished, () -> { onTaskFinish.task(); m_Threads.free(t); });

        return t::currentTaskFinished;
    }

    /** Removes a free thread. Use this in case there are too many threads. */
    public static LCLAsyncTaskExecutor removeThread()
    {
        m_Threads.remove();

        return METHOD_CHAIN;
    }

    public static LCLAsyncTaskExecutor removeThreads(int numberOfThreads)
    {
        m_Threads.remove(numberOfThreads);

        return METHOD_CHAIN;
    }

    public static LCLAsyncTaskExecutor createThreads(int numberOfThreads)
    {
        while(numberOfThreads-- > 0) { LCLThread t = new LCLThread(null); m_Threads.addObject(t); }

        return METHOD_CHAIN;
    }

    public static int getNumberOfThreads() { return m_Threads.getNumberOfObjects(); }
    public static int getNumberOfFreeThreads() { return m_Threads.getNumberOfFreeObjects(); }
    public static int getNumberOfThreadsInUse() { return m_Threads.getNumberOfObjectsInUse(); }

    public static void dispose()
    {
        LCL.getAppSystem().removeUpdateHandler(LCLAsyncTaskExecutor::update);
        m_Threads.dispose();
    }
}
