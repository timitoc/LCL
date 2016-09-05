package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.abstractions.IClonable;

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

public class LCLArray<OBJECT> implements Iterable<OBJECT>, IClonable<LCLArray>
{
    private Array<OBJECT> m_Array;

    public LCLArray()
    {
        m_Array = new Array<>();
    }

    public LCLArray<OBJECT> add(OBJECT o) { m_Array.add(o); return this; }
    public LCLArray<OBJECT> remove(int index) { m_Array.removeIndex(index); return this; }
    public LCLArray<OBJECT> remove(OBJECT object) { m_Array.removeValue(object, false); return this; }
    public LCLArray<OBJECT> clear() { m_Array.clear(); return this; }

    public int getSize() { return m_Array.size; }
    public boolean isEmpty() { return m_Array.size == 0; }
    public OBJECT get(int index) { return m_Array.get(index); }
    public boolean contains(OBJECT object) { return m_Array.contains(object, false); }

    public OBJECT[] asArray() { return m_Array.toArray(); }

    @Override public Iterator<OBJECT> iterator() { return m_Array.iterator(); }

    @Override public LCLArray getClone()
    {
        LCLArray array = new LCLArray<OBJECT>();
        for(OBJECT o : this) array.add(o);

        return array;
    }
}
