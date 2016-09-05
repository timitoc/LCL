package com.sasluca.lcl.dialogs;

import com.sasluca.lcl.abstractions.functional.ITask;

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

public interface IImageGalleryOpener
{
    void callGalleryOpener(String title);

    void callGalleryOpener(String title, ITask onFinish);

    boolean isRunning();

    String getImagePath();
}
