package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

public final class AutoHideHotbarClient implements AutoHideHotbarClientInterface, ClientModInitializer {
    public void onInitializeClient() {
        AutoHideHotbarClientInterface.initClient();

        HudElementRegistry.replaceElement(VanillaHudElements.HELD_ITEM_TOOLTIP, (original) -> (ctx, tick) -> {
            if (AutoHideHotbarConfig.showHeldItemTooltips) {
                original.extractRenderState(ctx, tick);
            }
        });
        HudElementRegistry.replaceElement(VanillaHudElements.HOTBAR, (original) -> (ctx, tick) -> {
            if (HotbarStateTracker.isHotbarActive() || AutoHideHotbarConfig.hotbarInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.HIDE) {
                original.extractRenderState(ctx, tick);
            }
        });
    }
}
