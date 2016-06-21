package com.sasluca.lcl.utils.pools;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IReusable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sas Luca on 11-Jun-16.
 */

public abstract class LCLPool<Object extends IReusable> implements IDisposable
{
    private final Map<Object, Boolean> m_Objects;

    public LCLPool() { m_Objects = new HashMap<>(); }

    public abstract Object newInstance();

    public int getNumberOfObjects() { return m_Objects.size(); }

    public Object get()
    {
        if(!m_Objects.isEmpty())
        {
            Iterator iterator = m_Objects.keySet().iterator();
            for(int i = 0; i++ < m_Objects.values().size();)
            {
                Object o = (Object) iterator.next();
                if(m_Objects.get(o))
                {
                    m_Objects.remove(o);
                    m_Objects.put(o, false);

                    return o;
                }
            }
        }

        Object o = newInstance();
        m_Objects.put(o, false);

        return o;
    }

    public void free(Object o)
    {
        if(!m_Objects.containsKey(o)) return;

        o.reset();
        m_Objects.remove(o);
        m_Objects.put(o, true);
    }

    public void remove(Object o)
    {
        if(o instanceof IDisposable) ((IDisposable) o).dispose();
        m_Objects.remove(o);
    }

    public void addObject(Object o) { m_Objects.put(o, true); }

    @Override public void dispose()
    {
        for(Object object : m_Objects.keySet()) if(object instanceof IDisposable) ((IDisposable) object).dispose();
        m_Objects.clear();
    }
}
