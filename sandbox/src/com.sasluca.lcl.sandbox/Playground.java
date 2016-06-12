package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Game;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.appsystems.impl.LCLDefaultAppSystem;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class Playground extends Game
{
    enum State
    {
        TEST1,
        TEST2
    }

    private LCLDefaultAppSystem<State> m_AppSystem;

    @Override public void create()
    {
        LCL.LCL_INIT();

        m_AppSystem = new LCLDefaultAppSystem<State>();
        for(State state : State.values()) m_AppSystem.addState(state);
        m_AppSystem.changeState(State.TEST1);
        m_AppSystem.addHandler(new TestHandler());
        LCL.AppSystem = m_AppSystem;

        setScreen(m_AppSystem);
    }
}
