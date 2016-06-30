package com.sasluca.lcl.applogic.appsystems;

import com.badlogic.gdx.Screen;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.applogic.managers.statemanager.LCLStateManager;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLAppSystem<State> extends LCLStateManager<State> implements Screen
{
    protected LCLArray<IUpdate> p_UpdateHandlers;
    protected LCLArray<IRender> p_RenderHandlers;
    protected LCLArray<IResizeable> p_ResizeHandlers;

    public LCLAppSystem()
    {
        p_UpdateHandlers = new LCLArray<>();
        p_ResizeHandlers = new LCLArray<>();
        p_RenderHandlers = new LCLArray<>();
    }

    public void addUpdateHandler(IUpdate handler) { if(!p_UpdateHandlers.contains(handler)) p_UpdateHandlers.add(handler); }
    public void addRenderHandler(IRender handler) { if(!p_RenderHandlers.contains(handler)) p_RenderHandlers.add(handler); }
    public void addResizeHandler(IResizeable handler) { if(!p_ResizeHandlers.contains(handler)) p_ResizeHandlers.add(handler); }
}
