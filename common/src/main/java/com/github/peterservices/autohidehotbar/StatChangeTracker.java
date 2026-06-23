package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;

public final class StatChangeTracker {
    private static float lastHealth = -1.0F;
    private static int lastFood = -1;
    private static int lastArmor = -1;
    private static int lastExperience = -1;
    private static int lastExperienceLevel = -1;
    private static int healthTimer = 0;
    private static int foodTimer = 0;
    private static int armorTimer = 0;
    private static int experienceTimer = 0;

    public static void tick(Minecraft client) {
        Player player = client.player;
        if (player != null) {
            float health = player.getHealth();
            int food = player.getFoodData().getFoodLevel();
            int armor = player.getArmorValue();
            int experience = player.totalExperience;
            int experienceLevel = player.experienceLevel;

            if (health != lastHealth) {
                lastHealth = health;
                healthTimer = AutoHideHotbarConfig.healthShowTicks;
            }

            if (food != lastFood) {
                lastFood = food;
                foodTimer = AutoHideHotbarConfig.foodShowTicks;
            }

            if (armor != lastArmor) {
                lastArmor = armor;
                armorTimer = AutoHideHotbarConfig.armorShowTicks;
            }

            if (experience != lastExperience || experienceLevel != lastExperienceLevel) {
                lastExperience = experience;
                lastExperienceLevel = experienceLevel;
                experienceTimer = AutoHideHotbarConfig.experienceShowTicks;
            }

            if (healthTimer > 0) {
                --healthTimer;
            }

            if (foodTimer > 0) {
                --foodTimer;
            }

            if (armorTimer > 0) {
                --armorTimer;
            }

            if (experienceTimer > 0) {
                --experienceTimer;
            }
        }
    }

    private static boolean isMaxHealth() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.getMaxHealth() == lastHealth;
    }

    private static boolean isMaxFood() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && !player.getFoodData().needsFood();
    }

    private static boolean isInInventoryUI() {
        return Minecraft.getInstance().gui.screen() instanceof InventoryScreen;
    }

    private static boolean shouldShowInInventory() {
        return isInInventoryUI() && !AutoHideHotbarConfig.useCustomStatsDisplay;
    }

    public static boolean shouldShowHealth() {
        return AutoHideHotbarConfig.neverHideHealth || shouldShowInInventory() || (AutoHideHotbarConfig.onlyHideWhenFullHealth && !isMaxHealth()) || healthTimer > 0;
    }

    public static boolean shouldShowFood() {
        return AutoHideHotbarConfig.neverHideFood || shouldShowInInventory() || (AutoHideHotbarConfig.onlyHideWhenFullFood && !isMaxFood()) || foodTimer > 0;
    }

    public static boolean shouldShowArmor() {
        return AutoHideHotbarConfig.neverHideArmor || shouldShowInInventory() || armorTimer > 0;
    }

    public static boolean shouldShowExperience() {
        return AutoHideHotbarConfig.neverHideExperience || shouldShowInInventory() || experienceTimer > 0;
    }
}
