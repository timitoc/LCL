package com.sasluca.lcl.utils.text;

import com.sasluca.lcl.abstractions.IText;

/**
 * Created by Sas Luca on 19-Jun-16.
 * Copyright (C) 2016 - LCL
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

    @Override public String getSplit(char splitBy, int index)
    {
        for(int i = 0; i < m_Buffer.length(); i++)
        {
            if(m_Buffer.charAt(i) == splitBy)
            {
                String s = "";

                for(int j = i + 1; j < m_Buffer.length(); j++)
                {
                    if(m_Buffer.charAt(j) != splitBy) s += Character.toString(m_Buffer.charAt(j));
                    else return s;
                }

                return s;
            }
        }
        return null;
    }

    @Override public String getSplit(String splitBy, int index)
    {
        for(int i = 0; i < m_Buffer.length(); i++)
        {
            if(m_Buffer.substring(i, splitBy.length()).matches(splitBy))
            {
                String s = "";

                for(int j = i + 1; j < m_Buffer.length(); j++)
                {
                    if(!m_Buffer.substring(j, splitBy.length()).matches(splitBy)) s += Character.toString(m_Buffer.charAt(j));
                    else return s;
                }

                return s;
            }
        }

        return null;
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
