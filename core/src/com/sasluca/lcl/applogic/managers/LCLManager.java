package com.sasluca.lcl.applogic.managers;

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.abstractions.IDisposable;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLManager<Handler>
{
    protected Array<Handler> p_Handlers;

    /**
     * Use this method to add a handler. If the handler already exists it won't be added.
     * @param handler The new handler.
     */
    public void addHandler(Handler handler) { if(!p_Handlers.contains(handler, true)) p_Handlers.add(handler); }

    /**
     * Use this method to remove a handler.
     * @param handler The handler to be removed.
     */
    public void removeHandler(Handler handler)
    {
        if(p_Handlers.contains(handler, true))
        {
            if(handler instanceof IDisposable) ((IDisposable) handler).dispose();
            p_Handlers.removeValue(handler, true);
        }
    }

    protected abstract void manage();
}
