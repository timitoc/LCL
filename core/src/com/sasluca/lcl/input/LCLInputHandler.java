package com.sasluca.lcl.input;

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

public abstract class LCLInputHandler<THIS>
{
    private int m_InputLayer = -1;

    public final THIS subscribeToInputLayer(int inputLayer)
    {
        //TODO: ERROR
        if(inputLayer == m_InputLayer || LCLInputSystem.numberOfInputLayers() < inputLayer) return ((THIS)this);

        onSubscribeToInputLayer(m_InputLayer, inputLayer);
        m_InputLayer = inputLayer;
        LCLInputSystem.getInputLayer(inputLayer).addInputHandler(this);

        return ((THIS)this);
    }

    public final THIS unsubscribeFromInputLayer()
    {
        if(m_InputLayer != -1)
        {
            onUnsubscribeFromInputLayer(m_InputLayer);
            LCLInputSystem.getInputLayer(m_InputLayer).removeInputHandler(this);
            m_InputLayer = -1;
        }

        return ((THIS)this);
    }

    protected void onSubscribeToInputLayer(int currentLayer, int newLayer) {}
    protected void onUnsubscribeFromInputLayer(int oldLayer) {}

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
