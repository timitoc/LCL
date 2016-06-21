package com.sasluca.lcl.graphics.mask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IScalable;
import com.sasluca.lcl.abstractions.ISizeable;

/**
 * Created by Sas Luca on 21-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLMask implements ISizeable<LCLMask>, IScalable<LCLMask>
{
    protected float p_X;
    protected float p_Y;
    protected float p_Width;
    protected float p_Height;
    protected float p_WidthScale;
    protected float p_HeightScale;

    public LCLMask(float x, float y, float width, float height, float widthScale, float heightScale)
    {
        p_X = x;
        p_Y = y;
        p_Width = width;
        p_Height = height;
        p_WidthScale = widthScale;
        p_HeightScale = heightScale;
    }

    public LCLMask(float x, float y, float width, float heigh)
    {
        this(x, y, width, heigh, 1, 1);
    }

    /** All objects rendered after {@link #start()} will be inside the mask */
    public void start()
    {
        LCL.MASTER().SpriteBatch.flush();

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor((int)p_X, (int)p_Y, (int)(p_Width * p_WidthScale), (int)(p_Height * p_HeightScale));
    }

    /** Objects rendered after {@link #end()} will not be inside the mask. Remember you must call {@link #end()} before you call {@link #start()} again*/
    public void end()
    {
        LCL.MASTER().SpriteBatch.flush();
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    public static void startMask(float x, float y, float width, float height)
    {
        LCL.MASTER().SpriteBatch.flush();

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor((int)x, (int)y, (int)width, (int)height);
    }

    /** Has the same effect as {@link #end()} */
    public static void endMask()
    {
        LCL.MASTER().SpriteBatch.flush();
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    @Override public float getOriginalWidth() { return p_Width; }
    @Override public float getOriginalHeight() { return p_Height; }
    @Override public float getWidthScale() { return p_WidthScale; }
    @Override public float getHeightScale() { return p_HeightScale; }
    @Override public float getWidth() { return p_Width * p_WidthScale; }
    @Override public float getHeight() { return p_Height * p_HeightScale; }
    @Override public LCLMask setWidth(float newWidth) { p_Width = newWidth; return this; }
    @Override public LCLMask setHeight(float newHeight) { p_Height = newHeight; return this; }
    @Override public LCLMask setWidthScale(float newWidthScale) { p_WidthScale = newWidthScale; return this;}
    @Override public LCLMask setScale(float newScale) { p_WidthScale = p_HeightScale = newScale;return this; }
    @Override public LCLMask setHeightScale(float newHeightScale) { p_HeightScale = newHeightScale; return this; }
    @Override public LCLMask setSize(float newWidth, float newHeight) { p_Width = newWidth; p_Height = newHeight; return this; }
    @Override public LCLMask setScale(float newWidthScale, float newHeightScale) { p_WidthScale = newWidthScale; p_HeightScale = newHeightScale; return this; }
}
