package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Sas Luca on 19-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLBitmapFont extends LCLFont
{

    public LCLBitmapFont(String fontName)
    {
        super(fontName);
        p_Font = new BitmapFont(Gdx.files.internal("fonts/bitmapfonts/" + fontName + "/" + fontName + ".fnt"));
    }

    public LCLBitmapFont(String fontName, String fontPath)
    {
        super(fontName);
        p_Font = new BitmapFont(Gdx.files.internal(fontPath));
    }
}
