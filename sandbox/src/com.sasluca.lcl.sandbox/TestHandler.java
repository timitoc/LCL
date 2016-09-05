package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.materialdesign.text.MaterialTextBox;
import com.sasluca.lcl.ui.text.UITextArea;
import com.sasluca.lcl.ui.text.UITextBox;
import com.sasluca.lcl.utils.LCLUtils;
import com.sasluca.lcl.utils.timer.LCLTimePoints;
import com.sasluca.lcl.utils.tuples.LCLTriplet;

import static com.sasluca.lcl.sandbox.Playground.State;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler implements IStateHandler<State>
{
    UITextArea m_TextArea;
    UITextBox m_TextBox;

    @Override public void onState(State currentState)
    {
        m_TextArea.render();
        m_TextBox.render();
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        m_TextBox = new MaterialTextBox("Test");
        LCLUtils.center(m_TextBox, true, true);
        m_TextArea = new UITextArea("Roboto", 500, 500, "THIS is a brand new \ntest", true);
        m_TextArea.setTextScale(0.4f).setColor(Color.WHITE);
        LCLTimePoints.addTimePoint(0);

        FileHandle f = Gdx.files.local("test.txt");

        int i = 100;
        while(i-- > 0) f.writeString(String.valueOf(LCLUtils.getRange(0, 100)), true);

        LCLTriplet<Long, Float, Float> timePoint = LCLTimePoints.get(0);
        System.out.println("Seconds: " + timePoint.get1() + " Miliseconds: " + timePoint.get2() + " Nanoseconds: " + timePoint.get3());

    }
}
