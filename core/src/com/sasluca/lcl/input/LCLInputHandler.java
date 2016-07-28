package com.sasluca.lcl.input;

/**
 * Created by Sas Luca on 21/06/16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLInputHandler<This>
{
    private int m_InputLayer = -1;

    public final This subscribeToInputLayer(int inputLayer)
    {
        //TODO: ERROR
        if(inputLayer == m_InputLayer || LCLInputSystem.numberOfInputLayers() < inputLayer) return ((This)this);

        onSubscribeToInputLayer(m_InputLayer, inputLayer);
        m_InputLayer = inputLayer;
        LCLInputSystem.getInputLayer(inputLayer).addInputHandler(this);

        return ((This)this);
    }

    public final This unsubscribeFromInputLayer()
    {
        if(m_InputLayer != -1)
        {
            onUnsubscribeFromInputLayer();
            LCLInputSystem.getInputLayer(m_InputLayer).removeInputHandler(this);
            m_InputLayer = -1;
        }

        return ((This)this);
    }

    protected void onSubscribeToInputLayer(int currentLayer, int newLayer) {}
    protected void onUnsubscribeFromInputLayer() {}

    public boolean keyUp(int keycode) { return false; }
    public boolean keyDown(int keycode) { return false; }
    public boolean scrolled(int amount) { return false; }
    public boolean keyTyped(char character) { return false; }
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

    public int getInputLayer() { return m_InputLayer; }
}
