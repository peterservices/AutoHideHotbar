package com.github.peterservices.autohidehotbar.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AutoHideHotbarConfig extends MidnightConfig {
    public static final String GENERAL = "general";
    public static final String TIMINGS = "timings";
    public static final String OPACITY = "opacity";

    public enum ElementInactivityMode {
        NOTHING, OPACITY, HIDE
    }

    public enum EffectsHidingMode {
        NEVER, AMBIENT, INFINITE, AMBIENTANDINFINITE, ALL
    }

    public enum LocatorBarDisplayMode {
        SHOW, OPACITY, NOBACKGROUND, OPACITYANDNOBACKGROUND, HIDE
    }

    public enum AirSupplyDisplayMode {
        SHOW, OPACITY, HIDE
    }

    @Entry(category = GENERAL) public static ElementInactivityMode hotbarInactivityMode = ElementInactivityMode.HIDE;
    @Entry(category = GENERAL) public static ElementInactivityMode healthInactivityMode = ElementInactivityMode.HIDE;
    @Entry(category = GENERAL) public static ElementInactivityMode hungerInactivityMode = ElementInactivityMode.HIDE;
    @Entry(category = GENERAL) public static ElementInactivityMode armorInactivityMode = ElementInactivityMode.HIDE;
    @Entry(category = GENERAL) public static ElementInactivityMode experienceInactivityMode = ElementInactivityMode.HIDE;
    @Entry(category = GENERAL) public static LocatorBarDisplayMode locatorBarDisplayMode = LocatorBarDisplayMode.NOBACKGROUND;
    @Entry(category = GENERAL) public static AirSupplyDisplayMode airSupplyDisplayMode = AirSupplyDisplayMode.SHOW;
    @Entry(category = GENERAL) public static EffectsHidingMode effectsHidingMode = EffectsHidingMode.NEVER;
    @Comment(category = GENERAL) public static Comment spacer1;
    @Entry(category = GENERAL, isSlider = true, min = 0, max = 100) public static int activeWhenHealthFallsBelowPercent = 100;
    @Entry(category = GENERAL, isSlider = true, min = 0, max = 100) public static int activeWhenHungerFallsBelowPercent = 100;
    @Comment(category = GENERAL) public static Comment spacer2;
    @Entry(category = GENERAL) public static boolean showHeldItemTooltips = true;
    @Entry(category = GENERAL) public static boolean showCustomStatsDisplay = false;

    @Entry(category = TIMINGS, min = 0) public static int hotbarActiveMilliseconds = 1500;
    @Entry(category = TIMINGS, min = 0) public static int healthActiveTicks = 40;
    @Entry(category = TIMINGS, min = 0) public static int foodActiveTicks = 30;
    @Entry(category = TIMINGS, min = 0) public static int armorActiveTicks = 30;
    @Entry(category = TIMINGS, min = 0) public static int experienceActiveTicks = 30;
    @Comment(category = TIMINGS, centered = true) public static Comment timingsComment;

    @Comment(category = OPACITY, centered = true) public static Comment opacityComment;
    @Entry(category = OPACITY, isSlider = true, min = 0F, max = 1F) public static float hotbarBackgroundOpacity = 1F;
    @Entry(category = OPACITY, isSlider = true, min = 0F, max = 1F) public static float hotbarSelectionOpacity = 1F;
    @Entry(category = OPACITY, isSlider = true, min = 0F, max = 1F) public static float hotbarStatsOpacity = 1F;
    @Entry(category = OPACITY, isSlider = true, min = 0F, max = 1F) public static float effectsBackgroundOpacity = 1F;
}
