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

import java.util.List;

@Mixin(LocatorBar.class)
public class LocatorBarMixin {
    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (AutoHideHotbarConfig.locatorBarDisplayMode == AutoHideHotbarConfig.LocatorBarDisplayMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else if (AutoHideHotbarConfig.locatorBarDisplayMode == AutoHideHotbarConfig.LocatorBarDisplayMode.SHOW) {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }

    @WrapOperation(method = "lambda$extractRenderState$1(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/PartialTickSupplier;Lnet/minecraft/client/gui/GuiGraphicsExtractor;ILnet/minecraft/world/waypoints/TrackedWaypoint;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V"))
    private void onExtractRenderStateWaypoint(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, int color, Operation<Void> original) {
        if (List.of(AutoHideHotbarConfig.LocatorBarDisplayMode.OPACITY, AutoHideHotbarConfig.LocatorBarDisplayMode.OPACITYANDNOBACKGROUND).contains(AutoHideHotbarConfig.locatorBarDisplayMode)) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.multiplyAlpha(color, AutoHideHotbarConfig.hotbarStatsOpacity));
        } else if (List.of(AutoHideHotbarConfig.LocatorBarDisplayMode.SHOW, AutoHideHotbarConfig.LocatorBarDisplayMode.NOBACKGROUND).contains(AutoHideHotbarConfig.locatorBarDisplayMode)) {
            original.call(instance, renderPipeline, location, x, y, width, height, color);
        }
    }

    @WrapOperation(method = "lambda$extractRenderState$1(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/PartialTickSupplier;Lnet/minecraft/client/gui/GuiGraphicsExtractor;ILnet/minecraft/world/waypoints/TrackedWaypoint;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractRenderStateArrow(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (List.of(AutoHideHotbarConfig.LocatorBarDisplayMode.OPACITY, AutoHideHotbarConfig.LocatorBarDisplayMode.OPACITYANDNOBACKGROUND).contains(AutoHideHotbarConfig.locatorBarDisplayMode)) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else if (List.of(AutoHideHotbarConfig.LocatorBarDisplayMode.SHOW, AutoHideHotbarConfig.LocatorBarDisplayMode.NOBACKGROUND).contains(AutoHideHotbarConfig.locatorBarDisplayMode)) {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }
}
