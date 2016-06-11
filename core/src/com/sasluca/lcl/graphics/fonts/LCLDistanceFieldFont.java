package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLDistanceFieldFont extends LCLFont
{
    /** The shader used to render the font. */
    public static ShaderProgram fontShader = new ShaderProgram(Gdx.files.internal("shaders/distancefieldfonts/font.vert"), Gdx.files.internal("shaders/distancefieldfonts/font.frag"));
    private final float m_Spread;

    public LCLDistanceFieldFont(String fontName, float spread)
    {
        super(fontName);

        m_Spread = spread;

        TsunTexturePool.addTexture(fontName, new Texture(Gdx.files.internal("ui/fonts/distancefieldfonts/" + fontName + "/" + fontName + ".png")));
        TsunTexturePool.getTexture(fontName).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //Don't forget that TsunTexturePool does not set the filter of the texture

        p_Font = new BitmapFont(Gdx.files.internal("ui/fonts/distancefieldfonts/" + fontName + "/" + fontName + ".fnt"), new TextureRegion(TsunTexturePool.getTexture(fontName)), false);
        p_Font.setColor(Color.BLACK);
        p_Cache = new BitmapFontCache(p_Font);
    }

    public void render(String text, float x, float y, Color color)
    {
        TsunTsun.getBatch().setShader(fontShader);

        fontShader.setUniformf("spread", m_Spread);
        fontShader.setUniformf("scale", p_Font.getScaleX() * p_Font.getScaleY());

        super.render(text, x, y, color);

        TsunTsun.getBatch().setShader(null);
    }

    public void render(String text, float x, float y, Color color, TsunColoredPart[] coloredParts)
    {
        TsunTsun.getBatch().setShader(fontShader);

        super.render(text, x, y, color, coloredParts);

        TsunTsun.getBatch().setShader(null);
    }
}
