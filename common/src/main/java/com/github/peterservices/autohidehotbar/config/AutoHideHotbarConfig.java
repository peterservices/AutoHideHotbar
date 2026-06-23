package com.github.peterservices.autohidehotbar.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AutoHideHotbarConfig extends MidnightConfig {
    public static final String GENERAL = "general";
    public static final String TIMINGS = "timings";

    @Entry(category = GENERAL) public static boolean hideHeldItemTooltips = false;
    @Entry(category = GENERAL) public static boolean useCustomStatsDisplay = false;
    @Comment(category = GENERAL) public static Comment spacer1;
    @Entry(category = GENERAL) public static boolean onlyHideWhenFullHealth = true;
    @Entry(category = GENERAL) public static boolean onlyHideWhenFullFood = true;
    @Comment(category = GENERAL) public static Comment spacer2;
    @Entry(category = GENERAL) public static boolean neverHideHotbar = false;;
    @Entry(category = GENERAL) public static boolean neverHideHealth = false;
    @Entry(category = GENERAL) public static boolean neverHideFood = false;
    @Entry(category = GENERAL) public static boolean neverHideArmor = false;
    @Entry(category = GENERAL) public static boolean neverHideExperience = false;

    @Entry(category = TIMINGS, min = 0) public static int hotbarShowMilliseconds = 1500;
    @Entry(category = TIMINGS, min = 0) public static int healthShowTicks = 40;
    @Entry(category = TIMINGS, min = 0) public static int foodShowTicks = 30;
    @Entry(category = TIMINGS, min = 0) public static int armorShowTicks = 30;
    @Entry(category = TIMINGS, min = 0) public static int experienceShowTicks = 30;
    @Comment(category = TIMINGS, centered = true) public static Comment timingsComment;
}
