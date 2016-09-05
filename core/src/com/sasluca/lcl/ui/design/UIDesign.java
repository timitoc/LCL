package com.sasluca.lcl.ui.design;

import com.badlogic.gdx.utils.ObjectMap;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.abstractions.IRender;
import com.sasluca.lcl.abstractions.IReusable;
import com.sasluca.lcl.abstractions.IUpdate;
import com.sasluca.lcl.input.LCLInputHandler;
import com.sasluca.lcl.ui.group.UIGroup;
import com.sasluca.lcl.ui.view.UIView;
import com.sasluca.lcl.utils.collections.LCLMap;

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

public abstract class UIDesign implements IReusable<UIDesign>
{
    private UIGroup m_Group;
    private LCLMap<String, Object> m_Objects;

    public UIDesign()
    {
        m_Group = new UIGroup();
        m_Objects = new LCLMap<>();

        create();
        reset();
    }

    protected final UIDesign addToDesign(String name, Object object)
    {
        if(object instanceof UIView) m_Group.addObject(object);
        m_Objects.put(name, object);

        return this;
    }

    public final <O> O get(String name) { return ((O) m_Objects.get(name)); }

    protected abstract void create();
    protected abstract void onReset();

    @Override public final UIDesign reset()
    {
        onReset();

        return this;
    }

    public UIGroup getGroup() { return m_Group; }
    public ObjectMap.Values getObjects() { return m_Objects.getValues(); }
}
