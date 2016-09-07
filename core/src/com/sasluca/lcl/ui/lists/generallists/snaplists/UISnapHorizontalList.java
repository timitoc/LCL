package com.sasluca.lcl.ui.lists.generallists.snaplists;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;
import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IFocusable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.animation.LCLUniversalAccessor;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.lists.IOnItemChanged;
import com.sasluca.lcl.ui.group.UIGroup;
import com.sasluca.lcl.ui.view.UIContainer;
import com.sasluca.lcl.ui.view.UIView;
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

public class UISnapHorizontalList extends UIView<UISnapHorizontalList> implements IDisposable
{
    static { LCLTween.addClass(UISnapHorizontalList.class); }

    private float m_OldX;
    private int m_Current;
    private LCLMask m_Mask;
    private float m_InitialX;
    private UIGroup m_Group;
    private long m_BeginTimer;
    private boolean m_Animating;
    private boolean m_CurrentChanged;
    private LCLList<UIContainer> m_List;

    public IOnItemChanged<UISnapHorizontalList> onItemChanged;

    public UISnapHorizontalList(float width, float height)
    {
        m_Group = new UIGroup();
        m_List = new LCLList<>();
        m_Mask = new LCLMask(0, 0, width, height);
    }

    public UISnapHorizontalList addItem(UIView item)
    {
        UIContainer container = new UIContainer(item, m_Mask.getWidth(), m_Mask.getHeight(), false);
        m_List.add(container);
        if(m_List.getSize() == 1) container.setPosX(m_Mask.getX()); else container.setPosX((m_List.getSize() - 1) * m_Mask.getWidth());
        m_Group.addObject(container);

        return this;
    }

    @Override public void renderImpl()
    {
        m_Mask.start();
        for(int i = 0; i < m_List.getSize(); i++) if(isVisible(i)) m_List.get(i).render();
        m_Mask.end();
    }

    private boolean isVisible(int id)
    {
        UIView i = m_List.get(id);
        return (
                i.getX() + i.getWidth() >= m_Mask.getX() && i.getX() <= m_Mask.getX() + m_Mask.getWidth())
                && (i.getY() + i.getHeight() >= m_Mask.getY() && i.getY() <= m_Mask.getY() + m_Mask.getHeight()
        );
    }

    public int getCurrent() { return m_Current; }
    public int getNumberOfItems() { return m_List.getSize() - 1; }
    public UIView getItem(int id) { return m_List.get(id).getObject(); }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        loseFocus();

        if (!m_Animating)
        {
            //System.out.println(m_InitialX - m_OldX + " " + ((System.nanoTime() - m_BeginTimer)/1000000000.0));

            //Moving to current + 1
            if(!m_CurrentChanged && m_InitialX - m_OldX > 0 && ((System.nanoTime() - m_BeginTimer)/1000000000.0) < 0.20f && m_Current != m_List.getSize() - 1)
            {
                m_Current++;
                if(onItemChanged != null) onItemChanged.onItemChanged(this, m_Current - 1, m_Current);
            }

            if(!m_CurrentChanged && m_InitialX - m_OldX < 0 && ((System.nanoTime() - m_BeginTimer)/1000000000.0) < 0.20f && m_Current != 0)
            {
                m_Current--;
                if(onItemChanged != null) onItemChanged.onItemChanged(this, m_Current + 1, m_Current);
            }

            if(m_List.get(m_Current).getX() > m_Mask.getX() || m_List.get(m_Current).getX() < m_Mask.getX())
            {
                Tween.to(m_Group, LCLUniversalAccessor.POS_X, 0.3f)
                        .targetRelative(m_Mask.getX() - m_List.get(m_Current).getX())
                        .ease(Quad.IN)
                        .setCallback((type, source) -> { m_Animating = false; })
                        .start(LCLTween.TWEEN_MANAGER);

                m_Animating = true;
            }

            m_CurrentChanged = false;
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Touch Down">
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (getHitbox().isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            focus();
            p_Pointer = pointer;
            m_OldX = screenX;
            m_InitialX = screenX;
            m_BeginTimer = System.nanoTime();
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Touch Dragged Event">
    @Override public boolean touchDragged(int x, int y, int pointer)
    {
        if(p_Pointer != pointer) return false;

        for(Object o : m_List) if(o instanceof IFocusable) ((IFocusable) o).loseFocus();

        if(getHitbox().isPointInside(x, y))
        {

            if(m_List.getSize() == 1) return false;

            //Moving to current + 1
            if(x < m_OldX)
            {
                if(m_Current != m_List.getSize()) m_Group.setPosX(m_Group.getX() - (m_OldX - x));

                m_OldX = x;

                if(m_Current == m_List.getSize() - 1 && m_List.get(m_Current).getX() < m_Mask.getX()) m_Group.setPosX(m_Mask.getX() - m_Group.getWidth() + m_List.get(m_Current).getWidth());
                if(m_List.get(m_Current).getX() < m_Mask.getX() - ((30.0/100.0)*m_Mask.getWidth()))
                {
                    m_Current++;
                    m_CurrentChanged = true;
                    if(onItemChanged != null) onItemChanged.onItemChanged(this, m_Current - 1, m_Current);
                }
            }
            //Moving to current - 1
            else if(x > m_OldX)
            {
                if(m_Current != 0 || m_List.get(m_Current).getX() != m_Mask.getX()) m_Group.setPosX(m_Group.getX() + (x - m_OldX));

                m_OldX = x;

                if(m_Current == 0 && m_List.get(m_Current).getX() > m_Mask.getX()) m_Group.setPosX(m_Mask.getX());
                if(m_List.get(m_Current).getX() > m_Mask.getX() + ((30.0/100.0)*m_Mask.getWidth()))
                {
                    m_Current--;
                    m_CurrentChanged = true;
                    if(onItemChanged != null) onItemChanged.onItemChanged(this, m_Current +1, m_Current);
                }
            }
        }
        else touchUp(x, y, pointer, 1);


        return false;
    }
    //</editor-fold>

    public void nextItem()
    {
        if(m_Current == m_List.getSize() - 1 || m_Animating) return;

        m_Current++;

        if(onItemChanged != null) onItemChanged.onItemChanged(this, m_Current - 1, m_Current);

        Tween.to(m_Group, LCLUniversalAccessor.POS_X, 0.3f)
                .targetRelative(m_Mask.getX() - m_List.get(m_Current).getX())
                .ease(Quad.INOUT)
                .setCallback((type, source) -> { m_Animating = false; })
                .start(LCLTween.TWEEN_MANAGER);

        m_Animating = true;
    }

    public void previousItem()
    {
        if(m_Current == 0 || m_Animating) return;

        m_Current--;

        if(onItemChanged != null) onItemChanged.onItemChanged(this, m_Current + 1, m_Current);

        Tween.to(m_Group, LCLUniversalAccessor.POS_X, 0.3f)
                .targetRelative(m_Mask.getX() - m_List.get(m_Current).getX())
                .ease(Quad.INOUT)
                .setCallback((type, source) -> m_Animating = false)
                .start(LCLTween.TWEEN_MANAGER);

        m_Animating = true;
    }

    @Override protected void updateImpl() { }
    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }
    @Override public UISnapHorizontalList setPosX(float newX)
    {
        m_Group.setPosX(newX - (m_Mask.getX() - m_Group.getX()));
        m_Mask.setPosX(newX);
        return this;
    }
    @Override public UISnapHorizontalList setPosY(float newY)
    {
        m_Group.setPosY(newY - (m_Mask.getY() - m_Group.getY()));
        m_Mask.setPosY(newY);
        m_Mask.setPosY(newY);
        return this;
    }

    @Override public void dispose()
    {
        for(Object o : m_List) if(o instanceof IDisposable) ((IDisposable) o).dispose();
    }
}
