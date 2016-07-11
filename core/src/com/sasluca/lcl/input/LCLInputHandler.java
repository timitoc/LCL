package com.sasluca.lcl.input;

import com.sasluca.lcl.LCL;

/**
 * Created by Sas Luca on 21/06/16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLInputHandler
{
    private int m_InputLayer = -1;

    public final void subscribeToInputLayer(int inputLayer)
    {
        //TODO: ERROR
        if(inputLayer == m_InputLayer || LCLInputSystem.numberOfInputLayers() < inputLayer) return;

        m_InputLayer = inputLayer;
        LCLInputSystem.getInputLayer(inputLayer).addInputHandler(this);
    }

    public final void unsubscribeFromInputLayer()
    {
        if(m_InputLayer != -1)
        {
            m_InputLayer = -1;
            LCLInputSystem.getInputLayer(m_InputLayer).removeInputHandler(this);
        }
    }

    public boolean keyUp(int keycode) { return false; }
    public boolean keyDown(int keycode) { return false; }
    public boolean scrolled(int amount) { return false; }
    public boolean keyTyped(char character) { return false; }
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
}
