package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractMountInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public final class StatChangeTracker {
    // Player stats
    private static float lastHealth = -1.0F;
    private static int lastFood = -1;
    private static int lastArmor = -1;
    private static int lastExperience = -1;
    private static int lastExperienceLevel = -1;

    private static int healthTimer = 0;
    private static int foodTimer = 0;
    private static int armorTimer = 0;
    private static int experienceTimer = 0;

    // Vehicle stats
    private static float lastVehicleHealth = -1.0F;
    private static int vehicleHealthTimer = 0;

    public static void tick(Minecraft client) {
        LocalPlayer player = client.player;
        if (player != null) {
            // Player variables
            float health = player.getHealth();
            int food = player.getFoodData().getFoodLevel();
            int armor = player.getArmorValue();
            int experience = player.totalExperience;
            int experienceLevel = player.experienceLevel;

            // Vehicle variables
            Entity vehicle = player.getControlledVehicle();

            if (health != lastHealth) {
                lastHealth = health;
                healthTimer = AutoHideHotbarConfig.healthActiveTicks;
            }

            if (food != lastFood) {
                lastFood = food;
                foodTimer = AutoHideHotbarConfig.foodActiveTicks;
            }

            if (armor != lastArmor) {
                lastArmor = armor;
                armorTimer = AutoHideHotbarConfig.armorActiveTicks;
            }

            if (experience != lastExperience || experienceLevel != lastExperienceLevel) {
                lastExperience = experience;
                lastExperienceLevel = experienceLevel;
                experienceTimer = AutoHideHotbarConfig.experienceActiveTicks;
            }

            if (vehicle instanceof LivingEntity) {
                float vehicleHealth = ((LivingEntity) vehicle).getHealth();
                if (vehicleHealth != lastVehicleHealth) {
                    lastVehicleHealth = vehicleHealth;
                    vehicleHealthTimer = AutoHideHotbarConfig.healthActiveTicks;
                }
            } else if (lastVehicleHealth != -1.0F) {
                lastVehicleHealth = -1.0F;
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

            if (vehicleHealthTimer > 0) {
                --vehicleHealthTimer;
            }
        }
    }

    private static boolean isHealthBelowPercent() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.getMaxHealth() * (AutoHideHotbarConfig.activeWhenHealthFallsBelowPercent / 100F) > lastHealth;
    }

    private static boolean isVehicleHealthBelowPercent() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.getControlledVehicle() instanceof LivingEntity && ((LivingEntity) player.getControlledVehicle()).getMaxHealth() * (AutoHideHotbarConfig.activeWhenHealthFallsBelowPercent / 100F) > lastVehicleHealth;
    }

    private static boolean isVehicleJumping() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.getJumpRidingScale() != 0.0F;
    }

    private static boolean isHungerBelowPercent() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && 20 * (AutoHideHotbarConfig.activeWhenHungerFallsBelowPercent / 100F) > lastFood;
    }

    private static boolean isInInventoryUI() {
        Screen screen = Minecraft.getInstance().screen;
        return screen instanceof InventoryScreen || screen instanceof AbstractMountInventoryScreen;
    }

    private static boolean isActiveInInventoryUI() {
        return isInInventoryUI() && !AutoHideHotbarConfig.showCustomStatsDisplay;
    }

    public static boolean isHealthActive() {
        return isActiveInInventoryUI() || isHealthBelowPercent() || healthTimer > 0;
    }

    public static boolean isHungerActive() {
        return isActiveInInventoryUI() || isHungerBelowPercent() || foodTimer > 0;
    }

    public static boolean isArmorActive() {
        return isActiveInInventoryUI() || armorTimer > 0;
    }

    public static boolean isExperienceActive() {
        return isActiveInInventoryUI() || experienceTimer > 0;
    }

    public static boolean isVehicleHealthActive() {
        return isActiveInInventoryUI() || isVehicleHealthBelowPercent() || vehicleHealthTimer > 0;
    }

    public static boolean isJumpBarActive() {
        return isVehicleJumping();
    }
}
