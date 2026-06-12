package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import dev.architectury.event.events.client.ClientTickEvent;
import eu.midnightdust.lib.config.MidnightConfig;

public interface AutoHideHotbarClientInterface {
    static void initClient() {
        MidnightConfig.init("autohidehotbar", AutoHideHotbarConfig.class);
        HotbarStateTracker.init();
        ClientTickEvent.CLIENT_POST.register(StatChangeTracker::tick);
    }
}
