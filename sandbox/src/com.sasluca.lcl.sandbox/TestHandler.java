package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.input.events.ITouchUpEvent;
import com.sasluca.lcl.ui.material_design.group.UIGroup;
import com.sasluca.lcl.ui.material_design.image.UIImage;
import com.sasluca.lcl.ui.material_design.label.UILabel;
import com.sasluca.lcl.ui.material_design.lists.genericlists.snaplists.UISnapHorizontalList;
import com.sasluca.lcl.utils.LCLUtils;

import static com.sasluca.lcl.sandbox.Playground.State;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler implements IStateHandler<State>
{

    UISnapHorizontalList m_SnapList;

    @Override public void onState(State currentState)
    {

    }

    @Override public void onChangeState(State currentState, State newState)
    {
        m_SnapList = new UISnapHorizontalList(720, 1280);

        UIImage btn = new UIImage(LCL.SYS.ResourceManger.<Texture>getResource("badlogic"));
        btn.setSize(100, 100)
                .setPosX(600)
                .setPosY(20)
                .subscribeToInputLayer(0);

        btn.onTouchUp = (x, y, p, b, sender) -> { m_SnapList.nextItem(); };


        for(int i = 0; i < 10; i++)
        {
            UIGroup g = new UIGroup();
            UILabel l = new UILabel("Roboto", Integer.toString(i), Color.WHITE);
            UIImage s = new UIImage(LCL.SYS.ResourceManger.<Texture>getResource("default"));
            s.setWidth(720).setHeight(1280);
            LCLUtils.centerToDrawable(l, s, true, true);
            g.addObject(s).addObject(l);

            if(i != 0) s.setColor(i % 2 == 0 ? Color.BLUE : Color.RED); else s.setColor(Color.BLUE);

            m_SnapList.addItem(g);
        }

        m_SnapList.subscribeToInputLayer(0);
        LCLUtils.center(m_SnapList, true, true);

        LCL.SYS.AppSystem.addRenderHandler(m_SnapList);
        LCL.SYS.AppSystem.addRenderHandler(btn);
    }
}
