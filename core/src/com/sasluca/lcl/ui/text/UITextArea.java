package com.sasluca.lcl.ui.text;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.fonts.LCLDistanceFieldFont;
import com.sasluca.lcl.graphics.fonts.LCLFont;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.image.UISprite;
import com.sasluca.lcl.ui.view.UIView;
import com.sasluca.lcl.utils.text.LCLString;

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
    private boolean m_FitText;
    public boolean drawFromTop;
    private String m_DisplayedString;

    private UISprite image;

    private static LCLString TEMP = new LCLString();

    public UITextArea(String font, float width, float height, String text, boolean fitText)
    {
        image = new UISprite("default");
        image.setSize(width, height).setColor(Color.BLUE);

        m_Font = LCLFontManager.getFont(font);
        m_Mask = new LCLMask(0, 0, width, height);
        m_Text = new LCLString(text);
        m_ScaleW = m_ScaleH = 1f;
        m_Color = new Color(Color.BLACK);
        m_Masking = true;
        m_FitText = fitText;
        m_Smoothing = 4;
        if(m_FitText) fitText();
    }

    public void calculateDisplayString()
    {
        m_DisplayedString = "";
        for(String i : m_Text.split("\n")) if(!(m_Font.getTextHeight(m_DisplayedString, m_ScaleH) > m_Mask.getHeight())) m_DisplayedString += "\n" + i;
    }

    public UITextArea setText(String text)
    {
        m_Text.write(text);
        fitText();
        return this;
    }

    public void fitText()
    {
        m_DisplayedString = null;

        String w = "";
        for(String j : m_Text.split(" "))
        {
            if(m_Font.getTextWidth(w + j, m_ScaleW) > m_Mask.getWidth()) w += "\n";
            w += j + " ";
        }

        m_DisplayedString = w;
    }

    public void fitText3()
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
        m_Mask.start();

        image.render();
        if(m_Font instanceof LCLDistanceFieldFont)
            ((LCLDistanceFieldFont)m_Font).drawText(m_DisplayedString, m_Mask.getX(), m_Mask.getY() + m_Mask.getHeight() - m_Font.getTextHeight(m_DisplayedString, m_ScaleH), m_ScaleW, m_ScaleH, m_Color, m_Smoothing, drawFromTop);
        else
            m_Font.drawText(m_DisplayedString, m_Mask.getX(), m_Mask.getY() + m_Mask.getHeight() - m_Font.getTextHeight(m_DisplayedString, m_ScaleH), m_ScaleW, m_ScaleH, m_Color, drawFromTop);

        m_Mask.end();
    }

    @Override protected void updateImpl() { }

    public boolean getFitTextStrategy() { return m_FitText; }
    public UITextArea setFitTextStrategy(boolean fitText) { m_FitText = fitText; return this; }

    public UITextArea setFont(String font)
    {
        m_Font = LCLFontManager.getFont(font);

        return this;
    }

    public LCLFont getFont() { if(m_FitText) fitText(); return m_Font; }
    public float getTextWidth() { return m_Font.getTextWidth(m_Text.getText(), m_ScaleW); }
    public float getTextHeight() { return m_Font.getTextHeight(m_Text.getText(), m_ScaleH); }

    public float getSmoothing() { return m_Smoothing; }
    public UITextArea setSmoothing(float smoothing) { m_Smoothing = smoothing; return this; }

    public boolean isOverflowing() { return (m_Font.getTextHeight(m_Text.getText(), m_ScaleH) > m_Mask.getHeight()); }

    public UITextArea setTextWidthScale(float scale) { m_ScaleW = scale; if(m_FitText) fitText(); return this; }
    public UITextArea setTextHeightScale(float scale) { m_ScaleH = scale; if(m_FitText) fitText(); return this; }
    public UITextArea setTextScale(float scale) { m_ScaleW = m_ScaleH = scale; if(m_FitText) fitText(); return this; }

    public boolean isMaskingEnabled() { return m_Masking; }
    public UITextArea enableMasking() { m_Masking = true; return this; }
    public UITextArea disableMasking() { m_Masking = false; return this; }

    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public UITextArea setPosX(float newX) { m_Mask.setPosX(newX); return this; }
    @Override public UITextArea setPosY(float newY) { m_Mask.setPosY(newY); return this; }

    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }
    @Override public UITextArea setWidth(float newWidth) { m_Mask.setWidth(newWidth); if(m_FitText) fitText(); return this; }
    @Override public UITextArea setHeight(float newHeight) { m_Mask.setHeight(newHeight); if(m_FitText) fitText(); return this; }
    @Override public UITextArea setSize(float newWidth, float newHeight) { m_Mask.setSize(newWidth, newHeight); if(m_FitText) fitText(); return this; }

    @Override public Color getColor() { return m_Color; }
    @Override public UITextArea setAlpha(float newAlpha) { m_Color.a = newAlpha; return this; }
    @Override public UITextArea setColor(Color newColor) { m_Color.set(newColor); return this; }
}
