package com.sasluca.lcl.ui.view;

import com.sasluca.lcl.abstractions.IMasking;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.mask.LCLMask;

import static com.sasluca.lcl.utils.LCLUtils.centerToDrawable;

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

public class UIContainer extends UIView<UIContainer> implements IMasking<UIContainer>, ISizeable<UIContainer>
{
    static { LCLTween.addClass(UIContainer.class); }

    private LCLMask m_Mask;
    private UIView m_Object;

    public UIContainer(float width, float height, boolean maskingStrategy)
    {
        m_Mask = new LCLMask(0, 0, width, height);
        m_Mask.setMasking(maskingStrategy);
    }

    public UIContainer(UIView object, float width, float height, boolean maskingStrategy)
    {
        m_Mask = new LCLMask(0, 0, width, height);
        m_Mask.setMasking(maskingStrategy);

        m_Object = object;
        centerToDrawable(m_Object, this, true, true);
    }

    @Override protected void renderImpl()
    {
        if((m_Object.getWidth() > m_Mask.getWidth() || m_Object.getHeight() > m_Mask.getHeight()) && m_Mask.isMaskingEnabled())
        m_Mask.start();

        if(m_Object != null) centerToDrawable(m_Object, this, true, true);
        if(m_Object != null) m_Object.render();

        m_Mask.end();
    }

    @Override protected void updateImpl() { }
    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }

    @Override public UIContainer setWidth(float newWidth) { m_Mask.setWidth(newWidth); return this; }
    @Override public UIContainer setHeight(float newHeight) { m_Mask.setHeight(newHeight); return this; }
    @Override public UIContainer setSize(float newWidth, float newHeight) { m_Mask.setSize(newWidth, newHeight); return this; }
    @Override public UIContainer setPosX(float newX) { m_Mask.setPosX(newX); return this; }
    @Override public UIContainer setPosY(float newY) { m_Mask.setPosY(newY); return this; }

    @Override public boolean isMaskingEnabled() { return m_Mask.isMaskingEnabled(); }
    @Override public UIContainer setMasking(boolean masking) { m_Mask.setMasking(masking); return this; }

    public UIContainer setObject(UIView object) { m_Object = object; return this; }
    public UIView getObject() { return m_Object; }
}
