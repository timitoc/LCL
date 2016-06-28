package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.Array;

import java.util.List;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLArray<Object> extends Array<Object>
{
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void remove(int index) { removeIndex(index); }
    public void remove(Object object) { removeValue(object, true); }
    public boolean contains(Object object) { return contains(object, true); }
}
