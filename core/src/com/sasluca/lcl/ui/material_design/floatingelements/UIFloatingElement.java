package com.sasluca.lcl.ui.material_design.floatingelements;

import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.ui.material_design.group.UIGroup;
import com.sasluca.lcl.ui.material_design.view.UIView;
import com.sasluca.lcl.utils.group.LCLGroup;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class UIFloatingElement<This> extends UIView<This> implements IColorable<This>
{
    protected LCLGroup p_Group;
    protected int p_FloatLevel;

    public final float Padding;
    public final int FloatLevels;
    
    protected UIFloatingElement(float padding, int floatLevels)
    {
        Padding = padding;
        FloatLevels = floatLevels;
        p_Group = new LCLGroup();
    }

    protected abstract void setFloatLevelImpl(int newFloatLevel);

    public This setFloatLevel(int newFloatLevel)
    {
        if(newFloatLevel > FloatLevels) return ((This)this);

        setFloatLevelImpl(newFloatLevel);

        return ((This)this);
    }
    
    @Override public float getX() { return p_Group.getX() + Padding; }
    @Override public float getY() { return p_Group.getY() + Padding; }
    @Override public float getWidth() { return p_Group.getWidth() - Padding * 2; }
    @Override public float getHeight() { return p_Group.getHeight() - Padding * 2; }
    @Override public This setPosX(float newX) { p_Group.setPosX(newX - Padding); return ((This)this); }
    @Override public This setPosY(float newY) { p_Group.setPosY(newY - Padding); return ((This)this); }

    public final int getFloatLevel() { return p_FloatLevel; }
}
