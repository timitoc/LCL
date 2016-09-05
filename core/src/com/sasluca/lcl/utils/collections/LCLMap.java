package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.ObjectMap;

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

public class LCLMap<Key, Value> implements Iterable<ObjectMap.Entry<Key, Value>>
{
    private ObjectMap<Key, Value> m_ObjectMap;

    public LCLMap()
    {
        m_ObjectMap = new ObjectMap<>();
    }

    public LCLMap<Key, Value> clear()
    {
        m_ObjectMap.clear();

        return this;
    }

    public LCLMap<Key, Value> remove(Key k) { m_ObjectMap.remove(k); return this; }
    public LCLMap<Key, Value> put(Key k, Value v) { m_ObjectMap.put(k, v); return this; }
    public LCLMap<Key, Value> replace(Key sender, Value value) { m_ObjectMap.remove(sender); m_ObjectMap.put(sender, value); return this; }

    public int getSize() { return m_ObjectMap.size; }
    public boolean isEmpty() { return getSize() == 0; }
    public boolean containsValue(Value v) { return m_ObjectMap.containsValue(v, false); }
    public boolean containsKey(Key k) { return m_ObjectMap.containsKey(k); }
    public Value get(Key key) { return m_ObjectMap.get(key); }
    public ObjectMap.Keys<Key> getKeys() { return m_ObjectMap.keys(); }
    public ObjectMap.Values<Value> getValues() { return m_ObjectMap.values(); }

    public Key getKey(int index)
    {
        int ct = 0;
        for(Key k : m_ObjectMap.keys()) if(ct++ == index) return k;

        return null;
    }

    public Key getKey(Value value)
    {
        for(Key k : m_ObjectMap.keys()) if(get(k).equals(value)) return k;

        return null;
    }

    @Override public Iterator<ObjectMap.Entry<Key, Value>> iterator() { return m_ObjectMap.iterator(); }
}
