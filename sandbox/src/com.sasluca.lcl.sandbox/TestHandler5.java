package com.sasluca.lcl.sandbox;

import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.utils.tuples.LCLPair;
import com.sasluca.lcl.utils.tuples.LCLTuple;

/**
 * Created by Sas Luca on 8/20/2016.
 */

public class TestHandler5 implements IStateHandler<Playground.State>
{

    @Override public void onState(Playground.State currentState)
    {

    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        System.out.println(tupleExample().toJson());
    }

    public LCLPair<String, Float> tupleExample()
    {
        return (LCLPair<String, Float>) LCLTuple.tuple("Test", 0);
    }
}
