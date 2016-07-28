package com.sasluca.lcl.input;

import com.badlogic.gdx.Gdx;
import com.sasluca.lcl.utils.collections.LCLMap;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLVirtualKeyboardManager
{
    private static boolean IsKeyboardVisible = false;
    private static LCLMap<Object, Boolean> KeyboardHandlers = new LCLMap<>();

    private LCLVirtualKeyboardManager() {}

    public static void addKeyboardHandler(Object sender)
    {
        if(!KeyboardHandlers.containsKey(sender)) KeyboardHandlers.put(sender, false);
    }

    //TODO: Maybe a little more work on this...

    public static void showKeyboard(Object sender)
    {
        if(!KeyboardHandlers.containsKey(sender)) return;
        if(!IsKeyboardVisible)
            Gdx.input.setOnscreenKeyboardVisible(IsKeyboardVisible = true);
        KeyboardHandlers.replace(sender, true);
        //KeyboardHandlers.(sender, true); NEVER EVER USE REPLACE!! DOES NOT WORK ON ANDROID
    }

    public static void hideKeyboard(Object sender, boolean enforce)
    {
        if(!KeyboardHandlers.containsKey(sender)) return;
        if(enforce)
        {
            for(Object o : KeyboardHandlers.keys()) KeyboardHandlers.replace(0, false);
            Gdx.input.setOnscreenKeyboardVisible(IsKeyboardVisible = false);
        }
        else
        {
            KeyboardHandlers.replace(sender, false);
            for(Object o : KeyboardHandlers.keys()) if(KeyboardHandlers.get(o)) return;
            Gdx.input.setOnscreenKeyboardVisible(IsKeyboardVisible = false);
        }
    }

    public static void removeKeyboardHandler(Object handler)
    {
        if(!KeyboardHandlers.containsKey(handler)) return;
        KeyboardHandlers.remove(handler);
    }

    public static boolean isKeyboardVisible() { return IsKeyboardVisible; }
}

