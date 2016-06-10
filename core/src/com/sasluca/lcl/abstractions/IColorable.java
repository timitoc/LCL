package com.sasluca.lcl.abstractions;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IColorable<This>
{
    Color   getColor();
    This    setColor(Color newColor);
}

