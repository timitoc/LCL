package com.sasluca.lcl.ui.image;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.animation.LCLTween;
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

public class UISprite extends UIView<UISprite> implements IColorable<UISprite>, IFlippable<UISprite>, IOrigin<UISprite>, ISizeable<UISprite>, IScalable<UISprite>, IRotatable<UISprite>
{
    static { LCLTween.addClass(UISprite.class); }

    protected Sprite p_Sprite;

    public UISprite(Texture texture) { p_Sprite = new Sprite(texture); }
    public UISprite(TextureRegion region) { p_Sprite = new Sprite(region); }
    public UISprite(String texture) { p_Sprite = new Sprite(LCL.getResourceManager().<Texture>getResource(texture)); }

    public UISprite setTexture(Texture t) { p_Sprite.setTexture(t); return this; }
    public UISprite setTexture(String t) { p_Sprite.setTexture(LCL.getResourceManager().getResource(t)); return this; }

    //Render
    @Override public void renderImpl()
    {
        p_Sprite.draw(LCL.getSpriteBatch());
    }
    @Override public void updateImpl() { }

    //Transform
    @Override public float getX() { return p_Sprite.getX(); }
    @Override public float getY() { return p_Sprite.getY(); }
    @Override public UISprite setPosX(float newX) { p_Sprite.setX(newX); return this; }
    @Override public UISprite setPosY(float newY) { p_Sprite.setY(newY); return this; }

    //Color
    @Override public Color getColor() { return p_Sprite.getColor(); }
    @Override public UISprite setColor(Color newColor) { p_Sprite.setColor(newColor); return this; }
    @Override public UISprite setAlpha(float newAlpha) { p_Sprite.setAlpha(newAlpha); return this; }

    //Flip
    @Override public boolean isFlipX() { return p_Sprite.isFlipX(); }
    @Override public boolean isFlipY() { return p_Sprite.isFlipY(); }
    @Override public UISprite flipX(boolean flipX) { p_Sprite.setFlip(flipX, isFlipY()); return this; }
    @Override public UISprite flipY(boolean flipY) { p_Sprite.setFlip(isFlipX(), flipY); return this; }
    @Override public UISprite flip(boolean flipX, boolean flipY) { p_Sprite.setFlip(flipX, flipY); return this; }

    //Size and scale
    @Override public float getWidthScale() { return p_Sprite.getScaleX(); }
    @Override public float getHeightScale() { return p_Sprite.getScaleY(); }
    @Override public float getOriginalWidth() { return p_Sprite.getWidth(); }
    @Override public float getOriginalHeight() { return p_Sprite.getHeight(); }
    @Override public float getWidth() { return p_Sprite.getWidth() * p_Sprite.getScaleX(); }
    @Override public float getHeight() { return p_Sprite.getHeight() * p_Sprite.getScaleY(); }
    @Override public UISprite setScale(float newScale) { p_Sprite.setScale(newScale); return this; }
    @Override public UISprite setWidth(float newWidth) { p_Sprite.setSize(newWidth, getHeight()); return this; }
    @Override public UISprite setHeight(float newHeight) { p_Sprite.setSize(getWidth(), newHeight); return this; }
    @Override public UISprite setSize(float newWidth, float newHeight) { p_Sprite.setSize(newWidth, newHeight); return this; }
    @Override public UISprite setWidthScale(float newWidthScale) { p_Sprite.setScale(newWidthScale, getHeightScale()); return this; }
    @Override public UISprite setHeightScale(float newHeightScale) { p_Sprite.setScale(getWidthScale(), newHeightScale); return this; }
    @Override public UISprite setScale(float newWidthScale, float newHeightScale) { p_Sprite.setScale(newWidthScale, newHeightScale); return this; }

    //Rotation and origin
    @Override public float getOriginX() { return p_Sprite.getOriginX(); }
    @Override public float getOriginY() { return p_Sprite.getOriginY(); }
    @Override public float getRotation() { return p_Sprite.getRotation(); }
    @Override public UISprite setRotation(float degrees) { p_Sprite.setRotation(degrees); return this; }
    @Override public UISprite setOriginX(float newOriginX) { p_Sprite.setOrigin(newOriginX, getOriginY()); return this; }
    @Override public UISprite setOriginY(float newOriginY) { p_Sprite.setOrigin(newOriginY, getOriginY()); return this; }
    @Override public UISprite setOrigin(float newOriginX, float newOriginY) { p_Sprite.setOrigin(newOriginX, newOriginY); return this; }
}
