package com.sasluca.lcl.utils.group;

import com.sasluca.lcl.abstractions.ITransformable;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLGroup
{
    private float m_X;
    private float m_Y;
    private float m_Width;
    private float m_Height;
    private LCLArray m_Objects;

    public LCLGroup()
    {
        m_Objects = new LCLArray();
    }

    public void addObject(Object object)
    {
        if(m_Objects.contains(object))
        {
            //TODO: ERROR
            return;
        }

        m_Objects.add(object);

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX_W = Float.MIN_VALUE;
        float maxY_H = Float.MIN_VALUE;
        for(int i = 0; i++ < m_Objects.getSize();)
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
    }
}
