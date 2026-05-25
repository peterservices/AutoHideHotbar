package com.github.peterservices.autohidehotbar.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

public final class AutoHideHotbarSaverClient implements ClientModInitializer {
    public void onInitializeClient() {
        HotbarStateTracker.init();
        ClientTickEvents.END_CLIENT_TICK.register((client) -> StatChangeTracker.tick());
        HudElementRegistry.replaceElement(VanillaHudElements.HOTBAR, (original) -> (ctx, tick) -> {
            if (HotbarStateTracker.shouldRender()) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.HELD_ITEM_TOOLTIP, (original) -> (ctx, tick) -> {
        });
        HudElementRegistry.replaceElement(VanillaHudElements.INFO_BAR, (original) -> (ctx, tick) -> {
            if (StatChangeTracker.shouldShowExperience()) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.EXPERIENCE_LEVEL, (original) -> (ctx, tick) -> {
            if (StatChangeTracker.shouldShowExperience()) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.HEALTH_BAR, (original) -> (ctx, tick) -> {
            if (StatChangeTracker.shouldShowHealth()) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.ARMOR_BAR, (original) -> (ctx, tick) -> {
            if (StatChangeTracker.shouldShowArmor()) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.FOOD_BAR, (original) -> (ctx, tick) -> {
            if (StatChangeTracker.shouldShowFood()) {
                original.extractRenderState(ctx, tick);
            }
        });
    }
}
