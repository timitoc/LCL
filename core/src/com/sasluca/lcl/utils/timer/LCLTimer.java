package com.sasluca.lcl.utils.timer;

import com.sasluca.lcl.LCL;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLTimer
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

    public void update()
    {
        m_ElapsedTime += LCL.SYS.Delta;

        if(m_ElapsedTime >= m_Duration)
        {
            m_ElapsedTime = m_Duration;
            m_UpdateState = false;
            m_Ended = true;
        }
    }

    public void setDuration(float duration)
    {
        m_ElapsedTime = 0;
        m_Duration = duration;
        m_Ended = false;
    }

    public boolean ended() { return m_Ended; }
    public float getDuration() { return m_Duration; }
    public float getElapsedTime() { return m_ElapsedTime; }
    public boolean getUpdateState() { return m_UpdateState; }
    public void setUpdateState(boolean updateState) { m_UpdateState = updateState; }
}
