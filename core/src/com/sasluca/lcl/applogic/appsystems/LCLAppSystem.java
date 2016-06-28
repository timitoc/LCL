package com.sasluca.lcl.applogic.appsystems;

import com.badlogic.gdx.Screen;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.IResizeable;
import com.sasluca.lcl.abstractions.IUpdatable;
import com.sasluca.lcl.applogic.managers.statemanager.LCLStateManager;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLAppSystem<State> extends LCLStateManager<State> implements Screen
{
    protected LCLArray<IResizeable> p_ResizeHandlers;
    protected LCLArray<IRenderable> p_RenderHandlers;
    protected LCLArray<IUpdatable> p_UpdateHandlers;

    public LCLAppSystem()
    {
        p_ResizeHandlers = new LCLArray<>();
        p_RenderHandlers = new LCLArray<>();
        p_UpdateHandlers = new LCLArray<>();
    }

    public void addResizeHandler(IResizeable handler) { if(!p_ResizeHandlers.contains(handler)) p_ResizeHandlers.add(handler); }
    public void addRenderHandler(IRenderable handler) { if(!p_RenderHandlers.contains(handler)) p_RenderHandlers.add(handler); }
    public void addUpdateHandler(IUpdatable hanlder) { if(!p_UpdateHandlers.contains(hanlder)) p_UpdateHandlers.add(hanlder); }
}
