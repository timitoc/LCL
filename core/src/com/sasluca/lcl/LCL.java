package com.sasluca.lcl;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.graphics.resources.LCLResourceManager;
import com.sasluca.lcl.input.LCLInputSystem;
import com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCL
{
    private static float delta;
    private static boolean rendering;
    private static float overridenDelta;
    private static boolean deltaOverride;
    private static LCLAppSystem appSystem;
    private static OrthographicCamera camera;
    private static final NativeResources NATIVE_RESOURCES = new NativeResources();

    private LCL() {}
    private static final class NativeResources implements IDisposable
    {
        //Native resources can not be static
        public SpriteBatch spriteBatch;
        public LCLResourceManager resourceManger;

        @Override public void dispose() { spriteBatch.dispose(); resourceManger.dispose(); }
    }

    public static void LCL_INIT(float cameraWidth, float cameraHeight, LCLAppSystem appSystem, int numberOfInputLayers, int numberOfRenderLayers)
    {
        NATIVE_RESOURCES.spriteBatch = new SpriteBatch();
        LCL.appSystem = appSystem;
        NATIVE_RESOURCES.resourceManger = new LCLResourceManager();
        camera = new OrthographicCamera(cameraWidth, cameraHeight);
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        camera.update();
        NATIVE_RESOURCES.spriteBatch.setProjectionMatrix(camera.combined);

        while(numberOfInputLayers-- > 0) LCLInputSystem.addInputLayer();
        while(numberOfRenderLayers-- > 0) appSystem.addRenderLayer();

        NATIVE_RESOURCES.resourceManger.addTexture("default", "default.jpg");

        Tween.setCombinedAttributesLimit(5);
    }
    public static float getDelta() { return delta; }
    public static OrthographicCamera getCamera() { return camera; }
    public static <O extends LCLAppSystem> O getAppSystem() { return (O) appSystem; }
    public static SpriteBatch getSpriteBatch() { return NATIVE_RESOURCES.spriteBatch; }
    public static LCLResourceManager getResourceManager() { return NATIVE_RESOURCES.resourceManger; }

    public static void overrideDelta(float newDelta)
    {
        overridenDelta = newDelta;
        deltaOverride = true;
    }

    public static void handleDelta(float newDelta)
    {
        if(deltaOverride) delta = overridenDelta; else delta = newDelta;
        deltaOverride = false;
    }

    public static void startRendering()
    {
        rendering = true;
        NATIVE_RESOURCES.spriteBatch.begin();
    }

    public static void endRendering()
    {
        rendering = false;
        NATIVE_RESOURCES.spriteBatch.end();
    }

    public static void dispose()
    {
        NATIVE_RESOURCES.dispose();
        LCLAsyncTaskExecutor.dispose();
    }
}
