package com.github.peterservices.autohidehotbar.client.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "autohidehotbar")
public class AutoHideHotbarConfig implements ConfigData {
    @Comment("Whether tooltips for held items are hidden")
    public boolean hideHeldItemTooltips = true;
    
    @Comment("Whether stats are represented as text instead of the vanilla stats UI while in the inventory")
    public boolean useCustomStatsDisplay = false;

    public static AutoHideHotbarConfig getInstance() {
        return AutoConfig.getConfigHolder(AutoHideHotbarConfig.class).getConfig();
    }
}
