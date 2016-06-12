package com.sasluca.lcl.applogic.appsystems.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IResizeable;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.applogic.managers.LCLManager;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLDefaultAppSystem<State> extends LCLAppSystem<State>
{
    private LCLManager<IResizeable> m_Resizeable;


    @Override public void render(float delta)
    {
        //Set the delta and update the camera.
        LCL.Delta = delta;

        //Clear the screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Initializes the OpenGL context
        LCL.SpriteBatch.begin();

        //Manages the current state
        manage();

        //Flushes the batch sending all the data to the GPU to be rendered
        LCL.SpriteBatch.end();
    }

    @Override public void show()
    {

    }

    @Override public void resize(int width, int height)
    {

    }

    @Override public void pause()
    {

    }

    @Override public void resume()
    {

    }

    @Override public void hide()
    {

    }

    @Override public void dispose()
    {

    }
}
