package com.sasluca.lcl.utils.group;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.IColorable;
import com.sasluca.lcl.abstractions.IRender;
import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.ITransformable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.sprite.LCLSprite;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLGroup implements IRenderable<LCLGroup>, IColorable<LCLGroup>, ITransformable<LCLGroup>
{
    static { LCLTween.addClass(LCLGroup.class); }

    private float m_X;
    private float m_Y;
    private Color m_Color;
    private float m_Width;
    private float m_Height;
    private LCLArray m_Objects;
    private boolean m_IsRendering;

    public LCLGroup()
    {
        m_Objects = new LCLArray();
        m_Color = new Color(Color.WHITE);
        m_IsRendering = true;
    }

    //<editor-fold desc="Add Object">
    public LCLGroup addObject(Object object)
    {
        if(m_Objects.contains(object))
        {
            //TODO: ERROR
            return this;
        }

        m_Objects.add(object);

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX_W = Float.MIN_VALUE;
        float maxY_H = Float.MIN_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getX() < minX) minX = t.getX();
                if(t.getY() < minY) minY = t.getY();
                if(t.getX() + t.getWidth() > maxX_W ) maxX_W = t.getX() + t.getWidth();
                if(t.getY() + t.getHeight() > maxY_H ) maxY_H = t.getY() + t.getHeight();
            }
        }

        m_X = minX;
        m_Y = minY;
        m_Width = maxX_W - m_X;
        m_Height = maxY_H - m_Y;

        return this;
    }

    public LCLGroup addObjectAndApplyColor(IColorable object)
    {
        if(m_Objects.contains(object))
        {
            //TODO: ERROR
            return this;
        }

        m_Objects.add(object);
        object.setColor(m_Color);

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX_W = Float.MIN_VALUE;
        float maxY_H = Float.MIN_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getX() < minX) minX = t.getX();
                if(t.getY() < minY) minY = t.getY();
                if(t.getX() + t.getWidth() > maxX_W ) maxX_W = t.getX() + t.getWidth();
                if(t.getY() + t.getHeight() > maxY_H ) maxY_H = t.getY() + t.getHeight();
            }
        }

        m_X = minX;
        m_Y = minY;
        m_Width = maxX_W - m_X;
        m_Height = maxY_H - m_Y;

        return this;
    }

    public LCLGroup removeObject(Object object)
    {
        if(!m_Objects.contains(object))
        {
            //TODO: ERROR
            return this;
        }

        m_Objects.remove(object);

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX_W = Float.MIN_VALUE;
        float maxY_H = Float.MIN_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getX() < minX) minX = t.getX();
                if(t.getY() < minY) minY = t.getY();
                if(t.getX() + t.getWidth() > maxX_W ) maxX_W = t.getX() + t.getWidth();
                if(t.getY() + t.getHeight() > maxY_H ) maxY_H = t.getY() + t.getHeight();
            }
        }

        m_X = minX;
        m_Y = minY;
        m_Width = maxX_W - m_X;
        m_Height = maxY_H - m_Y;

        return this;
    }

    public LCLGroup removeObject(int object)
    {
        if(!m_Objects.contains(object))
        {
            //TODO: ERROR
            return this;
        }

        m_Objects.remove(object);

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX_W = Float.MIN_VALUE;
        float maxY_H = Float.MIN_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getX() < minX) minX = t.getX();
                if(t.getY() < minY) minY = t.getY();
                if(t.getX() + t.getWidth() > maxX_W ) maxX_W = t.getX() + t.getWidth();
                if(t.getY() + t.getHeight() > maxY_H ) maxY_H = t.getY() + t.getHeight();
            }
        }

        m_X = minX;
        m_Y = minY;
        m_Width = maxX_W - m_X;
        m_Height = maxY_H - m_Y;

        return this;
    }

    public int numberOfObjects() { return m_Objects.getSize(); }
    //</editor-fold>

    //<editor-fold desc="Color">
    @Override public Color getColor() { return m_Color; }
    @Override public LCLGroup setColor(Color newColor)
    {
        m_Color.set(newColor);
        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof IColorable) ((IColorable) m_Objects.get(i)).setColor(m_Color);

        return this;
    }
    @Override public LCLGroup setAlpha(float newAlpha)
    {
        m_Color.a = newAlpha;
        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof IColorable) ((IColorable) m_Objects.get(i)).setAlpha(newAlpha);

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Transform">
    @Override public float getX()
    {
        float minX = Float.MAX_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getX() < minX) minX = t.getX();
            }
        }

        m_X = minX;

        return m_X;
    }

    @Override public float getY()
    {
        float minY = Float.MAX_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getY() < minY) minY = t.getY();
            }
        }

        m_Y = minY;

        return m_Y;
    }

    @Override public float getWidth()
    {
        float minX = Float.MAX_VALUE;
        float maxX_W = Float.MIN_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getX() < minX) minX = t.getX();
                if(t.getX() + t.getWidth() > maxX_W ) maxX_W = t.getX() + t.getWidth();
            }
        }

        m_Width = maxX_W - minX;

        return m_Width;
    }

    @Override public float getHeight()
    {
        float minY = Float.MAX_VALUE;
        float maxY_H = Float.MIN_VALUE;
        for(int i = 0; i < m_Objects.getSize(); i++)
        {
            if(m_Objects.get(i) instanceof ITransformable)
            {
                ITransformable t = (ITransformable) m_Objects.get(i);

                if(t.getY() < minY) minY = t.getY();
                if(t.getY() + t.getHeight() > maxY_H ) maxY_H = t.getY() + t.getHeight();
            }
        }

        m_Height = maxY_H - minY;

        return m_Height;
    }

    @Override public LCLGroup setPosX(float newX)
    {
        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof ITransformable) ((ITransformable) m_Objects.get(i)).setPosX(newX + (((ITransformable) m_Objects.get(i)).getX() - m_X));
        m_X = newX;

        return this;
    }

    @Override public LCLGroup setPosY(float newY)
    {
        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof ITransformable) ((ITransformable) m_Objects.get(i)).setPosY(newY + (((ITransformable) m_Objects.get(i)).getY() - m_Y));
        m_Y = newY;

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Description">
    @Override public boolean isRendering() { return m_IsRendering; }
    @Override public LCLGroup setRenderingState(boolean renderingState) { m_IsRendering = renderingState; return this; }
    @Override public LCLGroup render()
    {
        if(!isRendering()) return this;

        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof IRender) ((IRender) m_Objects.get(i)).render();

        return this;
    }
    //</editor-fold>
}
