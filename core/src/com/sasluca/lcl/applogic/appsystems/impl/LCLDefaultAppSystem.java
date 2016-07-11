package com.sasluca.lcl.applogic.appsystems.impl;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLDefaultAppSystem<State> extends LCLAppSystem<State>
{
    @Override public void render(float delta)
    {
        //Set the delta and update the camera
        LCL.SYS.Delta = delta;

        LCL.SYS.Camera.update();

        //Clear the screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Initializes the OpenGL context
        LCL.SYS.SpriteBatch.begin();

        if(p_UpdateTweenEngine) LCLTween.TWEEN_MANAGER.update(LCL.SYS.Delta);

        for(IRender renderable : p_RenderHandlers) renderable.render();
        for(IUpdate updatable : p_UpdateHandlers) updatable.update();

        //Manages the current state
        manage();

        //Flushes the batch sending all the data to the GPU to be rendered
        LCL.SYS.SpriteBatch.end();
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
