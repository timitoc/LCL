package com.sasluca.lcl.ui.material_design;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;

import static com.sasluca.lcl.LCL.SYS;

/**
 * Created by Sas Luca on 01-Jul-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLMaterialDesign
{
    /** UI elements use this colors if colors are not specified in their constructors */
    public static Color ErrorColor = new Color(Color.RED);
    public static Color UnfocusedColor = new Color(Color.GRAY);
    public static Color PrimaryColor = new Color(28f / 255f, 117f / 255f, 188f / 255f, 1);
    public static Color AccentColor = new Color(39f / 255f, 170f / 255f, 225f / 255f, 1);

    private LCLMaterialDesign() { }

    public static void init()
    {
        LCLFontManager.addDistanceFieldFont("Roboto", 4);

        SYS.ResourceManger.addNinepatchesFromAtlas("material_design/ninepatches/cards/floating_cards.pack",
                "card_roundcornerlevel_0_floatlevel_0_lcl",
                "card_roundcornerlevel_0_floatlevel_1_lcl",
                "card_roundcornerlevel_0_floatlevel_2_lcl",
                "card_roundcornerlevel_0_floatlevel_3_lcl",
                "card_roundcornerlevel_0_floatlevel_4_lcl",
                "card_roundcornerlevel_0_floatlevel_5_lcl",
                "card_roundcornerlevel_0_floatlevel_6_lcl"
        );

        SYS.ResourceManger.addTextureLL("check_box_checked", "material_design/textures/check_box/check_box_checked.png");
        SYS.ResourceManger.addTextureLL("check_box_unchecked", "material_design/textures/check_box/check_box_unchecked.png");
        SYS.ResourceManger.addTextureLL("radio_button_checked", "material_design/textures/radio_button/radio_button_checked.png");
        SYS.ResourceManger.addTextureLL("radio_button_unchecked", "material_design/textures/radio_button/radio_button_unchecked.png");
        SYS.ResourceManger.addTextureLL("textbox_idle", "material_design/textures/textbox/idle.png");
        SYS.ResourceManger.addTextureLL("textbox_focused", "material_design/textures/textbox/focused.png");
    }
}
