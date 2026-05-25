package com.github.peterservices.autohidehotbar.client.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({AbstractContainerScreen.class})
public interface HandledScreenAccessor {
    @Accessor("leftPos")
    int autohidehotbar$getX();

    @Accessor("topPos")
    int autohidehotbar$getY();
}
