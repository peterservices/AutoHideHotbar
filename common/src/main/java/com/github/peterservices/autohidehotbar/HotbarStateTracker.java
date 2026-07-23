package com.github.peterservices.autohidehotbar;

import java.util.Arrays;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import dev.architectury.event.events.client.ClientTickEvent;

import net.minecraft.client.Minecraft;
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

                Item[] hotbarItems = getHotbarItems(client);
                if (!Arrays.equals(hotbarItems, lastHotbarItems)) {
                    lastHotbarItems = hotbarItems;
                    lastChangeTime = System.currentTimeMillis();
                }
            }
        });
    }

    public static boolean isHotbarActive() {
        return System.currentTimeMillis() - lastChangeTime < AutoHideHotbarConfig.hotbarActiveMilliseconds;
    }

    private static Item[] getHotbarItems(Minecraft client) {
        Item[] hotbarItems = new Item[9];
        for (int i = 0; i < 9; i++) {
            hotbarItems[i] = client.player.getInventory().getItem(i).getItem();
        }
        return hotbarItems;
    }
}
