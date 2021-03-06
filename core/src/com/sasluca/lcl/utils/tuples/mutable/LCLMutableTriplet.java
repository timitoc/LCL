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

import com.sasluca.lcl.utils.tuples.LCLTuple;

/**
 * A mutable triplet is a {@link LCLTuple tuple} that holds three values which can be changed
 * @param <A> The class of the first value
 * @param <B> The class of the second value
 * @param <C> The class of the third value
 */
public class LCLMutableTriplet<A, B, C> extends LCLTuple
{
    /** Object is serializable */
    private static final long serialVersionUID = -6198021424203877646L;

    /** First value of type {@link A}, variable is final since the object is immutable */
    private A m_Value1;

    /** Second value of type {@link B}, variable is final since the object is immutable */
    private B m_Value2;

    /** Third value of type {@link C}, variable is final since the object is immutable */
    private C m_Value3;

    /** Default constructor, requires that you give it the values */
    public LCLMutableTriplet(A value1, B value2, C value3)
    {
        m_Value1 = value1;
        m_Value2 = value2;
        m_Value3 = value3;
    }

    public final <O extends A> LCLMutableTriplet<A, B, C> set1(O value) { m_Value1 = value; return this; }
    public final <O extends B> LCLMutableTriplet<A, B, C> set2(O value) { m_Value2 = value; return this; }
    public final <O extends C> LCLMutableTriplet<A, B, C> set3(O value) { m_Value3 = value; return this; }

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

    /**
     * @param <O> Extends {@link C}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value3 third value}
     */
    public final <O extends C> O get3()  { return (O) m_Value3;  }

    @Override public <O> O get(int i, boolean startFromZero)
    {
        if (startFromZero)
            switch (i)
            {
                case 0: return (O) m_Value1;
                case 1: return (O) m_Value2;
                case 2: return (O) m_Value3;
            }
        else
            switch (i)
            {
                case 1: return (O) m_Value1;
                case 2: return (O) m_Value2;
                case 3: return (O) m_Value3;
            }

        return null;
    }

    @Override public final int getSize() { return 3; }
    @Override public Object[] asArray() { return new Object[]{m_Value1, m_Value2, m_Value3}; }
}
