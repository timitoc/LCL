package com.sasluca.lcl.applogic.appsystems.impl;

import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.IUpdateable;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLDefaultAppSystem extends LCLAppSystem
{

    private List<IRenderable> m_Renderables;
    private List<IUpdateable> m_Updatables;
    private Thread m_RenderingThread;

    public LCLDefaultAppSystem()
    {
        m_Renderables = new ArrayList<>();
        m_Updatables = new ArrayList<>();
    }

    @Override public void render(float delta)
    {

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
