package com.sasluca.lcl.applogic.managers.statemanager;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

import com.sasluca.lcl.applogic.managers.LCLManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a list of {@link IStateHandler} and a state of type {@link State}.
 * Each time {@link #manage()} is called it alerts all the handlers in it's {@link #m_States list} about the current state.
 * @param <State>
 */
public class LCLStateManager<State> extends LCLManager<IStateHandler<State>>
{
    private final List<State> m_States;
    private State m_CurrentState;
    private State m_NewState;

    public LCLStateManager()
    {
        m_States = new ArrayList<>();
        p_Handlers = new ArrayList<>();
    }

    /**
     * Use this method to add a state. If the state already exists in the {@link #m_States list} it won't be added.
     * @param state The new state.
     */
    public final void addState(State newState) { m_States.add(newState); }

    /**
     * Use this method to change the state. The {@link #m_CurrentState current state} won't be update until the next time {@link #manageState()} is called.
     * @param newState The new state. If the state does not exist in the {@link #m_States list} the state won't be changed.
     */
    public final void changeState(State newState) { if(m_States.contains(newState) && m_CurrentState != newState) m_NewState = newState; }

    /**
     * When this method is called it will call the all of it's handlers with the current state.
     */
    @Override protected final void manage()
    {
        //Check if state changed
        if(m_NewState != m_CurrentState)
        {
            for(IStateHandler<State> handler : p_Handlers) handler.onChangeState(m_CurrentState, m_NewState);
            m_CurrentState = m_NewState;
        }

        for(IStateHandler<State> handler : p_Handlers) handler.onState(m_CurrentState);
    }
}
