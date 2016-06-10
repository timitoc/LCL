package com.sasluca.lcl.applogic;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

/**
 * A state handler is called each update by it's {@link LCLStateManager}.
 * The handler reacts to changes in state and does a specific job depending on the current state.
 * @param <State> The type of state used.
 */
public interface LCLStateHandler<State>
{
    /**
     * This method is called each frame.
     * @param currentState The current state.
     */
    void onState(State currentState);

    /**
     * This method is called when the current state of the manager is changed.
     * @param currentState The current state.
     * @param newState The new state.
     */
    void onChangeState(State currentState, State newState);
}
