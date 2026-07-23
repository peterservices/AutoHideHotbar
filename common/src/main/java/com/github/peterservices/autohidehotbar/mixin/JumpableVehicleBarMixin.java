package com.github.peterservices.autohidehotbar.mixin;

import com.github.peterservices.autohidehotbar.StatChangeTracker;
import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.JumpableVehicleBarRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(JumpableVehicleBarRenderer.class)
public class JumpableVehicleBarMixin {
    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (!StatChangeTracker.isJumpBarActive()) {
            switch (AutoHideHotbarConfig.experienceInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.experienceInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }

    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V"))
    private void onExtractProgress(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y, int width, int height, Operation<Void> original) {
        if (!StatChangeTracker.isJumpBarActive()) {
            switch (AutoHideHotbarConfig.experienceInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    instance.blitSprite(renderPipeline, location, spriteWidth, spriteHeight, textureX, textureY, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.experienceInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, spriteWidth, spriteHeight, textureX, textureY, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else {
            original.call(instance, renderPipeline, location, spriteWidth, spriteHeight, textureX, textureY, x, y, width, height);
        }
    }
}
