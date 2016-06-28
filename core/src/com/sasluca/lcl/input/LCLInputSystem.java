package com.sasluca.lcl.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLInputSystem
{
    private final InputMultiplexer INPUT_MULTIPLEXER = new InputMultiplexer();

    public LCLInputSystem()
    {
        addInputLayer(new LCLInputLayer(5, true));
    }

    public void deleteInputLayer(int index)
    {
        for(LCLInputHandler handler : ((LCLInputLayer)INPUT_MULTIPLEXER.getProcessors().get(index)).getInputHandlers()) handler.unsubscribeFromInputLayer();
        INPUT_MULTIPLEXER.removeProcessor(index);
    }

    public void addInputLayer(LCLInputLayer inputLayer)
    {
        if(INPUT_MULTIPLEXER.getProcessors().contains(inputLayer, true)) return;
        INPUT_MULTIPLEXER.addProcessor(inputLayer); Gdx.input.setInputProcessor(INPUT_MULTIPLEXER);
    }

    public int numberOfInputLayers() { return INPUT_MULTIPLEXER.size(); }
    public LCLInputLayer getInputLayer(int index) { return (LCLInputLayer)(INPUT_MULTIPLEXER.getProcessors().get(index)); }
    public int numberOfEnabledInputLayers() { int i = 0; for(Object inputLayer : INPUT_MULTIPLEXER.getProcessors()) if(((LCLInputLayer)inputLayer).isEnabled()) i++; return i; }
    public int numberOfDisabledInputLayers() { int i = 0; for(Object inputLayer : INPUT_MULTIPLEXER.getProcessors()) if(!((LCLInputLayer)inputLayer).isEnabled()) i++; return i; }
}
