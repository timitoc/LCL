package com.sasluca.lcl.graphics.fonts;

import com.sasluca.lcl.utils.collections.list.LCLList;

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

public class LCLFontManager
{
    private static LCLList<LCLFont> m_Fonts = new LCLList<>();

    private LCLFontManager() { }

    public static void addBitmapFont(String name) { m_Fonts.add(new LCLBitmapFont(name)); }
    public static void addTrueTypeFont(String name, int size) { m_Fonts.add(new LCLTrueTypeFont(name, size)); }
    public static void addDistanceFieldFont(String name, float spread) { m_Fonts.add(new LCLDistanceFieldFont(name, spread)); }
    public static void addTrueTypeFont(String name, String path, int size) { m_Fonts.add(new LCLTrueTypeFont(name, size, path)); }
    public static LCLFont getFont(String name) { for(LCLFont font : m_Fonts) if(font.getName().matches(name)) return font; return null; }
    public static void removeFont(String name) { for(LCLFont font : m_Fonts) if(font.getName().matches(name)) { m_Fonts.remove(font); font.dispose(); } }
    public static void addDistanceFieldFont(String name, String pngPath, String fntPath, float spread) { m_Fonts.add(new LCLDistanceFieldFont(name, spread, pngPath, fntPath)); }
}
