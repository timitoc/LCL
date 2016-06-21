package com.sasluca.lcl.graphics.fonts;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.utils.text.LCLString;

/**
 * Created by Sas Luca on 19-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLLabel<Font extends LCLFont> implements IRenderable<LCLLabel>, ITransformable<LCLLabel>, IScalable<LCLLabel>, IColorable<LCLLabel>, IText<LCLLabel>, IDisposable
{
    protected float p_Y;
    protected float p_X;
    protected Color p_Color;
    protected LCLFont p_Font;
    protected float p_WidthScale;
    protected float p_HeightScale;
    protected LCLString p_String;

    public LCLLabel(String font, String text, Color color)
    {
        p_Font = LCL.MASTER().FontManager.getFont(font);
        p_Color = new Color(color);
        p_String = new LCLString(text);
        p_WidthScale = 1;
        p_HeightScale = 1;
    }

    //Render
    @Override public LCLLabel render()
    {
        p_Font.drawText(p_String.getText(), p_X, p_Y, p_WidthScale, p_HeightScale, p_Color);

        return this;
    }

    //Color
    @Override public Color getColor() { return p_Color; }
    @Override public LCLLabel setColor(Color newColor) { p_Color = new Color(newColor); return this; }

    //Scale
    @Override public float getWidthScale() { return p_WidthScale; }
    @Override public float getHeightScale() { return p_HeightScale; }
    @Override public float getOriginalWidth() { return p_Font.getTextWidth(p_String.getText(), 1); }
    @Override public float getOriginalHeight() { return p_Font.getTextHeight(p_String.getText(), 1); }
    @Override public LCLLabel setWidthScale(float widthScale) { p_WidthScale = widthScale; return this; }
    @Override public LCLLabel setHeightScale(float heightScale) { p_HeightScale = heightScale; return this; }
    @Override public LCLLabel setScale(float newScale) { p_WidthScale = p_HeightScale = newScale; return this; }
    @Override public LCLLabel setScale(float newWidthScale, float newHeightScale) { p_WidthScale = newWidthScale; p_HeightScale = newHeightScale; return this; }

    //Text
    @Override public int getLength() { return p_String.getLength(); }
    @Override public String getText() { return p_String.getText(); }
    @Override public LCLLabel clear() { p_String.clear(); return this; }
    @Override public char getCharAt(int index) { return p_String.getCharAt(index); }
    @Override public LCLLabel toUpperCase() { p_String.toUpperCase(); return this; }
    @Override public LCLLabel toLowerCase() { p_String.toLowerCase(); return this; }
    @Override public LCLLabel write(String text) { p_String.write(text); return this; }
    @Override public LCLLabel append(String text) { p_String.append(text); return this; }
    @Override public LCLLabel delete(int begin, int end) { p_String.delete(begin, end); return this; }
    @Override public String getSubstring(int begin, int end) { return p_String.getSubstring(begin, end); }
    @Override public String getSplit(char splitBy, int index) { return p_String.getSplit(splitBy, index); }
    @Override public LCLLabel insert(int index, String string) { p_String.insert(index, string); return this; }
    @Override public LCLLabel changeChar(char change, char to) { p_String.changeChar(change, to); return this; }
    @Override public String getSplit(String splitBy, int index) { return p_String.getSplit(splitBy, index); }
    @Override public LCLLabel changeCharAtIndex(int index, char change) { p_String.changeCharAtIndex(index, change); return this; }

    //Transform
    @Override public float getX() { return p_X; }
    @Override public float getY() { return p_Y; }
    @Override public float getWidth() { return p_Font.getTextWidth(p_String.getText(), p_WidthScale); }
    @Override public float getHeight() { return p_Font.getTextHeight(p_String.getText(), p_HeightScale); }
    @Override public LCLLabel setPosX(int newX) { p_X = newX; return this; }
    @Override public LCLLabel setPosY(float newY) { p_Y = newY; return this; }

    //Dispose
    @Override public void dispose() { }
}
