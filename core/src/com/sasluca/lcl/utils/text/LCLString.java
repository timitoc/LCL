package com.sasluca.lcl.utils.text;

import com.sasluca.lcl.abstractions.IText;
import com.sasluca.lcl.utils.collections.list.LCLList;

import java.util.StringTokenizer;

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

public class LCLString implements IText<LCLString>
{
    private StringBuffer m_Buffer;

    public LCLString()
    {
        m_Buffer = new StringBuffer();
    }

    public LCLString(String text)
    {
        m_Buffer = new StringBuffer(text);
    }

    @Override public int getLength() { return m_Buffer.length(); }
    @Override public String getText() { return m_Buffer.toString(); }
    @Override public char getCharAt(int index) { return m_Buffer.charAt(index); }
    @Override public String getSubstring(int begin, int end) { return m_Buffer.substring(begin, end); }
    public boolean contains(String s) { return getText().contains(s); }
    public boolean contains(char c) { return getText().contains(Character.toString(c)); }

    public LCLList<String> split(String delims)
    {
        LCLList<String> array = new LCLList<>();
        StringTokenizer st = new StringTokenizer(m_Buffer.toString(), delims);
        while (st.hasMoreElements()) { array.add((String) st.nextElement()); }

        return array;
    }

    public LCLString removeChar(char c)
    {
        for(int i = 0; i < getLength(); i++) if(getCharAt(i) == c) deleteCharAt(i);
        return this;
    }

    @Override public LCLString clear()
    {
        m_Buffer.delete(0, m_Buffer.length());
        return this;
    }

    @Override public LCLString toUpperCase()
    {
        for(int i = 0; i < getLength(); i++) m_Buffer.replace(i, i + 1, Character.toString(Character.toUpperCase(m_Buffer.toString().toCharArray()[i])));

        return this;
    }

    @Override public LCLString toLowerCase()
    {
        for(int i = 0; i < getLength(); i++) m_Buffer.replace(i, i + 1, Character.toString(Character.toLowerCase(m_Buffer.toString().toCharArray()[i])));

        return this;
    }

    @Override public LCLString write(String text)
    {
        clear();
        m_Buffer.append(text);

        return null;
    }

    @Override public LCLString append(String text)
    {
        m_Buffer.append(text);

        return this;
    }

    @Override public LCLString delete(int begin, int end)
    {
        m_Buffer.delete(begin, end);

        return this;
    }

    public LCLString deleteCharAt(int index) { m_Buffer.deleteCharAt(index); return this; }

    @Override public LCLString insert(int index, String string)
    {
        m_Buffer.insert(index, string);

        return this;
    }

    @Override public LCLString changeChar(char change, char to)
    {
        for(int i = 0; i < getLength(); i++) if(m_Buffer.charAt(i) == change) m_Buffer.setCharAt(i, to);

        return this;
    }

    @Override public LCLString changeCharAtIndex(int index, char change)
    {
        m_Buffer.setCharAt(index, change);

        return this;
    }

}
