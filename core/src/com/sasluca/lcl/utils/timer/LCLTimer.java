package com.sasluca.lcl.utils.timer;

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IUpdatable;

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

public class LCLTimer implements IUpdatable<LCLTimer>
{
    private boolean m_Ended;
    private float m_Duration;
    private float m_ElapsedTime;
    private boolean m_UpdateState;

    public LCLTimer(float duration)
    {
        m_Duration = duration;
        m_UpdateState = true;
    }

    @Override public void update()
    {
        m_ElapsedTime += LCL.getDelta();

        if(m_ElapsedTime >= m_Duration) end();
    }

    public LCLTimer setDuration(float duration)
    {
        m_ElapsedTime = 0;
        m_Duration = duration;
        m_Ended = false;

        return this;
    }

    public void start()
    {
        m_Ended = false;
        m_UpdateState = true;
        LCL.getAppSystem().addUpdateHandler(this);
    }

    public void end()
    {
        m_UpdateState = false;
        m_Ended = true;
        LCL.getAppSystem().removeUpdateHandler(this);
    }

    public boolean ended() { return m_Ended; }
    public float getDuration() { return m_Duration; }
    public float getElapsedTime() { return m_ElapsedTime; }
    @Override public boolean isUpdating() { return m_UpdateState; }
    @Override public LCLTimer setUpdatingState(boolean updateState) { m_UpdateState = updateState; return this; }
}
