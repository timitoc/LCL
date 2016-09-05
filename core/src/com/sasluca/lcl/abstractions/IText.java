package com.sasluca.lcl.abstractions;

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

public interface IText<THIS>
{
    //Getters
    /** Returns the text as a {@link String}. */
    String getText();

    /** Return the length of the string. */
    int getLength();

    /** Return the character at the specified index. */
    char getCharAt(int index);

    /** Returns a substring from the string. */
    String getSubstring(int begin, int end);

    //Setters
    /** Deletes all the characters in the string. */
    THIS clear();

    /** Changes all characters to their upper case version, if they have one. */
    THIS toUpperCase();

    /** Changes all characters to their lower case version, if they have one. */
    THIS toLowerCase();

    /** Clears the string and writes the given text to it. */
    THIS write(String text);

    /** Appends the given text to the end of the string. */
    THIS append(String text);

    /** Deletes all the characters from within the specified range. */
    THIS delete(int begin, int end);

    /** Inserts a string at the specified index in the string. */
    THIS insert(int index, String string);

    /** Changes all the occurrences of a character in the string with the second argument. */
    THIS changeChar(char change, char to);

    /** Changes the character at the specified index. */
    THIS changeCharAtIndex(int index, char change);
}
