package com.sasluca.lcl.ui.material_design.lists.genericlists.scrolllists;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IMasking;
import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.input.LCLInputHandler;
import com.sasluca.lcl.ui.material_design.lists.IList;
import com.sasluca.lcl.ui.material_design.lists.modellists.scrolllists.UIModelVerticalList;
import com.sasluca.lcl.ui.material_design.view.UIView;
import com.sasluca.lcl.ui.material_design.group.UIGroup;
import com.sasluca.lcl.ui.material_design.view.UIContainer;
import com.sasluca.lcl.utils.collections.LCLArray;
import com.sasluca.lcl.utils.timer.LCLTimer;

/**
 * Created by Sas Luca on 13-Jul-16.
 */

public class UIVerticalList extends UIView<UIVerticalList> implements IList<UIVerticalList, UIView>, IMasking<UIVerticalList>, ISizeable<UIVerticalList>, IDisposable
{
    static { LCLTween.addClass(UIVerticalList.class); }

    private float m_OldY;
    private float m_Timer;
    private LCLMask m_Mask;
    private UIGroup m_Group;
    private float m_InitialY;
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

    @Override public int getNumberOfItems() { return m_List.getSize(); }

    public UIVerticalList addItem(UIView item , float heightPadding)
    {
        UIContainer container = new UIContainer(item, m_Mask.getWidth(), item.getHeight() + (heightPadding * 2), false);
        m_List.add(container);
        if (m_List.getSize() == 1) container.setPosY(m_Mask.getY() + m_Mask.getHeight() - (item.getHeight() + (heightPadding * 2)));
        else container.setPosY(m_List.get(m_List.getSize() - 2).getY() - (item.getHeight() + (heightPadding * 2)));
        m_Group.addObject(container);

        return this;
    }

    public UIVerticalList remove(int index)
    {
        m_Group.removeObject(m_List.get(index));
        m_List.remove(index);
        return this;
    }

    public UIVerticalList clear()
    {
        for(int i = 0; i < m_List.getSize(); i++)
        {
            m_List.remove(i);
            m_Group.removeObject(i);
        }

        return this;
    }

    @Override protected void onSubscribeToInputLayer(int currentLayer, int newLayer)
    {
        for(LCLInputHandler handler : m_List) handler.subscribeToInputLayer(newLayer);
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
        return (i.getX() + i.getWidth() > m_Mask.getX() && i.getX() < m_Mask.getX() + m_Mask.getWidth()) && (i.getY() + i.getHeight() > m_Mask.getY() && i.getY() < m_Mask.getY() + m_Mask.getHeight());
        //return (i.getX() >= m_Mask.getX() && i.getX() + i.getWidth() <= m_Mask.getX() + m_Mask.getWidth()) && (i.getY() >= m_Mask.getY() && i.getY() + i.getHeight() <= m_Mask.getY() + m_Mask.getHeight());
    }

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

        if(m_Group.getHeight() < m_Mask.getHeight()) return false;

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

    //<editor-fold desc="Transform">
    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }

    @Override public UIVerticalList setWidth(float newWidth)
    {
        m_Mask.setWidth(newWidth);
        for(int i = 0; i < m_List.getSize(); i++) m_List.get(i).setWidth(newWidth);
        return this;
    }
    @Override public UIVerticalList setHeight(float newHeight)
    {
        m_Mask.setHeight(newHeight);
        m_Group.setPosY(m_Mask.getY() + m_Mask.getHeight() - m_Group.getHeight());
        return this;
    }
    @Override public UIVerticalList setSize(float newWidth, float newHeight) { setWidth(newWidth); setHeight(newHeight); return this; }

    @Override public UIVerticalList setPosX(float newX)
    {
        m_Group.setPosX(newX);
        m_Mask.setPosX(newX);
        return this;
    }
    @Override public UIVerticalList setPosY(float newY)
    {
        m_Group.setPosY(newY);
        m_Mask.setPosY(newY);
        return this;
    }
    //</editor-fold>

    @Override public boolean getMaskingStrategy() { return false; }
    @Override public UIVerticalList setMaskingStrategy(boolean maskingStrategy) { return null; }

    @Override public void dispose()
    {
        for(Object o : m_List) if(o instanceof IDisposable) ((IDisposable) o).dispose();
    }
}

