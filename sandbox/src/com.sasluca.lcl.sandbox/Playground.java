package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Game;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.appsystems.impl.LCLDefaultAppSystem;
import com.sasluca.lcl.dialogs.IImageGalleryOpener;
import com.sasluca.lcl.materialdesign.LCLMaterialDesign;
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
    public static IImageGalleryOpener GalleryOpener;

    public Playground() {}
    public Playground(IImageGalleryOpener g) { GalleryOpener = g; }

    @Override public void create()
    {
        m_AppSystem = new LCLDefaultAppSystem<>();
        LCL.LCL_INIT(720, 1280, m_AppSystem, 1, 1);
        LCLMaterialDesign.init();

        for(State state : State.values()) m_AppSystem.addState(state);
        m_AppSystem.addHandler(new EXFonts());
        m_AppSystem.changeState(State.TEST1);

        setScreen(m_AppSystem);
    }

    @Override public void dispose()
    {
        super.dispose();
        m_AppSystem.dispose();
    }
}
