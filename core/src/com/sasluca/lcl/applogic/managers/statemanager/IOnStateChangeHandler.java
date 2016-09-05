package com.sasluca.lcl.applogic.managers.statemanager;

public interface IOnStateChangeHandler<STATE>
{
    /**
     * THIS method is called when the current state of the manager is changed.
     * @param currentState The current state.
     * @param newState The new state.
     */
    void onChangeState(STATE currentState, STATE newState);
}
