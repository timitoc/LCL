package com.sasluca.lcl.ui.button;

import com.sasluca.lcl.abstractions.ICheckable;
import com.sasluca.lcl.utils.collections.list.LCLList;

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

public class LCLRadioButtonGroup
{
    private ICheckable m_CurrentChecked;
    private LCLList<ICheckable> m_Buttons;

    public LCLRadioButtonGroup()
    {
        m_Buttons = new LCLList<>();
    }

    public LCLRadioButtonGroup unsubscribe(ICheckable btn)
    {
        m_Buttons.remove(btn);
        return this;
    }

    public LCLRadioButtonGroup subscribe(ICheckable btn)
    {
        if(!m_Buttons.contains(btn))
        {
            m_Buttons.add(btn);
            btn.uncheck();
        }

        return this;
    }

    public LCLRadioButtonGroup check(ICheckable sender)
    {
        if(!m_Buttons.contains(sender)) return this;

        m_CurrentChecked = sender;
        for(ICheckable btn : m_Buttons) if(!btn.equals(sender)) btn.uncheck();

        return this;
    }

    public ICheckable getCurrentChecked() { return m_CurrentChecked; }
}
