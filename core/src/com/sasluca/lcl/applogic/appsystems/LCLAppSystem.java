package com.sasluca.lcl.applogic.appsystems;

import com.badlogic.gdx.Screen;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.applogic.managers.statemanager.LCLStateManager;
import com.sasluca.lcl.applogic.renderlayer.LCLRenderLayer;
import com.sasluca.lcl.utils.collections.LCLArray;

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

public abstract class LCLAppSystem<State> extends LCLStateManager<State> implements Screen
{
    protected LCLArray<IUpdate> p_UpdateHandlers;
    protected LCLArray<IResizeable> p_ResizeHandlers;
    protected LCLArray<LCLRenderLayer> p_RenderLayers;
    protected boolean p_UpdateTweenEngine;
    protected boolean p_ManageKeyboard;
    protected boolean p_OverrideDelta;

    public LCLAppSystem()
    {
        p_UpdateHandlers = new LCLArray<>();
        p_ResizeHandlers = new LCLArray<>();
        p_RenderLayers = new LCLArray<>();
        addRenderLayer();
        p_UpdateTweenEngine = true;
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
    public LCLAppSystem manageKeyboard(boolean manage) { p_ManageKeyboard = manage; return this; }
}
