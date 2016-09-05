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

/** Getters and setters for the origin. The origin is based on the height and width of the object at pos(0,0) */
public interface IOrigin<THIS>
{
    float   getOriginX();
    float   getOriginY();
    THIS    setOriginX(float newOriginX);
    THIS    setOriginY(float newOriginY);
    THIS    setOrigin(float newOriginX, float newOriginY);
}
