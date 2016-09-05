package com.sasluca.lcl.utils.tuples;

import com.google.gson.Gson;
import com.sasluca.lcl.utils.collections.LCLArray;

import java.io.Serializable;
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

/**
 * Abstract class describing the basic methods of a tuple, also holds the static array used to generate iterators and implements Iterable and Serializable
 */
public abstract class LCLTuple implements Iterable, Serializable
{
    /** Used to generate iterators */
    private static final LCLArray ARRAY = new LCLArray();

    /** @return Json representing the values inside the tuple */
    public String toJson() { return new Gson().toJson(this); }
    /** @return Json representing the values inside the tuple with different identifier */
    public String toJson(String identifier) { return new Gson().toJson(this).replace("m_Value", identifier); }

    /** Returns number of values inside tuple*/
    public abstract int getSize();

    /** Returns a {@link LCLArray} which contains all the elements in the tuple*/
    public LCLArray asList()
    {
        LCLArray arrayList = new LCLArray();

        int i = 0;
        while(++i <= getSize()) arrayList.add(get(i));

        return arrayList;
    }

    public boolean contains(Object o)
    {
        boolean b = false;

        int i = 0;
        while(++i <= getSize()) if(get(i).equals(o)) { b = true; break; }

        return b;
    }

    /**
     * Returns the value with the specified index where {@link #get(int) get(1)} returns the first value in the tuple
     * @param i Index of the value
     * @param <O> You can use this generic to cast the object
     * @return The specified value
     */
    public <O> O get(int i) { return get(i, false); }

    /**
     * Returns the value with the specified index
     * @param i Index of the value
     * @param startingFromZero If true value indexes start at 0 instead of 1
     * @param <O> You can use this generic to cast the object
     * @return The specified value
     */
    public abstract <O> O get(int i, boolean startingFromZero);

    /** @return An array with the values inside the tuple */
    public abstract Object[] asArray();

    /**
     * Nested for loops not yet supported due to performance.
     * I do not want to create a new list for every tuple's iterator, that is why I use a single static one.
     * Maybe I am going to have a static array pool so new arrays are created only when they are needed so that
     * someone with a nested for loops will have more ArrayLists but someone who does not use nested for loops on tuples will not have them.
     * Suggestion on this issue will be appreciated
     */
    @Override public Iterator iterator()
    {
        ARRAY.clear();

        int i = 1;
        while(i <= getSize()) ARRAY.add(get(i));

        return ARRAY.iterator();
    }

    // Static factory method for tuples

    /** Static factory method returning a {@link LCLPair pair} */
    public static <A, B> LCLPair<A, B> pair(A value1, B value2) { return new LCLPair<A, B>(value1, value2); }

    /** Static factory method returning a {@link LCLTriplet triplet} */
    public static <A, B, C> LCLTriplet<A, B, C> triplet(A value1, B value2, C value3) { return new LCLTriplet<A, B, C>(value1, value2, value3); }

    /** Static factory method returning a {@link LCLQuartet quartet} */
    public static <A, B, C, D> LCLQuartet<A, B, C, D> quartet(A value1, B value2, C value3, D value4) { return new LCLQuartet<A, B, C, D>(value1, value2, value3, value4); }

    /** Static factory method returning a {@link LCLQuintet quintet} */
    public static <A, B, C, D, E> LCLQuintet<A, B, C, D, E> quintet(A value1, B value2, C value3, D value4, E value5) { return new LCLQuintet<A, B, C, D, E>(value1, value2, value3, value4, value5); }

    /** Static factory method returning a {@link LCLSextet sextet} */
    public static <A, B, C, D, E, F> LCLSextet<A, B, C, D, E, F> sextet(A value1, B value2, C value3, D value4, E value5, F value6) { return new LCLSextet<A, B, C, D, E, F>(value1, value2, value3, value4, value5, value6); }

    /** Static factory method returning a {@link LCLSeptet septet} */
    public static <A, B, C, D, E, F, G> LCLSeptet<A, B, C, D, E, F, G> septet(A value1, B value2, C value3, D value4, E value5, F value6, G value7) { return new LCLSeptet<A, B, C, D, E, F, G>(value1, value2, value3, value4, value5, value6, value7); }

    /** Static factory method returning a {@link LCLOctet octet} */
    public static <A, B, C, D, E, F, G, H> LCLOctet<A, B, C, D, E, F, G, H> octet(A value1, B value2, C value3, D value4, E value5, F value6, G value7, H value8) { return new LCLOctet<A, B, C, D, E, F, G, H>(value1, value2, value3, value4, value5, value6, value7, value8); }

    /** Static factory method returning a {@link LCLEnnead ennead} */
    public static <A, B, C, D, E, F, G, H, I> LCLEnnead<A, B, C, D, E, F, G, H, I> ennead(A value1, B value2, C value3, D value4, E value5, F value6, G value7, H value8, I value9) { return new LCLEnnead<A, B, C, D, E, F, G, H, I>(value1, value2, value3, value4, value5, value6, value7, value8, value9); }

    /** Static factory method returning a {@link LCLDecade decade} */
    public static <A, B, C, D, E, F, G, H, I, J> LCLDecade<A, B, C, D, E, F, G, H, I, J> decade(A value1, B value2, C value3, D value4, E value5, F value6, G value7, H value8, I value9, J value10) { return new LCLDecade<A, B, C, D, E, F, G, H, I, J>(value1, value2, value3, value4, value5, value6, value7, value8, value9, value10); }

    /** Static factory method returning a tuple containing all the values given in the parameters */
    public static LCLTuple tuple(Object... values)
    {
        if(values.length == 2)  return pair    (values[0], values[1]);
        if(values.length == 3)  return triplet (values[0], values[1], values[2]);
        if(values.length == 4)  return quartet (values[0], values[1], values[2], values[3]);
        if(values.length == 5)  return quintet (values[0], values[1], values[2], values[3], values[4]);
        if(values.length == 6)  return sextet  (values[0], values[1], values[2], values[3], values[4], values[5]);
        if(values.length == 7)  return septet  (values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
        if(values.length == 8)  return octet   (values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]);
        if(values.length == 9)  return ennead  (values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8]);
        if(values.length == 10) return decade  (values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);

        //TODO: ERROR

        return null;
    }

    /** Static factory method returning a tuple containing all the values given in the array parameter */
    public static LCLTuple tupleFromArray(Object[] values)
    {
        if(values.length == 2)  return pair    (values[0], values[1]);
        if(values.length == 3)  return triplet (values[0], values[1], values[2]);
        if(values.length == 4)  return quartet (values[0], values[1], values[2], values[3]);
        if(values.length == 5)  return quintet (values[0], values[1], values[2], values[3], values[4]);
        if(values.length == 6)  return sextet  (values[0], values[1], values[2], values[3], values[4], values[5]);
        if(values.length == 7)  return septet  (values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
        if(values.length == 8)  return octet   (values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]);
        if(values.length == 9)  return ennead  (values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8]);
        if(values.length == 10) return decade  (values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);

        //TODO: ERROR

        return null;
    }

    /** Static factory method returning a tuple containing all the values given in the list parameter*/
    public static LCLTuple tuple(LCLArray values)
    {
        if(values.getSize() == 2)  return pair    (values.get(0), values.get(1));
        if(values.getSize() == 3)  return triplet (values.get(0), values.get(1), values.get(2));
        if(values.getSize() == 4)  return quartet (values.get(0), values.get(1), values.get(2), values.get(3));
        if(values.getSize() == 5)  return quintet (values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
        if(values.getSize() == 6)  return sextet  (values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5));
        if(values.getSize() == 7)  return septet  (values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6));
        if(values.getSize() == 8)  return octet   (values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6), values.get(7));
        if(values.getSize() == 9)  return ennead  (values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6), values.get(7), values.get(8));
        if(values.getSize() == 10) return decade  (values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6), values.get(7), values.get(8), values.get(9));

        //TODO: ERROR

        return null;
    }
}
