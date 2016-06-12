package com.sasluca.lcl.sandbox;

import static com.sasluca.lcl.sandbox.Playground.State.*;

import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.sandbox.Playground.State;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import static com.sasluca.lcl.LCL.*;

/**
 * Created by Sas Luca on 12/06/16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler implements IStateHandler<State>
{

    @Override public void onState(State currentState)
    {
        if(currentState == TEST1)
        {
            System.out.println("Starting Test: Loading on main thread");
            float a = System.nanoTime();
            for(int i = 0; i < 100; i++)
            {
                ResourceManger.addTexture(String.valueOf(i), "badlogic.jpg");
                System.out.println("Loaded texture " + String.valueOf(i));
            }
            System.out.println("Execution time: " + String.valueOf((System.nanoTime() - a)/1000000000f));
            AppSystem.changeState(TEST2);
        }

        if(currentState == TEST2)
        {
            SPRITE_BATCH.draw(ResourceManger.<Texture>getResource("1"), 0, 0);
        }
    }

    @Override public void onChangeState(State currentState, State newState)
    {

    }
}
