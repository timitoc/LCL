package com.sasluca.lcl.ui.material_design.design;

import com.sasluca.lcl.utils.collections.LCLMap;

/**
 * Created by Sas Luca on 11-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class UIDesign
{
    private LCLMap<String, Object> m_Objects;

    public UIDesign()
    {
        m_Objects = new LCLMap<>();
    }

    protected void addToDesign(String name, Object object) { m_Objects.put(name, object); }
    public <OBJ> OBJ getObject(String name) { if(m_Objects.containsKey(name)) return ((OBJ)m_Objects.get(name)); else return null; }

    public abstract void addToRender();
    public abstract void addToUpdate();
    public abstract void enableInput();
    public abstract void disableInput();
    public abstract void removeFromRender();
    public abstract void removeFromUpdate();
}
