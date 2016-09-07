package com.sasluca.lcl.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IScalable;
import com.sasluca.lcl.abstractions.ITransformable;
import com.sasluca.lcl.utils.tuples.imutable.LCLPair;
import com.sasluca.lcl.utils.tuples.LCLTuple;

import java.util.Random;

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

public class LCLUtils
{
    /**
     * Consider you have a line the length of the distance between two points starting from the first point(denoted by aX and aY) and with a rotation of 0.
     * THIS method returns degree at which you have to rotate the line in order for it's end to match the second point(denoted by bX and bY) considering
     * it's starts from the first point
     * @param aX The position of the first point on the X axis
     * @param aY The position of the first point on the Y axis
     * @param bX The position of the second point on the X axis
     * @param bY The position of the second point on the Y axis
     * @return The degree at which you have to rotate the line in order for it's end to match the second point(denoted by bX and bY) considering it's starts from the first point
     */
    public static float angleBetweenPoints(float aX, float aY, float bX, float bY) { return (float) (Math.toDegrees(Math.atan2(bY - aY, bX - aX))); }

    /**
     * THIS method will set the position of a class that implements TsunTransform and TsunScale to the center of your screen (assuming the camera is at it's default position)
     * @param object The affected object
     * @param centerX Whether to center the object on the X axis or not
     * @param centerY Whether to center the object on the Y axis or not
     */
    public static void center(ITransformable object, boolean centerX, boolean centerY) { object.setPosX(centerX ? LCL.getCamera().viewportWidth / 2 - object.getWidth() / 2 : object.getX()); object.setPosY(centerY ? LCL.getCamera().viewportHeight / 2 - object.getHeight() / 2 : object.getY()); }

    public static LCLPair<Float, Float> getCenterPosition(ITransformable object) { return (LCLPair<Float, Float>) LCLTuple.tuple(LCL.getCamera().viewportWidth / 2 - object.getWidth() / 2, LCL.getCamera().viewportHeight / 2 - object.getHeight() / 2); }

    /**
     * THIS method will set the position of an object to the center of a point denoted by pointX and pointY
     * @param object The affected object
     * @param pointX The position of the point the object will be centered to on the X axis
     * @param pointY The position of the point the object will be centered to on the Y axis
     * @param centerX Whether to center the object on the X axis or not
     * @param centerY Whether to center the object on the Y axis or not
     */
    public static void centerToPoint(ITransformable object, float pointX, float pointY, boolean  centerX, boolean centerY) { object.setPosX(centerX ? pointX - object.getWidth() / 2 : object.getX()); object.setPosY(centerY ? pointY - object.getHeight() / 2 : object.getY()); }

    public static LCLPair<Float, Float> getCenterToPointPosition(ITransformable object, float pointX, float pointY) { return (LCLPair<Float, Float>) LCLTuple.tuple(pointX - object.getWidth() / 2, pointY - object.getHeight() / 2); }

    /**
     * THIS method will set the position of a object to the center of m_Target
     * @param object The affected object
     * @param target The object to which the object will be centered to
     * @param centerX Whether to center the object on the X axis or not
     * @param centerY Whether to center the object on the Y axis or not
     */
    public static void centerToDrawable(ITransformable object, ITransformable target, boolean centerX, boolean centerY) { object.setPosX(centerX ? target.getX() + target.getWidth() / 2 - object.getWidth() / 2 : object.getX()); object.setPosY(centerY ? target.getY() + target.getHeight() / 2 - object.getHeight() / 2 : object.getY()); }

    public static LCLPair<Float, Float> getCenterToDrawablePosition(ITransformable object, ITransformable target) { return (LCLPair<Float, Float>) LCLTuple.tuple(target.getWidth() / 2 - object.getWidth() / 2, target.getHeight() / 2 - object.getHeight() / 2); }

    /**
     * Changes the scales of a {@link com.sasluca.lcl.abstractions.IScalable} to fit a size
     * @param object The {@link com.sasluca.lcl.abstractions.IScalable}
     * @param newWidth The new width (in pixels)
     * @param newHeight The new height (int pixels)
     */
    public static void setScaleByPixels(IScalable object, float newWidth, float newHeight) { object.setScale(newWidth / object.getOriginalWidth(), newHeight / object.getOriginalHeight()); }

    /**
     * Checks to see whether a point is inside the area of a rectangle (Sprite and Labels are rectangles)
     * @param touchX The position of the touch on the X axis
     * @param touchY The position of the touch on the Y axis
     * @param x The position of the object on the X axis
     * @param y The position of the object on the Y axis
     * @param width The width of the object
     * @param height The height of the object
     * @return Whether the point is inside the object
     */
    public static boolean checkTouch(float touchX, float touchY, float x, float y, float width, float height) { return touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height; }

    public static boolean pointIsInside(ITransformable obj, float pointX, float pointY) { return (pointX >= obj.getX() && pointX <= obj.getWidth() + obj.getX()) && (pointY >= obj.getY() && pointY <= obj.getHeight() + obj.getY()); }

    /**
     * @param x1 The x position of the 1st point
     * @param x2 The x position of the 2nd point
     * @return The distance between two points in 1 dimension
     */
    public static float distanceBetweenPoints(float x1, float x2)
    {
        return (x1 > x2) ? (x1 - x2) : (x2 - x1);
    }

    /**
     * @param x1 The x position of the 1st point
     * @param y1 The y position of the 1st point
     * @param x2 The x position of the 2nd point
     * @param y2 The y position of the 2nd point
     * @return The distance between two points in 2 dimensions
     */
    public static float distanceBetweenPoints(float x1, float y1, float x2, float y2) { return (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)); }

    public static boolean compareColors(Color a, Color b) { if((a.r == b.r) && (a.g == b.g) && (a.b == b.b)) return true; else return false; }

    private static final Random random = new Random();
    /**
     * @param min The min of the range
     * @param max The max of the range
     * @return A random number that is greater or equal to min and smaller or equal to max
     */
    public static int getRange(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * @param n The dimension
     * @param p The points. The arguments must be in this order x1, y1, z1, ... , x2, y2, z3, ...
     * @return The distance between two points
     */
    public static float distanceBetweenPoints(int n, float... p)
    {
        float answer = 0;
        for(int i = 0; i < n; i++) answer += Math.pow((p[i] - p[i + n]), 2);
        return (float) Math.sqrt(answer);
    }

    /*
    public static TsunSprite getSprite(int width, int height, TsunBaseRender render)
    {
        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);

        Matrix4 m = new Matrix4();

        m.setToOrtho2D(0, 0, fbo.getWidth(), fbo.getHeight());

        float w = TsunTsun.getCamera().viewportWidth;
        float h = TsunTsun.getCamera().viewportHeight;

        TsunTsun.getBatch().setProjectionMatrix(m);

        boolean b = TsunTsun.isRendering();
        if(TsunTsun.isRendering()) TsunTsun.batchEnd();

        //TsunTsun.getBatch().enableBlending();
        //TsunTsun.getBatch().setBlendFunction(-1, -1);
        //Gdx.gl20.glBlendFuncSeparate(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE, GL20.GL_ONE);

        fbo.begin();

        TsunTsun.getBatch().begin();

        Gdx.gl.glClearColor(0F, 0F, 0F, 0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        render.render();

        TsunTsun.getBatch().end();

        fbo.end();

        if(b) TsunTsun.batchBegin();

        //TsunTsun.getBatch().setBlendFunction(-1, -1);
        //Gdx.gl20.glBlendFuncSeparate(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA,GL20.GL_ONE, GL20.GL_DST_ALPHA);

        TextureRegion textureRegion = new TextureRegion(fbo.getColorBufferTexture());

        textureRegion.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureRegion.flip(false, true);

        return new LCLSprite(textureRegion);
    }*/

    private static long start = System.currentTimeMillis();
    /**
     * THIS method can be used to limit the FPS under 60 FPS
     * @param fps the FPS cap. Must be under 60 fps
     */
    public static void limitFPS(int fps)
    {
        if(fps > 0)
        {
            long diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) try { Thread.sleep(targetDelay - diff); } catch(InterruptedException e) {  }
            start = System.currentTimeMillis();
        }
    }

    private static Vector3 vector3 = new Vector3(0, 0, 0);
    public static void startScrissor(float x, float y, float width, float height)
    {
        vector3.x = x;
        LCL.getCamera().project(vector3);
        x = vector3.x;

        vector3.y = y;
        LCL.getCamera().project(vector3);
        y = vector3.y;

        vector3.x = width;
        LCL.getCamera().project(vector3);
        width = vector3.x;

        vector3.y = height;
        LCL.getCamera().project(vector3);
        height = vector3.y;

        LCL.getSpriteBatch().flush();

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor((int)x, (int)y, (int)width, (int)height);
    }

    public static void endScrissor() { Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST); LCL.getSpriteBatch().flush(); }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isInteger(String s) { try { Integer.parseInt(s); } catch(NumberFormatException e) { return false; } catch(NullPointerException e) { return false; } return true; }
}
