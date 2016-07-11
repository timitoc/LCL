package com.sasluca.lcl.ui.material_design.button;

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

public class UIRadioButton extends UIView<UIRadioButton> implements ISizeable<UIRadioButton>
{
    static { LCLTween.addClass(UIRadioButton.class); }

    private boolean m_Checked;
    private LCLSprite m_Sprite;
    private Color m_CheckedColor;
    private Color m_UncheckedColor;
    private LCLRadioButtonGroup m_Group;

    public UIRadioButton(Color checkedColor, Color uncheckedColor)
    {
        m_CheckedColor = new Color(checkedColor);
        m_UncheckedColor = new Color(uncheckedColor);
        m_Sprite = new LCLSprite(SYS.ResourceManger.<Texture>getResource("radio_button_unchecked"));
        m_Sprite.setColor(m_UncheckedColor);
    }

    @Override protected void renderImpl() { m_Sprite.render(); }
    @Override protected void updateImpl() { }

    public UIRadioButton subscribeToGroup(LCLRadioButtonGroup group)
    {
        if(m_Group != null) m_Group.unsubscribe(this);

        m_Group = group;
        m_Group.subscribe(this);

        return this;
    }

    public boolean isChecked() { return m_Checked; }

    public UIRadioButton check()
    {
        if(m_Group != null) m_Group.check(this);
        m_Checked = true;
        m_Sprite.setTexture(SYS.ResourceManger.getResource("radio_button_checked"));
        m_Sprite.setColor(m_CheckedColor);

        return this;
    }

    public UIRadioButton uncheck()
    {
        m_Checked = false;
        m_Sprite.setTexture(SYS.ResourceManger.getResource("radio_button_unchecked"));
        m_Sprite.setColor(m_UncheckedColor);

        return this;
    }

    @Override public UIRadioButton focus()
    {
        super.focus();

        check();

        return this;
    }

    //Transform
    @Override public float getX() { return m_Sprite.getX(); }
    @Override public float getY() { return m_Sprite.getY(); }
    @Override public float getWidth() { return m_Sprite.getWidth(); }
    @Override public float getHeight() { return m_Sprite.getHeight(); }
    @Override public UIRadioButton setPosX(float newX) { m_Sprite.setPosX(newX); return this; }
    @Override public UIRadioButton setPosY(float newY) { m_Sprite.setPosY(newY); return this; }

    //Size
    @Override public UIRadioButton setWidth(float newWidth) { m_Sprite.setWidth(newWidth); return this; }
    @Override public UIRadioButton setHeight(float newHeight) { m_Sprite.setHeight(newHeight); return this; }
    @Override public UIRadioButton setSize(float newWidth, float newHeight) { m_Sprite.setSize(newWidth, newHeight); return this; }

    //Color
    public Color getCheckedColor() { return m_CheckedColor; }
    public Color getUncheckedColor() { return m_UncheckedColor; }
    public UIRadioButton setCheckedColor(Color color) { m_CheckedColor.set(color); return this; }
    public UIRadioButton setUncheckedColor(Color color) { m_UncheckedColor.set(color); return this; }
}
