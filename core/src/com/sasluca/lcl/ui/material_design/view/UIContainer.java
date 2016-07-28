package com.sasluca.lcl.ui.material_design.view;

import com.sasluca.lcl.abstractions.IMasking;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.material_design.lists.modellists.scrolllists.UIModelVerticalList;

import static com.sasluca.lcl.utils.LCLUtils.centerToDrawable;

/**
 * Created by Sas Luca on 11-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class UIContainer extends UIView<UIContainer> implements IMasking<UIContainer>, ISizeable<UIContainer>
{
    static { LCLTween.addClass(UIContainer.class); }

    private LCLMask m_Mask;
    private UIView m_Object;

    public UIContainer(UIView object, float width, float height, boolean maskingStrategy)
    {
        m_Mask = new LCLMask(0, 0, width, height);
        m_Mask.setMaskingStrategy(maskingStrategy);

        m_Object = object;
        centerToDrawable(m_Object, this, true, true);
    }

    @Override protected void renderImpl()
    {
        if((m_Object.getWidth() > m_Mask.getWidth() || m_Object.getHeight() > m_Mask.getHeight()) && m_Mask.getMaskingStrategy())
        m_Mask.start();

        centerToDrawable(m_Object, this, true, true);
        m_Object.render();

        m_Mask.end();
    }

    @Override protected void updateImpl() { }
    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }

    @Override public UIContainer setWidth(float newWidth) { m_Mask.setWidth(newWidth); centerToDrawable(m_Object, this, true, true); return this; }
    @Override public UIContainer setHeight(float newHeight) { m_Mask.setHeight(newHeight); centerToDrawable(m_Object, this, true, true); return this; }
    @Override public UIContainer setSize(float newWidth, float newHeight) { setWidth(newWidth); setHeight(newHeight); return this; }
    @Override public UIContainer setPosX(float newX) { m_Mask.setPosX(newX); centerToDrawable(m_Object, this, true, true); return this; }
    @Override public UIContainer setPosY(float newY) { m_Mask.setPosY(newY); centerToDrawable(m_Object, this, true, true); return this; }

    @Override public boolean getMaskingStrategy() { return m_Mask.getMaskingStrategy(); }
    @Override public UIContainer setMaskingStrategy(boolean mask) { m_Mask.setMaskingStrategy(mask); return this; }

    public UIContainer setObject(UIView object) { m_Object = object; return this; }
    public UIView getObject() { return m_Object; }
}
