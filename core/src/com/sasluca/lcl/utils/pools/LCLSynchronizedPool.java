package com.sasluca.lcl.utils.pools;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.utils.collections.LCLArray;

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

/**
 * Synchronized pool for use on when dealing with multiple threads like in LCLAsyncTaskExecutor
 * @param <Object>
 */
public class LCLSynchronizedPool<Object> implements IPool<Object>, IDisposable 
{
    private IOnReset<Object> m_OnReset;
    private LCLArray<Object> m_FreeObjects;
    private LCLArray<Object> m_InUseObjects;
    private IInstanceFactory<Object> m_InstanceFactory;

    public LCLSynchronizedPool(IInstanceFactory<Object> instanceFactory, IOnReset<Object> onReset)
    {
        m_OnReset = onReset;
        m_FreeObjects = new LCLArray<>();
        m_InUseObjects = new LCLArray<>();
        m_InstanceFactory = instanceFactory;
    }

    @Override public synchronized Object get()
    {
        if(!m_FreeObjects.isEmpty())
        {
            Object freeObject = m_FreeObjects.get(m_FreeObjects.getSize() - 1);

            m_FreeObjects.remove(m_FreeObjects.getSize() - 1);
            m_InUseObjects.add(freeObject);

            return freeObject;
        }

        Object o = m_InstanceFactory.newInstance();
        m_InUseObjects.add(o);

        return o;
    }

    @Override public synchronized LCLSynchronizedPool<Object> free(Object object)
    {
        if(m_InUseObjects.contains(object))
        {
            m_OnReset.onReset(object);
            m_InUseObjects.remove(object);
            m_FreeObjects.add(object);
        }

        return this;
    }

    @Override public synchronized LCLSynchronizedPool<Object> remove(Object object)
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

    @Override public synchronized LCLSynchronizedPool<Object> remove()
    {
        if(m_FreeObjects.get(0) instanceof IDisposable) ((IDisposable) m_FreeObjects.get(0)).dispose();
        m_FreeObjects.remove(0);

        return this;
    }

    @Override public synchronized LCLSynchronizedPool<Object> clear()
    {
        for(int i = 0; i < m_FreeObjects.getSize(); i++) if(m_FreeObjects.get(i) instanceof IDisposable) ((IDisposable) m_FreeObjects.get(i)).dispose();
        for(int i = 0; i < m_InUseObjects.getSize(); i++) if(m_InUseObjects.get(i) instanceof IDisposable) ((IDisposable) m_InUseObjects.get(i)).dispose();

        m_FreeObjects.clear();
        m_InUseObjects.clear();

        return this;
    }

    @Override public synchronized LCLSynchronizedPool<Object> remove(int remove)
    {
        for(Object object : m_FreeObjects)
        {
            if(object instanceof IDisposable) ((IDisposable) object).dispose();
            m_FreeObjects.remove(object);
            remove--;
            if(remove == 0) break;
        }

        return this;
    }

    @Override public synchronized LCLSynchronizedPool<Object> setInstanceFactory(IInstanceFactory<Object> instanceFactory) { m_InstanceFactory = instanceFactory; return this; }
    @Override public synchronized LCLSynchronizedPool<Object> addObject() { m_FreeObjects.add(m_InstanceFactory.newInstance()); return this; }

    @Override public synchronized int getNumberOfFreeObjects() { return m_FreeObjects.getSize(); }
    @Override public synchronized int getNumberOfObjectsInUse() { return m_InUseObjects.getSize(); }
    @Override public synchronized int getNumberOfObjects() { return getNumberOfFreeObjects() + getNumberOfObjectsInUse(); }

    @Override public void dispose()
    {
        if(getNumberOfObjectsInUse() != 0)
        {
            //TODO: ERROR
            return;
        }

        for(Object object : m_FreeObjects) if(object instanceof IDisposable) ((IDisposable) object).dispose();
        m_FreeObjects.clear();

        m_OnReset = null;
        m_FreeObjects = null;
        m_InUseObjects = null;
        m_InstanceFactory = null;
    }
}
