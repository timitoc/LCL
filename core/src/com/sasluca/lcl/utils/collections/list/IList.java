package com.sasluca.lcl.utils.collections.list;

import com.sasluca.lcl.abstractions.IClonable;

public interface IList<OBJECT> extends Iterable<OBJECT>, IClonable<IList>
{
    IList<OBJECT> add(OBJECT o);
    IList<OBJECT> remove(int index);
    IList<OBJECT> remove(OBJECT object);
    IList<OBJECT> clear();
    IList<OBJECT> erase(int index);
    IList<OBJECT> erase(OBJECT o);
    IList<OBJECT> undoErase(int index);
    IList<OBJECT> undoErase(OBJECT o);
    IList<OBJECT> clean();

    int getSize();
    boolean isEmpty();
    OBJECT[] asArray();
    OBJECT get(int index);
    boolean isErased(int i);
    int getIndexOf(OBJECT o);
    boolean isErased(OBJECT o);
    boolean contains(OBJECT object);
}
