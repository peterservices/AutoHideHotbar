package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = "autohidehotbar")
public final class AutoHideHotbarClient implements AutoHideHotbarClientInterface {
    @SubscribeEvent
    public static void onInitialize(FMLCommonSetupEvent event) {
        AutoHideHotbarClientInterface.initClient();
    }

    @SubscribeEvent
    public static void onRenderGuiLayer(RenderGuiLayerEvent.Pre event) {
        Identifier id = event.getName();

        if (id.equals(VanillaGuiLayers.HOTBAR)) {
            if (!HotbarStateTracker.shouldRender()) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.SELECTED_ITEM_NAME)) {
            if (AutoHideHotbarConfig.hideHeldItemTooltips) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND)) {
            if (!StatChangeTracker.shouldShowExperience() && !StatChangeTracker.shouldShowJumpBar()) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) {
            if (!StatChangeTracker.shouldShowExperience()) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.PLAYER_HEALTH)) {
            if (!StatChangeTracker.shouldShowHealth()) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.ARMOR_LEVEL)) {
            if (!StatChangeTracker.shouldShowArmor()) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.FOOD_LEVEL)) {
            if (!StatChangeTracker.shouldShowFood()) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.VEHICLE_HEALTH)) {
            if (!StatChangeTracker.shouldShowVehicleHealth()) {
                event.setCanceled(true);
            }
        }
    }
}
