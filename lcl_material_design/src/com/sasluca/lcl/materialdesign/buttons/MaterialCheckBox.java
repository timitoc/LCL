package com.sasluca.lcl.materialdesign.buttons;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.materialdesign.LCLMaterialDesign;
import com.sasluca.lcl.ui.button.UICheckBox;
import com.sasluca.lcl.ui.design.UIDesign;
import com.sasluca.lcl.ui.image.UISprite;

/**
 * Created by Sas Luca on 8/13/2016.
 */
public class MaterialCheckBox extends UICheckBox
{
    UISprite m_Sprite;
    private Color m_CheckedColor;
    private Color m_UncheckedColor;

    public MaterialCheckBox()
    {
        m_Sprite = new UISprite("check_box_unchecked");

        m_CheckedColor = new Color(LCLMaterialDesign.Theme.getColor("primary"));
        m_UncheckedColor = new Color(LCLMaterialDesign.Theme.getColor("unfocused"));

        m_Sprite.setTexture("check_box_unchecked")
                .setSize(70, 70)
                .setColor(m_UncheckedColor);
    }

    @Override protected void onCheck()
    {
        m_Sprite.setTexture("check_box_checked");
        m_Sprite.setColor(m_CheckedColor);
    }

    @Override protected void onUncheck()
    {
        m_Sprite.setTexture("check_box_checked");
        m_Sprite.setColor(m_CheckedColor);
    }

    @Override protected void renderImpl() { m_Sprite.render(); }

    @Override protected void updateImpl() {}

    //Size
    @Override public UICheckBox setWidth(float newWidth) { m_Sprite.setWidth(newWidth); return this; }
    @Override public UICheckBox setHeight(float newHeight) { m_Sprite.setHeight(newHeight); return this; }
    @Override public UICheckBox setSize(float newWidth, float newHeight) { m_Sprite.setSize(newWidth, newHeight); return this; }

    //Color
    public Color getCheckedColor() { return m_CheckedColor; }
    public Color getUncheckedColor() { return m_UncheckedColor; }
    public UICheckBox setCheckedColor(Color color) { m_CheckedColor.set(color); return this; }
    public UICheckBox setUncheckedColor(Color color) { m_UncheckedColor.set(color); return this; }

    //Transform
    @Override public float getX() { return m_Sprite.getX(); }
    @Override public float getY() { return m_Sprite.getY(); }
    @Override public float getWidth() { return m_Sprite.getWidth(); }
    @Override public float getHeight() { return m_Sprite.getHeight(); }
    @Override public UICheckBox setPosX(float newX) { m_Sprite.setPosX(newX); return this; }
    @Override public UICheckBox setPosY(float newY) { m_Sprite.setPosY(newY); return this; }
}
