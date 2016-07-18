package com.sasluca.lcl.ui.material_design.image;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.ui.material_design.UIView;

/**
 * Created by Sas Luca on 11-Jul-16.
 */
public class UIImage extends UIView<UIImage> implements IColorable<UIImage>, IFlippable<UIImage>, IOrigin<UIImage>, ISizeable<UIImage>, IScalable<UIImage>, IRotatable<UIImage>
{
    static { LCLTween.addClass(UIImage.class); }

    protected Sprite p_Sprite;
    protected boolean p_IsRendering = true;

    public UIImage(Texture texture)
    {
        p_Sprite = new Sprite(texture);
    }

    public UIImage(TextureRegion region)
    {
        p_Sprite = new Sprite(region);
    }

    public UIImage setTexture(Texture t) { p_Sprite.setTexture(t); return this; }

    //Render
    @Override public void renderImpl()
    {
        p_Sprite.draw(LCL.SYS.SpriteBatch);
    }

    @Override public void updateImpl() { }

    @Override public boolean isRendering() { return p_IsRendering; }
    @Override public UIImage setRenderingState(boolean renderingState) { p_IsRendering = renderingState; return this; }


    //Transform
    @Override public float getX() { return p_Sprite.getX(); }
    @Override public float getY() { return p_Sprite.getY(); }
    @Override public UIImage setPosX(float newX) { p_Sprite.setPosition(newX, getY()); return this; }
    @Override public UIImage setPosY(float newY) { p_Sprite.setPosition(getX(), newY); return this; }

    //Color
    @Override public Color getColor() { return p_Sprite.getColor(); }
    @Override public UIImage setColor(Color newColor) { p_Sprite.setColor(newColor); return this; }
    @Override public UIImage setAlpha(float newAlpha) { p_Sprite.setAlpha(newAlpha); return this; }

    //Flip
    @Override public boolean isFlipX() { return p_Sprite.isFlipX(); }
    @Override public boolean isFlipY() { return p_Sprite.isFlipY(); }
    @Override public UIImage flipX(boolean flipX) { p_Sprite.setFlip(flipX, isFlipY()); return this; }
    @Override public UIImage flipY(boolean flipY) { p_Sprite.setFlip(isFlipX(), flipY); return this; }
    @Override public UIImage flip(boolean flipX, boolean flipY) { p_Sprite.setFlip(flipX, flipY); return this; }

    //Size and scale
    @Override public float getWidthScale() { return p_Sprite.getScaleX(); }
    @Override public float getHeightScale() { return p_Sprite.getScaleY(); }
    @Override public float getOriginalWidth() { return p_Sprite.getWidth(); }
    @Override public float getOriginalHeight() { return p_Sprite.getHeight(); }
    @Override public float getWidth() { return p_Sprite.getWidth() * p_Sprite.getScaleX(); }
    @Override public float getHeight() { return p_Sprite.getHeight() * p_Sprite.getScaleY(); }
    @Override public UIImage setScale(float newScale) { p_Sprite.setScale(newScale); return this; }
    @Override public UIImage setWidth(float newWidth) { p_Sprite.setSize(newWidth, getHeight()); return this; }
    @Override public UIImage setHeight(float newHeight) { p_Sprite.setSize(getWidth(), newHeight); return this; }
    @Override public UIImage setSize(float newWidth, float newHeight) { p_Sprite.setSize(newWidth, newHeight); return this; }
    @Override public UIImage setWidthScale(float newWidthScale) { p_Sprite.setScale(newWidthScale, getHeightScale()); return this; }
    @Override public UIImage setHeightScale(float newHeightScale) { p_Sprite.setScale(getWidthScale(), newHeightScale); return this; }
    @Override public UIImage setScale(float newWidthScale, float newHeightScale) { p_Sprite.setScale(newWidthScale, newHeightScale); return this; }

    //Rotation and origin
    @Override public float getOriginX() { return p_Sprite.getOriginX(); }
    @Override public float getOriginY() { return p_Sprite.getOriginY(); }
    @Override public float getRotation() { return p_Sprite.getRotation(); }
    @Override public UIImage setRotation(float degrees) { p_Sprite.setRotation(degrees); return this; }
    @Override public UIImage setOriginX(float newOriginX) { p_Sprite.setOrigin(newOriginX, getOriginY()); return this; }
    @Override public UIImage setOriginY(float newOriginY) { p_Sprite.setOrigin(newOriginY, getOriginY()); return this; }
    @Override public UIImage setOrigin(float newOriginX, float newOriginY) { p_Sprite.setOrigin(newOriginX, newOriginY); return this; }
}
