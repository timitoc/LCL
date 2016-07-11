package com.sasluca.lcl.sandbox.examples;

import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.sandbox.Playground;
import com.sasluca.lcl.sandbox.Playground.State;
import com.sasluca.lcl.ui.material_design.floatingelements.UICard;
import com.sasluca.lcl.ui.material_design.floatingelements.UIFloatingElement;
import com.sasluca.lcl.utils.LCLUtils;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class EXFloatingCards implements IStateHandler<State>
{
    UICard[] m_Cards;

    @Override public void onState(Playground.State currentState) { for(int i = 0; i <= UIFloatingElement.FLOAT_LEVEL; i++) m_Cards[i].render(); }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        if(newState == Playground.State.TEST1)
        {
            m_Cards = new UICard[7];
            for(int i = 0; i <= UIFloatingElement.FLOAT_LEVEL; i++)
            {
                m_Cards[i] = new UICard(i);
                m_Cards[i].setWidth(700).setHeight(150);
                LCLUtils.center(m_Cards[i], true, false);
                if(i != 0) m_Cards[i].setPosY(m_Cards[i - 1].getY() + m_Cards[i - 1].getHeight() + 20); else m_Cards[i].setPosY(50);
            }
        }
    }
}
