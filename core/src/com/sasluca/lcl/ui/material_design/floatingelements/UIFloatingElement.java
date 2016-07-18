package com.sasluca.lcl.ui.material_design.floatingelements;

import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.ui.material_design.UIView;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class UIFloatingElement<This> extends UIView<This> implements IColorable<This>
{
    protected int p_FloatLevel;

    public static final int FLOAT_LEVEL = 6;

    public abstract This setFloatLevel(int newFloatLevel);

    public final int getFloatLevel() { return p_FloatLevel; }
}
