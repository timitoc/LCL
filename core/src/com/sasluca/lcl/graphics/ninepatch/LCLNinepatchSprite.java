package com.sasluca.lcl.graphics.ninepatch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.graphics.fonts.LCLLabel;

/**
 * Created by Sas Luca on 21-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLNinepatchSprite implements IRenderable<LCLNinepatchSprite>, IColorable<LCLNinepatchSprite>, ISizeable<LCLNinepatchSprite>, IScalable<LCLNinepatchSprite>
{
    protected float p_X;
    protected float p_Y;
    protected Color p_Color;
    protected float p_Width;
    protected float p_Height;
    protected float p_WidthScale;
    protected float p_HeightScale;
    protected NinePatch p_NinePatch;
    protected boolean p_IsRendering;
    protected Texture.TextureFilter p_MinFilter;
    protected Texture.TextureFilter p_MagFilter;

    public LCLNinepatchSprite(String ninepatch, Texture.TextureFilter minFilter, Texture.TextureFilter magFilter)
    {
        p_NinePatch = LCL.SYS.ResourceManger.<NinePatch>getResource(ninepatch);
        p_MinFilter = minFilter;
        p_MagFilter = magFilter;
        p_WidthScale = 1;
        p_HeightScale = 1;
        p_IsRendering = true;
    }

    //Render
    @Override public LCLNinepatchSprite render()
    {
        if(!p_IsRendering) return this;

        p_NinePatch.setColor(p_Color);
        p_NinePatch.scale(p_WidthScale, p_HeightScale);
        p_NinePatch.getTexture().setFilter(p_MinFilter, p_MagFilter);
        p_NinePatch.draw(LCL.SYS.SpriteBatch, p_X, p_Y, p_Width, p_Height);

        return this;
    }

    @Override public boolean isRendering() { return p_IsRendering; }
    @Override public LCLNinepatchSprite setRenderingState(boolean renderingState) { p_IsRendering = renderingState; return this; }


    //Color
    @Override public Color getColor() { return p_Color; }
    @Override public LCLNinepatchSprite setColor(Color newColor) { p_Color = new Color(newColor); return this; }

    //Size and scale
    @Override public float getOriginalWidth() { return p_Width; }
    @Override public float getOriginalHeight() { return p_Height; }
    @Override public float getWidthScale() { return p_WidthScale; }
    @Override public float getHeightScale() { return p_HeightScale; }
    @Override public float getWidth() { return p_Width * p_WidthScale; }
    @Override public float getHeight() { return p_Height * p_HeightScale; }
    @Override public LCLNinepatchSprite setWidth(float newWidth) { p_Width = newWidth; return this; }
    @Override public LCLNinepatchSprite setHeight(float newHeight) { p_Height = newHeight; return this; }
    @Override public LCLNinepatchSprite setWidthScale(float newWidthScale) { p_WidthScale = newWidthScale; return this; }
    @Override public LCLNinepatchSprite setScale(float newScale) { p_WidthScale = p_HeightScale = newScale; return this; }
    @Override public LCLNinepatchSprite setHeightScale(float newHeightScale) { p_HeightScale = newHeightScale; return this; }
    @Override public LCLNinepatchSprite setSize(float newWidth, float newHeight) { p_Width = newWidth; p_Height = newHeight; return this; }
    @Override public LCLNinepatchSprite setScale(float newWidthScale, float newHeightScale) { p_WidthScale = newWidthScale; p_HeightScale = newHeightScale; return this; }
}
