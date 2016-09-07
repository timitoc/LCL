package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.ObjectMap;
import com.sasluca.lcl.utils.text.LCLString;

import java.util.Iterator;

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

public class LCLMap<KEY, VALUE> implements Iterable<ObjectMap.Entry<KEY, VALUE>>
{
    private LCLString m_Erasables;
    private ObjectMap<KEY, VALUE> m_ObjectMap;

    public LCLMap()
    {
        m_Erasables = new LCLString("");
        m_ObjectMap = new ObjectMap<>();
    }

    public LCLMap<KEY, VALUE> clear()
    {
        m_ObjectMap.clear();

        return this;
    }

    public LCLMap<KEY, VALUE> remove(KEY k) { m_ObjectMap.remove(k); return this; }

    public LCLMap<KEY, VALUE> put(KEY k, VALUE v) { m_ObjectMap.put(k, v); return this; }
    public LCLMap<KEY, VALUE> replace(KEY sender, VALUE value) { m_ObjectMap.remove(sender); m_ObjectMap.put(sender, value); return this; }

    public int getIndexOfKey(KEY key)
    {
        for (int i = 0; i < getSize(); i++) for (KEY k : getKeys()) if (key.equals(k)) return i;

        return -1;
    }

    public int getSize() { return m_ObjectMap.size; }
    public boolean isEmpty() { return getSize() == 0; }
    public boolean containsValue(VALUE v) { return m_ObjectMap.containsValue(v, false); }
    public boolean containsKey(KEY k) { return m_ObjectMap.containsKey(k); }
    public VALUE get(KEY key) { return m_ObjectMap.get(key); }
    public ObjectMap.Keys<KEY> getKeys() { return m_ObjectMap.keys(); }
    public ObjectMap.Values<VALUE> getValues() { return m_ObjectMap.values(); }

    public KEY getKey(int index)
    {
        int ct = 0;
        for(KEY k : m_ObjectMap.keys()) if(ct++ == index) return k;

        return null;
    }

    public KEY getKey(VALUE value)
    {
        for(KEY k : m_ObjectMap.keys()) if(get(k).equals(value)) return k;

        return null;
    }

    @Override public Iterator<ObjectMap.Entry<KEY, VALUE>> iterator() { return m_ObjectMap.iterator(); }
}
