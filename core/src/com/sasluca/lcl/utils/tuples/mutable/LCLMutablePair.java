package com.sasluca.lcl.utils.tuples.mutable;

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

import com.sasluca.lcl.utils.tuples.imutable.LCLPair;
import com.sasluca.lcl.utils.tuples.LCLTuple;

/**
 * A mutable pair is a {@link LCLTuple tuple} that holds two values which can be changed
 * @param <A> The class of the first value
 * @param <B> The class of the second value
 */
public class LCLMutablePair<A, B> extends LCLTuple
{
    /** Object is serializable */
    private static final long serialVersionUID = -1244615202378420828L;

    /** First value of type {@link A}, variable is final since the object is immutable */
    private A m_Value1;

    /** Second value of type {@link B}, variable is final since the object is immutable */
    private B m_Value2;

    /** Default constructor, requires that you give it the values */
    public LCLMutablePair(A value1, B value2)
    {
        m_Value1 = value1;
        m_Value2 = value2;
    }

    public final <O extends A> void set1(O o) { m_Value1 = o; }

    public final <O extends B> void set2(O o) { m_Value2 = o; }

    /**
     * @param <O> Extends {@link A}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value1 first value}
     */
    public final <O extends A> O get1()  { return (O) m_Value1;  }

    /**
     * @param <O> Extends {@link B}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value2 second value}
     */
    public final <O extends B> O get2()  { return (O) m_Value2;  }

    @Override public <O> O get(int i, boolean startFromZero)
    {
        if(startFromZero)
            switch (i)
            {
                case 0: return (O) m_Value1;
                case 1: return (O) m_Value2;
            }
        else
            switch (i)
            {
                case 1: return (O) m_Value1;
                case 2: return (O) m_Value2;
            }

        return null;
    }

    @Override public final int getSize() { return 2; }
    @Override public Object[] asArray() { return new Object[]{m_Value1, m_Value2}; }

    @Override public boolean equals(Object pair)
    {
        if(!(pair instanceof LCLPair)) return false;

        return ((LCLPair) pair).get1().equals(m_Value1) && ((LCLPair) pair).get2().equals(m_Value2);
    }
}
