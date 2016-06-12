package com.sasluca.lcl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sasluca.lcl.applogic.appsystems.LCLAppSystem;
import com.sasluca.lcl.graphics.resources.LCLResourceManager;
import com.sasluca.lcl.utils.threads.LCLAsyncTaskExecutor;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCL
{
    public static LCLAppSystem AppSystem;
    public static SpriteBatch SPRITE_BATCH;
    public static LCLResourceManager ResourceManger;
    public static LCLAsyncTaskExecutor AsyncTaskExecutor;

    public static void LCL_INIT()
    {
        SPRITE_BATCH = new SpriteBatch();
        ResourceManger = new LCLResourceManager();
        AsyncTaskExecutor = new LCLAsyncTaskExecutor();
    }
}
