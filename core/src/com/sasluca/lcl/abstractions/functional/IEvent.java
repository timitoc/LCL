package com.sasluca.lcl.abstractions.functional;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/** An event to which you can pass arguments and does not return anything. */
public interface IEvent
{
    /** An event to which you can pass arguments and does not return anything. */
    void event(Object... args);
}