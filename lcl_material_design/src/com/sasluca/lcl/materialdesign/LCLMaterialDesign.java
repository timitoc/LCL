package com.sasluca.lcl.materialdesign;

import com.badlogic.gdx.graphics.Color;
import com.sasluca.lcl.LCL;
import com.sasluca.lcl.graphics.fonts.LCLFontManager;
import com.sasluca.lcl.materialdesign.theme.UITheme;

/**
 * Created by Sas Luca on 8/10/2016.
 * Copyright (C) 2016 - LCL
 */

public class LCLMaterialDesign
{
    public static UITheme Theme;

    public static void init()
    {
        Theme = new UITheme();
        Theme.addColor("primary", new Color(28f / 255f, 117f / 255f, 188f / 255f, 1));
        Theme.addColor("accent", new Color(39f / 255f, 170f / 255f, 225f / 255f, 1));
        Theme.addColor("background", new Color(Color.WHITE));
        Theme.addColor("error", new Color(Color.RED));
        Theme.addColor("unfocused", new Color(Color.GRAY));

        LCLFontManager.addDistanceFieldFont("Roboto", 4);
        LCLFontManager.addDistanceFieldFont("Roboto_Light", 4);
        LCLFontManager.addDistanceFieldFont("Roboto_Medium", 4);
        LCLFontManager.addDistanceFieldFont("Roboto_Italic", 4);

        //LCLFontManager.getFont("Roboto").getFont().getData().getGlyph('t').xadvance += 3;
        //LCLFontManager.getFont("Roboto_Light").getFont().getData().getGlyph('t').xadvance += 3;
        //LCLFontManager.getFont("Roboto_Medium").getFont().getData().getGlyph('t').xadvance += 3;
        //LCLFontManager.getFont("Roboto_Italic").getFont().getData().getGlyph('t').xadvance += 3;
        //LCLFontManager.getFont("Roboto").getFont().getData().getGlyph('j').xadvance += 3;
        //LCLFontManager.getFont("Roboto_Light").getFont().getData().getGlyph('j').xadvance += 3;
        //LCLFontManager.getFont("Roboto_Medium").getFont().getData().getGlyph('j').xadvance += 3;
        //LCLFontManager.getFont("Roboto_Italic").getFont().getData().getGlyph('j').xadvance += 3;

        LCL.getResourceManager().addNinepatchesFromAtlas("material_design/ninepatches/cards/floating_cards.pack",
                "card_roundcornerlevel_0_floatlevel_0_lcl",
                "card_roundcornerlevel_0_floatlevel_1_lcl",
                "card_roundcornerlevel_0_floatlevel_2_lcl",
                "card_roundcornerlevel_0_floatlevel_3_lcl",
                "card_roundcornerlevel_0_floatlevel_4_lcl",
                "card_roundcornerlevel_0_floatlevel_5_lcl",
                "card_roundcornerlevel_0_floatlevel_6_lcl"
        );

        LCL.getResourceManager().addTextureLL("textbox_idle", "material_design/textures/textbox/textbox_idle.png");
        LCL.getResourceManager().addTextureLL("textbox_focused", "material_design/textures/textbox/textbox_focused.png");
        LCL.getResourceManager().addTextureLL("textbox_navigator", "material_design/textures/textbox/textbox_navigator.png");
        LCL.getResourceManager().addTextureLL("check_box_checked", "material_design/textures/check_box/check_box_checked.png");
        LCL.getResourceManager().addTextureLL("check_box_unchecked", "material_design/textures/check_box/check_box_unchecked.png");
        LCL.getResourceManager().addTextureLL("radio_button_checked", "material_design/textures/radio_button/radio_button_checked.png");
        LCL.getResourceManager().addTextureLL("radio_button_unchecked", "material_design/textures/radio_button/radio_button_unchecked.png");
    }
}
