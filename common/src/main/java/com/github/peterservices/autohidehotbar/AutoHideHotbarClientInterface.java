package com.github.peterservices.autohidehotbar;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import dev.architectury.event.events.client.ClientTickEvent;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public interface AutoHideHotbarClientInterface {
    static void initClient() {
        AutoConfig.register(AutoHideHotbarConfig.class, JanksonConfigSerializer::new);
        HotbarStateTracker.init();
        ClientTickEvent.CLIENT_POST.register(StatChangeTracker::tick);
    }
}
