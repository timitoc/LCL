package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.materialdesign.text.MaterialTextBox;
import com.sasluca.lcl.ui.text.UITextArea;
import com.sasluca.lcl.ui.text.UITextBox;
import com.sasluca.lcl.utils.LCLUtils;

import static com.sasluca.lcl.sandbox.Playground.State;

public class TestHandler implements IStateHandler<State>
{
    UITextArea m_TextArea;
    UITextBox m_TextBox;

    class A
    {
        public A(int b)
        {

        }
    }

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
    }
}
