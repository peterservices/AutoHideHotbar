package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

public final class AutoHideHotbarClient implements AutoHideHotbarClientInterface, ClientModInitializer {
    public void onInitializeClient() {
        AutoHideHotbarClientInterface.initClient();

        HudElementRegistry.replaceElement(VanillaHudElements.HOTBAR, (original) -> (ctx, tick) -> {
            if (HotbarStateTracker.shouldRender()) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.HELD_ITEM_TOOLTIP, (original) -> (ctx, tick) -> {
            if (!AutoHideHotbarConfig.hideHeldItemTooltips) {
                original.extractRenderState(ctx, tick);
            }
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
