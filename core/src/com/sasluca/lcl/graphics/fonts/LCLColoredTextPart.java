package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLColoredTextPart
{
    public int begin;
    public int end;
    public Color color;

    public LCLColoredTextPart(int begin, int end, Color color)
    {
        this.end = end;
        this.begin = begin;
        this.color = new Color(color);
    }
}
