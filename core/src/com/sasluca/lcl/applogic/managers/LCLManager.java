package com.sasluca.lcl.applogic.managers;

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.abstractions.IDisposable;

/*
 * Copyright 2016 Sas Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public abstract class LCLManager<HANDLER>
{
    protected Array<HANDLER> p_Handlers;

    /**
     * Use this method to add a handler. If the handler already exists it won't be added.
     * @param handler The new handler.
     */
    public void addHandler(HANDLER handler) { if(!p_Handlers.contains(handler, true)) p_Handlers.add(handler); }

    /**
     * Use this method to remove a handler.
     * @param handler The handler to be removed.
     */
    public void removeHandler(HANDLER handler)
    {
        if(p_Handlers.contains(handler, true))
        {
            if(handler instanceof IDisposable) ((IDisposable) handler).dispose();
            p_Handlers.removeValue(handler, true);
        }
    }

    protected abstract void manage();
}
