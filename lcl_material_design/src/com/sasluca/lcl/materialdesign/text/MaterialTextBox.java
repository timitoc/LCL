package com.sasluca.lcl.materialdesign.text;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.IRender;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.materialdesign.LCLMaterialDesign;
import com.sasluca.lcl.ui.design.UIDesign;
import com.sasluca.lcl.ui.group.UIGroup;
import com.sasluca.lcl.ui.image.UISprite;
import com.sasluca.lcl.ui.text.UILabel;
import com.sasluca.lcl.ui.text.UITextBox;
import com.sasluca.lcl.utils.LCLUtils;

/**
 * Created by Sas Luca on 8/15/2016.
 */

public class MaterialTextBox extends UITextBox
{
    LCLMask m_Mask;
    UISprite m_Cursor;
    UISprite m_FocusedBar;
    UISprite m_UnfocusedBar;
    UILabel m_DisplayedText;
    private UIGroup m_Group;
    private String m_DefaultText;

    public MaterialTextBox(String defaultString)
    {
        m_DefaultText = defaultString;

        m_Group = new UIGroup();
        m_Mask = new LCLMask(0, 0, 0, 0);
        m_Cursor = new UISprite("default");
        m_FocusedBar = new UISprite("default");
        m_UnfocusedBar = new UISprite("default");
        m_DisplayedText = new UILabel("Roboto", "", Color.BLACK);

        m_Mask.setSize(560, 70);

        m_Cursor.setSize(4, 40)
                .setColor(LCLMaterialDesign.Theme.getColor("primary"))
                .setRenderingState(false);

        m_UnfocusedBar.setSize(560, 2)
                .setColor(LCLMaterialDesign.Theme.getColor("unfocused"));

        m_FocusedBar.setSize(1, 4)
                .setColor(LCLMaterialDesign.Theme.getColor("primary"))
                .setRenderingState(false);

        m_DisplayedText.setFont("Roboto")
                .write("test")
                .setScale(0.7f)
                .setColor(LCLMaterialDesign.Theme.getColor("unfocused"));
                LCLUtils.centerToDrawable(m_DisplayedText, m_Mask, false, true);

        m_Group.addObject(m_Mask)
                .addObject(m_Cursor)
                .addObject(m_FocusedBar)
                .addObject(m_UnfocusedBar)
                .addObject(m_DisplayedText);

        p_Cursor = m_Cursor;
    }

    @Override protected void renderImpl()
    {
        m_UnfocusedBar.render();
        m_FocusedBar.render();

        m_Mask.start();

        m_DisplayedText.render();
        p_Cursor.render();

        m_Mask.end();
    }

    @Override protected void setCursorPosition(int cursorColumn)
    {

    }

    @Override protected void onFocus()
    {

    }

    @Override protected void onLoseFocus()
    {

    }

    @Override public UITextBox setWidth(float newWidth)
    {
        m_FocusedBar.setWidth(newWidth);
        m_UnfocusedBar.setWidth(newWidth);
        return this;
    }

    @Override public UITextBox setHeight(float newHeight)
    {
        m_FocusedBar.setHeight(newHeight);
        m_UnfocusedBar.setHeight(newHeight);
        return this;
    }

    @Override public UITextBox setSize(float newWidth, float newHeight)
    {
        setWidth(newWidth).setHeight(newHeight);
        return this;
    }

    //<editor-fold desc="Transform">
    @Override public float getX() { return m_UnfocusedBar.getX(); }
    @Override public float getY() { return m_UnfocusedBar.getY(); }
    @Override public float getWidth() { return m_UnfocusedBar.getWidth(); }
    @Override public float getHeight() { return m_UnfocusedBar.getHeight(); }
    @Override public UITextBox setPosX(float newX) { m_Group.setPosX(newX); return this; }
    @Override public UITextBox setPosY(float newY) { m_Group.setPosY(newY); return this; }
    //</editor-fold>
}
