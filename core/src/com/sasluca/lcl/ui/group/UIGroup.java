package com.sasluca.lcl.ui.group;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.abstractions.*;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.ui.view.UIView;
import com.sasluca.lcl.utils.collections.LCLArray;

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

public class UIGroup extends UIView<UIGroup> implements IColorable<UIGroup>
{
    static { LCLTween.addClass(UIGroup.class); }

    private float m_X;
    private float m_Y;
    private Color m_Color;
    private float m_Width;
    private float m_Height;
    private float m_WidthScale;
    private float m_HeightScale;
    private LCLArray m_Objects;

    public UIGroup()
    {
        m_Objects = new LCLArray();
        m_Color = new Color(Color.WHITE);
    }

    //<editor-fold desc="Add Object">
    public UIGroup addObject(Object object)
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

    public UIGroup addObjectAndApplyColor(IColorable object)
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

    public Object getObject(int index) { return m_Objects.get(index); }

    public UIGroup removeObject(Object object)
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

    /**
     * Removes an object based on it's index.
     * @param index The index of the object you want removed.
     * @return THIS instance.
     */
    public UIGroup removeObject(int index)
    {
        if(!m_Objects.contains(index))
        {
            //TODO: ERROR
            return this;
        }

        m_Objects.remove(index);

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

    /**
     * Removes all objects from the group.
     * @return THIS instance.
     */
    public UIGroup clear()
    {
        m_Objects.clear();

        return this;
    }

    public int getNumberOfObjects() { return m_Objects.getSize(); }
    //</editor-fold>

    //<editor-fold desc="Color">
    @Override public Color getColor() { return m_Color; }
    @Override public UIGroup setColor(Color newColor)
    {
        m_Color.set(newColor);
        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof IColorable) ((IColorable) m_Objects.get(i)).setColor(m_Color);

        return this;
    }
    @Override public UIGroup setAlpha(float newAlpha)
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

    @Override public UIGroup setPosX(float newX)
    {
        for(int i = 0; i < m_Objects.getSize(); i++)
            if(m_Objects.get(i) instanceof ITransformable) ((ITransformable) m_Objects.get(i)).setPosX(newX + (((ITransformable) m_Objects.get(i)).getX() - m_X));

        m_X = newX;

        return this;
    }

    @Override public UIGroup setPosY(float newY)
    {
        for(int i = 0; i < m_Objects.getSize(); i++)
            if(m_Objects.get(i) instanceof ITransformable) ((ITransformable) m_Objects.get(i)).setPosY(newY + (((ITransformable) m_Objects.get(i)).getY() - m_Y));

        m_Y = newY;

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Description">
    @Override protected void renderImpl()
    {
        for(int i = 0; i < m_Objects.getSize(); i++) if(m_Objects.get(i) instanceof IRender) ((IRender) m_Objects.get(i)).render();
    }

    @Override protected void updateImpl() { }
    //</editor-fold>
}
