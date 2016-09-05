package com.sasluca.lcl.input;

import com.sasluca.lcl.abstractions.ITransformable;

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
    public LCLHitbox setObject(ITransformable object) { m_Object = object; return this; }
    public LCLHitbox setTopPadding(float topPadding) { m_TopPadding = topPadding; return this; }
    public LCLHitbox setLeftPadding(float leftPadding) { m_LeftPadding = leftPadding; return this; }
    public LCLHitbox setRightPadding(float rightPadding) { m_RightPadding = rightPadding; return this; }
    public LCLHitbox setBottomPadding(float bottomPadding) { m_BottomPadding = bottomPadding; return this; }
    public LCLHitbox setVerticalPadding(float vpadding) { m_LeftPadding = m_RightPadding = vpadding / 2; return this; }
    public LCLHitbox setHorizontalPadding(float hpadding) { m_TopPadding = m_BottomPadding = hpadding / 2; return this; }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public ITransformable getObject() { return m_Object; }
    public float getTopPadding() { return m_TopPadding; }
    public float getLeftPadding() { return m_LeftPadding; }
    public float getRightPadding() { return m_RightPadding; }
    public float getBottomPadding() { return m_BottomPadding; }
    //</editor-fold>
}
