package com.github.peterservices.autohidehotbar;

import java.util.Arrays;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import dev.architectury.event.events.client.ClientTickEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.AbstractMountInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.item.Item;

public class HotbarStateTracker {
    private static int lastSlot = -1;
    private static long lastChangeTime = 0L;
    private static Item[] lastHotbarItems = new Item[9];

    public static void init() {
        ClientTickEvent.CLIENT_POST.register((client) -> {
            if (client.player != null) {
                int current = client.player.getInventory().getSelectedSlot();
                if (current != lastSlot) {
                    lastSlot = current;
                    lastChangeTime = System.currentTimeMillis();
                }

                if (isInInventoryUI()) {
                    Item[] hotbarItems = getHotbarItems(client);
                    if (!Arrays.equals(hotbarItems, lastHotbarItems)) {
                        lastHotbarItems = hotbarItems;
                        lastChangeTime = System.currentTimeMillis();
                    }
                }
            }
        });
    }

    private static boolean shouldShowHotbarRaw() {
        return System.currentTimeMillis() - lastChangeTime < AutoHideHotbarConfig.hotbarShowMilliseconds;
    }

    public static boolean shouldRender() {
        return AutoHideHotbarConfig.neverHideHotbar || shouldShowHotbarRaw();
    }

    private static boolean isInInventoryUI() {
        Screen screen = Minecraft.getInstance().gui.screen();
        return (screen instanceof InventoryScreen 
            || screen instanceof AbstractMountInventoryScreen 
            || screen instanceof AbstractContainerScreen
            || screen instanceof AbstractFurnaceScreen);
    }

    private static Item[] getHotbarItems(Minecraft client) {
        Item[] hotbarItems = new Item[9];
        for (int i = 0; i < 9; i++) {
            hotbarItems[i] = client.player.getInventory().getSlot(i).get().getItem();
        }
        return hotbarItems;
    }
}
