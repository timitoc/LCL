package com.sasluca.lcl.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 22/06/16.
 * Copyright (C) 2016 - LCL
 */

public class LCLInputLayer implements InputProcessor
{
    private Vector3 m_Touch;
    private boolean m_Enabled;
    private int m_NumberOfPointers;
    private boolean m_UnprojectInput;
    private LCLArray<LCLInputHandler> m_InputHandlers;

    public LCLInputLayer(boolean unprojectInput)
    {
        m_InputHandlers = new LCLArray<>();
        m_Touch = new Vector3(0, 0, 0);
        m_NumberOfPointers = 1;
        m_Enabled = true;
        m_UnprojectInput = unprojectInput;
    }

    public LCLInputLayer(int numberOfPointers, boolean unprojectInput) { this(unprojectInput); m_NumberOfPointers = numberOfPointers; }

    public void enable() { m_Enabled = true; }
    public void disable() { m_Enabled = false; }
    public boolean isEnabled() { return m_Enabled; }

    public void addInputHandler(LCLInputHandler handler) { m_InputHandlers.add(handler); }
    public void setNumberOfPointers(int numberOfPointers) { m_NumberOfPointers = numberOfPointers; }
    public void removeInputHandler(LCLInputHandler handler) { m_InputHandlers.removeValue(handler, true); }

    public Array<LCLInputHandler> getInputHandlers() { return m_InputHandlers; }

    //<editor-fold desc="Events">
    @Override public final boolean keyDown(int keycode)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).keyDown(keycode)) b = true;

        return b;
    }

    @Override public final boolean keyUp(int keycode)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).keyUp(keycode)) b = true;

        return b;
    }

    @Override public final boolean keyTyped (char character)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).keyTyped(character)) b = true;

        return b;
    }

    @Override public final boolean touchDown (int screenX, int screenY, int pointer, int button)
    {
        if(!m_Enabled) return false;

        if(pointer > m_NumberOfPointers) return false;

        m_Touch.x = screenX;
        m_Touch.y = screenY;

        if(m_UnprojectInput) LCL.SYS.Camera.unproject(m_Touch);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).touchDown((int) m_Touch.x, (int) m_Touch.y, pointer, button)) b = true;

        return b;
    }

    @Override public final boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(!m_Enabled) return false;

        if(pointer > m_NumberOfPointers) return false;

        m_Touch.x = screenX;
        m_Touch.y = screenY;

        if(m_UnprojectInput) LCL.SYS.Camera.unproject(m_Touch);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).touchUp((int) m_Touch.x, (int) m_Touch.y, pointer, button)) b = true;

        return b;
    }

    @Override public final boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(!m_Enabled || pointer > m_NumberOfPointers) return false;

        m_Touch.x = screenX;
        m_Touch.y = screenY;

        if(m_UnprojectInput) LCL.SYS.Camera.unproject(m_Touch);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).touchDragged((int) m_Touch.x, (int) m_Touch.y, pointer)) b = true;

        return b;
    }

    @Override public final boolean mouseMoved(int screenX, int screenY)
    {
        if(!m_Enabled) return false;

        m_Touch.x = screenX;
        m_Touch.y = screenY;

        if(m_UnprojectInput) LCL.SYS.Camera.unproject(m_Touch);

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).mouseMoved((int) m_Touch.x, (int) m_Touch.y)) b = true;

        return b;
    }

    @Override public final boolean scrolled(int amount)
    {
        if(!m_Enabled) return false;

        boolean b = false;
        for(int i = 0; i < m_InputHandlers.getSize(); i++) if(m_InputHandlers.get(i).scrolled(amount)) b = true;

        return b;
    }
    //</editor-fold>
}

