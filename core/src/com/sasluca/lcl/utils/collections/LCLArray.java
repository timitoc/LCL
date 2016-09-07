package com.sasluca.lcl.utils.collections;

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.abstractions.IClonable;
import com.sasluca.lcl.utils.text.LCLString;

import java.util.Iterator;

public class LCLArray<OBJECT> implements Iterable<OBJECT>, IClonable<LCLArray>
{
    private Array<OBJECT> m_Array;
    private LCLString m_Erasables;

    public LCLArray()
    {
        m_Erasables = new LCLString("");
        m_Array = new Array<>();
    }

    public LCLArray<OBJECT> add(OBJECT o)
    {
        m_Erasables.append("0");
        m_Array.add(o);

        return this;
    }

    public LCLArray<OBJECT> remove(int index)
    {
        m_Erasables.deleteCharAt(index);
        m_Array.removeIndex(index);

        return this;
    }

    public LCLArray<OBJECT> remove(OBJECT object)
    {
        m_Array.indexOf(object, false);
        m_Array.removeValue(object, false);

        return this;
    }

    public LCLArray<OBJECT> clear()
    {
        m_Erasables.clear();
        m_Array.clear();

        return this;
    }

    public LCLArray<OBJECT> erase(int index)
    {
        if (index > 0 && index < m_Erasables.getLength()) m_Erasables.changeCharAtIndex(index, '1');

        return this;
    }

    public LCLArray<OBJECT> erase(OBJECT o)
    {
        if (contains(o)) m_Erasables.changeCharAtIndex(getIndexOf(o), '1');

        return this;
    }

    public LCLArray<OBJECT> undoErase(int index)
    {
        if (index > 0 && index < m_Erasables.getLength()) m_Erasables.changeCharAtIndex(index, '0');

        return this;
    }

    public LCLArray<OBJECT> undoErase(OBJECT o)
    {
        if (contains(o)) m_Erasables.changeCharAtIndex(getIndexOf(o), '0');

        return this;
    }

    public boolean isErased(int i) { return m_Erasables.getCharAt(i) == '1'; }
    public boolean isErased(OBJECT o) { return m_Erasables.getCharAt(getIndexOf(o)) == '1'; }

    public LCLArray<OBJECT> clean()
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

    public int getSize() { return m_Array.size; }
    public boolean isEmpty() { return m_Array.size == 0; }
    public OBJECT get(int index) { return m_Array.get(index); }
    public int getIndexOf(OBJECT o) { return m_Array.indexOf(o, false); }
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
