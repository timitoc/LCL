package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import static com.sasluca.lcl.LCL.*;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLDistanceFieldFont extends LCLFont
{
    /** The shader used to drawText the font. */
    public static ShaderProgram fontShader = new ShaderProgram(Gdx.files.internal("shaders/distancefieldfonts/font.vert"), Gdx.files.internal("shaders/distancefieldfonts/font.frag"));
    private final float m_Spread;

    public LCLDistanceFieldFont(String fontName, float spread)
    {
        super(fontName);

        m_Spread = spread;

        ResourceManger.addTextureLL(fontName, "fonts/distancefieldfonts/" + fontName + "/" + fontName + ".png");

        p_Font = new BitmapFont(Gdx.files.internal("ui/fonts/distancefieldfonts/" + fontName + "/" + fontName + ".fnt"), new TextureRegion(ResourceManger.<Texture>getResource(fontName)), false);
        p_Font.setColor(Color.BLACK);
        p_Cache = new BitmapFontCache(p_Font);
    }

    public LCLDistanceFieldFont(String fontName, float spread, String pngPath, String fntPath)
    {
        super(fontName);

        m_Spread = spread;

        ResourceManger.addTextureLL(fontName, pngPath);

        p_Font = new BitmapFont(Gdx.files.internal(fntPath), new TextureRegion(ResourceManger.<Texture>getResource(fontName)), false);
        p_Font.setColor(Color.BLACK);
        p_Cache = new BitmapFontCache(p_Font);
    }

    public void drawText(String text, float x, float y, Color color)
    {
        SPRITE_BATCH.setShader(fontShader);

        fontShader.setUniformf("spread", m_Spread);
        fontShader.setUniformf("scale", p_Font.getScaleX() * p_Font.getScaleY());

        super.drawText(text, x, y, color);

        SPRITE_BATCH.setShader(null);
    }

    public void drawText(String text, float x, float y, Color color, LCLColoredTextPart[] coloredParts)
    {
        SPRITE_BATCH.setShader(fontShader);

        super.drawText(text, x, y, color, coloredParts);

        SPRITE_BATCH.setShader(null);
    }

    @Override public void dispose()
    {
        super.dispose();
    }
}
