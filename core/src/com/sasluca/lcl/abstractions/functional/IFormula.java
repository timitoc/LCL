package com.sasluca.lcl.abstractions.functional;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/** A formula that returns a result based on variables. */
public interface IFormula<Result>
{
    /** A formula that returns a result based on variables. */
    Result formula(Object... args);
}
