package com.sasluca.lcl.utils.timer;

import com.sasluca.lcl.utils.collections.LCLMap;
import com.sasluca.lcl.utils.tuples.LCLTriplet;
import com.sasluca.lcl.utils.tuples.LCLTuple;

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

public class LCLTimePoints
{
    private static LCLMap<Integer, Long> m_TimePoints = new LCLMap<>();

    private LCLTimePoints() { }

    public static void addTimePoint(int index)
    {
        if(m_TimePoints.containsKey(index)) m_TimePoints.replace(index, System.nanoTime());
        else m_TimePoints.put(index, System.nanoTime());
    }

    public static float getInNanoSeconds(int index) { return getResult(index, 1); }
    public static float getInMiliseconds(int index) { return getResult(index, 1000); }
    public static float getInSeconds(int index) { return getResult(index, 1000000000); }

    private static float getResult(int index, float d)
    {
        if(!m_TimePoints.containsKey(index))
        {
            //TODO: ERROR
            return 0;
        }

        float result = (System.nanoTime() - m_TimePoints.get(index)) / d;

        return result;
    }

    /** Returns the result of the timepoint as a triplet with the value in seconds, miliseconds and nanoseconds */
    public static LCLTriplet<Long, Float, Float> get(int index)
    {
        if(!m_TimePoints.containsKey(index))
        {
            //TODO: ERROR
            return null;
        }

        long resultN = (System.nanoTime() - m_TimePoints.get(index));
        float resultM = resultN / 1000f;
        float resultS = resultN / 1000000000f;

        return (LCLTriplet) LCLTuple.tuple(resultS, resultM, resultN);
    }
}
