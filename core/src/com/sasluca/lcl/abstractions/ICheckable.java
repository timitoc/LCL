package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 7/27/2016.
 * Copyright (C) 2016 - LCL
 */

public interface ICheckable<This>
{
    boolean isChecked();

    This check();
    This uncheck();
}
