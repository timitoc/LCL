package com.sasluca.lcl.materialdesign.floatingelements;

import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.ui.view.UIView;
import com.sasluca.lcl.utils.group.LCLGroup;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class UIFloatingElement<THIS> extends UIView<THIS> implements IColorable<THIS>
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

    public THIS setFloatLevel(int newFloatLevel)
    {
        if(newFloatLevel > FloatLevels) return ((THIS)this);

        setFloatLevelImpl(newFloatLevel);

        return ((THIS)this);
    }
    
    @Override public float getX() { return p_Group.getX() + Padding; }
    @Override public float getY() { return p_Group.getY() + Padding; }
    @Override public float getWidth() { return p_Group.getWidth() - Padding * 2; }
    @Override public float getHeight() { return p_Group.getHeight() - Padding * 2; }
    @Override public THIS setPosX(float newX) { p_Group.setPosX(newX - Padding); return ((THIS)this); }
    @Override public THIS setPosY(float newY) { p_Group.setPosY(newY - Padding); return ((THIS)this); }

    public final int getFloatLevel() { return p_FloatLevel; }
}
