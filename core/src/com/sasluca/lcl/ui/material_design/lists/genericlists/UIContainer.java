package com.sasluca.lcl.ui.material_design.lists.genericlists;

import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.UIView;
import static com.sasluca.lcl.utils.LCLUtils.centerToDrawable;

/**
 * Created by Sas Luca on 11-Jul-16.
 */

public class UIContainer extends UIView<UIContainer>
{
    private float m_X;
    private float m_Y;
    private float m_Width;
    private float m_Height;
    private boolean m_MasskingStrategy;
    private UIView m_Object;

    public UIContainer(UIView object, float width, float height, boolean maskingStrategy)
    {
        m_Width = width;
        m_Height = height;
        m_Object = object;
        m_MasskingStrategy = maskingStrategy;
        centerToDrawable(m_Object, this, true, true);
    }

    @Override protected void renderImpl()
    {
        if((m_Object.getWidth() > m_Width || m_Object.getHeight() > m_Height) && m_MasskingStrategy)
        LCLMask.startMask(m_X, m_Y, m_Width, m_Height);

        m_Object.render();

        if((m_Object.getWidth() > m_Width || m_Object.getHeight() > m_Height) && m_MasskingStrategy)
        LCLMask.endMask();

    }

    @Override protected void updateImpl() { }
    @Override public float getX() { return m_X; }
    @Override public float getY() { return m_Y; }
    @Override public float getWidth() { return m_Width; }
    @Override public float getHeight() { return m_Height; }
    @Override public UIContainer setPosX(float newX) { m_X = newX; centerToDrawable(m_Object, this, true, true); return this; }
    @Override public UIContainer setPosY(float newY) { m_Y = newY; centerToDrawable(m_Object, this, true, true); return this; }

    public boolean getMaskingStrategy() { return m_MasskingStrategy; }
    public UIContainer setMaskingStrategy(boolean mask) { m_MasskingStrategy = mask; return this; }

    public UIView getObject() { return m_Object; }
}
