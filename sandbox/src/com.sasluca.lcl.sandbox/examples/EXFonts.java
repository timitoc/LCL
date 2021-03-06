package com.sasluca.lcl.sandbox.examples;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.graphics.fonts.LCLLabel;

import static com.sasluca.lcl.sandbox.Playground.State;

/**
 * Created by Sas Luca on 19-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class EXFonts implements IStateHandler<State>
{
    LCLLabel label;

    @Override public void onState(State currentState)
    {
        if(currentState == State.TEST1)
        {
            label.render();
        }
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        if(newState == State.TEST1)
        {
            //LCLFontManager.addTrueTypeFont("Roboto_d". "fonts", 16);
            label = new LCLLabel("Roboto", "Hello World", Color.BLACK);
            label.setPosY(1280 - label.getHeight()).setPosX(720 - label.getWidth());
            System.out.println(label.getHeight() * 1.4f);
        }
    }
}
