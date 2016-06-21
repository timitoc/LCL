package com.sasluca.lcl.applogic.appsystems.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.IResizeable;
import com.sasluca.lcl.abstractions.IUpdateable;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLDefaultAppSystem<State> extends LCLAppSystem<State>
{
    private OrthographicCamera m_Camera;

    public LCLDefaultAppSystem(float width, float height)
    {
        m_Camera = new OrthographicCamera(width, height);
        m_Camera.setToOrtho(false, width, height);
        LCL.MASTER().SpriteBatch.setProjectionMatrix(m_Camera.combined);
    }

    @Override public void render(float delta)
    {
        //Set the delta and update the camera
        LCL.MASTER().Delta = delta;

        //m_Camera.update();

        //Clear the screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Initializes the OpenGL context
        LCL.MASTER().SpriteBatch.begin();

        for(IRenderable renderable : p_RenderHandlers) renderable.render();
        for(IUpdateable updateable : p_UpdateHandlers) updateable.update();

        //Manages the current state
        manage();

        //Flushes the batch sending all the data to the GPU to be rendered
        LCL.MASTER().SpriteBatch.end();
    }

    @Override public void show()
    {

    }

    @Override public void resize(int width, int height)
    {
        for(IResizeable resizeable : p_ResizeHandlers) resizeable.resize(width, height);
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
