package com.sasluca.lcl.ui.material_design.label;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.fonts.LCLDistanceFieldFont;
import com.sasluca.lcl.graphics.fonts.LCLFont;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.material_design.UIView;
import com.sasluca.lcl.utils.text.LCLString;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class UITextArea extends UIView<UITextArea> implements ISizeable<UITextArea>, IColorable<UITextArea>
{
    static { LCLTween.addClass(UITextArea.class); }

    private Color m_Color;
    private float m_ScaleW;
    private float m_ScaleH;
    private LCLFont m_Font;
    private LCLMask m_Mask;
    private LCLString m_Text;
    private boolean m_Masking;
    private float m_Smoothing;
    private boolean m_IsRendering;
    private String m_DisplayedString;

    private static LCLString TEMP = new LCLString();

    public UITextArea(String font, float width, float height, String text)
    {
        m_Font = LCLFontManager.getFont(font);
        m_Mask = new LCLMask(width, 0, width, height);
        m_Text = new LCLString(text);
        m_ScaleW = 1;
        m_ScaleH = 1;
        m_Color = new Color(Color.BLACK);
        m_IsRendering = true;
        m_Masking = true;
        fitText();
    }

    public void fitText()
    {
        m_DisplayedString = null;

        TEMP.clear();

        m_Text.removeChar('\n');

        int ct = 0;

        for (int i = 0; i < m_Text.getText().split(" ").length; i++)
        {
            if (m_Font.getTextWidth(TEMP.getText().split("\n")[ct] + m_Text.getText().split(" ")[i], m_ScaleW) >= m_Mask.getWidth())
            {
                ct++;
                TEMP.append((i == 0 ? "\n" : " \n") + m_Text.getText().split(" ")[i]);
                continue;
            }

            TEMP.append((i == 0 ? "" : " ") + m_Text.getText().split(" ")[i]);
        }

        m_Text.clear().append(TEMP.getText());

        m_DisplayedString = m_Text.getText();
    }

    @Override public void renderImpl()
    {
        if (m_Masking) m_Mask.start();

        if(m_Font instanceof LCLDistanceFieldFont)
            ((LCLDistanceFieldFont)m_Font).drawText(m_Text.getText(), m_Mask.getX(), m_Mask.getY() - (m_Font.getTextHeight(m_Text.getText(), m_ScaleH) - m_Mask.getHeight()), m_ScaleW, m_ScaleH, m_Color, m_Smoothing);
        else
            m_Font.drawText(m_Text.getText(), m_Mask.getX(), m_Mask.getY() - (m_Font.getTextHeight(m_Text.getText(), m_ScaleH) - m_Mask.getHeight()), m_ScaleW, m_ScaleH, m_Color);

        if (m_Masking) m_Mask.end();
    }

    @Override protected void updateImpl() { }

    public UITextArea setFont(String font)
    {
        m_Font = LCLFontManager.getFont(font);
        fitText();

        return this;
    }

    public LCLFont getFont() { return m_Font; }
    public float getTextWidth() { return m_Font.getTextWidth(m_Text.getText(), m_ScaleW); }
    public float getTextHeight() { return m_Font.getTextHeight(m_Text.getText(), m_ScaleH); }

    public float getSmoothing() { return m_Smoothing; }
    public UITextArea setSmoothing(float smoothing) { m_Smoothing = smoothing; return this; }

    public boolean isOverflowing() { return (m_Font.getTextHeight(m_Text.getText(), m_ScaleH) > m_Mask.getHeight()); }

    public UITextArea setTextWidthScale(float scale) { m_ScaleW = scale; fitText(); return this; }
    public UITextArea setTextHeightScale(float scale) { m_ScaleH = scale; fitText(); return this; }
    public UITextArea setTextScale(float scale) { m_ScaleW = m_ScaleH = scale; fitText(); return this; }

    public boolean isMaskingEnabled() { return m_Masking; }
    public UITextArea enableMasking() { m_Masking = true; return this; }
    public UITextArea disableMasking() { m_Masking = false; return this; }

    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public UITextArea setPosX(float newX) { m_Mask.setPosX(newX); return this; }
    @Override public UITextArea setPosY(float newY) { m_Mask.setPosY(newY); return this; }

    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }
    @Override public UITextArea setWidth(float newWidth) { m_Mask.setWidth(newWidth); fitText(); return this; }
    @Override public UITextArea setHeight(float newHeight) { m_Mask.setHeight(newHeight); fitText(); return this; }
    @Override public UITextArea setSize(float newWidth, float newHeight) { m_Mask.setSize(newWidth, newHeight); fitText(); return this; }

    @Override public Color getColor() { return m_Color; }
    @Override public UITextArea setAlpha(float newAlpha) { m_Color.a = newAlpha; return this; }
    @Override public UITextArea setColor(Color newColor) { m_Color.set(newColor); return this; }
}
