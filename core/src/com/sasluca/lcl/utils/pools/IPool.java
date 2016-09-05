package com.sasluca.lcl.utils.pools;

import com.sasluca.lcl.abstractions.IDisposable;

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

/**
 * Interface describing a pool
 * @param <OBJECT>
 */
public interface IPool<OBJECT> extends IDisposable
{
    OBJECT get();

    IPool<OBJECT> clear();
    IPool<OBJECT> remove();
    IPool<OBJECT> addObject();
    IPool<OBJECT> remove(int remove);
    IPool<OBJECT> free(OBJECT object);
    IPool<OBJECT> remove(OBJECT object);
    IPool<OBJECT> addObject(OBJECT object);
    IPool<OBJECT> setInstanceFactory(IInstanceFactory<OBJECT> instanceFactory);

    int getNumberOfObjectsInUse();
    int getNumberOfFreeObjects();
    int getNumberOfObjects();
}
