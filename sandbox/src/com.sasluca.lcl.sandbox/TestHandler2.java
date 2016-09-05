package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.ui.text.UILabel;
import com.sasluca.lcl.utils.LCLUtils;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler2 implements IStateHandler<Playground.State>
{
    @Override public void onState(Playground.State currentState)
    {
        LCLTween.TWEEN_MANAGER.update(LCL.getDelta());
    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        float densityIndependentSize = Gdx.graphics.getHeight() * 16 / LCL.getCamera().viewportHeight;
        int fontSize = Math.round(densityIndependentSize);

        LCLFontManager.addTrueTypeFont("Roboto_T", "fonts/truetypefonts/Roboto/Roboto.ttf", fontSize);
        UILabel label = new UILabel("Roboto_T", "test", Color.BLACK);

        LCLUtils.center(label, true, true);

        System.out.println(label.getHeight());

        LCL.getAppSystem().getRenderLayer(0).addRenderable(label);
    }
}