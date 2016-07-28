package com.sasluca.lcl.sandbox;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.fonts.LCLFont;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.ui.material_design.floatingelements.UICard;
import com.sasluca.lcl.ui.material_design.group.UIGroup;
import com.sasluca.lcl.ui.material_design.label.UILabel;
import com.sasluca.lcl.ui.material_design.lists.genericlists.scrolllists.UIVerticalList;
import com.sasluca.lcl.ui.material_design.text.UITextBox;
import com.sasluca.lcl.utils.LCLUtils;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler2 implements IStateHandler<Playground.State>
{
    @Override public void onState(Playground.State currentState)
    {
        LCLTween.TWEEN_MANAGER.update(LCL.SYS.Delta);
    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        LCLFontManager.addTrueTypeFont("Roboto", 16);
        UILabel label = new UILabel("Roboto", "test", Color.BLACK);

        LCL.SYS.AppSystem.getRenderLayer(0).addRenderable(label);
    }
}