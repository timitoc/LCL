package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Game;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.appsystems.impl.LCLDefaultAppSystem;
import com.sasluca.lcl.sandbox.examples.EXBlurredImage;
import com.sasluca.lcl.sandbox.examples.EXFonts;

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
        LCL.SYS.LCL_INIT(720, 1280);

        m_AppSystem = new LCLDefaultAppSystem<State>();
        for(State state : State.values()) m_AppSystem.addState(state);
        m_AppSystem.addHandler(new EXBlurredImage());
        m_AppSystem.changeState(State.TEST1);
        LCL.SYS.AppSystem = m_AppSystem;

        setScreen(m_AppSystem);
    }
}
