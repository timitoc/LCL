package com.sasluca.lcl.ui.material_design.button;

import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 06-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLRadioButtonGroup
{
    private LCLArray<UIRadioButton> m_Buttons;

    public LCLRadioButtonGroup()
    {
        m_Buttons = new LCLArray<>();
    }

    public LCLRadioButtonGroup unsubscribe(UIRadioButton btn) { m_Buttons.remove(btn); return this; }
    public LCLRadioButtonGroup subscribe(UIRadioButton btn) { if(!m_Buttons.contains(btn)) { m_Buttons.add(btn); btn.uncheck(); } return this; }

    public LCLRadioButtonGroup check(UIRadioButton sender) { for(UIRadioButton btn : m_Buttons) btn.uncheck(); return this; }
}
