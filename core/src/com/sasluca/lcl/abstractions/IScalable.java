package com.sasluca.lcl.abstractions;

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

public interface IScalable<THIS>
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
     * @return The height of the object without scale.
     */
    float   getWidthScale();

    float   getHeightScale();
    float   getOriginalHeight();
    THIS    setScale(float newScale);
    THIS    setWidthScale(float newWidthScale);
    THIS    setHeightScale(float newHeightScale);
    THIS    setScale(float newWidthScale, float newHeightScale);
}
