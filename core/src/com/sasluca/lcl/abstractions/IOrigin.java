package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/** Getters and setters for the origin. The origin is based on the height and width of the object at pos(0,0) */
public interface IOrigin<This>
{
    float   getOriginX();
    float   getOriginY();
    This    setOriginX(float newOriginX);
    This    setOriginY(float newOriginY);
    This    setOrigin(float newOriginX, float newOriginY);
}
