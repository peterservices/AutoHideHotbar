package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import dev.architectury.event.events.client.ClientTickEvent;

public class HotbarStateTracker {
    private static int lastSlot = -1;
    private static long lastChangeTime = 0L;

    public static void init() {
        ClientTickEvent.CLIENT_POST.register((client) -> {
            if (client.player != null) {
                int current = client.player.getInventory().getSelectedSlot();
                if (current != lastSlot) {
                    lastSlot = current;
                    lastChangeTime = System.currentTimeMillis();
                }
            }
        });
    }

    public static boolean isHotbarActive() {
        return System.currentTimeMillis() - lastChangeTime < AutoHideHotbarConfig.hotbarActiveMilliseconds;
    }
}
