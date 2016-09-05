package com.sasluca.lcl.ui.view;

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.input.LCLHitbox;
import com.sasluca.lcl.input.LCLInputHandler;
import com.sasluca.lcl.input.inputevents.*;

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

public abstract class UIView<THIS> extends LCLInputHandler<THIS> implements IRenderable<THIS>, IUpdatable<THIS>, ITransformable<THIS>, IFocusable<THIS>, IDisposable
{
    static { LCLTween.addClass(UIView.class); }

    public IKeyUpEvent keyUpEvent;
    public IKeyDownEvent keyDownEvent;
    public ITouchUpEvent touchUpEvent;
    public IKeyTypedEvent keyTypedEvent;
    public IScrolledEvent scrolledEvent;
    public ITouchDownEvent touchDownEvent;
    public IMouseMovedEvent mouseMovedEvent;
    public ITouchDraggedEvent touchDraggedEvent;

    protected int p_Pointer;
    private LCLHitbox m_Hitbox;
    private boolean m_IsUpdating;
    private boolean m_FocusedState;
    private boolean m_IsRendering;

    public UIView()
    {
        p_Pointer = -1;
        m_Hitbox = new LCLHitbox(this);
        m_IsRendering = true;
        m_IsUpdating = true;
    }

    //<editor-fold desc="Render">
    @Override public final void render()
    {
        if(!m_IsRendering || !isVisible()) return;

        renderImpl();
    }

    protected abstract void renderImpl();

    @Override public boolean isRendering() { return m_IsRendering; }
    @Override public THIS setRenderingState(boolean renderingState) { m_IsRendering = renderingState; return ((THIS)this); }
    //</editor-fold>

    //<editor-fold desc="Update">
    @Override public final void update()
    {
        if(!m_IsUpdating) return;

        updateImpl();
    }

    protected abstract void updateImpl();

    @Override public boolean isUpdating() { return m_IsUpdating; }
    @Override public THIS setUpdatingState(boolean updatingState) { m_IsUpdating = updatingState; return ((THIS)this); }
    //</editor-fold>

    public boolean isVisible()
    {
        float h = LCL.getCamera().viewportHeight;
        float w = LCL.getCamera().viewportWidth;
        return getX() + getWidth() <= LCL.getCamera().viewportWidth || getX() >= w || getY() + getHeight() <= h || getY() >= h;
    }

    //<editor-fold desc="Basic implementation of input events">
    @Override public boolean keyUp(int keycode)
    {
        if(m_FocusedState)
        {
            if(keyUpEvent != null) keyUpEvent.keyUp(keycode, this);

            return true;
        }

        return false;
    }

    @Override public boolean keyDown(int keycode)
    {
        if(m_FocusedState)
        {
            if(keyDownEvent != null) keyDownEvent.keyDown(keycode, this);

            return true;
        }

        return false;
    }

    @Override public boolean scrolled(int amount)
    {
        if(m_FocusedState)
        {
            if(scrolledEvent != null) scrolledEvent.scrolled(amount, this);

            return true;
        }

        return false;
    }

    @Override public boolean keyTyped(char character)
    {
        if(m_FocusedState)
        {
            if(keyTypedEvent!= null) keyTypedEvent.keyTyped(character, this);

            return true;
        }

        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY)
    {
        if(m_FocusedState)
        {
            if(mouseMovedEvent != null) mouseMovedEvent.mouseMoved(screenX, screenY, this);

            return true;
        }

        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(p_Pointer == pointer)
        {
            if(touchDraggedEvent != null) touchDraggedEvent.touchDragged(screenX, screenY, pointer, this);

            return true;
        }

        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(p_Pointer == pointer)
        {
            loseFocus();

            if(touchUpEvent != null) touchUpEvent.touchUp(screenX, screenY, pointer, button, this);

            return true;
        }

        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(m_Hitbox.isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            p_Pointer = pointer;
            focus();

            if(touchDownEvent != null) touchDownEvent.touchDown(screenX, screenY, pointer, button, this);

            return true;
        }

        return false;
    }
    //</editor-fold>

    public LCLHitbox getHitbox() { return m_Hitbox; }

    //<editor-fold desc="Focus">
    public final THIS loseFocus()
    {
        p_Pointer = -1;
        m_FocusedState = false;

        onLoseFocus();

        return ((THIS)this);
    }

    public final THIS focus()
    {
        m_FocusedState = true;

        onFocus();

        return ((THIS)this);
    }

    protected void onFocus() {};
    protected void onLoseFocus() {};

    public final boolean isFocused() { return m_FocusedState; }
    //</editor-fold>

    @Override public void dispose()
    {
        unsubscribeFromInputLayer();
    }
}
