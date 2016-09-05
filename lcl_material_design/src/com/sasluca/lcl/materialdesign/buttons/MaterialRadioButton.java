package com.sasluca.lcl.materialdesign.buttons;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.materialdesign.LCLMaterialDesign;
import com.sasluca.lcl.ui.button.UIRadioButton;
import com.sasluca.lcl.ui.image.UISprite;

/**
 * Created by Sas Luca on 8/13/2016.
 */

public class MaterialRadioButton extends UIRadioButton
{
    UISprite m_Sprite;
    Color m_CheckedColor;
    Color m_UncheckedColor;
    
    public MaterialRadioButton()
    {
        m_Sprite = new UISprite("radio_button_unchecked");
        m_CheckedColor = new Color(LCLMaterialDesign.Theme.getColor("Primary"));
        m_UncheckedColor = new Color(LCLMaterialDesign.Theme.getColor("Unfocused"));
    }

    @Override protected void onCheck()
    {
        m_Sprite.setTexture("radio_button_checked")
                .setColor(m_CheckedColor);
    }

    @Override protected void onUncheck()
    {
        m_Sprite.setTexture("radio_button_unchecked")
                .setColor(m_UncheckedColor);
    }

    @Override protected void renderImpl() { m_Sprite.render(); }

    @Override protected void updateImpl()
    {

    }

    //Size
    @Override public UIRadioButton setWidth(float newWidth) { m_Sprite.setWidth(newWidth); return this; }
    @Override public UIRadioButton setHeight(float newHeight) { m_Sprite.setHeight(newHeight); return this; }
    @Override public UIRadioButton setSize(float newWidth, float newHeight) { m_Sprite.setSize(newWidth, newHeight); return this; }

    //Color
    public Color getCheckedColor() { return m_CheckedColor; }
    public Color getUncheckedColor() { return m_UncheckedColor; }
    public MaterialRadioButton setCheckedColor(Color color) { m_CheckedColor.set(color); return this; }
    public MaterialRadioButton setUncheckedColor(Color color) { m_UncheckedColor.set(color); return this; }

    //Transform
    @Override public float getX() { return m_Sprite.getX(); }
    @Override public float getY() { return m_Sprite.getY(); }
    @Override public float getWidth() { return m_Sprite.getWidth(); }
    @Override public float getHeight() { return m_Sprite.getHeight(); }
    @Override public MaterialRadioButton setPosX(float newX) { m_Sprite.setPosX(newX); return this; }
    @Override public MaterialRadioButton setPosY(float newY) { m_Sprite.setPosY(newY); return this; }
}
