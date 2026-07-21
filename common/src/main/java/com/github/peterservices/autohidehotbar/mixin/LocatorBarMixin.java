package com.github.peterservices.autohidehotbar.mixin;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.LocatorBar;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocatorBar.class)
public class LocatorBarMixin {
    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (AutoHideHotbarConfig.locatorBarDisplayMode == AutoHideHotbarConfig.LocatorBarDisplayMode.SHOW) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        }
    }

    @WrapOperation(method = "lambda$extractRenderState$1(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/PartialTickSupplier;Lnet/minecraft/client/gui/GuiGraphicsExtractor;ILnet/minecraft/world/waypoints/TrackedWaypoint;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V"))
    private void onExtractRenderStateWaypoint(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, int color, Operation<Void> original) {
        if (AutoHideHotbarConfig.locatorBarDisplayMode != AutoHideHotbarConfig.LocatorBarDisplayMode.HIDE) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.multiplyAlpha(color, AutoHideHotbarConfig.hotbarStatsOpacity));
        }
    }

    @WrapOperation(method = "lambda$extractRenderState$1(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/PartialTickSupplier;Lnet/minecraft/client/gui/GuiGraphicsExtractor;ILnet/minecraft/world/waypoints/TrackedWaypoint;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractRenderStateArrow(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (AutoHideHotbarConfig.locatorBarDisplayMode != AutoHideHotbarConfig.LocatorBarDisplayMode.HIDE) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        }
    }
}
