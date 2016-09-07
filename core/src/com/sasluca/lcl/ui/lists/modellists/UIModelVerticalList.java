package com.sasluca.lcl.ui.lists.modellists;

import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.group.UIGroup;
import com.sasluca.lcl.ui.view.UIView;
import com.sasluca.lcl.ui.mvc.LCLModel;
import com.sasluca.lcl.ui.mvc.UIModelContainer;
import com.sasluca.lcl.ui.mvc.UIModelView;
import com.sasluca.lcl.utils.collections.list.LCLList;
import com.sasluca.lcl.utils.pools.LCLPool;

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

public class UIModelVerticalList<Model extends LCLModel, View extends UIModelView<Model, View>> extends UIView<UIModelVerticalList> implements IDisposable
{
    static { LCLTween.addClass(UIModelVerticalList.class); }

    private float m_OldY;
    private LCLMask m_Mask;
    private UIGroup m_Group;
    private float m_InitialY;
    private float m_ViewHeight;
    private float m_HeightPadding;
    private LCLPool<View> m_ViewsPool;
    private LCLList<UIModelContainer> m_List;

    public UIModelVerticalList(float listWidth, float listHeight, float heightPadding, View view)
    {
        m_Group = new UIGroup();
        m_List = new LCLList<>();
        m_HeightPadding = heightPadding;
        m_Mask = new LCLMask(0, 0, listWidth, listHeight);
        m_ViewsPool = new LCLPool<>(view::getClone, EMPTY_LAMBDA -> {} );
        m_ViewsPool.addObject(view);
        m_ViewHeight = view.getHeight();
    }

    public UIModelVerticalList addItem(Model model)
    {
        UIModelContainer container = new UIModelContainer(model, m_Mask.getWidth(), m_Mask.getHeight(), false);
        m_List.add(container);

        if (m_List.getSize() == 1) container.setPosY(m_Mask.getY() + m_Mask.getHeight() - container.getHeight());
        else container.setPosY(m_List.get(m_List.getSize() - 2).getY() - container.getHeight());

        m_Group.addObject(container);

        if(isVisible(m_List.getSize() - 1)) m_List.get(m_List.getSize() - 1).setView(m_ViewsPool.get());

        return this;
    }

    public UIModelVerticalList clear()
    {
        m_Group.setPosY(m_Mask.getY() + m_Mask.getHeight() - m_Group.getHeight());

        m_List.clear();
        m_Group.clear();

        return this;
    }

    @Override public void renderImpl()
    {
        m_Mask.start();
        for (int i = 0; i < m_List.getSize(); i++) if (isVisible(i)) m_List.get(i).render();
        m_Mask.end();
    }

    public boolean isVisible(int id)
    {
        UIModelContainer i = m_List.get(id);
        return (i.getX() + i.getWidth() > m_Mask.getX() && i.getX() < m_Mask.getX() + m_Mask.getWidth()) && (i.getY() + i.getHeight() > m_Mask.getY() && i.getY() < m_Mask.getY() + m_Mask.getHeight());
    }

    public View getView(int id) { return (View) m_List.get(id).getView(); }
    public Model getItem(int id) { return (Model) m_List.get(id).getModel(); }

    @Override public void onUnsubscribeFromInputLayer(int oldLayer)
    {
        for(int i = 0; i < m_List.getSize(); i++) if(m_List.get(i).getView() != null) m_List.get(i).getView().unsubscribeFromInputLayer();
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        loseFocus();

        return false;
    }

    //<editor-fold desc="Touch Down">
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (getHitbox().isPointInside(screenX, screenY) && p_Pointer == -1)
        {
            focus();
            p_Pointer = pointer;
            m_InitialY = m_OldY = screenY;
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Touch Dragged Event">
    @Override public boolean touchDragged(int x, int y, int pointer)
    {
        if (p_Pointer != pointer) return false;
        if (m_List.getSize() == 0) return false;

        if (getHitbox().isPointInside(x, y))
        {
            if (y - m_InitialY > 2 || y - m_InitialY < -2) for(int i = 0; i < m_List.getSize(); i++) if(m_List.get(i).getView() != null) m_List.get(i).getView().loseFocus();
            if (m_Group.getHeight() < m_Mask.getHeight()) return false;

            //Moving up the list
            if (y < m_OldY)
            {
                UIModelContainer topMostItem = m_List.get(0);

                if (topMostItem.getY() >= m_Mask.getY() + m_Mask.getHeight() - topMostItem.getHeight()) m_Group.setPosY(m_Group.getY() - (m_OldY - y));
                if (topMostItem.getY() < m_Mask.getY() + m_Mask.getHeight() - topMostItem.getHeight()) m_Group.setPosY(m_Mask.getY() + m_Mask.getHeight() - m_Group.getHeight());

                m_OldY = y;
            }
            //Moving down the list
            else if (y > m_OldY)
            {
                UIModelContainer bottomItem = m_List.get(m_List.getSize() - 1);

                if (bottomItem.getY() <= m_Mask.getY()) m_Group.setPosY(m_Group.getY() + (y - m_OldY));
                if (bottomItem.getY() > m_Mask.getY()) m_Group.setPosY(m_Mask.getY());

                m_OldY = y;
            }

            for(int i = 0; i < m_List.getSize(); i++)
                //If we can not see the container but it has a view allocated to it we get it back
                if (!isVisible(i) && m_List.get(i).getView() != null)
                {
                    m_ViewsPool.free((View) m_List.get(i).getView());
                    m_List.get(i).setView(null);
                }


            for(int i = 0; i < m_List.getSize(); i++)
                //After we free all unused views we allocate them to the containers that are visible but have no views
                if (isVisible(i) && m_List.get(i).getView() == null) m_List.get(i).setView(m_ViewsPool.get());
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

    @Override public UIModelVerticalList setPosX(float newX)
    {
        m_Group.setPosX(newX - (m_Mask.getX() - m_Group.getX()));
        m_Mask.setPosX(newX);
        return this;
    }

    @Override public UIModelVerticalList setPosY(float newY)
    {
        m_Group.setPosY(newY - (m_Mask.getY() - m_Group.getY()));
        m_Mask.setPosY(newY);
        m_Mask.setPosY(newY);
        return this;
    }

    public int getNumberOfItems() { return m_List.getSize(); }
    public int getNumberOfViews() { return m_ViewsPool.getNumberOfObjects(); }
    public int getNumberOfFreeViews() { return m_ViewsPool.getNumberOfFreeObjects(); }
    public int getNumberOfInUseViews() { return m_ViewsPool.getNumberOfObjectsInUse(); }

    @Override public void dispose()
    {
        for(Object o : m_List) if(o instanceof IDisposable) ((IDisposable) o).dispose();
    }
}

