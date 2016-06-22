package com.sasluca.lcl.input.events;

/**
 * Created by Sas Luca on 22/06/16.
 * Copyright (C) 2016 - LCL
 */

public interface ITouchUpEvent<Sender>
{
    void touchUp(int screenX, int screenY, int pointer, int button, Sender sender);
}
