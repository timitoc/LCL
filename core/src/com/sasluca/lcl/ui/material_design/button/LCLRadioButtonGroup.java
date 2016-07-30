package com.sasluca.lcl.ui.material_design.button;

import com.sasluca.lcl.abstractions.ICheckable;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 06-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLRadioButtonGroup
{
    private LCLArray<ICheckable> m_Buttons;

    public LCLRadioButtonGroup()
    {
        m_Buttons = new LCLArray<>();
    }

    public LCLRadioButtonGroup unsubscribe(ICheckable btn) { m_Buttons.remove(btn); return this; }
    public LCLRadioButtonGroup subscribe(ICheckable btn) { if(!m_Buttons.contains(btn)) { m_Buttons.add(btn); btn.uncheck(); } return this; }

    public LCLRadioButtonGroup check(ICheckable sender) { if(m_Buttons.contains(sender)) for(ICheckable btn : m_Buttons) btn.uncheck(); return this; }
}
