package com.sasluca.lcl.input;

import com.badlogic.gdx.InputProcessor;
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

public class LCLInputSystem implements InputProcessor
{
    private static final LCLList<LCLInputLayer> INPUT_LAYERS = new LCLList<>();
    private static final LCLInputSystem INSTANCE = new LCLInputSystem();
    private LCLInputLayer TOP_INPUT_LAYER; //TOP INPUT LAYER is layer 0
    
    private LCLInputSystem()
    {
        TOP_INPUT_LAYER = new LCLInputLayer(5, true);
        addInputLayer(TOP_INPUT_LAYER);
    }
    
    public static void deleteInputLayer(int index)
    {
        for(LCLInputHandler handler : INPUT_LAYERS.get(index).getInputHandlers()) handler.unsubscribeFromInputLayer();
        INPUT_LAYERS.remove(index);
    }

    public static void addInputLayer() { INPUT_LAYERS.add(new LCLInputLayer(1, true)); }
    public static void addInputLayer(int numberOfPointers, boolean projectInput) { INPUT_LAYERS.add(new LCLInputLayer(1, true)); }
    public static void addInputLayer(LCLInputLayer inputLayer) { if(!INPUT_LAYERS.contains(inputLayer)) INPUT_LAYERS.add(inputLayer); }

    public static int numberOfInputLayers() { return INPUT_LAYERS.getSize(); }
    public static LCLInputLayer getInputLayer(int index) { return INPUT_LAYERS.get(index); }
    public static int numberOfEnabledInputLayers() { int i = 0; for(Object inputLayer : INPUT_LAYERS) if(((LCLInputLayer)inputLayer).isEnabled()) i++; return i; }
    public static int numberOfDisabledInputLayers() { int i = 0; for(Object inputLayer : INPUT_LAYERS) if(!((LCLInputLayer)inputLayer).isEnabled()) i++; return i; }

    @Override public boolean keyDown(int keycode) 
    {
        if(TOP_INPUT_LAYER.keyDown(keycode)) return true;
        
        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).keyDown(keycode)) return true;
        
        return false;
    }

    @Override public boolean keyUp(int keycode) 
    {
        if(TOP_INPUT_LAYER.keyDown(keycode)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).keyUp(keycode)) return true;
        
        return false;
    }

    @Override public boolean keyTyped(char character) 
    {
        if(TOP_INPUT_LAYER.keyTyped(character)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).keyTyped(character)) return true;
        
        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) 
    {
        if(TOP_INPUT_LAYER.touchDown(screenX, screenY, pointer, button)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).touchDown(screenX, screenY, pointer, button)) return true;
        
        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) 
    {
        if(TOP_INPUT_LAYER.touchUp(screenX, screenY, pointer, button)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).touchUp(screenX, screenY, pointer, button)) return true;
        
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) 
    {
        if(TOP_INPUT_LAYER.touchDragged(screenX, screenY, pointer)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).touchDragged(screenX, screenY, pointer)) return true;
        
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY) 
    {
        if(TOP_INPUT_LAYER.mouseMoved(screenX, screenY)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).mouseMoved(screenX, screenY)) return true;
        
        return false;
    }

    @Override public boolean scrolled(int amount) 
    {
        if(TOP_INPUT_LAYER.scrolled(amount)) return true;

        for(int i = INPUT_LAYERS.getSize() - 1; i >= 1; i--) if(INPUT_LAYERS.get(i).scrolled(amount)) return true;
        
        return false;
    }
}
