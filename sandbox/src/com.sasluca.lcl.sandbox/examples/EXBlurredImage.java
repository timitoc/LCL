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
    LCLSprite m_Sprite;

    @Override public void onState(State currentState)
    {
        if(currentState == TEST1) m_Sprite.render();
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        if(newState == TEST1)
        {
            Pixmap orig = new Pixmap(Gdx.files.internal("test.png"));
            orig.setColor(Color.BLACK);
            Pixmap blurred = EXTBlurUtils.blur(orig, 6, 2, true);

            Texture blurTex = new Texture(blurred);
            m_Sprite = new LCLSprite(blurTex);
            blurred.dispose();
        }
    }
}
