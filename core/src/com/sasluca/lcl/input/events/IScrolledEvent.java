package com.sasluca.lcl.input.events;

/**
 * Created by Sas Luca on 21/06/16.
 * Copyright (C) 2016 - LCL
 */

public interface IScrolledEvent<Sender>
{
    void scrolled(int amount, Sender sender);
}
