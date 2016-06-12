package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.sandbox.Playground.State;
import com.sasluca.lcl.utils.threads.IAsyncTaskObserver;

import static com.sasluca.lcl.LCL.ResourceManger;
import static com.sasluca.lcl.sandbox.Playground.State.TEST1;
import static com.sasluca.lcl.sandbox.Playground.State.TEST2;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler implements IStateHandler<Playground.State>
{
    IAsyncTaskObserver observer;
    Thread thread;

    @Override public void onState(Playground.State currentState)
    {
        float start = System.nanoTime();

        if(currentState == TEST1)
        {
            /*
            System.out.println("Starting Test: Loading 1000 textures async");

            ResourceManger.addTexture("Test", "badlogic.jpg");

            observer = AsyncTaskExecutor.executeTaskAsync(() -> {
                for(int i = 0; i++ < 1000;)
                {
                    ResourceManger.addTexture(String.valueOf(i), "badlogic.jpg");
                    System.out.println("Loaded texture " + String.valueOf(i));
                }
            });
            AppSystem.changeState(TEST2); */

            thread = new Thread(() -> {
                for(int i = 0; i++ < 1000;)
                {
                    ResourceManger.addTexture(String.valueOf(i), "badlogic.jpg");
                    System.out.println("Loaded texture " + String.valueOf(i));
                }
            });
            thread.start();
            LCL.AppSystem.changeState(TEST2);

        }

        if(currentState == TEST2)
        {
            if(observer.finished())
            {
                System.out.println("Execution time: " + String.valueOf((System.nanoTime() - start) / 1000000000f));
            }
            LCL.SpriteBatch.draw(ResourceManger.<Texture>getResource("Test"), 0, 0);
        }
    }

    @Override public void onChangeState(State currentState, Playground.State newState)
    {

    }
}
