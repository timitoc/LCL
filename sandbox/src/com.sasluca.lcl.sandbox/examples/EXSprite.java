package com.sasluca.lcl.sandbox.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.sprite.LCLSprite;

import static com.sasluca.lcl.sandbox.Playground.State;

/**
 * Created by Sas Luca on 21-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class EXSprite implements IStateHandler<State>
{
    LCLSprite sprite;

    @Override public void onState(State currentState)
    {
        if(currentState == State.TEST1)
        {
            sprite.render();
        }
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        if(newState == State.TEST1)
        {
            sprite = new LCLSprite(new Texture(Gdx.files.local("badlogic.jpg")));
            sprite.setColor(Color.BLACK).setScale(2).flipX(true);
        }
    }
}
