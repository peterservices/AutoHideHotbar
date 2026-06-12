package com.github.peterservices.autohidehotbar.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AutoHideHotbarConfig extends MidnightConfig {
    public static final String GENERAL = "general";
    public static final String TIMINGS = "timings";

    @Entry(category = GENERAL) public static boolean hideHeldItemTooltips = true;
    @Entry(category = GENERAL) public static boolean useCustomStatsDisplay = false;

    @Comment(category = TIMINGS, centered = true) public static Comment timingsComment;
    @Entry(category = TIMINGS, min = 0) public static int hotbarShowMilliseconds = 1000;
    @Entry(category = TIMINGS, min = 0) public static int healthShowTicks = 40;
    @Entry(category = TIMINGS, min = 0) public static int foodShowTicks = 25;
    @Entry(category = TIMINGS, min = 0) public static int armorShowTicks = 25;
    @Entry(category = TIMINGS, min = 0) public static int experienceShowTicks = 25;
}
