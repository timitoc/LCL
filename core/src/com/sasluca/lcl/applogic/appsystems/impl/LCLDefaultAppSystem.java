package com.sasluca.lcl.applogic.appsystems.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.input.LCLVirtualKeyboardManager;

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

public class LCLDefaultAppSystem<State> extends LCLAppSystem<State>
{
    //Background color
    public Color BackgroundColor = new Color(Color.WHITE);

    @Override public void render(float delta)
    {
        //Set the delta and update the camera
        LCL.handleDelta(delta);

        LCL.getCamera().update();

        //Clear the screen
        Gdx.gl.glClearColor(BackgroundColor.r, BackgroundColor.g, BackgroundColor.b, BackgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Initialize the OpenGL context
        LCL.startRendering();

        if(p_UpdateTweenEngine) LCLTween.TWEEN_MANAGER.update(LCL.getDelta());
        if(p_ManageKeyboard) LCLVirtualKeyboardManager.manage();

        for(IRender renderable : p_RenderLayers) renderable.render();
        for(IUpdate updatable : p_UpdateHandlers) updatable.update();

        //Manage the current state
        manage();

        //Flush the batch sending all the data to the GPU to be rendered
        LCL.endRendering();
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
