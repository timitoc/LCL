package com.sasluca.lcl.sandbox;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Circ;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.sprite.LCLSprite;

import static com.sasluca.lcl.sandbox.Playground.State;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

class ColorAccesor implements TweenAccessor<IColorable>
{

    public static final int ALPHA = 1;

    @Override public int getValues(IColorable iColorable, int tweentype, float[] floats)
    {
        switch(tweentype)
        {
            case ALPHA:
                floats[0] = iColorable.getColor().a;
                return 1;

            default:
                assert false;
                return 0;
        }
    }

    @Override public void setValues(IColorable iColorable, int tweentype, float[] floats)
    {
        switch(tweentype)
        {
            case ALPHA:
                //System.out.println(floats[0]);
                iColorable.setAlpha(floats[0]);
                break;

            default:
                assert false; break;
        }
    }
}

public class TestHandler implements IStateHandler<State>
{
    LCLSprite m_Sprite;
    LCLSprite m_Sprite2;
    TweenManager m_TweenManager;


    @Override public void onState(State currentState)
    {
        if(currentState == State.TEST1) LCLTween.TWEEN_MANAGER.update(LCL.SYS.Delta);
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        if(newState == State.TEST1)
        {
            m_Sprite = new LCLSprite(new Texture(Gdx.files.internal("badlogic.jpg")));
            m_Sprite2 = new LCLSprite(new Texture(Gdx.files.internal("badlogic.jpg")));

            LCL.SYS.AppSystem.addRenderHandler(m_Sprite);
            LCL.SYS.AppSystem.addRenderHandler(m_Sprite2);

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
