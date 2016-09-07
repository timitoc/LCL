package com.sasluca.lcl.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.utils.collections.list.LCLList;

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

public class LCLInputLayer implements InputProcessor
{
    protected static Vector3 VEC3 = new Vector3(0, 0, 0);

    private boolean m_Enabled;
    private int m_NumberOfPointers;
    private boolean m_UnprojectInput;
    private LCLList<LCLInputHandler> m_InputHandlers;

    public LCLInputLayer(boolean unprojectInput)
    {
        m_InputHandlers = new LCLList<>();
        m_NumberOfPointers = 1;
        m_Enabled = true;
        m_UnprojectInput = unprojectInput;
    }

    public LCLInputLayer(int numberOfPointers, boolean unprojectInput) { this(unprojectInput); m_NumberOfPointers = numberOfPointers; }

    public LCLInputLayer enable() { m_Enabled = true; return this; }
    public LCLInputLayer disable() { m_Enabled = false; return this; }
    public boolean isEnabled() { return m_Enabled; }

    public LCLInputLayer addInputHandler(LCLInputHandler handler) { m_InputHandlers.add(handler); return this; }
    public LCLInputLayer setNumberOfPointers(int numberOfPointers) { m_NumberOfPointers = numberOfPointers; return this; }
    public LCLInputLayer removeInputHandler(LCLInputHandler handler) { m_InputHandlers.remove(handler); return this; }

    public LCLList<LCLInputHandler> getInputHandlers() { return m_InputHandlers; }

    //<editor-fold desc="Events">
    @Override public boolean keyDown(int keycode)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).keyDown(keycode)) b = true;

        return b;
    }

    @Override public boolean keyUp(int keycode)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).keyUp(keycode)) b = true;

        return b;
    }

    @Override public boolean keyTyped (char character)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).keyTyped(character)) b = true;

        return b;
    }

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button)
    {
        if(!m_Enabled) return false;

        if(pointer > m_NumberOfPointers) return false;

        VEC3.x = screenX;
        VEC3.y = screenY;

        if(m_UnprojectInput) LCL.getCamera().unproject(VEC3);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).touchDown((int) VEC3.x, (int) VEC3.y, pointer, button)) b = true;

        return b;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(!m_Enabled) return false;

        if(pointer > m_NumberOfPointers) return false;

        VEC3.x = screenX;
        VEC3.y = screenY;

        if(m_UnprojectInput) LCL.getCamera().unproject(VEC3);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).touchUp((int) VEC3.x, (int) VEC3.y, pointer, button)) b = true;

        return b;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(!m_Enabled || pointer > m_NumberOfPointers) return false;

        VEC3.x = screenX;
        VEC3.y = screenY;

        if(m_UnprojectInput) LCL.getCamera().unproject(VEC3);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).touchDragged((int) VEC3.x, (int) VEC3.y, pointer)) b = true;

        return b;
    }

    @Override public boolean mouseMoved(int screenX, int screenY)
    {
        if(!m_Enabled) return false;

        VEC3.x = screenX;
        VEC3.y = screenY;

        if(m_UnprojectInput) LCL.getCamera().unproject(VEC3);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).mouseMoved((int) VEC3.x, (int) VEC3.y)) b = true;

        return b;
    }

    @Override public boolean scrolled(int amount)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).scrolled(amount)) b = true;

        return b;
    }
    //</editor-fold>
}

