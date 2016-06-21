package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Game;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.appsystems.impl.LCLDefaultAppSystem;
import com.sasluca.lcl.sandbox.examples.EXSprite;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class Playground extends Game
{
    public enum State
    {
        TEST1,
        TEST2,
        TEST3
    }

    private LCLDefaultAppSystem<State> m_AppSystem;

    @Override public void create()
    {
        LCL.MASTER().LCL_INIT();

        m_AppSystem = new LCLDefaultAppSystem<State>(720, 1280);
        for(State state : State.values()) m_AppSystem.addState(state);
        m_AppSystem.addHandler(new EXSprite());
        m_AppSystem.changeState(State.TEST1);
        LCL.MASTER().AppSystem = m_AppSystem;

        setScreen(m_AppSystem);
    }
}
