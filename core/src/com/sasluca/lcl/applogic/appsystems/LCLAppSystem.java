package com.sasluca.lcl.applogic.appsystems;

import com.badlogic.gdx.Screen;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.applogic.managers.statemanager.LCLStateManager;
import com.sasluca.lcl.applogic.renderlayer.LCLRenderLayer;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLAppSystem<State> extends LCLStateManager<State> implements Screen
{
    protected LCLArray<IUpdate> p_UpdateHandlers;
    protected LCLArray<IResizeable> p_ResizeHandlers;
    protected LCLArray<LCLRenderLayer> p_RenderLayers;
    protected boolean p_UpdateTweenEngine;
    protected boolean p_OverrideDelta;

    public LCLAppSystem()
    {
        p_UpdateHandlers = new LCLArray<>();
        p_ResizeHandlers = new LCLArray<>();
        p_RenderLayers = new LCLArray<>();
        addRenderLayer();
        p_UpdateTweenEngine = true;
    }

    public void overrideDelta(float delta)
    {
        p_OverrideDelta = true;
        LCL.SYS.Delta = delta;
    }

    public LCLAppSystem addRenderLayer() { p_RenderLayers.add(new LCLRenderLayer()); return this;}
    public LCLAppSystem removeRenderLayer(int index) { p_ResizeHandlers.remove(index); return this; }
    public LCLAppSystem addUpdateHandler(IUpdate handler) { if(!p_UpdateHandlers.contains(handler)) p_UpdateHandlers.add(handler); return this; }
    public LCLAppSystem addResizeHandler(IResizeable handler) { if(!p_ResizeHandlers.contains(handler)) p_ResizeHandlers.add(handler); return this; }
    public LCLAppSystem removeUpdateHandler(IUpdate handler) { if(p_UpdateHandlers.contains(handler)) p_UpdateHandlers.remove(handler); return this; }
    public LCLAppSystem removeResizeHandler(IResizeable handler) { if(p_ResizeHandlers.contains(handler)) p_ResizeHandlers.remove(handler); return this; }

    public int getNumberOfRenderLayer() { return p_RenderLayers.getSize(); }
    public LCLRenderLayer getRenderLayer(int index) { return p_RenderLayers.get(index); }
    public boolean isUpdatingTweenEngine() { return p_UpdateTweenEngine; }
    public LCLAppSystem setTweenEngineUpdateState(boolean updateState) { p_UpdateTweenEngine = updateState; return this; }
}
