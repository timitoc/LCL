package com.sasluca.lcl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.graphics.resources.LCLResourceManager;
import com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCL
{
    public float Delta;
    public LCLAppSystem AppSystem;
    public SpriteBatch SpriteBatch;
    public LCLFontManager FontManager;
    public LCLResourceManager ResourceManger;
    public LCLAsyncTaskExecutor AsyncTaskExecutor;

    private final static LCL INSTANCE = new LCL();

    private LCL() {}

    public static LCL MASTER() { return INSTANCE; }

    public void LCL_INIT()
    {
        SpriteBatch = new SpriteBatch();
        ResourceManger = new LCLResourceManager();
        AsyncTaskExecutor = new LCLAsyncTaskExecutor();
        FontManager = new LCLFontManager();
    }
}
