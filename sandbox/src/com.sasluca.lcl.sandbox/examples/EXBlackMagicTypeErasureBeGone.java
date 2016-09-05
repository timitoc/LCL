package com.sasluca.lcl.sandbox.examples;

import org.jquantlib.lang.reflect.TypeTokenTree;

/**
 * Created by Sas Luca on 8/7/2016.
 */

class FuckTypeErasureSpell<O>
{
    protected Class p_ClassOfGeneric;

    public FuckTypeErasureSpell()
    {
        p_ClassOfGeneric = new TypeTokenTree(this.getClass()).getElement(0);
    }

    public boolean castSpellCheckTypeOfGeneric(Object object) { return p_ClassOfGeneric.isInstance(object); }
}

public class EXBlackMagicTypeErasureBeGone
{
    public static void blackMagic()
    {
        Integer i = 1;
        Double d = 2.0d;

        FuckTypeErasureSpell<Integer> spell = new FuckTypeErasureSpell<Integer>() {};

        System.out.println(spell.castSpellCheckTypeOfGeneric(i));
        System.out.println(spell.castSpellCheckTypeOfGeneric(d));
    }

    public static void blackMagicOnAcid()
    {
        int howMuchLSD = 1000;

        FuckTypeErasureSpell[] blackMagicArray = new FuckTypeErasureSpell[howMuchLSD];
        for(int i = 0; i < howMuchLSD; i++)
        {
            blackMagicArray[i] = new FuckTypeErasureSpell<Integer>() ;
            System.out.println(blackMagicArray[i].castSpellCheckTypeOfGeneric(i));
        }
    }
}
