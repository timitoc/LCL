package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IScalable<This>
{
    /** 
     * If a class implements both {@link ITransformable} and {@link IScalable}
     * then {@link ITransformable#getWidth()} will return (width * widthScale)
     * and this method will return the width without the scale. 
     * @return The width of the object without scale.
     */
    float   getOriginalWidth();

    /**
     * If a class implements both {@link ITransformable} and {@link IScalable}
     * then {@link ITransformable#getHeight()} will return (height* heightScale)
     * and this method will return the height without the scale.
     * @return The heigth of the object without scale.
     */
    float   getWidthScale();
    float   getHeightScale();
    float   getOriginalHeight();
    This    setScale(float newScale);
    This    setWidthScale(float newWidthScale);
    This    setHeightScale(float newHeightScale);
    This    setScale(float newWidthScale, float newHeightScale);
}
