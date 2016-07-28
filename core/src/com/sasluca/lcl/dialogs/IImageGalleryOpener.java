package com.sasluca.lcl.dialogs;

import com.sasluca.lcl.abstractions.functional.ITask;

/**
 * Created by Sas Luca on 7/15/2016.
 * Copyright (C) 2016 - LCL
 */

public interface IImageGalleryOpener
{
    void callGalleryOpener(String title);

    void callGalleryOpener(String title, ITask onFinish);

    boolean isRunning();

    String getImagePath();
}
