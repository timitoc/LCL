package com.sasluca.lcl.utils.pools;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/**
 * Describes how to reset an object when you free it in an IPool
 * @param <OBJECT>
 */
public interface IOnReset<OBJECT>
{
    void onReset(OBJECT object);
}
