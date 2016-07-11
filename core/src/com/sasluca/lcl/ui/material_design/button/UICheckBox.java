package com.sasluca.lcl.ui.material_design.button;

/**
 * Created by Sas Luca on 06-Jul-16.
 * Copyright (C) 2016 - LCL
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.ui.UIView;

import static com.sasluca.lcl.LCL.SYS;

/**
 * Created by Sas Luca on 06-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class UICheckBox extends UIView<UICheckBox> implements ISizeable<UICheckBox>
{
    static { LCLTween.addClass(UICheckBox.class); }

    private boolean m_Checked;
    private LCLSprite m_Sprite;
    private Color m_CheckedColor;
    private Color m_UncheckedColor;

    public UICheckBox(Color checkedColor, Color uncheckedColor)
    {
        m_CheckedColor = new Color(checkedColor);
        m_UncheckedColor = new Color(uncheckedColor);
        m_Sprite = new LCLSprite(SYS.ResourceManger.<Texture>getResource("check_box_unchecked"));
        m_Sprite.setColor(m_UncheckedColor);
    }

    @Override protected void renderImpl() { m_Sprite.render(); }
    @Override protected void updateImpl() { }

    public boolean isChecked() { return m_Checked; }

    public UICheckBox check()
    {
        m_Checked = true;
        m_Sprite.setTexture(SYS.ResourceManger.getResource("check_box_checked"));
        m_Sprite.setColor(m_CheckedColor);

        return this;
    }

    public UICheckBox uncheck()
    {
        m_Checked = false;
        m_Sprite.setTexture(SYS.ResourceManger.getResource("check_box_unchecked"));
        m_Sprite.setColor(m_UncheckedColor);

        return this;
    }

    @Override public UICheckBox focus()
    {
        super.focus();

        if(m_Checked) uncheck(); else check();

        return this;
    }

    //Transform
    @Override public float getX() { return m_Sprite.getX(); }
    @Override public float getY() { return m_Sprite.getY(); }
    @Override public float getWidth() { return m_Sprite.getWidth(); }
    @Override public float getHeight() { return m_Sprite.getHeight(); }
    @Override public UICheckBox setPosX(float newX) { m_Sprite.setPosX(newX); return this; }
    @Override public UICheckBox setPosY(float newY) { m_Sprite.setPosY(newY); return this; }

    //Size
    @Override public UICheckBox setWidth(float newWidth) { m_Sprite.setWidth(newWidth); return this; }
    @Override public UICheckBox setHeight(float newHeight) { m_Sprite.setHeight(newHeight); return this; }
    @Override public UICheckBox setSize(float newWidth, float newHeight) { m_Sprite.setSize(newWidth, newHeight); return this; }

    //Color
    public Color getCheckedColor() { return m_CheckedColor; }
    public Color getUncheckedColor() { return m_UncheckedColor; }
    public UICheckBox setCheckedColor(Color color) { m_CheckedColor.set(color); return this; }
    public UICheckBox setUncheckedColor(Color color) { m_UncheckedColor.set(color); return this; }
}