package com.sasluca.lcl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sasluca.lcl.graphics.resources.LCLResourceManager;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCL
{
    public static SpriteBatch SPRITE_BATCH;
    public static LCLResourceManager ResourceManger;

    public static void LCL_INIT()
    {
        SPRITE_BATCH = new SpriteBatch();
        ResourceManger = new LCLResourceManager();
    }
}
