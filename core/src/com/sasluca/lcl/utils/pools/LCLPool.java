package com.sasluca.lcl.utils.pools;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IReusable;
import java.util.*;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLPool<Object extends IReusable> implements IDisposable
{
    private List<Object> m_FreeObjects;
    private List<Object> m_InUseObjects;
    private IInstanceFactory<Object> m_InstanceFactory;

    public LCLPool(IInstanceFactory<Object> instanceFactory)
    {
        m_FreeObjects = new ArrayList<>();
        m_InUseObjects = new ArrayList<>();
        m_InstanceFactory = instanceFactory;
    }

    public synchronized Object get()
    {
        if(!m_FreeObjects.isEmpty())
        {
            Object freeObject = m_FreeObjects.get(m_FreeObjects.size() - 1);

            m_FreeObjects.remove(m_FreeObjects.size() - 1);
            m_InUseObjects.add(freeObject);

            return freeObject;
        }

        Object o = m_InstanceFactory.newInstance();
        m_InUseObjects.add(o);

        return o;
    }

    public synchronized LCLPool<Object> free(Object object)
    {
        if(m_InUseObjects.contains(object))
        {
            object.reset();
            m_InUseObjects.remove(object);
            m_FreeObjects.add(object);
        }

        return this;
    }

    public synchronized LCLPool<Object> remove(Object object)
    {
        if(!m_FreeObjects.contains(object))
        {
            //TODO:ERROR
            return this;
        }

        if(object instanceof IDisposable) ((IDisposable) object).dispose();
        m_FreeObjects.remove(object);

        return this;
    }

    public LCLPool<Object> setInstanceFactory(IInstanceFactory<Object> instanceFactory) { m_InstanceFactory = instanceFactory; return this; }

    public synchronized LCLPool<Object> addObject(Object object) { m_FreeObjects.add(object); return this; }
    public synchronized LCLPool<Object> addObject() { m_FreeObjects.add(m_InstanceFactory.newInstance()); return this; }

    public synchronized int getNumberOfFreeObjects() { return m_FreeObjects.size(); }
    public synchronized int getNumberOfObjectsInUse() { return m_InUseObjects.size(); }
    public synchronized int getNumberOfObjects() { return getNumberOfFreeObjects() + getNumberOfObjectsInUse(); }

    @Override public void dispose()
    {
        if(getNumberOfObjectsInUse() != 0)
        {
            //TODO: ERROR
            return;
        }

        for(Object object : m_FreeObjects) if(object instanceof IDisposable) ((IDisposable) object).dispose();
        m_FreeObjects.clear();

        m_FreeObjects = null;
        m_InUseObjects = null;
        m_InstanceFactory = null;
    }
}
