package com.sasluca.lcl.utils.collections.list;

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.utils.text.LCLString;

import java.util.Iterator;

public class LCLSynchronizedList<OBJECT> implements IList<OBJECT>
{
    //I know it's called Array but List sounds way better
    private Array<OBJECT> m_Array;
    private LCLString m_Erasables;

    public LCLSynchronizedList()
    {
        m_Erasables = new LCLString("");
        m_Array = new Array<>();
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> add(OBJECT o)
    {
        m_Erasables.append("0");
        m_Array.add(o);

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> remove(int index)
    {
        m_Erasables.deleteCharAt(index);
        m_Array.removeIndex(index);

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> remove(OBJECT object)
    {
        m_Array.indexOf(object, false);
        m_Array.removeValue(object, false);

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> clear()
    {
        m_Erasables.clear();
        m_Array.clear();

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> erase(int index)
    {
        if (index > 0 && index < m_Erasables.getLength()) m_Erasables.changeCharAtIndex(index, '1');

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> erase(OBJECT o)
    {
        if (contains(o)) m_Erasables.changeCharAtIndex(getIndexOf(o), '1');

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> undoErase(int index)
    {
        if (index > 0 && index < m_Erasables.getLength()) m_Erasables.changeCharAtIndex(index, '0');

        return this;
    }

    @Override public synchronized LCLSynchronizedList<OBJECT> undoErase(OBJECT o)
    {
        if (contains(o)) m_Erasables.changeCharAtIndex(getIndexOf(o), '0');

        return this;
    }

    @Override public synchronized boolean isErased(int i) { return m_Erasables.getCharAt(i) == '1'; }
    @Override public synchronized boolean isErased(OBJECT o) { return m_Erasables.getCharAt(getIndexOf(o)) == '1'; }

    @Override public synchronized LCLSynchronizedList<OBJECT> clean()
    {
        for (int i = 0; i < m_Erasables.getLength(); i++)
        {
            if(m_Erasables.getLength() == 0) break;
            if (m_Erasables.getCharAt(i) == '1')
            {
                m_Array.removeIndex(i);
                m_Erasables.deleteCharAt(i);
                i--;
            }
        }

        return this;
    }

    @Override public synchronized int getSize() { return m_Array.size; }
    @Override public synchronized boolean isEmpty() { return m_Array.size == 0; }
    @Override public synchronized OBJECT get(int index) { return m_Array.get(index); }
    @Override public synchronized int getIndexOf(OBJECT o) { return m_Array.indexOf(o, false); }
    @Override public synchronized boolean contains(OBJECT object) { return m_Array.contains(object, false); }

    @Override public synchronized OBJECT[] asArray() { return m_Array.toArray(); }

    //TODO:QUESTION -> Should the iterator be synchronized
    @Override public synchronized Iterator<OBJECT> iterator() { return m_Array.iterator(); }

    @Override public synchronized LCLSynchronizedList getClone()
    {
        LCLSynchronizedList array = new LCLSynchronizedList<OBJECT>();
        for (OBJECT o : this) array.add(o);

        //TODO:QUESTION -> Should the clone also contain
        for (int i = 0; i < m_Erasables.getLength(); i++) if (m_Erasables.getCharAt(i) == '1') array.erase(i);

        return array;
    }
}
