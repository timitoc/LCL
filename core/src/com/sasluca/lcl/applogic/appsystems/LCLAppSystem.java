package com.sasluca.lcl.applogic.appsystems;

import com.badlogic.gdx.Screen;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.IResizeable;
import com.sasluca.lcl.abstractions.IUpdateable;
import com.sasluca.lcl.applogic.managers.statemanager.LCLStateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLAppSystem<State> extends LCLStateManager<State> implements Screen
{
    protected List<IResizeable> p_ResizeHandlers;
    protected List<IRenderable> p_RenderHandlers;
    protected List<IUpdateable> p_UpdateHandlers;

    public LCLAppSystem()
    {
        p_ResizeHandlers = new ArrayList<>();
        p_RenderHandlers = new ArrayList<>();
        p_UpdateHandlers = new ArrayList<>();
    }

    public void addResizeHandler(IResizeable handler) { if(!p_ResizeHandlers.contains(handler)) p_ResizeHandlers.add(handler); }
    public void addRenderHandler(IRenderable handler) { if(!p_ResizeHandlers.contains(handler)) p_RenderHandlers.add(handler); }
    public void addUpdateHandler(IUpdateable hanlder) { if(!p_UpdateHandlers.contains(hanlder)) p_UpdateHandlers.add(hanlder); }
}
