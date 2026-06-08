package com.github.peterservices.autohidehotbar;

import dev.architectury.event.events.client.ClientTickEvent;

public class HotbarStateTracker {
    private static int lastSlot = -1;
    private static long lastChangeTime = 0L;
    private static final long HIDE_DELAY_MS = 1000L;

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

    private static boolean shouldShowHotbarRaw() {
        return System.currentTimeMillis() - lastChangeTime < HIDE_DELAY_MS;
    }

    public static boolean shouldRender() {
        return shouldShowHotbarRaw();
    }
}
