package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLObjectMap<Key, Value> extends ObjectMap<Key, Value>
{
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void replace(Key sender, Value value) { remove(sender); put(sender, value); }
}
