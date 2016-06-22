package com.sasluca.lcl.input.events;

/**
 * Created by Sas Luca on 22/06/16.
 * Copyright (C) 2016 - LCL
 */

public interface IKeyTypedEvent<Sender>
{
    void keyTyped(char character, Sender sender);
}
