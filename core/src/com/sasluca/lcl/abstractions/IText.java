package com.sasluca.lcl.abstractions;

/**
 * Created by Sas Luca on 10-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public interface IText<This>
{
    //Getters
    /** Returns the text as a {@link String}. */String      getText();
    /** Return the length of the string. */int         getLength();
    /** Return the character at the specified index. */char        getCharAt(int index);
    /** Returns a substring from the string. */String      getSubstring(int begin, int end);
    /** More efficient than {@link String#split(String)}. Returns the same value as {@link String#split(String)}[index]. */String      getSplit(char splitBy, int index);
    /** Similar to {@link #getSplit(char, int)}, but splits the text by a string instead of a char.*/String      getSplit(String splitBy, int index);

    //Setters
    /** Deletes all the characters in the string. */This        clear();
    /** Changes all characters to their upper case version, if they have one. */This        toUpperCase();
    /** Changes all characters to their lower case version, if they have one. */This        toLowerCase();
    /** Clears the string and writes the given text to it. */This        write(String text);
    /** Appends the given text to the end of the string. */This        append(String text);
    /** Deletes all the charachters from within the specified range. */This        delete(int begin, int end);
    /** Inserts a string at the specified index in the string. */This        insert(int index, String string);
    /** Changes all the occurences of a character in the string with the second argument. */This        changeChar(char change, char to);
    /** Changes the character at the specified index. */This        changeCharAtIndex(int index, char change);
}
