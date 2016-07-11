package com.sasluca.lcl.input;

import com.sasluca.lcl.abstractions.ITransformable;

/**
 * Created by Sas Luca on 02-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLHitbox
{
    //<editor-fold desc="Vars">
    private float m_TopPadding;
    private float m_LeftPadding;
    private float m_RightPadding;
    private float m_BottomPadding;
    private ITransformable m_Object;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public LCLHitbox(ITransformable object, float topPadding, float bottomPadding, float leftPadding, float rightPadding)
    {
        m_Object = object;
        m_TopPadding = topPadding;
        m_LeftPadding = leftPadding;
        m_RightPadding = rightPadding;
        m_BottomPadding = bottomPadding;
    }

    public LCLHitbox(ITransformable object) { this(object, 0, 0, 0, 0); }
    //</editor-fold>

    //<editor-fold desc="Utils">
    public boolean isPointInside(float x, float y) { return (x >= m_Object.getX() - m_LeftPadding && x <= m_Object.getX() + m_Object.getWidth() + m_RightPadding && y >= m_Object.getY() - m_BottomPadding && y <= m_Object.getY() + m_Object.getHeight() + m_TopPadding); }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setObject(ITransformable object) { m_Object = object; }
    public void setTopPadding(float topPadding) { m_TopPadding = topPadding; }
    public void setLeftPadding(float leftPadding) { m_LeftPadding = leftPadding; }
    public void setRightPadding(float rightPadding) { m_RightPadding = rightPadding; }
    public void setBottomPadding(float bottomPadding) { m_BottomPadding = bottomPadding; }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public ITransformable getObject() { return m_Object; }
    public float getTopPadding() { return m_TopPadding; }
    public float getLeftPadding() { return m_LeftPadding; }
    public float getRightPadding() { return m_RightPadding; }
    public float getBottomPadding() { return m_BottomPadding; }
    //</editor-fold>
}
