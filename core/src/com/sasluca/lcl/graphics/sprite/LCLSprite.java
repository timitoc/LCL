package com.sasluca.lcl.graphics.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.animation.LCLTween;

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

/**
 *  A wrapper class for {@link Sprite} with the abstractions from LCL
 */
public class LCLSprite implements IRenderable<LCLSprite>, ITransformable<LCLSprite>, IColorable<LCLSprite>, IFlippable<LCLSprite>, ISizeable<LCLSprite>, IScalable<LCLSprite>, IRotatable<LCLSprite>, IOrigin<LCLSprite>
{
    static { LCLTween.addClass(LCLSprite.class); }

    protected Sprite p_Sprite;
    protected boolean p_IsRendering = true;

    public LCLSprite(Texture texture)
    {
        p_Sprite = new Sprite(texture);
    }

    public LCLSprite(TextureRegion region)
    {
        p_Sprite = new Sprite(region);
    }

    public LCLSprite setTexture(Texture t) { p_Sprite.setTexture(t); return this; }

    //Render
    @Override public void render()
    {
        if(!p_IsRendering) return;

        p_Sprite.draw(LCL.getSpriteBatch());
    }

    @Override public boolean isRendering() { return p_IsRendering; }
    @Override public LCLSprite setRenderingState(boolean renderingState) { p_IsRendering = renderingState; return this; }


    //Transform
    @Override public float getX() { return p_Sprite.getX(); }
    @Override public float getY() { return p_Sprite.getY(); }
    @Override public LCLSprite setPosX(float newX) { p_Sprite.setPosition(newX, getY()); return this; }
    @Override public LCLSprite setPosY(float newY) { p_Sprite.setPosition(getX(), newY); return this; }

    //Color
    @Override public Color getColor() { return p_Sprite.getColor(); }
    @Override public LCLSprite setColor(Color newColor) { p_Sprite.setColor(newColor); return this; }
    @Override public LCLSprite setAlpha(float newAlpha) { p_Sprite.setAlpha(newAlpha); return this; }

    //Flip
    @Override public boolean isFlipX() { return p_Sprite.isFlipX(); }
    @Override public boolean isFlipY() { return p_Sprite.isFlipY(); }
    @Override public LCLSprite flipX(boolean flipX) { p_Sprite.setFlip(flipX, isFlipY()); return this; }
    @Override public LCLSprite flipY(boolean flipY) { p_Sprite.setFlip(isFlipX(), flipY); return this; }
    @Override public LCLSprite flip(boolean flipX, boolean flipY) { p_Sprite.setFlip(flipX, flipY); return this; }

    //Size and scale
    @Override public float getWidthScale() { return p_Sprite.getScaleX(); }
    @Override public float getHeightScale() { return p_Sprite.getScaleY(); }
    @Override public float getOriginalWidth() { return p_Sprite.getWidth(); }
    @Override public float getOriginalHeight() { return p_Sprite.getHeight(); }
    @Override public float getWidth() { return p_Sprite.getWidth() * p_Sprite.getScaleX(); }
    @Override public float getHeight() { return p_Sprite.getHeight() * p_Sprite.getScaleY(); }
    @Override public LCLSprite setScale(float newScale) { p_Sprite.setScale(newScale); return this; }
    @Override public LCLSprite setWidth(float newWidth) { p_Sprite.setSize(newWidth, getHeight()); return this; }
    @Override public LCLSprite setHeight(float newHeight) { p_Sprite.setSize(getWidth(), newHeight); return this; }
    @Override public LCLSprite setSize(float newWidth, float newHeight) { p_Sprite.setSize(newWidth, newHeight); return this; }
    @Override public LCLSprite setWidthScale(float newWidthScale) { p_Sprite.setScale(newWidthScale, getHeightScale()); return this; }
    @Override public LCLSprite setHeightScale(float newHeightScale) { p_Sprite.setScale(getWidthScale(), newHeightScale); return this; }
    @Override public LCLSprite setScale(float newWidthScale, float newHeightScale) { p_Sprite.setScale(newWidthScale, newHeightScale); return this; }

    //Rotation and origin
    @Override public float getOriginX() { return p_Sprite.getOriginX(); }
    @Override public float getOriginY() { return p_Sprite.getOriginY(); }
    @Override public float getRotation() { return p_Sprite.getRotation(); }
    @Override public LCLSprite setRotation(float degrees) { p_Sprite.setRotation(degrees); return this; }
    @Override public LCLSprite setOriginX(float newOriginX) { p_Sprite.setOrigin(newOriginX, getOriginY()); return this; }
    @Override public LCLSprite setOriginY(float newOriginY) { p_Sprite.setOrigin(newOriginY, getOriginY()); return this; }
    @Override public LCLSprite setOrigin(float newOriginX, float newOriginY) { p_Sprite.setOrigin(newOriginX, newOriginY); return this; }
}
