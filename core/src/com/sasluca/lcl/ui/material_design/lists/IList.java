package com.sasluca.lcl.ui.material_design.lists;

/**
 * Created by Sas Luca on 7/22/2016.
 * Copyright (C) 2016 - LCL
 */

public interface IList<This, Item>
{
    Item getItem(int index);

    This addItem(Item item);

    int getNumberOfItems();
}
