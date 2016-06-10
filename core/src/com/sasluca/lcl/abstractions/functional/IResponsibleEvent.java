package com.sasluca.lcl.abstractions.functional;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/** An event that returns a response. */
public interface IResponsibleEvent<Response>
{
    /** An event that returns a response. */
    Response event(Object... args);
}
