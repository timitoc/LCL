package com.sasluca.lcl.ui.material_design.text;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.graphics.fonts.LCLFont;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.input.LCLVirtualKeyboardManager;
import com.sasluca.lcl.ui.UIView;
import com.sasluca.lcl.ui.material_design.LCLMaterialDesign;
import com.sasluca.lcl.ui.material_design.label.UILabel;
import com.sasluca.lcl.utils.group.LCLGroup;
import com.sasluca.lcl.utils.text.LCLString;
import com.sasluca.lcl.utils.timer.LCLTimer;
import com.sasluca.lcl.utils.tuples.LCLTuple;

import static com.sasluca.lcl.utils.LCLUtils.*;
import static com.sasluca.lcl.utils.tuples.LCLTuple.tuple;

/**
 * Created by Sas Luca on 07-Jul-16.
 * Copyright (C) 2016 - LCL
 */

/** A single line text box */
public class UITextBox extends UIView<UITextBox>
{
    static { LCLTween.addClass(UITextBox.class); }

    private LCLMask m_TextMask;
    private LCLMask m_InfoMask;
    private int m_CursorCol;
    private LCLSprite m_Idle;
    private LCLString m_Text;
    private Color m_TextColor;
    private LCLSprite m_Cursor;
    private char m_PasswordChar;
    private Color m_FocusedColor;
    private LCLSprite m_Focused;
    private Color m_IdleColor;
    private UILabel m_DisplayedLabel;
    private LCLString m_PasswordString;
    private LCLString m_AllowedChars;
    private UILabel m_InfoLabel;
    private String m_InfoMessage;
    private String m_ErrorMessage;
    private LCLGroup m_Group;
    private UILabel m_DefaultLabel;
    private boolean m_ShowError;
    private boolean m_Animating;
    private LCLTimer m_BlinkingTimer;
    private float m_BlinkingRate = 0.5f;
    private LCLTimer m_KeysTimer;
    private float m_KeysRate = 0.2f;

    public UITextBox() { this(LCLMaterialDesign.PrimaryColor, LCLMaterialDesign.UnfocusedColor, Color.BLACK); }

    public UITextBox(Color focusedColor, Color idleColor, Color textColor)
    {
        m_ErrorMessage = "";
        m_PasswordChar = ' ';

        m_FocusedColor = focusedColor;
        m_IdleColor = idleColor;
        m_TextColor = textColor;

        m_Text = new LCLString();
        m_AllowedChars = new LCLString();
        m_PasswordString = new LCLString();

        m_Idle = new LCLSprite(LCL.SYS.ResourceManger.<Texture>getResource("textbox_idle"));
        m_Idle.setWidth(560);
        m_Idle.setColor(idleColor);

        m_Focused = new LCLSprite(LCL.SYS.ResourceManger.<Texture>getResource("textbox_focused"));
        m_Focused.setColor(focusedColor)
                .setRenderingState(false);

        m_TextMask = new LCLMask(0, 0, m_Idle.getWidth(), m_Focused.getHeight());

        m_InfoLabel = new UILabel("Roboto", "", focusedColor);
        m_InfoLabel.setScale(1f).setPosX(1);
        centerToDrawable(m_InfoLabel, m_Idle, false, true);

        m_InfoMask = new LCLMask(0, -55, m_Idle.getWidth(), 55);

        m_DisplayedLabel = new UILabel("Roboto", "", textColor);
        m_DisplayedLabel.setScale(1f).setPosX(1);
        centerToDrawable(m_DisplayedLabel, m_Idle, false, true);

        m_DefaultLabel = new UILabel("Roboto", "", idleColor);
        m_DefaultLabel.setScale(1f).setPosX(1);
        centerToDrawable(m_DefaultLabel, m_Idle, false, true);

        m_Cursor = new LCLSprite(LCL.SYS.ResourceManger.<Texture>getResource("default"));
        m_Cursor.setSize(2, 35)
                .setPosX(5)
                .setColor(focusedColor)
                .setRenderingState(false);
        centerToDrawable(m_Cursor, m_Focused, false, true);

        m_BlinkingTimer = new LCLTimer(m_BlinkingRate);
        m_KeysTimer = new LCLTimer(m_KeysRate);

        m_Group = new LCLGroup();
        m_Group.addObject(m_Idle)
                .addObject(m_Focused)
                .addObject(m_DisplayedLabel)
                .addObject(m_DefaultLabel)
                .addObject(m_InfoLabel)
                .addObject(m_Cursor)
                .addObject(m_TextMask)
                .addObject(m_InfoMask);

        allowSpace();
        allowNumbers();
        allowLetters();
        allowEmailCharacters();

        LCLVirtualKeyboardManager.addKeyboardHandler(this);
    }

    public UITextBox showError()
    {
        m_ShowError = true;
        m_DefaultLabel.setColor(LCLMaterialDesign.ErrorColor);
        m_Cursor.setColor(LCLMaterialDesign.ErrorColor);
        m_Focused.setColor(LCLMaterialDesign.ErrorColor);
        m_Idle.setColor(LCLMaterialDesign.ErrorColor);
        m_InfoLabel.write(m_ErrorMessage);
        m_InfoLabel.setColor(LCLMaterialDesign.ErrorColor);
        return this;
    }

    public UITextBox hideError()
    {
        m_ShowError = false;
        m_DefaultLabel.setColor(m_FocusedColor);
        m_Cursor.setColor(m_FocusedColor);
        m_Focused.setColor(m_FocusedColor);
        m_Idle.setColor(m_IdleColor);
        m_InfoLabel.write(m_InfoMessage);

        return this;
    }

    //<editor-fold desc="Setters">
    public UITextBox setDefaultText(String defaultText) { m_DefaultLabel.write(defaultText); return this; }
    public UITextBox setInfoText(String info) { m_InfoMessage = info; if(!m_ShowError) m_InfoLabel.write(info); return this; }
    public UITextBox setErrorText(String error) { m_ErrorMessage = error; if(m_ShowError) m_InfoLabel.write(error); return this; }
    //</editor-fold>

    //<editor-fold desc="Chars">
    public UITextBox setPasswordChar(char c) { m_PasswordChar = c; return this; }
    public UITextBox allowSpace() { allowChar((char)32); return this; }
    public UITextBox disallowSpace() { disallowChar((char)32); return this; }
    public UITextBox allowLetters() { allowLowercaseLetters(); allowUppercaseLetters(); return this; }
    public UITextBox allowNumbers() { for(int i = '0'; i <= '9'; i++) allowChar((char)i); return this; }
    public boolean isCharAllowed(char c) { return m_AllowedChars.getText().contains(Character.toString(c)); }
    public UITextBox disallowNumbers() { for(int i = '0'; i <= '9'; i++) disallowChar((char)i); return this; }
    public UITextBox disallowLetters() { disallowLowercaseLetters(); disallowUppercaseLetters(); return this; }
    public UITextBox allowLowercaseLetters() { for(int i = 'a'; i <= 'z'; i++) allowChar((char)i); return this; }
    public UITextBox allowUppercaseLetters() { for(int i = 'A'; i <= 'Z'; i++) allowChar((char)i); return this; }
    public UITextBox disallowUppercaseLetters() { for(int i = 'A'; i <= 'Z'; i++) disallowChar((char)i); return this; }
    public UITextBox disallowLowercaseLetters() { for(int i = 'a'; i <= 'z'; i++) disallowChar((char)i); return this; }
    public UITextBox allowChar(char c) { if(!m_AllowedChars.contains(c)) m_AllowedChars.append(Character.toString(c)); return this; }
    public UITextBox allowEmailCharacters() { for(String i : "64 33 35 36 37 38 39 42 43 45 47 61 63 94 95 96 123 124 125 126 46".split(" ")) allowChar((char)Integer.parseInt(i)); return this; }
    public UITextBox disallowEmailCharacters() { for(String i : "64 33 35 36 37 38 39 42 43 45 47 61 63 94 95 96 123 124 125 126 46".split(" ")) disallowChar((char)Integer.parseInt(i)); return this; }
    public UITextBox disallowChar(char c) { if(m_AllowedChars.contains(c)) m_AllowedChars.delete(m_AllowedChars.getText().lastIndexOf(c), m_AllowedChars.getText().lastIndexOf(c) + 1); return this; }
    //</editor-fold

    private LCLTuple<Boolean, Float> isFullyVisible(int col)
    {
        String text = m_DisplayedLabel.getText().substring(0, col);
        float textWidth = m_DisplayedLabel.getFont().getTextWidth(text, m_DisplayedLabel.getWidthScale());

        String charS = Character.toString(m_DisplayedLabel.getText().charAt(col));
        float charWidth = m_DisplayedLabel.getX() + m_DisplayedLabel.getFont().getTextWidth(charS, m_DisplayedLabel.getWidthScale());

        return tuple
        (
                (m_DisplayedLabel.getX() + textWidth) - (m_TextMask.getX() + 1) >= charWidth,
                (m_DisplayedLabel.getX() + textWidth) - (m_TextMask.getX() + 1)
        );
    }

    private void setTextPosition(int oldColumn, int newColumn)
    {
        if(newColumn > oldColumn)
        {
            //If label gets to lenght of textbox
            if(m_DisplayedLabel.getWidth() > m_TextMask.getWidth() - 1 && newColumn == m_DisplayedLabel.getLength())
            {
                LCLFont f = m_DisplayedLabel.getFont();
                String t = m_DefaultLabel.getText();
                float dif = m_DisplayedLabel.getFont().getTextWidth(Character.toString(m_DisplayedLabel.getCharAt(m_DisplayedLabel.getLength() - 1)), m_DisplayedLabel.getWidthScale());
                m_DisplayedLabel.setPosX(m_DisplayedLabel.getX() - dif);
            }
        }

        if(newColumn < oldColumn)
        {
            LCLTuple<Boolean, Float> t = isFullyVisible(newColumn);

            /*if(!t.getItem1())
            {
                m_DisplayedLabel.setPosX(m_DisplayedLabel.getX() + (t.getItem2() - m_TextMask.getWidth() + 1));
            }*/
        }
    }

    public UITextBox setCursorPosition(int column)
    {
        setTextPosition(m_CursorCol, column);

        m_CursorCol = column;

        m_Cursor.setPosX(m_DisplayedLabel.getX() + m_DisplayedLabel.getFont().getTextWidth(m_DisplayedLabel.getText().substring(0, m_CursorCol), m_DisplayedLabel.getWidthScale()));

        return this;
    }

    @Override protected void renderImpl()
    {
        m_Idle.render();
        m_Focused.render();

        m_Cursor.render();

        m_TextMask.start();
        m_DisplayedLabel.render();
        m_TextMask.end();

        m_DefaultLabel.render();

        m_InfoMask.start();
        m_InfoLabel.render();
        m_InfoMask.end();
    }

    @Override protected void updateImpl()
    {
        centerToDrawable(m_Focused, m_Idle, true, false);

        if(isFocused() && !m_Animating)
        {
            m_BlinkingTimer.update();
            m_KeysTimer.update();

            if(m_BlinkingTimer.ended())
            {
                m_BlinkingTimer.setDuration(m_BlinkingRate);
                m_Cursor.setRenderingState(!m_Cursor.isRendering());
            }

            if(Gdx.input.isKeyPressed(Input.Keys.BACK)) loseFocus();

            if(m_KeysTimer.ended())
            {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                {
                    if (m_CursorCol != 0) setCursorPosition(m_CursorCol - 1);
                    m_KeysTimer.setDuration(m_KeysRate);
                }

                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                {
                    if(m_CursorCol != m_Text.getLength()) setCursorPosition(m_CursorCol + 1);
                    m_KeysTimer.setDuration(m_KeysRate);
                }
            }
        }
    }

    //<editor-fold desc="Input">
    @Override public boolean keyTyped(char character)
    {
        if(!isFocused()) return false;

        if (onKeyType != null) onKeyType.keyTyped(character, this);

        if (m_AllowedChars.contains(character))
        {
            m_Text.insert(m_CursorCol, Character.toString(character));
            m_PasswordString.append(Character.toString(m_PasswordChar));
            m_DisplayedLabel.write(m_PasswordChar == ' ' ? m_Text.getText() : m_PasswordString.getText());

            setCursorPosition(m_CursorCol + 1);
        }

        if(character == 8 && m_CursorCol != 0)
        {
            m_Text.deleteCharAt(m_CursorCol - 1);
            m_DisplayedLabel.write(m_Text.getText());
            setCursorPosition(m_CursorCol - 1);
        }

        return true;
    }
    
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (p_Hitbox.isPointInside(screenX, screenY) && p_Pointer == pointer)
        {
            LCLVirtualKeyboardManager.showKeyboard(this);

            if (onTouchUp != null) onTouchUp.touchUp(screenX, screenY, pointer, button, this);
            p_Pointer = -1;

            if (!isFocused()) focus();

            return true;
        }

        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(p_Hitbox.isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            p_Pointer = pointer;
            if(onTouchDown != null) onTouchDown.touchDown(screenX, screenY, pointer, button, this);
        
            return true;
        }

        loseFocus();

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Focus">
    @Override public UITextBox focus()
    {
        super.focus();

        LCLVirtualKeyboardManager.showKeyboard(this);

        m_Focused.setWidth(1).setRenderingState(true);

        if(!m_ShowError) m_DefaultLabel.setColor(m_FocusedColor);

        if(m_DisplayedLabel.getText().isEmpty())
        {
            Tween.to(m_DefaultLabel, LCLUniversalAccessor.POS_XY, 0.3f)
                    .target(getX(), getY() + m_Idle.getHeight())
                    .ease(Quad.OUT)
                    .setCallback((type, source) -> { m_Cursor.setRenderingState(true); })
                    .start(LCLTween.TWEEN_MANAGER);
        }

        if(!m_InfoLabel.getText().isEmpty())
        {
            Tween.to(m_InfoLabel, LCLUniversalAccessor.POS_Y, 0.3f)
                    .target(m_InfoMask.getY() + (m_InfoMask.getHeight() / 2) - (m_InfoLabel.getHeight() / 2))
                    .ease(Quad.OUT)
                    .start(LCLTween.TWEEN_MANAGER);
        }

        Tween.to(m_Focused, LCLUniversalAccessor.WIDTH, 0.3f)
                .target(m_Idle.getWidth())
                .ease(Quad.IN)
                .setCallback((type, source) -> m_Animating = false)
                .start(LCLTween.TWEEN_MANAGER);

        m_Animating = true;

        return this;
    }

    @Override public UITextBox loseFocus()
    {
        super.loseFocus();

        LCLVirtualKeyboardManager.hideKeyboard(this, false);

        if(!m_ShowError) m_DefaultLabel.setColor(m_IdleColor);
        m_Cursor.setRenderingState(false);

        if(m_DisplayedLabel.getText().isEmpty())
        {
            Tween.to(m_DefaultLabel, LCLUniversalAccessor.POS_XY, 0.3f)
                    .target(getX() + 5, m_DisplayedLabel.getY())
                    .ease(Quad.OUT)
                    .start(LCLTween.TWEEN_MANAGER);
        }

        if(!m_InfoLabel.getText().isEmpty())
        {
            Tween.to(m_InfoLabel, LCLUniversalAccessor.POS_Y, 0.3f)
                    .target(m_Idle.getY() + (m_Idle.getHeight() / 2) - (m_InfoLabel.getHeight() / 2))
                    .ease(Quad.OUT)
                    .start(LCLTween.TWEEN_MANAGER);
        }

        Tween.to(m_Focused, LCLUniversalAccessor.WIDTH, 0.3f)
                .target(1)
                .ease(Quad.OUT)
                .setCallback((type, source) -> { m_Focused.setRenderingState(false); m_Cursor.setRenderingState(false); m_Animating = false; })
                .start(LCLTween.TWEEN_MANAGER);

        m_Animating = true;
        m_BlinkingTimer.setDuration(m_BlinkingRate);

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Transform">
    @Override public float getX() { return m_Idle.getX(); }
    @Override public float getY() { return m_Idle.getY(); }
    @Override public float getWidth() { return m_Group.getWidth(); }
    @Override public float getHeight() { return m_Group.getHeight(); }
    @Override public UITextBox setPosX(float newX) { m_Group.setPosX(newX); return this; }
    @Override public UITextBox setPosY(float newY) { m_Group.setPosY(newY); return this; }
    public UITextBox setWidth(float newWidth) { m_Idle.setWidth(newWidth); m_Focused.setWidth(newWidth); return this; }
    //</editor-fold>

    //<editor-fold desc="Color">
    public Color getTextColor() { return m_TextColor; }
    public Color getFocusedColor() { return m_FocusedColor; }
    public Color getUnfocusedColor() { return m_IdleColor; }
    public UITextBox setTextColor(Color color) { m_TextColor.set(color); return this; }
    public UITextBox setFocusedColor(Color color) { m_FocusedColor.set(color); return this; }
    public UITextBox setUnfocusedColor(Color color) { m_IdleColor.set(color); return this; }
    //</editor-fold>
}
