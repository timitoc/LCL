package com.sasluca.lcl.materialdesign.floatingelements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.ui.image.UISprite;

/**
 * Created by Sas Luca on 7/23/2016.
 * Copyright (C) 2016 - LCL
 */

public class UIFloatingImage extends UIFloatingElement<UIFloatingImage>
{
    UISprite m_Image;

    public UIFloatingImage(Texture image, float padding)
    {
        super(padding, 0);

        m_Image = new UISprite(image);

        p_Group.addObject(m_Image);
    }

    @Override protected final void setFloatLevelImpl(int newFloatLevel) { }

    @Override public Color getColor() { return m_Image.getColor(); }
    @Override public UIFloatingImage setColor(Color newColor) { m_Image.setColor(newColor); return this; }
    @Override public UIFloatingImage setAlpha(float newAlpha) { m_Image.setAlpha(newAlpha); return this; }

    @Override protected void renderImpl() { m_Image.render(); }
    @Override protected void updateImpl() { }
}
