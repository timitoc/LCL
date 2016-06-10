package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IFlippable<This>
{
    boolean     isFlipX();
    boolean     isFlipY();
    This        flipX(boolean flipX);
    This        flipY(boolean flipY);
    This        flip(boolean flipX, boolean flipY);
}
