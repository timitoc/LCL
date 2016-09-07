package com.sasluca.lcl.animation;

import aurelienribon.tweenengine.*;
import com.sasluca.lcl.abstractions.*;
import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.utils.collections.list.LCLList;

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

public class LCLTween
{
    private static Tween TWEEN_INSTANCE;
    private static final LCLTween INSTANCE = new LCLTween();
    public static final TweenManager TWEEN_MANAGER = new TweenManager();
    private static final LCLList<Class> REGISTERED_CLASSES = new LCLList<>();

    private LCLTween() {}

    public static LCLTween addClass(Class clazz)
    {
        if(!REGISTERED_CLASSES.contains(clazz))
        {
            REGISTERED_CLASSES.add(clazz);
            Tween.registerAccessor(clazz, LCLUniversalAccessor.ACCESSOR);
            addClass(Color.class);
        }

        return INSTANCE;
    }

    //<editor-fold desc="Color">
    @Deprecated public static LCLTween setColorR(IColorable colorable, float r) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_R).target(r); return INSTANCE; }
    @Deprecated public static LCLTween setColorG(IColorable colorable, float g) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_G).target(g); return INSTANCE; }
    @Deprecated public static LCLTween setColorB(IColorable colorable, float b) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_B).target(b); return INSTANCE; }
    @Deprecated public static LCLTween setAlpha(IColorable colorable, float alpha) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_A).target(alpha); return INSTANCE; }
    @Deprecated public static LCLTween setColor(IColorable colorable, Color color) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_RGBA).target(color.r, color.g, color.b, color.a); return INSTANCE; }
    @Deprecated public static LCLTween setColor(IColorable colorable, float r, float g, float b, float a) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_RGBA).target(r, g, b, a); return INSTANCE; }

    @Deprecated public static LCLTween setRelativeColorR(IColorable colorable, float r) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_R).targetRelative(r); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeColorG(IColorable colorable, float g) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_G).targetRelative(g); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeColorB(IColorable colorable, float b) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_B).targetRelative(b); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeAlpha(IColorable colorable, float alpha) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_A).targetRelative(alpha); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeColor(IColorable colorable, Color color) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_RGBA).targetRelative(color.r, color.g, color.b, color.a); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeColor(IColorable colorable, float r, float g, float b, float a) { TWEEN_INSTANCE = Tween.set(colorable, LCLUniversalAccessor.COLOR_RGBA).targetRelative(r, g, b, a); return INSTANCE; }

    @Deprecated public static LCLTween toColorR(IColorable colorable, float r, float duration) { TWEEN_INSTANCE = Tween.to(colorable, LCLUniversalAccessor.COLOR_R, duration).target(r); return INSTANCE; }
    @Deprecated public static LCLTween toColorG(IColorable colorable, float g, float duration) { TWEEN_INSTANCE = Tween.to(colorable, LCLUniversalAccessor.COLOR_G, duration).target(g); return INSTANCE; }
    @Deprecated public static LCLTween toColorB(IColorable colorable, float b, float duration) { TWEEN_INSTANCE = Tween.to(colorable, LCLUniversalAccessor.COLOR_B, duration).target(b); return INSTANCE; }
    @Deprecated public static LCLTween toAlpha(IColorable colorable, float a, float duration) { TWEEN_INSTANCE = Tween.to(colorable, LCLUniversalAccessor.COLOR_A, duration).target(a); return INSTANCE; }
    @Deprecated public static LCLTween toColor(IColorable colorable, Color color, float duration) { TWEEN_INSTANCE = Tween.to(colorable, LCLUniversalAccessor.COLOR_RGBA, duration).target(color.r, color.g, color.b, color.a); return INSTANCE; }
    @Deprecated public static LCLTween toColor(IColorable colorable, float r, float g, float b, float a, float duration) { TWEEN_INSTANCE = Tween.to(colorable, LCLUniversalAccessor.COLOR_RGBA, duration).target(r, g, b, a); return INSTANCE; }
    //</editor-fold>

    //<editor-fold desc="Rotation">
    @Deprecated public static LCLTween setRotation(IRotatable rotatable, float rotation) { TWEEN_INSTANCE = Tween.set(rotatable, LCLUniversalAccessor.ROTATION).target(rotation); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeRotation(IRotatable rotatable, float rotation) { TWEEN_INSTANCE = Tween.set(rotatable, LCLUniversalAccessor.ROTATION).targetRelative(rotation); return INSTANCE; }
    //</editor-fold>

    //<editor-fold desc="Size">
    @Deprecated public static LCLTween setWidth(ISizeable sizeable, float width) { TWEEN_INSTANCE = Tween.set(sizeable, LCLUniversalAccessor.WIDTH).target(width); return INSTANCE; }
    @Deprecated public static LCLTween setHeight(ISizeable sizeable, float height) { TWEEN_INSTANCE = Tween.set(sizeable, LCLUniversalAccessor.HEIGHT).target(height); return INSTANCE; }
    @Deprecated public static LCLTween setSize(ISizeable sizeable, float width, float height) { TWEEN_INSTANCE = Tween.set(sizeable, LCLUniversalAccessor.WIDTH_HEIGHT).target(width, height); return INSTANCE; }

    @Deprecated public static LCLTween setRelativeWidth(ISizeable sizeable, float width) { TWEEN_INSTANCE = Tween.set(sizeable, LCLUniversalAccessor.WIDTH).targetRelative(width); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeHeight(ISizeable sizeable, float height) { TWEEN_INSTANCE = Tween.set(sizeable, LCLUniversalAccessor.HEIGHT).targetRelative(height); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeSize(ISizeable sizeable, float width, float height) { TWEEN_INSTANCE = Tween.set(sizeable, LCLUniversalAccessor.WIDTH_HEIGHT).targetRelative(width, height); return INSTANCE; }
    //</editor-fold>

    //<editor-fold desc="Scale">
    @Deprecated public static LCLTween setScale(IScalable scalable, float scale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.SCALE).target(scale); return INSTANCE; }
    @Deprecated public static LCLTween setWidthScale(IScalable scalable, float widthScale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.W_SCALE).target(widthScale); return INSTANCE; }
    @Deprecated public static LCLTween setHeightScale(IScalable scalable, float heightScale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.H_SCALE).target(heightScale); return INSTANCE; }
    @Deprecated public static LCLTween setScale(IScalable scalable, float widthScale, float heightScale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.W_H_SCALE).target(widthScale, heightScale); return INSTANCE; }

    @Deprecated public static LCLTween setRelativeScale(IScalable scalable, float scale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.SCALE).targetRelative(scale); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeWidthScale(IScalable scalable, float widthScale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.W_SCALE).targetRelative(widthScale); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeHeightScale(IScalable scalable, float heightScale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.H_SCALE).targetRelative(heightScale); return INSTANCE; }
    @Deprecated public static LCLTween setRelativeScale(IScalable scalable, float widthScale, float heightScale) { TWEEN_INSTANCE = Tween.set(scalable, LCLUniversalAccessor.W_H_SCALE).targetRelative(widthScale, heightScale); return INSTANCE; }
    //</editor-fold>

    //<editor-fold desc="Transformable">
    @Deprecated public static LCLTween setPosX(ITransformable transformable, float x) { TWEEN_INSTANCE = Tween.set(transformable, LCLUniversalAccessor.POS_X).target(x); return INSTANCE; }
    @Deprecated public static LCLTween setPosY(ITransformable transformable, float y) { TWEEN_INSTANCE = Tween.set(transformable, LCLUniversalAccessor.POS_Y).target(y); return INSTANCE; }
    @Deprecated public static LCLTween setPos(ITransformable transformable, float x, float y) { TWEEN_INSTANCE = Tween.set(transformable, LCLUniversalAccessor.POS_XY).target(x, y); return INSTANCE; }

    @Deprecated public static LCLTween setRelativePosX(ITransformable transformable, float x) { TWEEN_INSTANCE = Tween.set(transformable, LCLUniversalAccessor.POS_X).targetRelative(x); return INSTANCE; }
    @Deprecated public static LCLTween setRelativePosY(ITransformable transformable, float y) { TWEEN_INSTANCE = Tween.set(transformable, LCLUniversalAccessor.POS_Y).targetRelative(y); return INSTANCE; }
    @Deprecated public static LCLTween setRelativePos(ITransformable transformable, float x, float y) { TWEEN_INSTANCE = Tween.set(transformable, LCLUniversalAccessor.POS_XY).targetRelative(x, y); return INSTANCE; }
    //</editor-fold>

    @Deprecated public static void setCombinedAttributesLimit(int limit) { Tween.setCombinedAttributesLimit(limit); }
    @Deprecated public static void setWaypointsLimit(int limit) { Tween.setWaypointsLimit(limit); }
    @Deprecated public static String getVersion() {return Tween.getVersion(); }
    @Deprecated public static int getPoolSize() { return Tween.getPoolSize(); }
    @Deprecated public static void ensurePoolCapacity(int minCapacity) { Tween.ensurePoolCapacity(minCapacity); }
    @Deprecated public static void registerAccessor(Class<?> someClass, TweenAccessor<?> defaultAccessor) { Tween.registerAccessor(someClass, defaultAccessor); }
    @Deprecated public static TweenAccessor<?> getRegisteredAccessor(Class<?> someClass) { return Tween.getRegisteredAccessor(someClass); }
    @Deprecated public static LCLTween to(Object target, int tweenType, float duration) { TWEEN_INSTANCE = Tween.to(target, tweenType, duration); return INSTANCE; }
    @Deprecated public static LCLTween from(Object target, int tweenType, float duration) { TWEEN_INSTANCE = Tween.from(target, tweenType, duration); return INSTANCE; }
    @Deprecated public static LCLTween set(Object target, int tweenType) { TWEEN_INSTANCE = Tween.set(target, tweenType); return INSTANCE; }
    @Deprecated public static LCLTween call(TweenCallback callback) { TWEEN_INSTANCE = Tween.call(callback); return INSTANCE; }
    @Deprecated public static LCLTween mark() { TWEEN_INSTANCE = Tween.mark(); return INSTANCE; }
    @Deprecated public static LCLTween ease(TweenEquation easeEquation) { checkTweenInstance(); TWEEN_INSTANCE.ease(easeEquation); return INSTANCE; }
    @Deprecated public static LCLTween cast(Class<?> targetClass) { checkTweenInstance(); TWEEN_INSTANCE.cast(targetClass); return INSTANCE; }
    @Deprecated public static LCLTween delay(float delay) { checkTweenInstance(); TWEEN_INSTANCE.delay(delay); return INSTANCE; }
    @Deprecated public static LCLTween start() { checkTweenInstance(); TWEEN_INSTANCE.start(TWEEN_MANAGER); return INSTANCE; }
    @Deprecated public static LCLTween kill() { checkTweenInstance(); TWEEN_INSTANCE.kill(); return INSTANCE; }
    @Deprecated public static LCLTween pause() { checkTweenInstance(); TWEEN_INSTANCE.pause(); return INSTANCE; }
    @Deprecated public static LCLTween resume() { checkTweenInstance(); TWEEN_INSTANCE.resume(); return INSTANCE; }
    @Deprecated public static LCLTween repeat(int count, int delay) { checkTweenInstance(); TWEEN_INSTANCE.repeat(count, delay); return INSTANCE; }
    @Deprecated public static LCLTween repeatYoyo(int count, int delay) { checkTweenInstance(); TWEEN_INSTANCE.repeatYoyo(count, delay); return INSTANCE; }
    @Deprecated public static LCLTween setCallback(TweenCallback callback) { checkTweenInstance(); TWEEN_INSTANCE.setCallback(callback); return INSTANCE; }
    @Deprecated public static LCLTween setCallbackTriggers(int flags) { checkTweenInstance(); TWEEN_INSTANCE.setCallbackTriggers(flags); return INSTANCE; }
    @Deprecated public static LCLTween setUserData(Object data) { checkTweenInstance(); TWEEN_INSTANCE.setUserData(data); return INSTANCE; }
    @Deprecated public static float getDelay() { checkTweenInstance(); return TWEEN_INSTANCE.getDelay(); }
    @Deprecated public static float getDuration() { checkTweenInstance(); return TWEEN_INSTANCE.getDuration(); }
    @Deprecated public static int getRepeatCount() { checkTweenInstance(); return TWEEN_INSTANCE.getRepeatCount(); }
    @Deprecated public static float getRepeatDelay() { checkTweenInstance(); return TWEEN_INSTANCE.getRepeatDelay(); }
    @Deprecated public static float getFullDuration() { checkTweenInstance(); return TWEEN_INSTANCE.getFullDuration(); }
    @Deprecated public static Object getUserData() { checkTweenInstance(); return TWEEN_INSTANCE.getUserData(); }
    @Deprecated public static int getStep() { checkTweenInstance(); return TWEEN_INSTANCE.getStep(); }
    @Deprecated public static float getCurrentTime() { checkTweenInstance(); return TWEEN_INSTANCE.getCurrentTime(); }
    @Deprecated public static boolean isStarted() { checkTweenInstance(); return TWEEN_INSTANCE.isStarted(); }
    @Deprecated public static boolean isInitialized() { checkTweenInstance(); return TWEEN_INSTANCE.isInitialized(); }
    @Deprecated public static boolean isFinished() { checkTweenInstance(); return TWEEN_INSTANCE.isFinished(); }
    @Deprecated public static boolean isYoyo() { checkTweenInstance(); return TWEEN_INSTANCE.isYoyo(); }
    @Deprecated public static boolean isPaused() { checkTweenInstance(); return TWEEN_INSTANCE.isPaused(); }
    @Deprecated public static void update(float delta) { checkTweenInstance();TWEEN_INSTANCE.update(delta); }

    private static void checkTweenInstance()
    {
        if(TWEEN_INSTANCE == null)
        {
            //TODO: ERROR
            assert false;
        }
    }
}
