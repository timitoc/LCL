package com.sasluca.lcl.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLInputSystem implements InputProcessor
{
    private static final LCLArray<LCLInputLayer> INPUT_LAYERS = new LCLArray<>();
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

    public static void addInputLayer(LCLInputLayer inputLayer)
    {
        if(INPUT_LAYERS.contains(inputLayer, true)) return;
        INPUT_LAYERS.add(inputLayer);
        Gdx.input.setInputProcessor(INSTANCE);
    }

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
