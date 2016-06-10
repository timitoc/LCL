package com.sasluca.lcl.abstractions.functional;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/** A condition returns true or false and it may receive arguments. */
public interface ICondition
{
    /** A condition returns true or false and it may receive arguments. */
    boolean condition(Object... args);
}
