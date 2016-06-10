package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface ITransformable<This>
{
    float   getX();
    float   getY();
    float   getWidth();
    float   getHeight();
    This    setPosX(int newX);
    This    setPosY(float newY);
}
