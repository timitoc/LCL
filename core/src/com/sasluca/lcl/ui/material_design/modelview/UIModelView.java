package com.sasluca.lcl.ui.material_design.modelview;

import com.sasluca.lcl.abstractions.IClonable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.ui.material_design.lists.modellists.scrolllists.UIModelVerticalList;
import com.sasluca.lcl.ui.material_design.view.UIView;

/**
 * Created by Sas Luca on 7/18/2016.
 * Copyright (C) 2016 - LCL
 */

public abstract class UIModelView<Model, This> extends UIView<This> implements IClonable<This>
{
    static { LCLTween.addClass(UIModelView.class); }

    protected Model p_Model;

    public UIModelView()
    {

    }

    public UIModelView(Model model)
    {
        p_Model = model;
    }

    protected abstract void setModelImpl(Model model);

    /** Attention, {@link #setModel(Model model)} only changes {@link #p_Model the current model} only after it called {@link #setModelImpl(Model model)}*/
    public This setModel(Model model)
    {
        setModelImpl(model);

        p_Model = model;

        return ((This)this);
    }

    public final Model getModel() { return p_Model; }
}
