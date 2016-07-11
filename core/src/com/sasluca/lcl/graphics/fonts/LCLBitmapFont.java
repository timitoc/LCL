package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.LCL;

/**
 * Created by Sas Luca on 19-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLBitmapFont extends LCLFont
{

    public LCLBitmapFont(String fontName)
    {
        super(fontName);
        TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("fonts/bitmapfonts/" + fontName + "/" + fontName + ".png")));
        textureRegion.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        p_Font = new BitmapFont(Gdx.files.internal("fonts/bitmapfonts/" + fontName + "/" + fontName + ".fnt"), textureRegion);
        p_Font.getData().setScale(0.25f, 0.25f);
    }

    public LCLBitmapFont(String fontName, String fontPath)
    {
        super(fontName);
        p_Font = new BitmapFont(Gdx.files.internal(fontPath));
    }
}
