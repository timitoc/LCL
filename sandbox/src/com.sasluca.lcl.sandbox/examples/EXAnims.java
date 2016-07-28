package com.sasluca.lcl.sandbox.examples;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.sandbox.Playground;

/**
 * Created by Sas Luca on 11-Jul-16.
 */

public class EXAnims implements IStateHandler<Playground.State>
{
    LCLSprite m_Sprite;
    LCLSprite m_Sprite2;
    TweenManager m_TweenManager;


    @Override public void onState(Playground.State currentState)
    {
        if(currentState == Playground.State.TEST1) LCLTween.TWEEN_MANAGER.update(LCL.SYS.Delta);
    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        if(newState == Playground.State.TEST1)
        {
            m_Sprite = new LCLSprite(new Texture(Gdx.files.internal("badlogic.jpg")));
            m_Sprite2 = new LCLSprite(new Texture(Gdx.files.internal("badlogic.jpg")));

            LCL.SYS.AppSystem.getRenderLayer(0).addRenderable(m_Sprite);
            LCL.SYS.AppSystem.getRenderLayer(0).addRenderable(m_Sprite2);

            LCLTween.addClass(m_Sprite.getClass());
            //LCLTween.setAlpha(m_Sprite, 0).start();
            //LCLTween.toAlpha(m_Sprite, 1, 2f).ease(Quad.IN).start();

            Tween.set(m_Sprite, LCLUniversalAccessor.POS_XY).target(0, 0).start(LCLTween.TWEEN_MANAGER);
            Tween.set(m_Sprite2, LCLUniversalAccessor.POS_XY).target(100, 100).start(LCLTween.TWEEN_MANAGER);
            Tween.to(m_Sprite, LCLUniversalAccessor.POS_XY, 1f).target(500, 500).ease(Quad.IN).start(LCLTween.TWEEN_MANAGER);
            Tween.to(m_Sprite2, LCLUniversalAccessor.POS_XY, 1f).target(100, 400).ease(Quad.IN).start(LCLTween.TWEEN_MANAGER);
        }
    }
}
