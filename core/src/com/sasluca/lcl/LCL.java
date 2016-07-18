package com.sasluca.lcl;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.graphics.resources.LCLResourceManager;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.input.LCLInputHandler;
import com.sasluca.lcl.input.LCLInputLayer;
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
    public LCLResourceManager ResourceManger;
    public StretchViewport Viewport;
    public LCLAsyncTaskExecutor AsyncTaskExecutor;

    public final static LCL SYS = new LCL();

    private LCL() {}

    public void LCL_INIT(float width, float height)
    {
        SpriteBatch = new SpriteBatch();
        ResourceManger = new LCLResourceManager();
        AsyncTaskExecutor = new LCLAsyncTaskExecutor();
        Camera = new OrthographicCamera(width, height);
        Camera.setToOrtho(false, width, height);
        Camera.update();
        SpriteBatch.setProjectionMatrix(Camera.combined);
        LCLInputSystem.addInputLayer(new LCLInputLayer(1, true));
        ResourceManger.addTexture("default", "default.jpg");
        ResourceManger.addTexture("badlogic", "badlogic.jpg");

        Tween.setCombinedAttributesLimit(5);
    }
}
