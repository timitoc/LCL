package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IUpdatable<This> extends IUpdate<This>
{
    boolean isUpdating();

    This setUpdatingState(boolean updatingState);
}
