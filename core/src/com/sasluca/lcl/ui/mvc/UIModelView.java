package com.sasluca.lcl.ui.mvc;

import com.sasluca.lcl.abstractions.IClonable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.ui.view.UIView;

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

public abstract class UIModelView<Model extends LCLModel, THIS> extends UIView<THIS> implements IClonable<THIS>
{
    static { LCLTween.addClass(UIModelView.class); }

    protected Model p_Model;

    public UIModelView() { }
    public UIModelView(Model model) { p_Model = model; }

    protected abstract void setModelImpl(Model model);

    /** Attention, {@link #setModel(Model model)} only changes {@link #p_Model the current model} only after it called {@link #setModelImpl(Model model)}*/
    public THIS setModel(Model model)
    {
        setModelImpl(model);

        p_Model = model;

        p_Model.addView(this);

        return ((THIS)this);
    }

    public final Model getModel() { return p_Model; }
}
