package com.sasluca.lcl.applogic.renderlayer;

import com.sasluca.lcl.abstractions.IRender;
import com.sasluca.lcl.abstractions.IRenderable;
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

    @Override public void render()
    {
        for(IRender r : m_Renderables) r.render();
    }

    public LCLRenderLayer removeRenderable(IRender render)
    {
        if(m_Renderables.contains(render)) m_Renderables.remove(render);

        return this;
    }
}
