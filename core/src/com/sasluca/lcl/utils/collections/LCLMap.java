package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLMap<Key, Value> extends ObjectMap<Key, Value>
{
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void replace(Key sender, Value value) { remove(sender); put(sender, value); }

    public Key getKey(int index)
    {
        int ct = 0;

        for(Key k : keys())
        {
            if(ct == index) return k;

            ct++;
        }

        return null;
    }
}
