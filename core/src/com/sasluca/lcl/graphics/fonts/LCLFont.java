package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IDisposable;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLFont implements IDisposable
{
    protected String p_Name;
    protected BitmapFont p_Font;
    protected BitmapFontCache p_Cache;
    private static final GlyphLayout GLYPH_LAYOUT = new GlyphLayout();

    public LCLFont(String name)
    {
        p_Name = name;
    }

    public void drawText(String text, float x, float y, Color color)
    {
        p_Font.setColor(color);
        p_Font.draw(LCL.SPRITE_BATCH, text, x, y + getTextHeight(text, p_Font.getScaleY()));
    }

    public void drawText(String text, float x, float y, Color color, LCLColoredTextPart[] coloredParts)
    {
        p_Cache.setColor(color);
        p_Cache.setText(text, x, y);

        for(int i = 0; i < coloredParts.length; i++) p_Cache.setColors(coloredParts[i].color, coloredParts[i].begin, coloredParts[i].end);

        p_Cache.draw(LCL.SPRITE_BATCH);
    }

    public final float getTextWidth(String text, float widthScale)
    {
        p_Font.getData().setScale(widthScale, p_Font.getScaleY());

        GLYPH_LAYOUT.setText(p_Font, text);

        return GLYPH_LAYOUT.width;
    }

    public final float getTextHeight(String text, float heightScale)
    {
        p_Font.getData().setScale(p_Font.getScaleX(), heightScale);

        GLYPH_LAYOUT.setText(p_Font, text);

        return GLYPH_LAYOUT.height;
    }

    public final BitmapFont getFont() { return p_Font; }

    public final String getName() { return p_Name; }

    @Override public void dispose()
    {
        p_Font.dispose();
    }
}
