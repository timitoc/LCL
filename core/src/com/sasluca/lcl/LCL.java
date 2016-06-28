package com.sasluca.lcl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.graphics.resources.LCLResourceManager;
import com.sasluca.lcl.input.LCLInputSystem;
import com.sasluca.lcl.input.LCLVirtualKeyboardManager;
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
    public OrthographicCamera Camera;
    public LCLFontManager FontManager;
    public LCLInputSystem InputSystem;
    public LCLResourceManager ResourceManger;
    public LCLAsyncTaskExecutor AsyncTaskExecutor;
    public LCLVirtualKeyboardManager VirtualKeyboardManager;

    public final static LCL SYS = new LCL();

    private LCL() {}

    public void LCL_INIT(float width, float height)
    {
        SpriteBatch = new SpriteBatch();
        ResourceManger = new LCLResourceManager();
        AsyncTaskExecutor = new LCLAsyncTaskExecutor();
        FontManager = new LCLFontManager();
        InputSystem = new LCLInputSystem();
        Camera = new OrthographicCamera(width, height);
        Camera.setToOrtho(false, width, height);
        SpriteBatch.setProjectionMatrix(Camera.combined);
        VirtualKeyboardManager = new LCLVirtualKeyboardManager();
    }
}
