package com.sasluca.lcl.input.events;

/**
 * Created by Sas Luca on 22/06/16.
 * Copyright (C) 2016 - LCL
 */

public interface ITouchDownEvent<Sender>
{
    void touchDown(int screenX, int screenY, int pointer, int button, Sender sender);
}
