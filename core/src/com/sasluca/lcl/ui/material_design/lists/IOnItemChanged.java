package com.sasluca.lcl.ui.material_design.lists;

/**
 * Created by Sas Luca on 11-Jul-16.
 * Copyright (C) 2016 - LCL
 */

/** An event that occurs in a snaplist when the item changes */
public interface IOnItemChanged<List>
{
    void onItemChanged(List sender, int oldItem, int currentItem);
}
