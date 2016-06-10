package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IRotatable<This> extends IOrigin<This>
{
    float   getRotation();
    This    setRotation(float degrees);
}