package com.sasluca.lcl.graphics.mask;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IMasking;
import com.sasluca.lcl.abstractions.IScalable;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.abstractions.ITransformable;
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

public class LCLMask implements IMasking<LCLMask>, ITransformable<LCLMask>, ISizeable<LCLMask>, IScalable<LCLMask>
{
    static { LCLTween.addClass(LCLMask.class); }

    protected boolean p_Enabled;
    protected float p_WidthScale;
    protected int p_NumberInStack;
    protected boolean p_IsMasking;
    protected float p_HeightScale;
    protected Rectangle p_Rectangle;

    protected static Rectangle CLIP_BOUNDS = new Rectangle();
    protected static int NumberOfScissors = 0;
    
    public LCLMask(float x, float y, float width, float height, float widthScale, float heightScale)
    {
        p_Rectangle = new Rectangle(x, y, width * widthScale, height * heightScale);

        p_WidthScale = widthScale;
        p_HeightScale = heightScale;

        p_Enabled = true;
        p_NumberInStack = -1;
    }

    public LCLMask(float x, float y, float width, float height)
    {
        this(x, y, width, height, 1, 1);
    }

    /** All objects rendered after {@link #start()} will be inside the mask */
    public void start()
    {
        if(!p_Enabled) return;

        if(getWidth() < 1 || getHeight() < 1)
        {
            System.out.println("LCLMask: width or height can not be smaller than 1");
            return;
        }

        p_IsMasking = true;

        ScissorStack.calculateScissors(LCL.getCamera(), LCL.getSpriteBatch().getTransformMatrix(), p_Rectangle, CLIP_BOUNDS);
        ScissorStack.pushScissors(CLIP_BOUNDS);

        p_NumberInStack = NumberOfScissors++;
    }

    /** Objects rendered after {@link #end()} will not be inside the mask. Remember you must call {@link #end()} before you call {@link #start()} again*/
    public void end()
    {
        if(!p_Enabled) return;

        p_IsMasking = false;

        ScissorStack.popScissors();

        //TODO: Test this
        if(--NumberOfScissors == 0) LCL.getSpriteBatch().flush();

        p_NumberInStack = -1;
    }

    public boolean isMasking() { return p_IsMasking; }
    public int getNumberInStack() { return p_NumberInStack; }

    public static int getNumberOfScissors() { return NumberOfScissors; }

    @Override public float getOriginalWidth() { return p_Rectangle.getWidth() / p_WidthScale; }
    @Override public float getOriginalHeight() { return p_Rectangle.getHeight() / p_HeightScale; }
    @Override public float getWidthScale() { return p_WidthScale; }
    @Override public float getHeightScale() { return p_HeightScale; }

    @Override public float getX() { return p_Rectangle.getX(); }
    @Override public float getY() { return p_Rectangle.getY(); }
    @Override public float getWidth() { return p_Rectangle.getWidth(); }
    @Override public float getHeight() { return p_Rectangle.getHeight(); }

    @Override public LCLMask setPosX(float newX) { p_Rectangle.x = newX; return this; }
    @Override public LCLMask setPosY(float newY) { p_Rectangle.y = newY; return this; }

    @Override public LCLMask setWidth(float newWidth) { p_Rectangle.width = newWidth * p_WidthScale; return this; }
    @Override public LCLMask setHeight(float newHeight) { p_Rectangle.height = newHeight * p_HeightScale; return this; }
    @Override public LCLMask setScale(float newScale) { setWidthScale(newScale).setHeightScale(newScale); return this; }
    @Override public LCLMask setSize(float newWidth, float newHeight) { setWidth(newWidth).setHeight(newHeight); return this; }
    @Override public LCLMask setWidthScale(float w) { p_Rectangle.width = getOriginalWidth() * w; p_WidthScale = w; return this;}
    @Override public LCLMask setHeightScale(float h) { p_Rectangle.height = getOriginalHeight() * h; p_HeightScale = h; return this;}
    @Override public LCLMask setScale(float newWidthScale, float newHeightScale) { setWidthScale(newWidthScale).setHeightScale(newHeightScale); return this; }

    @Override public boolean isMaskingEnabled() { return p_Enabled; }
    @Override public LCLMask setMasking(boolean masking) { p_Enabled = masking; return this; }
}
