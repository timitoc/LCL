package com.sasluca.lcl.utils.io;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Sas Luca on 11-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLFile extends FileHandle
{
    public LCLFile(String file)
    {
        super((Gdx.app.getType() == Application.ApplicationType.Android ? "assets/" : "") + file);
    }

    public boolean isEmpty()
    {
        return this.readString().isEmpty();
    }
}
