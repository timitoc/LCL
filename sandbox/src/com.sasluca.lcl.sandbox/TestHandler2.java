package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.ui.material_design.label.UILabel;
import com.sasluca.lcl.ui.material_design.text.UITextBox;
import com.sasluca.lcl.utils.LCLUtils;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler2 implements IStateHandler<Playground.State>
{
    UITextBox m_Textbox;
    UITextBox m_Textbox2;

    @Override public void onState(Playground.State currentState)
    {
        LCLTween.TWEEN_MANAGER.update(LCL.SYS.Delta);
    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        //LCLFontManager.getFont("Roboto").getFont().getData().getGlyph('j').yoffset //x=2    y=287  width=19   height=87   xoffset=-3   yoffset=18   xadvance=22   page=0    chnl=0
        m_Textbox = new UITextBox();
        m_Textbox.setDefaultText("Test")
                .setInfoText("This is just a test")
                .subscribeToInputLayer(0);

        LCLUtils.center(m_Textbox, true, true);

        m_Textbox2 = new UITextBox();
        m_Textbox2.setDefaultText("Test")
                .setInfoText("This is just a test")
                .setPosX(m_Textbox.getX())
                .setPosY(m_Textbox.getY() + m_Textbox.getHeight() + 10)
                .setErrorText("This is an error")
                .showError()
                .subscribeToInputLayer(0);

        LCL.SYS.AppSystem.addRenderHandler(m_Textbox)
                .addUpdateHandler(m_Textbox)
                .addRenderHandler(m_Textbox2)
                .addUpdateHandler(m_Textbox2)
                .addUpdateHandler(() -> { System.out.println(Gdx.graphics.getFramesPerSecond()); return null; });
    }
}