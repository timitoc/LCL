package com.sasluca.lcl.ui.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sasluca.lcl.abstractions.IAnimates;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.input.LCLVirtualKeyboardManager;
import com.sasluca.lcl.ui.view.UIView;
import com.sasluca.lcl.utils.text.LCLString;
import com.sasluca.lcl.utils.timer.LCLTimer;

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

public abstract class UITextBox extends UIView<UITextBox> implements IAnimates, ISizeable<UITextBox>
{
    protected int p_CursorCol;
    protected LCLString p_Text;
    protected char p_PasswordChar;
    protected IRenderable p_Cursor;
    protected LCLTimer p_KeysTimer;
    protected float p_KeyboardDelay;
    protected boolean p_IsAnimating;
    protected String p_PasswordString;
    protected float p_KeysRate = 0.2f;
    protected String p_DisplayedString;
    protected LCLString p_AllowedChars;
    protected LCLTimer p_BlinkingTimer;
    protected float p_BlinkingRate = 0.5f;

    public UITextBox()
    {
        p_Text = new LCLString();
        p_AllowedChars = new LCLString();
    }

    @Override protected void updateImpl()
    {
        if(isFocused() && !p_IsAnimating)
        {
            p_BlinkingTimer.update();
            p_KeysTimer.update();

            if(p_BlinkingTimer.ended())
            {
                p_BlinkingTimer.setDuration(p_BlinkingRate);
                p_Cursor.setRenderingState(!p_Cursor.isRendering());
            }

            if(Gdx.input.isKeyPressed(Input.Keys.BACK)) loseFocus();

            if(p_KeysTimer.ended())
            {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                {
                    if (p_CursorCol != 0) setCursorPosition(p_CursorCol - 1);
                    p_KeysTimer.setDuration(p_KeysRate);
                }

                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                {
                    if(p_CursorCol != p_Text.getLength()) setCursorPosition(p_CursorCol + 1);
                    p_KeysTimer.setDuration(p_KeysRate);
                }
            }
        }
    }

    protected abstract void setCursorPosition(int cursorColumn);

    //<editor-fold desc="Input Events">
    @Override public boolean keyTyped(char character)
    {
        if(!isFocused()) return false;

        //if (onKeyType != null) onKeyType.keyTyped(character, this);

        if (p_AllowedChars.contains(character))
        {
            p_Text.insert(p_CursorCol, Character.toString(character));
            p_PasswordString += Character.toString(p_PasswordChar);
            p_DisplayedString = p_PasswordChar == ' ' ? p_Text.getText() : p_PasswordString;

            setCursorPosition(p_CursorCol + 1);
        }

        if(character == 8 && p_CursorCol != 0)
        {
            p_Text.deleteCharAt(p_CursorCol - 1);
            p_PasswordString = p_PasswordString.substring(0, p_PasswordString.length() - 2);
            p_DisplayedString = p_PasswordChar == ' ' ? p_Text.getText() : p_PasswordString;
            setCursorPosition(p_CursorCol - 1);
        }

        return true;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (getHitbox().isPointInside(screenX, screenY) && p_Pointer == pointer)
        {
            LCLVirtualKeyboardManager.showKeyboard(this);

            if (touchUpEvent != null) touchUpEvent.touchUp(screenX, screenY, pointer, button, this);
            p_Pointer = -1;

            if (!isFocused()) focus();

            return true;
        }

        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(getHitbox().isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            p_Pointer = pointer;
            if(touchDownEvent != null) touchDownEvent.touchDown(screenX, screenY, pointer, button, this);

            return true;
        }

        if(isFocused()) loseFocus();

        return false;
    }
    //</editor-fold>
    
    //<editor-fold desc="Chars">
    public UITextBox setPasswordChar(char c)
    {
        p_PasswordChar = c;
        p_DisplayedString = (c == ' ' ? p_Text.getText() : p_PasswordString);
        return this;
    }

    public UITextBox allowSpace() { allowChar((char)32); return this; }
    public UITextBox disallowSpace() { disallowChar((char)32); return this; }
    public UITextBox allowLetters() { allowLowercaseLetters(); allowUppercaseLetters(); return this; }
    public UITextBox allowNumbers() { for(int i = '0'; i <= '9'; i++) allowChar((char)i); return this; }
    public boolean isCharAllowed(char c) { return p_AllowedChars.getText().contains(Character.toString(c)); }
    public UITextBox disallowNumbers() { for(int i = '0'; i <= '9'; i++) disallowChar((char)i); return this; }
    public UITextBox disallowLetters() { disallowLowercaseLetters(); disallowUppercaseLetters(); return this; }
    public UITextBox allowLowercaseLetters() { for(int i = 'a'; i <= 'z'; i++) allowChar((char)i); return this; }
    public UITextBox allowUppercaseLetters() { for(int i = 'A'; i <= 'Z'; i++) allowChar((char)i); return this; }
    public UITextBox disallowUppercaseLetters() { for(int i = 'A'; i <= 'Z'; i++) disallowChar((char)i); return this; }
    public UITextBox disallowLowercaseLetters() { for(int i = 'a'; i <= 'z'; i++) disallowChar((char)i); return this; }
    public UITextBox allowChar(char c) { if(!p_AllowedChars.contains(c)) p_AllowedChars.append(Character.toString(c)); return this; }
    public UITextBox allowEmailCharacters() { for(String i : "64 33 35 36 37 38 39 42 43 45 47 61 63 94 95 96 123 124 125 126 46".split(" ")) allowChar((char)Integer.parseInt(i)); return this; }
    public UITextBox disallowEmailCharacters() { for(String i : "64 33 35 36 37 38 39 42 43 45 47 61 63 94 95 96 123 124 125 126 46".split(" ")) disallowChar((char)Integer.parseInt(i)); return this; }
    public UITextBox disallowChar(char c) { if(p_AllowedChars.contains(c)) p_AllowedChars.delete(p_AllowedChars.getText().lastIndexOf(c), p_AllowedChars.getText().lastIndexOf(c) + 1); return this; }
    //</editor-fold

    @Override public final boolean isAnimating() { return p_IsAnimating; }
}
