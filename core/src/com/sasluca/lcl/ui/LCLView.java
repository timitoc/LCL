package com.sasluca.lcl.ui;

import com.sasluca.lcl.abstractions.IRenderable;
import com.sasluca.lcl.abstractions.ITransformable;
import com.sasluca.lcl.input.LCLInputHandler;
import com.sasluca.lcl.input.events.*;

/**
 * Created by Sas Luca on 27-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public abstract class LCLView extends LCLInputHandler implements IRenderable<LCLView>, ITransformable<LCLView>
{
    public IKeyUpEvent onKeyUp;
    public IKeyDownEvent onKeyDown;
    public IScrolledEvent onScroll;
    public ITouchUpEvent onTouchUp;
    public IKeyTypedEvent onKeyType;
    public ITouchDownEvent onTouchDown;
    public IMouseMovedEvent onMouseMove;
    public ITouchDraggedEvent onTouchDrag;
}
