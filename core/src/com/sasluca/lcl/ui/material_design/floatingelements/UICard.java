package com.sasluca.lcl.ui.material_design.floatingelements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.ninepatch.LCLNinepatchSprite;

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

    public static final int PADDING = 50;
    public static final int FLOAT_LEVEL = 6;

    public UICard(int floatLevel)
    {
        super(50, 6);
        m_RoundCornerLevel = 0;
        p_FloatLevel = floatLevel > 6 || floatLevel < 0 ? 6 : floatLevel;
        m_Sprite = new LCLNinepatchSprite("card_roundcornerlevel_" + m_RoundCornerLevel + "_floatlevel_" + floatLevel + "_lcl", 200, 200, Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_Sprite.setPosX(-PADDING).setPosY(-PADDING);
        p_Group.addObject(m_Sprite);
    }

    //<editor-fold desc="Float level">
    public void setFloatLevelImpl(int floatLevel)
    {
        p_FloatLevel = floatLevel > 6 || floatLevel < 0 ? 6 : floatLevel;
        m_Sprite.setNinePatch("card_roundcornerlevel" + m_RoundCornerLevel + "_floatlevel_" + p_FloatLevel + "_lcl");
    }
    //</editor-fold>

    //<editor-fold desc="Rendering">
    @Override protected void renderImpl() { m_Sprite.render(); }
    //</editor-fold>

    @Override protected void updateImpl() { }

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
