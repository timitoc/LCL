package com.sasluca.lcl.ui.material_design;

import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IFocusable;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.ITransformable;
import com.sasluca.lcl.abstractions.IUpdatable;
import com.sasluca.lcl.input.LCLHitbox;
import com.sasluca.lcl.input.LCLInputHandler;
import com.sasluca.lcl.input.events.*;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class UIView<This> extends LCLInputHandler implements IRenderable<This>, IUpdatable<This>, ITransformable<This>, IFocusable<This>
{
    /** This events should be implemented by the client of the implementation */
    public IKeyUpEvent<This> onKeyUp;
    public IKeyDownEvent<This> onKeyDown;
    public IScrolledEvent<This> onScroll;
    public ITouchUpEvent<This> onTouchUp;
    public IKeyTypedEvent<This> onKeyType;
    public ITouchDownEvent<This> onTouchDown;
    public IMouseMovedEvent<This> onMouseMove;
    public ITouchDraggedEvent<This> onTouchDrag;

    protected int p_Pointer;
    protected LCLHitbox p_Hitbox;
    protected boolean p_IsUpdating;
    protected boolean p_FocusedState;
    protected boolean p_IsRendering;

    public UIView()
    {
        p_Pointer = -1;
        p_Hitbox = new LCLHitbox(this);
        p_IsRendering = true;
        p_IsUpdating = true;
    }

    //<editor-fold desc="Render">
    @Override public final This render()
    {
        if(!p_IsRendering || !isVisible()) return ((This)this);

        renderImpl();

        return ((This)this);
    }

    protected abstract void renderImpl();

    @Override public boolean isRendering() { return p_IsRendering; }
    @Override public This setRenderingState(boolean renderingState) { p_IsRendering = renderingState; return ((This)this); }
    //</editor-fold>

    //<editor-fold desc="Update">
    @Override public final This update()
    {
        if(!p_IsUpdating) return ((This)this);

        updateImpl();

        return ((This)this);
    }

    protected abstract void updateImpl();

    @Override public boolean isUpdating() { return p_IsUpdating; }
    @Override public This setUpdatingState(boolean updatingState) { p_IsUpdating = updatingState; return ((This)this); }
    //</editor-fold>

    public boolean isVisible()
    {
        float h = LCL.SYS.Camera.viewportHeight;
        float w = LCL.SYS.Camera.viewportWidth;
        return getX() + getWidth() <= LCL.SYS.Camera.viewportWidth || getX() >= w || getY() + getHeight() <= h || getY() >= h;
    }

    //<editor-fold desc="Basic implementation of input events">
    @Override public boolean keyUp(int keycode)
    {
        if(onKeyUp != null) onKeyUp.keyUp(keycode, ((This)this));
        return false; 
    }
    
    @Override public boolean keyDown(int keycode)
    {
        if(onKeyDown != null) onKeyDown.keyDown(keycode, ((This)this));
        return false; 
    }

    @Override public boolean scrolled(int amount)
    {
        if(onScroll != null) onScroll.scrolled(amount, ((This)this));
        return false;
    }

    @Override public boolean keyTyped(char character)
    {
        if(onKeyType != null) onKeyType.keyTyped(character, ((This)this));
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY)
    {
        if(onMouseMove != null) onMouseMove.mouseMoved(screenX, screenY, ((This)this));
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(p_Pointer == pointer)
        {
            if(onTouchDrag != null) onTouchDrag.touchDragged(screenX, screenY, pointer, ((This)this));

            return true;
        }

        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(p_Pointer == pointer)
        {
            if(onTouchUp != null) onTouchUp.touchUp(screenX, screenY, pointer, button, ((This)this));
            loseFocus();

            return true;
        }

        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(p_Hitbox.isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            p_Pointer = pointer;
            if(onTouchDown != null) onTouchDown.touchDown(screenX, screenY, pointer, button, ((This)this));
            focus();

            return true;
        }

        return false;
    }
    //</editor-fold>

    public LCLHitbox getHitbox() { return p_Hitbox; }

    //<editor-fold desc="Focus">
    /** Call super when you override it. */
    public This loseFocus()
    {
        p_Pointer = -1;
        p_FocusedState = false;

        return ((This)this);
    }

    public This focus()
    {
        p_FocusedState = true;

        return ((This)this);
    }

    public final boolean isFocused() { return p_FocusedState; }
    //</editor-fold>
}
