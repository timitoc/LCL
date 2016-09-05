package com.sasluca.lcl.sandbox.examples;

/**
 * Created by Sas Luca on 14-Jun-16.
 * Copyright (C) 2016 - LCL
 */

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.sandbox.Playground;
import com.sasluca.lcl.utils.threads.IAsyncTaskObserver;
import com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor;

import static com.sasluca.lcl.sandbox.Playground.State.TEST1;
import static com.sasluca.lcl.sandbox.Playground.State.TEST2;
import static com.sasluca.lcl.sandbox.Playground.State.TEST3;
import static com.sasluca.lcl.utils.net.LCLNetUtils.callURL;

/**
 *  THIS is an example of using the {@link com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor} to call a url and retrieve a string.
 *  THIS demo exemplifies how {@link com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor} uses a pool of {@link com.sasluca.lcl.utils.threads.LCLThread LCLThreads}
 *  to execute this task. When the first task is executed the LCLThread pool has no elements so it creates one. After the first task ends, the thread is freed, but
 *  it can still be used. THIS way when we start the second task we can see that the number of threads in the pool is still 1, meaning we are reusing the first thread.
 *  Finally  at the end we destroy the thread.
 */
public class EXAsyncTasks implements IStateHandler<Playground.State>
{
    IAsyncTaskObserver observer;

    @Override public void onState(Playground.State currentState)
    {
        if(currentState == TEST1)
        {
            if(observer.finished()) LCL.getAppSystem().changeState(TEST2);
        }

        if(currentState == TEST2)
        {
            if (observer.finished()) LCL.getAppSystem().changeState(TEST3);
        }
    }


    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        if(newState == TEST1)
        {
            observer = LCLAsyncTaskExecutor.executeTaskAsync(() -> callURL("http://www.google.com"));

            /** Print the number of threads created by the executor. In the beggining there were no threads but now it created 1. */
            System.out.println("Task 1 created, number of threads: " + LCLAsyncTaskExecutor.getNumberOfThreads());
        }

        if(newState == TEST2)
        {
            System.out.println("Task 1 currentTaskFinished, number of free threads: " + LCLAsyncTaskExecutor.getNumberOfFreeThreads());
            observer = LCLAsyncTaskExecutor.executeTaskAsync(() -> callURL("http://www.youtube.com"));

            /** Prints the number of threads in use. You can see there is still just one thread in use since it reuses the previously created one. */
            System.out.println("Task 2 created, number of threads: " + LCLAsyncTaskExecutor.getNumberOfThreads());
        }

        if(newState == TEST3)
        {
            System.out.println("Task 2 currentTaskFinished, number of free threads: " + LCLAsyncTaskExecutor.getNumberOfFreeThreads());

            LCLAsyncTaskExecutor.removeThread();

            System.out.println("One free thread removed, number of remaining threads: " + LCLAsyncTaskExecutor.getNumberOfThreads());
        }
    }
}
