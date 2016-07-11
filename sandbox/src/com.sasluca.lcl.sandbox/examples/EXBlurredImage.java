package com.sasluca.lcl.sandbox.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.sandbox.Playground.State;
import com.sasluca.lcl.utils.blurutils.EXTBlurUtils;

import static com.sasluca.lcl.sandbox.Playground.State.*;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class EXBlurredImage implements IStateHandler<State>
{
    LCLSprite m_Shadow;
    LCLSprite m_Card;
    Texture m_Texture;

    @Override public void onState(State currentState)
    {
        //m_Shadow.render();
        m_Card.render();
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        if(newState == TEST1)
        {
            Pixmap orig = new Pixmap(Gdx.files.internal("Shadow1.png"));
            orig.setColor(Color.BLACK);
            Pixmap blurred = EXTBlurUtils.blur(orig, 6, 1, true);

            m_Texture = new Texture(blurred);
            m_Shadow = new LCLSprite(m_Texture);
            m_Shadow.setSize(300, 300).setPosX(80).setPosY(80);

            m_Card = new LCLSprite(new Texture(Gdx.files.internal("Shadow1.png")));
            m_Card.setSize(300, 300).setPosX(100).setPosY(100);
            blurred.dispose();
        }
    }
}
