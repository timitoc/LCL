package com.sasluca.lcl.graphics.fonts;

import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLFontManager
{
    private static LCLArray<LCLFont> m_Fonts = new LCLArray<>();

    private LCLFontManager()
    {

    }

    public static void addBitmapFont(String name) { m_Fonts.add(new LCLBitmapFont(name)); }
    public static void addTrueTypeFont(String name, int size) { m_Fonts.add(new LCLTrueTypeFont(name, size)); }
    public static void addDistanceFieldFont(String name, float spread) { m_Fonts.add(new LCLDistanceFieldFont(name, spread)); }
    public static void addTrueTypeFont(String name, String path, int size) { m_Fonts.add(new LCLTrueTypeFont(name, size, path)); }
    public static LCLFont getFont(String name) { for(LCLFont font : m_Fonts) if(font.getName().matches(name)) return font; return null; }
    public static void removeFont(String name) { for(LCLFont font : m_Fonts) if(font.getName().matches(name)) { m_Fonts.remove(font); font.dispose(); } }
    public static void addDistanceFieldFont(String name, String pngPath, String fntPath, float spread) { m_Fonts.add(new LCLDistanceFieldFont(name, spread, pngPath, fntPath)); }
}
