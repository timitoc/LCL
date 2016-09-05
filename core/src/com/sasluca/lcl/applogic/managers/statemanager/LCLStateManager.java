package com.sasluca.lcl.applogic.managers.statemanager;

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

import com.badlogic.gdx.utils.Array;
import com.sasluca.lcl.applogic.managers.LCLManager;

/**
 * THIS class contains a list of {@link IStateHandler} and a state of type {@link STATE}.
 * Each time {@link #manage()} is called it alerts all the handlers in it's {@link #m_States list} about the current state.
 * @param <STATE>
 */
public class LCLStateManager<STATE> extends LCLManager<IOnStateChangeHandler<STATE>>
{
    private final Array<STATE> m_States;
    private STATE m_CurrentState;
    private STATE m_NewState;

    public LCLStateManager()
    {
        m_States = new Array<>();
        p_Handlers = new Array<>();
    }

    /**
     * Use this method to add a state. If the state already exists in the {@link #m_States list} it won't be added.
     * @param newState The new state.
     */
    public final void addState(STATE newState) { m_States.add(newState); }

    /**
     * Use this method to change the state. The {@link #m_CurrentState current state} won't be update until the next time {@link #manage()} is called.
     * @param newState The new state. If the state does not exist in the {@link #m_States list} the state won't be changed.
     */
    public final void changeState(STATE newState) { if(m_States.contains(newState, true) && m_CurrentState != newState) m_NewState = newState; }

    /**
     * When this method is called it will call the all of it's handlers with the current state.
     */
    @Override protected final void manage()
    {
        //Check if state changed
        if(m_NewState != m_CurrentState)
        {
            for(IOnStateChangeHandler<STATE> handler : p_Handlers) handler.onChangeState(m_CurrentState, m_NewState);
            m_CurrentState = m_NewState;
        }

        for(IOnStateChangeHandler<STATE> handler : p_Handlers) if(handler instanceof IStateHandler) ((IStateHandler<STATE>)handler).onState(m_CurrentState);
    }

    public final STATE getCurrentState() { return m_CurrentState; }
}
