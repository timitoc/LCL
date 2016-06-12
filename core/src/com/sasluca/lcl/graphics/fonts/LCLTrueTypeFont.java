package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLTrueTypeFont extends LCLFont
{
    public LCLTrueTypeFont(String fontName, int size)
    {
        super(fontName);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/truetypefonts/" + fontName + "/" + fontName + ".ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;
        p_Font = generator.generateFont(parameter);

        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public LCLTrueTypeFont(String fontName, int size, String fontPath)
    {
        super(fontName);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;
        p_Font = generator.generateFont(parameter);

        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }
}
