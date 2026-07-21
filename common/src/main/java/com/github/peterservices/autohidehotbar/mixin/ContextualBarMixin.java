package com.github.peterservices.autohidehotbar.mixin;

import com.github.peterservices.autohidehotbar.StatChangeTracker;
import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.ContextualBar;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ContextualBar.class)
public interface ContextualBarMixin {
    @WrapOperation(method = "extractExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"))
    private static void onExtractExperienceLevel(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color, boolean dropShadow, Operation<Void> original) {
        if (!StatChangeTracker.isExperienceActive()) {
            switch (AutoHideHotbarConfig.experienceInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    original.call(instance, font, str, x, y, ARGB.multiplyAlpha(color, AutoHideHotbarConfig.hotbarStatsOpacity), dropShadow);
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.experienceInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            original.call(instance, font, str, x, y, ARGB.multiplyAlpha(color, AutoHideHotbarConfig.hotbarStatsOpacity), dropShadow);
        } else {
            original.call(instance, font, str, x, y, color, dropShadow);
        }
    }
}
