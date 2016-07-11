package com.sasluca.lcl.ui.material_design.floatingelements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.ninepatch.LCLNinepatchSprite;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.ui.material_design.button.UICheckBox;

/**
 * Created by Sas Luca on 01-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class UICard extends UIFloatingElement<UICard> implements ISizeable<UICard>
{
    static { LCLTween.addClass(UICard.class); }

    private int p_FloatLevel;
    private int m_RoundCornerLevel;
    private LCLNinepatchSprite m_Sprite;

    private final static int PADDING = 50;

    public UICard(int floatLevel)
    {
        m_RoundCornerLevel = 0;
        p_FloatLevel = floatLevel > 6 || floatLevel < 0 ? 6 : floatLevel;
        m_Sprite = new LCLNinepatchSprite("CARD_RC_" + m_RoundCornerLevel + "_FLOAT_" + floatLevel + "_LCL", 200, 200, Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_Sprite.setPosX(-PADDING).setPosY(-PADDING);
    }

    //<editor-fold desc="Float level">
    public UICard setFloatLevel(int floatLevel)
    {
        p_FloatLevel = floatLevel > 6 || floatLevel < 0 ? 6 : floatLevel;
        m_Sprite.setNinePatch("card_roundcornerlevel" + m_RoundCornerLevel + "_floatlevel_" + p_FloatLevel + "_lcl");

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Rendering">
    @Override protected void renderImpl() { m_Sprite.render(); }
    //</editor-fold>

    @Override protected void updateImpl() { }

    //<editor-fold desc="Transform">
    @Override public float getX() { return m_Sprite.getX() + PADDING; }
    @Override public float getY() { return m_Sprite.getY() + PADDING; }
    @Override public float getWidth() { return m_Sprite.getWidth() - PADDING * 2; }
    @Override public float getHeight() { return m_Sprite.getHeight() - PADDING * 2; }
    @Override public UICard setPosX(float newX) { m_Sprite.setPosX(newX - PADDING); return this; }
    @Override public UICard setPosY(float newY) { m_Sprite.setPosY(newY - PADDING); return this; }
    //</editor-fold>

    //<editor-fold desc="Size">
    @Override public UICard setWidth(float newWidth) { m_Sprite.setWidth(newWidth + PADDING * 2 < 200 ? 200 : newWidth + PADDING * 2); return this; }
    @Override public UICard setHeight(float newHeight) { m_Sprite.setHeight(newHeight + PADDING * 2 < 200 ? 200 : newHeight + PADDING * 2); return this; }
    @Override public UICard setSize(float newWidth, float newHeight) { setWidth(newWidth).setHeight(newHeight); return this; }
    //</editor-fold>

    //<editor-fold desc="Color">
    @Override public Color getColor() { return m_Sprite.getColor(); }
    @Override public UICard setColor(Color newColor) { m_Sprite.setColor(newColor); return this; }
    @Override public UICard setAlpha(float newAlpha) { m_Sprite.setAlpha(newAlpha); return this; }
    //</editor-fold>
}
