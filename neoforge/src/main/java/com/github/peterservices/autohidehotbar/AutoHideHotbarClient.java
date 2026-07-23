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

        if (id.equals(VanillaGuiLayers.SELECTED_ITEM_NAME)) {
            if (!AutoHideHotbarConfig.showHeldItemTooltips) {
                event.setCanceled(true);
            }
        } else if (id.equals(VanillaGuiLayers.HOTBAR)) {
            if (!HotbarStateTracker.isHotbarActive() && AutoHideHotbarConfig.hotbarInactivityMode == AutoHideHotbarConfig.ElementInactivityMode.HIDE) {
                event.setCanceled(true);
            }
        }
    }
}
