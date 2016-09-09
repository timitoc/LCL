package com.sasluca.lcl.utils.collections.list;

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.utils.text.LCLString;

import java.util.Iterator;

public class LCLList<OBJECT> implements IList<OBJECT>
{
    //I know it's called Array but List sounds way better
    private Array<OBJECT> m_Array;
    private LCLString m_Erasables;

    public LCLList()
    {
        m_Erasables = new LCLString("");
        m_Array = new Array<>();
    }

    @Override public LCLList<OBJECT> add(OBJECT o)
    {
        m_Erasables.append("0");
        m_Array.add(o);

        return this;
    }

    @Override public LCLList<OBJECT> remove(int index)
    {
        m_Erasables.deleteCharAt(index);
        m_Array.removeIndex(index);

        return this;
    }

    @Override public LCLList<OBJECT> remove(OBJECT object)
    {
        m_Erasables.deleteCharAt(m_Array.indexOf(object, false));
        m_Array.removeValue(object, false);

        return this;
    }

    @Override public LCLList<OBJECT> clear()
    {
        m_Erasables.clear();
        m_Array.clear();

        return this;
    }

    @Override public LCLList<OBJECT> erase(int index)
    {
        if (index > 0 && index < m_Erasables.getLength()) m_Erasables.changeCharAtIndex(index, '1');

        return this;
    }

    @Override public LCLList<OBJECT> erase(OBJECT o)
    {
        if (contains(o)) m_Erasables.changeCharAtIndex(getIndexOf(o), '1');

        return this;
    }

    @Override public LCLList<OBJECT> undoErase(int index)
    {
        if (index > 0 && index < m_Erasables.getLength()) m_Erasables.changeCharAtIndex(index, '0');

        return this;
    }

    @Override public LCLList<OBJECT> undoErase(OBJECT o)
    {
        if (contains(o)) m_Erasables.changeCharAtIndex(getIndexOf(o), '0');

        return this;
    }

    @Override public boolean isErased(int i) { return m_Erasables.getCharAt(i) == '1'; }
    @Override public boolean isErased(OBJECT o) { return m_Erasables.getCharAt(getIndexOf(o)) == '1'; }

    @Override public LCLList<OBJECT> clean()
    {
        Array<OBJECT> cleaned_m_Array = new Array<>();
        LCLString cleaned_m_Erasables = new LCLString("");
        for (int i = 0; i < m_Array.getLength(); i++)
        {
            if (m_Erasables.getCharAt(i) == '0')
            {
                cleaned_m_Array.add(m_Array.get(i));
                cleaned_m_Erasables.append('0');
            }
        }
        m_Erasables = cleaned_m_Erasables;
        m_Array = cleaned_m_Array;
        return this;
    }



    @Override public int getSize() { return m_Array.size; }
    @Override public boolean isEmpty() { return m_Array.size == 0; }
    @Override public OBJECT get(int index) { return m_Array.get(index); }
    @Override public int getIndexOf(OBJECT o) { return m_Array.indexOf(o, false); }
    @Override public boolean contains(OBJECT object) { return m_Array.contains(object, false); }

    @Override public OBJECT[] asArray() { return m_Array.toArray(); }

    @Override public Iterator<OBJECT> iterator() { return m_Array.iterator(); }

    @Override public LCLList getClone()
    {
        LCLList array = new LCLList<OBJECT>();
        for (OBJECT o : this) array.add(o);

        //TODO:QUESTION -> Should the clone also contain
        for (int i = 0; i < m_Erasables.getLength(); i++) if (m_Erasables.getCharAt(i) == '1') array.erase(i);

        return array;
    }
}
