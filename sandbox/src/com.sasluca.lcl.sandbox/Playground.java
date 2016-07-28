package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Pixmap;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.appsystems.impl.LCLDefaultAppSystem;
import com.sasluca.lcl.dialogs.IImageGalleryOpener;
import com.sasluca.lcl.sandbox.examples.EXBlurredImage;
import com.sasluca.lcl.sandbox.examples.EXGroup;
import com.sasluca.lcl.ui.material_design.LCLMaterialDesign;

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
        LCL.SYS.LCL_INIT(720, 1280, m_AppSystem);
        LCLMaterialDesign.init();

        for(State state : State.values()) m_AppSystem.addState(state);
        m_AppSystem.addHandler(new TestHandler());
        m_AppSystem.changeState(State.TEST1);
        LCL.SYS.AppSystem = m_AppSystem;

        setScreen(m_AppSystem);
    }

    @Override public void dispose()
    {
        super.dispose();
        m_AppSystem.dispose();
    }
}
