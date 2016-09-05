package com.sasluca.lcl.ui.overlayview;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IFocusable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.input.inputevents.ITouchUpEvent;
import com.sasluca.lcl.ui.image.UISprite;
import com.sasluca.lcl.ui.view.UIView;

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

public abstract class UIOverlayView<THIS> implements IFocusable<THIS>
{
    private UISprite m_DarkOverlay;

    protected ITouchUpEvent p_OnOverlayClick;
    protected boolean p_UseDarkOverlay = true;

    protected abstract void onEnter();
    protected abstract void onExit();

    protected float p_DarkOverlayTransparency = 0.6f;
    protected float p_DarkOverlayAnimDuration = 0.5f;

    public UIOverlayView()
    {
        m_DarkOverlay = new UISprite(LCL.getResourceManager().<Texture>getResource("default"));
        m_DarkOverlay.setSize(LCL.getCamera().viewportWidth, LCL.getCamera().viewportHeight)
                .setColor(Color.BLACK)
                .setAlpha(0);
    }

    @Override public final THIS focus()
    {
        m_DarkOverlay.subscribeToInputLayer(0);
        if(p_UseDarkOverlay) LCL.getAppSystem().getRenderLayer(LCL.getAppSystem().getNumberOfRenderLayer() - 1).addRenderable(m_DarkOverlay);

        if(p_OnOverlayClick != null) m_DarkOverlay.touchUpEvent = p_OnOverlayClick;

        if(p_UseDarkOverlay)
        {
            Tween.to(m_DarkOverlay, LCLUniversalAccessor.COLOR_A, p_DarkOverlayAnimDuration)
                    .target(p_DarkOverlayTransparency)
                    .ease(Quad.INOUT)
                    .start(LCLTween.TWEEN_MANAGER);
        }

        onEnter();

        return ((THIS)this);
    }

    @Override public final THIS loseFocus()
    {
        m_DarkOverlay.touchUpEvent = null;

        m_DarkOverlay.unsubscribeFromInputLayer();

        if(p_UseDarkOverlay)
        {
            Tween.to(m_DarkOverlay, LCLUniversalAccessor.COLOR_A, p_DarkOverlayAnimDuration)
                    .target(0)
                    .ease(Quad.INOUT)
                    .setCallback((a, b) -> LCL.getAppSystem().getRenderLayer(LCL.getAppSystem().getNumberOfRenderLayer() - 1).removeRenderable(m_DarkOverlay))
                    .start(LCLTween.TWEEN_MANAGER);
        }

        onExit();

        return ((THIS)this);
    }
}
