package com.sasluca.lcl.utils.tuples.imutable;

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
 * An ennead is a {@link LCLTuple tuple} that holds nine values
 * @param <A> The class of the first value
 * @param <B> The class of the second value
 * @param <C> The class of the third value
 * @param <D> The class of the fourth value
 * @param <E> The class of the fifth value
 * @param <F> The class of the sixth value
 * @param <G> The class of the seventh value
 * @param <H> The class of the eighth value
 * @param <I> The class of the ninth value
 */
public class LCLEnnead<A, B, C, D, E, F, G, H, I> extends LCLTuple
{
    /** Object is serializable */
    private static final long serialVersionUID = 6070474799085133017L;

    /** First value of type {@link A}, variable is final since the object is immutable */
    private final A m_Value1;

    /** Second value of type {@link B}, variable is final since the object is immutable */
    private final B m_Value2;

    /** Third value of type {@link C}, variable is final since the object is immutable */
    private final C m_Value3;

    /** Fourth value of type {@link D}, variable is final since the object is immutable */
    private final D m_Value4;

    /** Fifth value of type {@link E}, variable is final since the object is immutable */
    private final E m_Value5;

    /** Sixth value of type {@link F}, variable is final since the object is immutable */
    private final F m_Value6;

    /** Seventh value of type {@link G}, variable is final since the object is immutable */
    private final G m_Value7;

    /** Eighth value of type {@link H}, variable is final since the object is immutable */
    private final H m_Value8;

    /** Ninth value of type {@link I}, variable is final since the object is immutable */
    private final I m_Value9;

    /** Default constructor, requires that you give it the values */
    public LCLEnnead(A value1, B value2, C value3, D value4, E value5, F value6, G value7, H value8, I value9)
    {
        m_Value1 = value1;
        m_Value2 = value2;
        m_Value3 = value3;
        m_Value4 = value4;
        m_Value5 = value5;
        m_Value6 = value6;
        m_Value7 = value7;
        m_Value8 = value8;
        m_Value9 = value9;
    }

    /**
     * @param <O> Extends {@link A}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value1 first value}
     */
    public final <O extends A> O get1() { return (O) m_Value1; }

    /**
     * @param <O> Extends {@link B}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value2 second value}
     */
    public final <O extends B> O get2() { return (O) m_Value2; }

    /**
     * @param <O> Extends {@link C}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value3 third value}
     */
    public final <O extends C> O get3() { return (O) m_Value3; }

    /**
     * @param <O> Extends {@link D}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value4 fourth value}
     */
    public final <O extends D> O get4() { return (O) m_Value4; }

    /**
     * @param <O> Extends {@link E}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value5 fifth value}
     */
    public final <O extends E> O get5() { return (O) m_Value5; }

    /**
     * @param <O> Extends {@link F}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value6 sixth value}
     */
    public final <O extends F> O get6() { return (O) m_Value6; }

    /**
     * @param <O> Extends {@link G}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value7 seventh value}
     */
    public final <O extends G> O get7() { return (O) m_Value7; }

    /**
     * @param <O> Extends {@link H}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value8 eighth value}
     */
    public final <O extends H> O get8() { return (O) m_Value8; }

    /**
     * @param <O> Extends {@link I}, can be use to cast the object if the generics were not specified
     * @return The {@link #m_Value9 ninth value}
     */
    public final <O extends I> O get9() { return (O) m_Value9; }

    @Override public <O> O get(int i, boolean startFromZero)
    {
        if(startFromZero)
            switch (i)
            {
                case 0: return (O) m_Value1;
                case 1: return (O) m_Value2;
                case 2: return (O) m_Value3;
                case 3: return (O) m_Value4;
                case 4: return (O) m_Value5;
                case 5: return (O) m_Value6;
                case 6: return (O) m_Value7;
                case 7: return (O) m_Value8;
                case 8: return (O) m_Value9;
            }
        else
            switch (i)
            {
                case 1: return (O) m_Value1;
                case 2: return (O) m_Value2;
                case 3: return (O) m_Value3;
                case 4: return (O) m_Value4;
                case 5: return (O) m_Value5;
                case 6: return (O) m_Value6;
                case 7: return (O) m_Value7;
                case 8: return (O) m_Value8;
                case 9: return (O) m_Value9;
            }

        return null;
    }

    @Override public final int getSize() { return 9; }
    @Override public Object[] asArray() { return new Object[]{m_Value1, m_Value2, m_Value3, m_Value4, m_Value5, m_Value6, m_Value7, m_Value8, m_Value9}; }
}
