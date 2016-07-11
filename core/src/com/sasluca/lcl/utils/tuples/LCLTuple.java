package com.sasluca.lcl.utils.tuples;

/**
 * Created by Sas Luca on 10-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLTuple<A, B>
{
    private A m_Item1;
    private B m_Item2;
    
    private LCLTuple() { }

    private LCLTuple(A value1, B value2) { m_Item1 = value1; m_Item2 = value2; }
    
    public static <A, B> LCLTuple tuple(A value1, B value2) { return new LCLTuple<A, B>(value1, value2); }
    
    public A getItem1() { return m_Item1; }
    public B getItem2() { return m_Item2; }
}
