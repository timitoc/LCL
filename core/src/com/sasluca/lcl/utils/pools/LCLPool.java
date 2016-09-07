package com.sasluca.lcl.utils.pools;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.utils.collections.list.LCLList;
import com.sasluca.lcl.utils.tuples.mutable.LCLMutablePair;

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

public class LCLPool<OBJECT> implements IPool<OBJECT>, IDisposable
{
    private LCLList<LCLMutablePair<Boolean, OBJECT>> m_Objects; //True if in use
    private IOnReset<OBJECT> m_OnReset;
    private IInstanceFactory<OBJECT> m_InstanceFactory;

    public LCLPool(IInstanceFactory<OBJECT> instanceFactory, IOnReset<OBJECT> onReset)
    {
        m_OnReset = onReset;
        m_Objects = new LCLList<>();
        m_InstanceFactory = instanceFactory;
    }

    public OBJECT get()
    {
        if(!m_Objects.isEmpty())
        {
            for (LCLMutablePair<Boolean, OBJECT> p : m_Objects)
            {
                if (!p.get1())
                {
                    p.set1(true);
                    return p.get2();
                }
            }
        }

        LCLMutablePair<Boolean, OBJECT> newObj = new LCLMutablePair<>(false, m_InstanceFactory.newInstance());

        m_Objects.add(newObj);

        return newObj.get2();
    }

    public LCLPool<OBJECT> free(OBJECT object)
    {
        if (!m_Objects.isEmpty())
        {
            for (LCLMutablePair<Boolean, OBJECT> p : m_Objects)
                if (p.get1() && p.get2().equals(object))
                {
                    m_OnReset.onReset(object);
                    p.set1(false);
                }
        }

        return this;
    }

    public LCLPool<OBJECT> remove(OBJECT object)
    {
        for (LCLMutablePair<Boolean, OBJECT> p : m_Objects)
        {
            if (p.get2().equals(object))
            {
                m_Objects.erase(p);
                break;
            }
        }

        m_Objects.clean();

        return this;
    }

    public LCLPool<OBJECT> remove()
    {
        for (LCLMutablePair<Boolean, OBJECT> p : m_Objects)
        {
            if (!p.get1())
            {
                m_Objects.erase(p);
                break;
            }
        }

        m_Objects.clean();

        return this;
    }

    public LCLPool<OBJECT> clear()
    {
        m_Objects.clear();

        return this;
    }

    public LCLPool<OBJECT> remove(int remove)
    {
        for (LCLMutablePair<Boolean, OBJECT> p : m_Objects)
        {
            if (!p.get1())
            {
                m_Objects.erase(p);
                if(--remove == 0) break;
            }
        }

        m_Objects.clean();

        return this;
    }

    public LCLPool<OBJECT> setInstanceFactory(IInstanceFactory<OBJECT> instanceFactory) { m_InstanceFactory = instanceFactory; return this; }

    public LCLPool<OBJECT> addObject() { m_Objects.add(new LCLMutablePair<>(false, m_InstanceFactory.newInstance())); return this; }

    public int getNumberOfFreeObjects()
    {
        int ct = 0;
        for (LCLMutablePair<Boolean, OBJECT> p : m_Objects) if (!p.get1()) ct++;
        return m_Objects.getSize();
    }

    public int getNumberOfObjectsInUse()
    {
        int ct = 0;
        for (LCLMutablePair<Boolean, OBJECT> p : m_Objects) if (p.get1()) ct++;

        return ct;
    }

    public int getNumberOfObjects() { return getNumberOfFreeObjects() + getNumberOfObjectsInUse(); }

    @Override public void dispose()
    {
        if(getNumberOfObjectsInUse() != 0)
        {
            //TODO: ERROR
            return;
        }

        m_Objects.clear();

        m_OnReset = null;
        m_Objects = null;
        m_InstanceFactory = null;
    }
}
