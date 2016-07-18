package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.ui.material_design.group.UIGroup;
import com.sasluca.lcl.ui.material_design.image.UIImage;
import com.sasluca.lcl.ui.material_design.label.UILabel;
import com.sasluca.lcl.ui.material_design.lists.genericlists.scrolllists.UIVerticalList;
import com.sasluca.lcl.ui.material_design.lists.genericlists.snaplists.UISnapHorizontalList;
import com.sasluca.lcl.utils.LCLUtils;

import static com.sasluca.lcl.sandbox.Playground.State;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler implements IStateHandler<State>
{

    UIVerticalList list;

    @Override public void onState(State currentState)
    {
        list.render();
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        list = new UIVerticalList(500, 800);
        //LCLUtils.center(list, true, true);

        UIGroup g = new UIGroup();
        UILabel l = new UILabel("Roboto", "Text", Color.BLUE);
        UIImage m = new UIImage(LCL.SYS.ResourceManger.<Texture>getResource("default"));
        m.setSize(500, 900).setColor(Color.GRAY);
        LCLUtils.centerToDrawable(l, m, true, true);
        g.addObject(m).addObject(l);

        list.addItem(g);
        list.subscribeToInputLayer(0);

        LCL.SYS.AppSystem.addRenderHandler(list);
    }
}
