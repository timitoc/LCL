package com.sasluca.lcl.ui.material_design.label;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.abstractions.IScalable;
import com.sasluca.lcl.abstractions.IText;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.fonts.LCLFont;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.ui.UIView;
import com.sasluca.lcl.utils.text.LCLString;

/**
 * Created by Sas Luca on 04-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class UILabel extends UIView<UILabel> implements IText<UILabel>, IColorable<UILabel>, IScalable<UILabel>
{
    static { LCLTween.addClass(UILabel.class); }

    protected float p_Y;
    protected float p_X;
    protected Color p_Color;
    protected LCLFont p_Font;
    protected LCLString p_String;
    protected float p_WidthScale;
    protected float p_HeightScale;

    public UILabel(String font, String text, Color color)
    {
        p_Font = LCLFontManager.getFont(font);
        p_Color = new Color(color);
        p_String = new LCLString(text);
        p_WidthScale = 1;
        p_HeightScale = 1;
    }

    //Render
    @Override public void renderImpl() { p_Font.drawText(p_String.getText(), p_X, p_Y, p_WidthScale, p_HeightScale, p_Color); }
    @Override protected void updateImpl() { }

    public LCLFont getFont() { return p_Font; }

    //Color
    @Override public Color getColor() { return p_Color; }
    @Override public UILabel setColor(Color newColor) { p_Color = new Color(newColor); return this; }
    @Override public UILabel setAlpha(float newAlpha) { p_Color.a = newAlpha; return this; }

    //Scale
    @Override public float getWidthScale() { return p_WidthScale; }
    @Override public float getHeightScale() { return p_HeightScale; }
    @Override public float getOriginalWidth() { return p_Font.getTextWidth(p_String.getText(), 1); }
    @Override public float getOriginalHeight() { return p_Font.getTextHeight(p_String.getText(), 1); }
    @Override public UILabel setWidthScale(float widthScale) { p_WidthScale = widthScale; return this; }
    @Override public UILabel setHeightScale(float heightScale) { p_HeightScale = heightScale; return this; }
    @Override public UILabel setScale(float newScale) { p_WidthScale = p_HeightScale = newScale; return this; }
    @Override public UILabel setScale(float newWidthScale, float newHeightScale) { p_WidthScale = newWidthScale; p_HeightScale = newHeightScale; return this; }

    //Text
    @Override public String getText() { return p_String.getText(); }
    @Override public int getLength() { return p_String.getLength(); }
    @Override public UILabel clear() { p_String.clear(); return this; }
    @Override public char getCharAt(int index) { return p_String.getCharAt(index); }
    @Override public UILabel toUpperCase() { p_String.toUpperCase(); return this; }
    @Override public UILabel toLowerCase() { p_String.toLowerCase(); return this; }
    @Override public UILabel write(String text) { p_String.write(text); return this; }
    @Override public UILabel append(String text) { p_String.append(text); return this; }
    @Override public UILabel delete(int begin, int end) { p_String.delete(begin, end); return this; }
    @Override public String getSubstring(int begin, int end) { return p_String.getSubstring(begin, end); }
    @Override public UILabel insert(int index, String string) { p_String.insert(index, string); return this; }
    @Override public UILabel changeChar(char change, char to) { p_String.changeChar(change, to); return this; }
    @Override public UILabel changeCharAtIndex(int index, char change) { p_String.changeCharAtIndex(index, change); return this; }

    //Transform
    @Override public float getX() { return p_X; }
    @Override public float getY() { return p_Y; }
    @Override public float getWidth() { return p_Font.getTextWidth(p_String.getText(), p_WidthScale); }
    @Override public float getHeight() { return p_Font.getTextHeight(p_String.getText(), p_HeightScale); }
    @Override public UILabel setPosX(float newX) { p_X = newX; return this; }
    @Override public UILabel setPosY(float newY) { p_Y = newY; return this; }
}
