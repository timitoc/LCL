package com.sasluca.lcl.input;

import com.badlogic.gdx.Gdx;
import com.sasluca.lcl.utils.collections.LCLObjectMap;

/**
 * Created by Sas Luca on 22-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLVirtualKeyboardManager
{
    private static boolean m_IsKeyboardVisible = false;
    private static LCLObjectMap<Object, Boolean> m_KeyboardHandlers = new LCLObjectMap<>();

    private LCLVirtualKeyboardManager() {}

    public static void addKeyboardHandler(Object sender)
    {
        if(!m_KeyboardHandlers.containsKey(sender)) m_KeyboardHandlers.put(sender, false);
    }

    public static void showKeyboard(Object sender)
    {
        if(!m_KeyboardHandlers.containsKey(sender)) return;
        Gdx.input.setOnscreenKeyboardVisible(m_IsKeyboardVisible = true);
        m_KeyboardHandlers.replace(sender, true);
        //m_KeyboardHandlers.(sender, true); NEVER EVER USE REPLACE!! DOES NOT WORK ON ANDROID
    }

    public static void hideKeyboard(Object sender, boolean enforce)
    {
        if(!m_KeyboardHandlers.containsKey(sender)) return;
        if(enforce)
        {
            for(Object o : m_KeyboardHandlers.keys()) m_KeyboardHandlers.replace(0, false);
            Gdx.input.setOnscreenKeyboardVisible(m_IsKeyboardVisible = false);
        }
        else
        {
            m_KeyboardHandlers.replace(sender, false);
            for(Object o : m_KeyboardHandlers.keys()) if(m_KeyboardHandlers.get(o)) { Gdx.input.setOnscreenKeyboardVisible(m_IsKeyboardVisible = true); return; }
            Gdx.input.setOnscreenKeyboardVisible(m_IsKeyboardVisible = false);
        }
    }

    public static void removeKeyboardHandler(Object handler)
    {
        if(!m_KeyboardHandlers.containsKey(handler)) return;
        m_KeyboardHandlers.remove(handler);
    }

    public static boolean isKeyboardVisible() { return m_IsKeyboardVisible; }
}

