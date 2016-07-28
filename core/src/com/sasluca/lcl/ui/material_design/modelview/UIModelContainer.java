package com.sasluca.lcl.ui.material_design.modelview;

import com.sasluca.lcl.abstractions.IMasking;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.graphics.mask.LCLMask;
import com.sasluca.lcl.ui.material_design.lists.modellists.scrolllists.UIModelVerticalList;
import com.sasluca.lcl.ui.material_design.view.UIView;

import static com.sasluca.lcl.utils.LCLUtils.centerToDrawable;

/**
 * Created by Sas Luca on 7/18/2016.
 * Copyright (C) 2016 - LCL
 */

public class UIModelContainer<Model, View extends UIModelView<Model, View>> extends UIView<UIModelContainer> implements IMasking<UIModelContainer>
{
    static { LCLTween.addClass(UIModelContainer.class); }

    private View m_View;
    private Model m_Model;
    private LCLMask m_Mask;

    public UIModelContainer(View view, Model model, float width, float height, boolean maskingStrategy)
    {
        m_Mask = new LCLMask(0, 0, width, height);
        m_Mask.setMaskingStrategy(maskingStrategy);
        m_Model = model;
        m_View = view;
        if(m_View != null) centerToDrawable(m_View, this, true, true);
    }

    public UIModelContainer(Model model, float width, float height, boolean maskingStrategy)
    {
        this(null, model, width, height, maskingStrategy);
    }

    @Override protected void renderImpl()
    {
        //if(m_View != null) if((m_View.getWidth() > m_Mask.getWidth() || m_View.getHeight() > m_Mask.getHeight())) m_Mask.start();

        if(m_View != null)
        {
            centerToDrawable(m_View, this, true, true);
            m_View.render();
        }

        //m_Mask.end();
    }

    @Override protected void updateImpl() { }
    @Override public float getX() { return m_Mask.getX(); }
    @Override public float getY() { return m_Mask.getY(); }
    @Override public float getWidth() { return m_Mask.getWidth(); }
    @Override public float getHeight() { return m_Mask.getHeight(); }
    @Override public UIModelContainer setPosX(float newX) { m_Mask.setPosX(newX); if(m_View != null) centerToDrawable(m_View, this, true, true); return this; }
    @Override public UIModelContainer setPosY(float newY) { m_Mask.setPosY(newY); if(m_View != null) centerToDrawable(m_View, this, true, true); return this; }

    @Override public boolean getMaskingStrategy() { return m_Mask.getMaskingStrategy(); }
    @Override public UIModelContainer setMaskingStrategy(boolean mask) { m_Mask.setMaskingStrategy(mask); return this; }

    public UIModelContainer setModel(Model model) { m_Model = model; if(m_View != null) m_View.setModel(m_Model); return this; }
    public Model getModel() { return m_Model; }

    public UIModelContainer setView(View view)
    {
        m_View = view;

        if(m_View != null)
        {
            m_View.setModel(m_Model);
            centerToDrawable(m_View, this, true, true);
        }

        return this;
    }
    public View getView() { return m_View; }
}
