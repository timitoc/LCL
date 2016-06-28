package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 09-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IRenderable<This> extends IRender<This>
{
    boolean isRendering();

    This setRenderingState(boolean renderingState);
}