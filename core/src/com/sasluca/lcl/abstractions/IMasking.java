package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 7/18/2016.
 * Copyright (C) 2016 - LCL
 */

public interface IMasking<This>
{
    boolean getMaskingStrategy();

    This setMaskingStrategy(boolean maskingStrategy);
}
