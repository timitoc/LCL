package com.sasluca.lcl.applogic.renderlayer;

import com.sasluca.lcl.abstractions.IRender;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 7/20/2016.
 * Copyright (C) 2016 - LCL
 */

public class LCLRenderLayer implements IRenderable<LCLRenderLayer>
{
    private boolean m_Rendering;
    private LCLArray<IRender> m_Renderables;

    public LCLRenderLayer()
    {
        m_Renderables = new LCLArray<>();
        m_Rendering = true;
    }

    public LCLRenderLayer addRenderable(IRender r)
    {
        m_Renderables.add(r);

        return this;
    }

    @Override public boolean isRendering() { return m_Rendering; }
    @Override public LCLRenderLayer setRenderingState(boolean renderingState) { m_Rendering = renderingState; return this; }

    @Override public LCLRenderLayer render()
    {
        for(IRender r : m_Renderables) r.render();

        return this;
    }

    public LCLRenderLayer removeRenderable(IRender render)
    {
        if(m_Renderables.contains(render, false)) m_Renderables.remove(render);

        return this;
    }
}
