package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.ui.image.UISprite;

/**
 * Created by Sas Luca on 8/7/2016.
 */

public class TestHandler3 implements IStateHandler<Playground.State>
{

    @Override public void onState(Playground.State currentState)
    {
        LCLTween.TWEEN_MANAGER.update(LCL.getDelta());
    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState)
    {
        Texture t = new Texture(Gdx.files.internal("test.png"));
        t.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        int hr = 6;
        int wr = 10;

        TextureRegion tr = new TextureRegion(t);
        tr.setRegion(0, 0, wr * t.getWidth(), hr * t.getHeight());

        UISprite i = new UISprite(tr);
        //i.setScale(2);
        i.setSize(wr * t.getWidth(), hr * t.getHeight());

        LCL.getAppSystem().getRenderLayer(0).addRenderable(i);
    }
}
