package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Item;
import java.util.Arrays;

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

                Item[] hotbarItems = getHotbarItems(client.player);
                if (!Arrays.equals(hotbarItems, lastHotbarItems)) {
                    lastHotbarItems = hotbarItems;
                    lastChangeTime = System.currentTimeMillis();
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

    private static Item[] getHotbarItems(LocalPlayer player) {
        Item[] hotbarItems = new Item[9];
        for (int i = 0; i < 9; i++) {
            hotbarItems[i] = player.getInventory().getItem(i).getItem();
        }
        return hotbarItems;
    }
}
