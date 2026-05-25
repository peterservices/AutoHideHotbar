package com.github.peterservices.autohidehotbar.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public final class StatChangeTracker {
    private static float lastHealth = -1.0F;
    private static int lastFood = -1;
    private static int lastArmor = -1;
    private static int lastExperience = -1;
    private static int healthTimer = 0;
    private static int foodTimer = 0;
    private static int armorTimer = 0;
    private static int experienceTimer = 0;
    private static final int HEALTH_SHOW_TIME = 40;
    private static final int OTHER_SHOW_TIME = 25;

    public static void tick() {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            float health = player.getHealth();
            int food = player.getFoodData().getFoodLevel();
            int armor = player.getArmorValue();
            int experience = player.totalExperience;
            if (health != lastHealth) {
                lastHealth = health;
                healthTimer = HEALTH_SHOW_TIME;
            }

            if (food != lastFood) {
                lastFood = food;
                foodTimer = OTHER_SHOW_TIME;
            }

            if (armor != lastArmor) {
                lastArmor = armor;
                armorTimer = OTHER_SHOW_TIME;
            }

            if (experience != lastExperience) {
                lastExperience = experience;
                experienceTimer = OTHER_SHOW_TIME;
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

    public static boolean shouldShowHealth() {
        return healthTimer > 0;
    }

    public static boolean shouldShowFood() {
        return foodTimer > 0;
    }

    public static boolean shouldShowArmor() {
        return armorTimer > 0;
    }

    public static boolean shouldShowExperience() {
        return experienceTimer > 0;
    }
}
