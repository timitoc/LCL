package com.sasluca.lcl.graphics.fonts;

import java.util.HashMap;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLFontManager
{
    private HashMap<String, LCLFont> m_Fonts;

    public LCLFontManager()
    {
        m_Fonts = new HashMap<>();
        addDistanceFieldFont("DEFAULT_FONT", 4);
    }

    public void removeFont(String name) { m_Fonts.get(name).dispose(); m_Fonts.remove(name); }
    public <Font extends LCLFont> Font getFont(String name) { return (Font) m_Fonts.get(name); }
    public void addTrueTypeFont(String name, int size) { m_Fonts.put(name, new LCLTrueTypeFont(name, size)); }
    public void addTrueTypeFont(String name, String path, int size) { m_Fonts.put(name, new LCLTrueTypeFont(name, size, path)); }
    public void addDistanceFieldFont(String name, float spread) { m_Fonts.put(name, new LCLDistanceFieldFont(name, spread)); }
    public void addDistanceFieldFont(String name, String pngPath, String fntPath, float spread) { m_Fonts.put(name, new LCLDistanceFieldFont(name, spread, pngPath, fntPath)); }
}
