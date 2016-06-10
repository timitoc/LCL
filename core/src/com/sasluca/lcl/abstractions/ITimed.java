package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/** Returns the amount of time elapsed since the {@link #start()} method was called. */
public interface ITimed<This>
{
    float   getElapsedTime();
    This    start();
    This    end();
}
