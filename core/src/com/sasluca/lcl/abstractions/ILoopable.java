package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface ILoopable<This>
{
    boolean     isLooping();
    This        setLooping(boolean loop);
}
