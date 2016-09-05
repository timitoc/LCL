package com.sasluca.lcl.materialdesign.theme;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.ui.text.UITextBox;
import com.sasluca.lcl.utils.collections.LCLMap;

/**
 * Created by Sas Luca on 8/10/2016.
 * Copyright (C) 2016 - LCL
 */

public class UITheme
{
    private LCLMap<String, Color> m_Colors;

    public UITheme()
    {
        m_Colors = new LCLMap<>();
    }

    public Color getColor(String name) { return m_Colors.get(name); }

    public UITheme addColor(String name, Color color)
    {
        m_Colors.put(name, new Color(color));

        return this;
    }

    public UITheme setColor(String name, Color color)
    {
        m_Colors.replace(name, color);

        return this;
    }
}
