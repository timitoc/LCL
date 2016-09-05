package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
