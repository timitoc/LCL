package com.sasluca.lcl.sandbox.examples;

/**
 * Created by Sas Luca on 14-Jun-16.
 * Copyright (C) 2016 - LCL
 */

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.sandbox.Playground;
import com.sasluca.lcl.utils.threads.IAsyncTaskObserver;

import static com.sasluca.lcl.sandbox.Playground.State.TEST1;
import static com.sasluca.lcl.sandbox.Playground.State.TEST2;
import static com.sasluca.lcl.sandbox.Playground.State.TEST3;
import static com.sasluca.lcl.utils.net.LCLNetUtils.callURL;
import static com.sasluca.lcl.utils.net.LCLNetUtils.encodeParameter;

/**
 *  This is an example of using the {@link com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor} to call a url and retrieve a string.
 *  This demo exemplifies how {@link com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor} uses a pool of {@link com.sasluca.lcl.utils.threads.LCLThread LCLThreads}
 *  to execute this task. When the first task is executed the LCLThread pool has no elements so it creates one. After the first task ends, the thread is freed, but
 *  it can still be used. This way when we start the second task we can see that the number of threads in the pool is still 1, meaning we are reusing the first thread.
 *  Finally  at the end we destroy the thread.
 */
public class EXAsyncTasks implements IStateHandler<Playground.State>
{
    IAsyncTaskObserver observer;

    @Override public void onState(Playground.State currentState)
    {
        if(currentState == TEST1)
        {
            if(observer == null) observer = LCL.MASTER().AsyncTaskExecutor.executeTaskAsync(() ->
            {
                System.out.println(callURL("/api/Default/VerifyAccount/" + encodeParameter("SasLuca") + "/" + encodeParameter("WrongPassword:)")));
            });

            if(observer.finished()) LCL.MASTER().AppSystem.changeState(TEST2);
        }

        if(currentState == TEST2)
        {
            System.out.println(LCL.MASTER().AsyncTaskExecutor.getNumberOfThreads());

            if(observer != null)
            {
                LCL.MASTER().AsyncTaskExecutor.executeTaskAsync(() ->
                {
                    System.out.println(callURL("/api/Default/VerifyAccount/" + encodeParameter("SasLuca") + "/" + encodeParameter("WrongPassword:)")));
                });

                observer = null;
            }

            System.out.println(LCL.MASTER().AsyncTaskExecutor.getNumberOfThreads());
            LCL.MASTER().AppSystem.changeState(TEST3);
        }
    }


    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {

    }
}
