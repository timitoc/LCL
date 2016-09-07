package com.sasluca.lcl.sandbox;

import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.utils.collections.list.LCLList;

public class TestHandler5 implements IStateHandler<Playground.State>
{

    @Override public void onState(Playground.State currentState)
    {

    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        //How to solve concurrent modification
        LCLList<Integer> array = new LCLList<>();

        array.add(1)
             .add(2)
             .add(3)
             .add(4);

        for (Integer i : array) { array.erase(i); }

        System.out.println(array.isErased(1));

        array.clean();

        System.out.println(array.getSize());
    }
}
