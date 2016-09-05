package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.sasluca.lcl.LCL;

/*
 * Copyright 2016 Sas Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class LCLDistanceFieldFont extends LCLFont
{
    /** The shader used to drawText the font. */
    public static ShaderProgram fontShader = DistanceFieldFont.createDistanceFieldShader(); //new ShaderProgram(Gdx.files.internal("shaders/distancefieldfonts/font.vert"), Gdx.files.internal("shaders/distancefieldfonts/font.frag"));
    private final float m_Spread;

    public LCLDistanceFieldFont(String fontName, float spread)
    {
        super(fontName);

        m_Spread = spread;

        LCL.getResourceManager().addTextureLL(fontName, "fonts/distancefieldfonts/" + fontName + "/" + fontName + ".png");

        p_Font = new BitmapFont(Gdx.files.internal("fonts/distancefieldfonts/" + fontName + "/" + fontName + ".fnt"), new TextureRegion(LCL.getResourceManager().<Texture>getResource(fontName)), false);
        p_Font.setColor(Color.BLACK);
        p_Cache = new BitmapFontCache(p_Font);
    }

    public LCLDistanceFieldFont(String fontName, float spread, String pngPath, String fntPath)
    {
        super(fontName);

        m_Spread = spread;

        LCL.getResourceManager().addTextureLL(fontName, pngPath);

        p_Font = new BitmapFont(Gdx.files.internal(fntPath), new TextureRegion(LCL.getResourceManager().<Texture>getResource(fontName)), false);
        p_Font.setColor(Color.BLACK);
        p_Cache = new BitmapFontCache(p_Font);
    }

    public void drawText(String text, float x, float y, float widthScale, float heightScale, Color color, float smoothing, boolean drawFromTop)
    {
        LCL.getSpriteBatch().setShader(fontShader);

        fontShader.setUniformf("u_smoothing", smoothing);
        //fontShader.setUniformf("spread", m_Spread);
        //fontShader.setUniformf("scale", p_Font.getScaleX() * p_Font.getScaleY());

        super.drawText(text, x, y, widthScale, heightScale, color, drawFromTop);

        LCL.getSpriteBatch().setShader(null);
    }

    public void drawText(String text, float x, float y, float widthScale, float heightScale, Color color, LCLColoredTextPart[] coloredParts)
    {
        LCL.getSpriteBatch().setShader(fontShader);

        super.drawText(text, x, y, color, widthScale, heightScale, coloredParts);

        LCL.getSpriteBatch().setShader(null);
    }

    @Override public void dispose() { super.dispose(); }
}
