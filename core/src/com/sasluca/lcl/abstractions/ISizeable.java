package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface ISizeable<This>
{
    float   getWidth();
    float   getHeight();
    This    setWidth(float newWidth);
    This    setHeight(float newHeight);
    This    setSize(float newWidth, float newHeight);
}
