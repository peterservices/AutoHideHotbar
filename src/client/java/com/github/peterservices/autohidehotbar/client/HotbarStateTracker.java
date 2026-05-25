package com.github.peterservices.autohidehotbar.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class HotbarStateTracker {
    private static int lastSlot = -1;
    private static long lastChangeTime = 0L;
    private static final long HIDE_DELAY_MS = 1000L;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register((ClientTickEvents.EndTick)(client) -> {
            if (client.player != null) {
                int current = client.player.getInventory().getSelectedSlot();
                if (current != lastSlot) {
                    lastSlot = current;
                    lastChangeTime = System.currentTimeMillis();
                }
            }
        });
    }

    private static boolean shouldShowHotbarRaw() {
        return System.currentTimeMillis() - lastChangeTime < HIDE_DELAY_MS;
    }

    public static boolean shouldRender() {
        return shouldShowHotbarRaw();
    }
}
