package com.sasluca.lcl.animation;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.*;

/**
 * Created by Sas Luca on 29-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLUniversalAccessor implements TweenAccessor
{
    public static final int COLOR_R = 1;
    public static final int COLOR_G = 2;
    public static final int COLOR_B = 3;
    public static final int COLOR_A = 4;
    public static final int COLOR_RGBA = 5;

    public static final int ROTATION = 6;

    public static final int W_SCALE = 7;
    public static final int H_SCALE = 8;
    public static final int W_H_SCALE = 9;
    public static final int SCALE = 10;

    public static final int WIDTH = 11;
    public static final int HEIGHT = 12;
    public static final int WIDTH_HEIGHT = 13;

    public static final int POS_X = 14;
    public static final int POS_Y = 15;
    public static final int POS_XY = 16;


    @Override public int getValues(Object object, int tweentype, float[] floats)
    {
        switch(tweentype)
        {
            //<editor-fold desc="Color">
            case COLOR_R:
                floats[0] = object instanceof IColorable ? ((IColorable)object).getColor().r : ((Color)object).r;
                return 1;

            case COLOR_G:
                floats[0] = object instanceof IColorable ? ((IColorable)object).getColor().g : ((Color)object).g;
                return 1;

            case COLOR_B:
                floats[0] = object instanceof IColorable ? ((IColorable)object).getColor().b : ((Color)object).b;
                return 1;

            case COLOR_A:
                floats[0] = object instanceof IColorable ? ((IColorable)object).getColor().a : ((Color)object).a;
                return 1;

            case COLOR_RGBA:
                floats[0] = object instanceof IColorable ? ((IColorable)object).getColor().r : ((Color)object).r;
                floats[1] = object instanceof IColorable ? ((IColorable)object).getColor().g : ((Color)object).g;
                floats[2] = object instanceof IColorable ? ((IColorable)object).getColor().b : ((Color)object).b;
                floats[3] = object instanceof IColorable ? ((IColorable)object).getColor().a : ((Color)object).a;
                return 4;
            //</editor-fold>

            //<editor-fold desc="Rotation">
            case ROTATION:
                floats[0] = ((IRotatable)object).getRotation();
                return 1;
            //</editor-fold>

            //<editor-fold desc="Scale">
            case W_SCALE:
                floats[0] = ((IScalable)object).getWidthScale();
                return 1;

            case H_SCALE:
                floats[0] = ((IScalable)object).getHeightScale();
                return 1;

            case W_H_SCALE:
                floats[0] = ((IScalable)object).getWidthScale();
                floats[1] = ((IScalable)object).getHeightScale();
                return 2;
            //</editor-fold>

            //<editor-fold desc="Size">
            case WIDTH:
                floats[0] = ((ISizeable)object).getWidth();
                return 1;

            case HEIGHT:
                floats[0] = ((ISizeable)object).getHeight();
                return 1;

            case WIDTH_HEIGHT:
                floats[0] = ((ISizeable)object).getWidth();
                floats[1] = ((ISizeable)object).getHeight();
                return 2;
            //</editor-fold>

            //<editor-fold desc="Transform">
            case POS_X:
                floats[0] = ((ITransformable)object).getX();
                return 1;

            case POS_Y:
                floats[0] = ((ITransformable)object).getY();
                return 1;

            case POS_XY:
                floats[0] = ((ITransformable)object).getX();
                floats[1] = ((ITransformable)object).getY();
                return 2;
            //</editor-fold>

            default:
                assert false;
                return 0;
        }
    }

    @Override public void setValues(Object object, int tweentype, float[] floats)
    {
        switch(tweentype)
        {
            //<editor-fold desc="Color">
            case COLOR_R:
                if(object instanceof IColorable) ((IColorable)object).getColor().r = floats[0]; else ((Color)object).r = floats[0];
                break;

            case COLOR_G:
                if(object instanceof IColorable) ((IColorable)object).getColor().g = floats[0]; else ((Color)object).g = floats[0];
                break;

            case COLOR_B:
                if(object instanceof IColorable) ((IColorable)object).getColor().b = floats[0]; else ((Color)object).b = floats[0];
                break;

            case COLOR_A:
                if(object instanceof IColorable) ((IColorable)object).setAlpha(floats[0]); else ((Color)object).a = floats[0];
                break;

            case COLOR_RGBA:
                if(object instanceof IColorable) ((IColorable)object).getColor().r = floats[0]; else ((Color)object).r = floats[0];
                if(object instanceof IColorable) ((IColorable)object).getColor().g = floats[1]; else ((Color)object).g = floats[1];
                if(object instanceof IColorable) ((IColorable)object).getColor().b = floats[2]; else ((Color)object).b = floats[2];
                if(object instanceof IColorable) ((IColorable)object).setAlpha(floats[3]); else ((Color)object).a = floats[3];
                break;
            //</editor-fold>

            //<editor-fold desc="Rotation">
            case ROTATION:
                ((IRotatable)object).setRotation(floats[0]);
                break;
            //</editor-fold>

            //<editor-fold desc="Scale">
            case W_SCALE:
                ((IScalable)object).setWidthScale(floats[0]);
                break;

            case H_SCALE:
                ((IScalable)object).setHeightScale(floats[0]);
                break;

            case W_H_SCALE:
                ((IScalable)object).setScale(floats[0], floats[1]);
                break;

            case SCALE:
                ((IScalable)object).setScale(floats[0]);
                break;
            //</editor-fold>

            //<editor-fold desc="Size">
            case WIDTH:
                ((ISizeable)object).setWidth(floats[0]);
                break;

            case HEIGHT:
                ((ISizeable)object).setHeight(floats[0]);
                break;

            case WIDTH_HEIGHT:
                ((ISizeable)object).setSize(floats[0], floats[1]);
                break;
            //</editor-fold>

            //<editor-fold desc="Transform">
            case POS_X:
                ((ITransformable)object).setPosX(floats[0]);
                break;

            case POS_Y:
                ((ITransformable)object).setPosY(floats[0]);
                break;

            case POS_XY:
                ((ITransformable)object).setPosX(floats[0]);
                ((ITransformable)object).setPosY(floats[1]);
                break;
            //</editor-fold>

            default:
                assert false; break;
        }
    }
}
