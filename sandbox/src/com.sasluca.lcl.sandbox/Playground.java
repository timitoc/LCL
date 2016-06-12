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
        TEST1, TEST2
    }

    private LCLDefaultAppSystem<State> m_AppSystem;

    @Override public void create()
    {
        LCL.LCL_INIT();

        m_AppSystem = new LCLDefaultAppSystem<State>();
        m_AppSystem.changeState(State.TEST2);
        m_AppSystem.addHandler(new TestHandler());
        setScreen(m_AppSystem);
    }
}
