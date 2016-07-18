package com.sasluca.lcl.ui.material_design.lists.genericlists.scrolllists;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.material_design.UIView;
import com.sasluca.lcl.ui.material_design.group.UIGroup;
import com.sasluca.lcl.ui.material_design.lists.genericlists.UIContainer;
import com.sasluca.lcl.utils.collections.LCLArray;

/**
 * Created by Sas Luca on 13-Jul-16.
 */

public class UIVerticalList extends UIView<UIVerticalList> implements IDisposable
{
    private float m_OldY;
    private int m_Current;
    private LCLMask m_Mask;
    private float m_InitialY;
    private UIGroup m_Group;
    private boolean m_Animating;
    private LCLArray<UIContainer> m_List;

    public UIVerticalList(float width, float height)
    {
        m_Group = new UIGroup();
        m_List = new LCLArray<>();
        m_Mask = new LCLMask(0, 0, width, height);
    }

    public UIVerticalList addItem(UIView item)
    {
        UIContainer container = new UIContainer(item, m_Mask.getWidth(), item.getHeight(), false);
        m_List.add(container);
        if (m_List.getSize() == 1) container.setPosY(m_Mask.getY() + m_Mask.getHeight() - item.getHeight());
        else container.setPosY(m_List.get(m_List.getSize() - 2).getY() - item.getHeight());
        m_Group.addObject(container);

        return this;
    }

    public UIVerticalList addItem(UIView item , float heightPadding)
    {
        UIContainer container = new UIContainer(item, m_Mask.getWidth(), item.getHeight() + (heightPadding * 2), false);
        m_List.add(container);
        if (m_List.getSize() == 1) container.setPosY(m_Mask.getY() + m_Mask.getHeight() - item.getHeight());
        else container.setPosY(m_List.get(m_List.getSize() - 2).getY() - item.getHeight());
        m_Group.addObject(container);

        return this;
    }

    @Override public void renderImpl()
    {
        m_Mask.start();
        for (int i = 0; i < m_List.getSize(); i++) if (isVisible(i)) m_List.get(i).render();
        m_Mask.end();
    }

    private boolean isVisible(int id)
    {
        UIView i = m_List.get(id);
        return (i.getX() >= m_Mask.getX() && i.getX() + i.getWidth() <= m_Mask.getX() + m_Mask.getWidth()) || (i.getY() >= m_Mask.getY() && i.getY() + i.getHeight() <= m_Mask.getY() + m_Mask.getHeight());
    }

    public int getCurrent() { return m_Current; }

    public UIView getItem(int id) { return m_List.get(id).getObject(); }

    //<editor-fold desc="Touch Down">
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (p_Hitbox.isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            focus();
            p_Pointer = pointer;
            m_OldY = screenY;
            m_InitialY = screenY;
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Touch Dragged Event">
    @Override public boolean touchDragged(int x, int y, int pointer)
    {
        if (p_Pointer != pointer) return false;
        if (m_List.getSize() == 0) return false;

        if (p_Hitbox.isPointInside(x, y))
        {
            //Moving to current + 1
            if (y < m_OldY)
            {
                if (m_List.get(0).getY() > m_Mask.getY() + m_Mask.getHeight() - m_List.get(0).getHeight()) m_Group.setPosY(m_Group.getY() - (m_OldY - y));

                if(m_List.get(0).getY() < m_Mask.getY() + m_Mask.getHeight() - m_List.get(0).getHeight()) m_Group.setPosY(m_Mask.getY() + m_Mask.getHeight() - m_Group.getHeight());

                m_OldY = y;
            }
            //Moving to current - 1
            else if (y > m_OldY)
            {
                if (m_List.get(m_List.getSize() - 1).getY() <= m_Mask.getY()) m_Group.setPosY(m_Group.getY() + (y - m_OldY));

                if(m_List.get(m_List.getSize() - 1).getY() > m_Mask.getY()) m_Group.setPosY(m_Mask.getY());

                m_OldY = y;
            }
        }
        else touchUp(x, y, pointer, 1);


        return false;
    }
    //</editor-fold>

    @Override protected void updateImpl() { }
    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }

    @Override public UIVerticalList setPosX(float newX)
    {
        m_Group.setPosX(newX - (m_Mask.getX() - m_Group.getX()));
        m_Mask.setPosX(newX);
        return this;
    }

    @Override public UIVerticalList setPosY(float newY)
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

