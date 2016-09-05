package com.sasluca.lcl.sandbox.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.sandbox.Playground.State;
import com.sasluca.lcl.utils.group.LCLGroup;

/**
 * Created by Sas Luca on 30-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class EXGroup implements IStateHandler<State>
{
    private LCLSprite m_Sprite1;
    private LCLSprite m_Sprite2;
    private LCLGroup m_Group;

    @Override public void onState(State currentState)
    {
        m_Group.render();
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        m_Sprite1 = new LCLSprite(texture);
        m_Sprite2 = new LCLSprite(texture);
        m_Sprite2.setPosX(m_Sprite1.getWidth()).setPosY(m_Sprite1.getHeight());

        m_Group = new LCLGroup();
        m_Group.addObject(m_Sprite1).addObject(m_Sprite2);
        m_Group.setPosX(-100);
    }
}
