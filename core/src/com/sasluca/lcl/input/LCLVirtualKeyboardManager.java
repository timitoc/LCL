package com.sasluca.lcl.input;

import com.badlogic.gdx.Gdx;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.utils.collections.LCLMap;

/*
 * Copyright 2016 Sas Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class LCLVirtualKeyboardManager
{
    private static boolean IsKeyboardVisible = false;
    private static LCLMap<Object, Boolean> KeyboardHandlers = new LCLMap<>();

    private LCLVirtualKeyboardManager() {}

    public static void manage()
    {
        for(Object o : KeyboardHandlers.getKeys())
        {
            if(KeyboardHandlers.get(o))
            {
                if(!IsKeyboardVisible) Gdx.input.setOnscreenKeyboardVisible(IsKeyboardVisible = true);
                return;
            }
        }

        Gdx.input.setOnscreenKeyboardVisible(IsKeyboardVisible = false);
    }

    public static void addKeyboardHandler(Object sender)
    {
        if(!KeyboardHandlers.containsKey(sender)) KeyboardHandlers.put(sender, false);
    }

    public static void showKeyboard(Object sender)
    {
        if(!KeyboardHandlers.containsKey(sender)) return;
        if(!IsKeyboardVisible) Gdx.input.setOnscreenKeyboardVisible(IsKeyboardVisible = true);

        KeyboardHandlers.replace(sender, true);
    }

    public static void hideKeyboard(Object sender, boolean enforce)
    {
        if(!KeyboardHandlers.containsKey(sender)) return;
        if(enforce) for(Object o : KeyboardHandlers.getKeys()) KeyboardHandlers.replace(0, false);
        else KeyboardHandlers.replace(sender, false);
    }

    public static void removeKeyboardHandler(Object handler)
    {
        if(!KeyboardHandlers.containsKey(handler)) return;
        KeyboardHandlers.remove(handler);
    }

    public static boolean isKeyboardVisible() { return IsKeyboardVisible; }
}

