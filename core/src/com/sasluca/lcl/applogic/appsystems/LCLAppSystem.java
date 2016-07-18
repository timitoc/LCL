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
    protected boolean p_UpdateTweenEngine;

    public LCLAppSystem()
    {
        p_UpdateHandlers = new LCLArray<>();
        p_ResizeHandlers = new LCLArray<>();
        p_RenderHandlers = new LCLArray<>();
        p_UpdateTweenEngine = true;
    }

    public LCLAppSystem addUpdateHandler(IUpdate handler) { if(!p_UpdateHandlers.contains(handler)) p_UpdateHandlers.add(handler); return this; }
    public LCLAppSystem addRenderHandler(IRender handler) { if(!p_RenderHandlers.contains(handler)) p_RenderHandlers.add(handler); return this;}
    public LCLAppSystem addResizeHandler(IResizeable handler) { if(!p_ResizeHandlers.contains(handler)) p_ResizeHandlers.add(handler); return this; }
    public LCLAppSystem removeUpdateHandler(IUpdate handler) { if(p_UpdateHandlers.contains(handler)) p_UpdateHandlers.remove(handler); return this; }
    public LCLAppSystem removeRenderHandler(IRender handler) { if(p_RenderHandlers.contains(handler)) p_RenderHandlers.remove(handler); return this;}
    public LCLAppSystem removeResizeHandler(IResizeable handler) { if(p_ResizeHandlers.contains(handler)) p_ResizeHandlers.remove(handler); return this; }

    public boolean isUpdatingTweenEngine() { return p_UpdateTweenEngine; }
    public LCLAppSystem setTweenEngineUpdateState(boolean updateState) { p_UpdateTweenEngine = updateState; return this; }
}
