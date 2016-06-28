package com.sasluca.lcl.graphics.fonts;

import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLFontManager
{
    private LCLArray<LCLFont> m_Fonts;

    public LCLFontManager()
    {
        m_Fonts = new LCLArray<>();
    }

    public void addTrueTypeFont(String name, int size) { m_Fonts.add(new LCLTrueTypeFont(name, size)); }
    public void addDistanceFieldFont(String name, float spread) { m_Fonts.add(new LCLDistanceFieldFont(name, spread)); }
    public void addTrueTypeFont(String name, String path, int size) { m_Fonts.add(new LCLTrueTypeFont(name, size, path)); }
    public void removeFont(String name) { for(LCLFont font : m_Fonts) if(font.getName().matches(name)) { m_Fonts.remove(font); font.dispose(); } }
    public <Font extends LCLFont> Font getFont(String name) { for(LCLFont font : m_Fonts) if(font.getName().matches(name)) return (Font) font; return null; }
    public void addDistanceFieldFont(String name, String pngPath, String fntPath, float spread) { m_Fonts.add(new LCLDistanceFieldFont(name, spread, pngPath, fntPath)); }
}
