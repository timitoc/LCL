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

/**
 * A state handler is called each update by it's {@link LCLStateManager}.
 * The handler reacts to changes in state and does a specific job depending on the current state.
 * @param <STATE> The type of state used.
 */
public interface IStateHandler<STATE> extends IOnStateChangeHandler<STATE>
{
    /**
     * This method is called each frame.
     * @param currentState The current state.
     */
    void onState(STATE currentState);

    /**
     * This method is called when the current state of the manager is changed.
     * @param currentState The current state.
     * @param newState The new state.
     */
    void onChangeState(STATE currentState, STATE newState);
}
