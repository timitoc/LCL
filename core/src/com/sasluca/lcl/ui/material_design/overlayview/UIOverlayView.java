package com.sasluca.lcl.ui.material_design.overlayview;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IFocusable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.input.events.ITouchUpEvent;
import com.sasluca.lcl.ui.material_design.design.UIDesign;
import com.sasluca.lcl.ui.material_design.image.UIImage;

/**
 * Created by Sas Luca on 11-Jul-16.
 */

public abstract class UIOverlayView<This> implements IFocusable<This>
{
    static { LCLTween.addClass(UIOverlayView.class); }

    private UIImage m_DarkOverlay;

    protected boolean p_UseDarkOverlay = true;
    protected ITouchUpEvent p_OnOverlayClick;
    protected boolean p_FocusedState;
    protected UIDesign p_Design;

    protected abstract void onEnter();
    protected abstract void onExit();

    public static float DarkOverlayTransparency = 0.6f;
    public static float DarkOverlayAnimDuration = 0.5f;

    public UIOverlayView()
    {
        m_DarkOverlay = new UIImage(LCL.SYS.ResourceManger.<Texture>getResource("default"));
        m_DarkOverlay.setSize(LCL.SYS.Camera.viewportWidth, LCL.SYS.Camera.viewportHeight)
                .setColor(Color.BLACK)
                .setAlpha(0);
    }

    @Override public final This focus()
    {
        if(p_FocusedState) return ((This)this);
        p_FocusedState = true;

        m_DarkOverlay.subscribeToInputLayer(0);
        if(p_UseDarkOverlay) LCL.SYS.AppSystem.getRenderLayer(LCL.SYS.AppSystem.getNumberOfRenderLayer() - 1).addRenderable(m_DarkOverlay);

        if(p_OnOverlayClick != null) m_DarkOverlay.onTouchUp = p_OnOverlayClick;

        if(p_UseDarkOverlay)
        {
            Tween.to(m_DarkOverlay, LCLUniversalAccessor.COLOR_A, DarkOverlayAnimDuration)
                    .target(DarkOverlayTransparency)
                    .ease(Quad.IN)
                    .start(LCLTween.TWEEN_MANAGER);
        }

        onEnter();

        return ((This)this);
    }

    @Override public final This loseFocus()
    {
        if(!p_FocusedState) return ((This)this);
        m_DarkOverlay.onTouchUp = null;
        p_FocusedState = false;

        m_DarkOverlay.unsubscribeFromInputLayer();

        if(p_UseDarkOverlay)
        {
            Tween.to(m_DarkOverlay, LCLUniversalAccessor.COLOR_A, DarkOverlayAnimDuration)
                    .target(0)
                    .ease(Quad.IN)
                    .setCallback((a, b) -> LCL.SYS.AppSystem.getRenderLayer(LCL.SYS.AppSystem.getNumberOfRenderLayer() - 1).removeRenderable(m_DarkOverlay))
                    .start(LCLTween.TWEEN_MANAGER);
        }

        onExit();

        return ((This)this);
    }

    public boolean isFocused() { return p_FocusedState; }
}
