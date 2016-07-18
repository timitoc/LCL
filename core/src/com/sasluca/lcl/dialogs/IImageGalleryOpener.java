package com.sasluca.lcl.dialogs;

/**
 * Created by Sas Luca on 7/15/2016.
 * Copyright (C) 2016 - LCL
 */

public interface IImageGalleryOpener
{
    void callGalleryOpener(String title);

    boolean isRunning();

    String getImagePath();
}
