package com.github.peterservices.autohidehotbar.mixin;

import com.github.peterservices.autohidehotbar.HotbarStateTracker;
import com.github.peterservices.autohidehotbar.StatChangeTracker;
import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Hud.class)
public abstract class HudExtractorMixin {
    @Shadow
    @Final
    private static Identifier HOTBAR_SPRITE;

    @Shadow
    @Final
    private static Identifier HOTBAR_SELECTION_SPRITE;

    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_LEFT_SPRITE;

    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_RIGHT_SPRITE;

    // Apply opacity to or hide effect backgrounds
    @WrapOperation(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractEffectBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original, @Local(name = "instance") MobEffectInstance effectInstance) {
        if (AutoHideHotbarConfig.effectsHidingMode == AutoHideHotbarConfig.EffectsHidingMode.ALL) {
            return;
        } else if (List.of(AutoHideHotbarConfig.EffectsHidingMode.AMBIENT, AutoHideHotbarConfig.EffectsHidingMode.AMBIENTANDINFINITE).contains(AutoHideHotbarConfig.effectsHidingMode) && effectInstance.isAmbient()) {
            return;
        } else if (List.of(AutoHideHotbarConfig.EffectsHidingMode.INFINITE, AutoHideHotbarConfig.EffectsHidingMode.AMBIENTANDINFINITE).contains(AutoHideHotbarConfig.effectsHidingMode) && effectInstance.isInfiniteDuration()) {
            return;
        }
        instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.effectsBackgroundOpacity));
    }

    // Hide infinite effect icons
    @WrapOperation(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V"))
    private void onExtractEffectSprite(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, int color, Operation<Void> original, @Local(name = "instance") MobEffectInstance effectInstance, @Local(name = "beneficialCount") LocalIntRef beneficialCountRef, @Local(name = "harmfulCount") LocalIntRef harmfulCountRef) {
        MobEffect effect = effectInstance.getEffect().value();
        if (AutoHideHotbarConfig.effectsHidingMode == AutoHideHotbarConfig.EffectsHidingMode.ALL) {
            return;
        } else if ((List.of(AutoHideHotbarConfig.EffectsHidingMode.AMBIENT, AutoHideHotbarConfig.EffectsHidingMode.AMBIENTANDINFINITE).contains(AutoHideHotbarConfig.effectsHidingMode) && effectInstance.isAmbient())
                || (List.of(AutoHideHotbarConfig.EffectsHidingMode.INFINITE, AutoHideHotbarConfig.EffectsHidingMode.AMBIENTANDINFINITE).contains(AutoHideHotbarConfig.effectsHidingMode) && effectInstance.isInfiniteDuration())) {
            // Decrement respective counter for hidden effects so the next visible effect is not shifted over
            if (effect.isBeneficial()) {
                beneficialCountRef.set(beneficialCountRef.get() - 1);
            } else {
                harmfulCountRef.set(harmfulCountRef.get() - 1);
            }
            return;
        }
        original.call(instance, renderPipeline, location, x, y, width, height, color);
    }

    // Apply opacity to or hide hotbar background and selection sprites
    @WrapOperation(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onExtractItemHotbar(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (!HotbarStateTracker.isHotbarActive()) {
            switch (AutoHideHotbarConfig.hotbarInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    if (List.of(HOTBAR_SPRITE, HOTBAR_OFFHAND_LEFT_SPRITE, HOTBAR_OFFHAND_RIGHT_SPRITE).contains(location)) {
                        instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarBackgroundOpacity));
                        return;
                    } else if (location == HOTBAR_SELECTION_SPRITE) {
                        instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarSelectionOpacity));
                        return;
                    }
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.hotbarInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            if (List.of(HOTBAR_SPRITE, HOTBAR_OFFHAND_LEFT_SPRITE, HOTBAR_OFFHAND_RIGHT_SPRITE).contains(location)) {
                instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarBackgroundOpacity));
                return;
            } else if (location == HOTBAR_SELECTION_SPRITE) {
                instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarSelectionOpacity));
                return;
            }
        }
        original.call(instance, renderPipeline, location, x, y, width, height);
    }

    // Apply opacity to or hide armor
    @WrapOperation(method = "extractArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void onExtractArmor(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (!StatChangeTracker.isArmorActive()) {
            switch (AutoHideHotbarConfig.armorInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.armorInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }

    // Apply opacity to or hide health
    @WrapOperation(method = "extractHeart", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void onExtractHearts(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (!StatChangeTracker.isHealthActive()) {
            switch (AutoHideHotbarConfig.healthInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.healthInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }

    // Apply opacity to or hide air bubbles
    @WrapOperation(method = "extractAirBubbles", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void onExtractAirBubbles(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        switch (AutoHideHotbarConfig.airSupplyDisplayMode) {
            case AutoHideHotbarConfig.AirSupplyDisplayMode.OPACITY -> {
                instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                return;
            }
            case AutoHideHotbarConfig.AirSupplyDisplayMode.HIDE -> {
                return;
            }
        }
        original.call(instance, renderPipeline, location, x, y, width, height);
    }

    // Apply opacity to or hide hunger
    @WrapOperation(method = "extractFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void onExtractFood(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (!StatChangeTracker.isHungerActive()) {
            switch (AutoHideHotbarConfig.hungerInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.hungerInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }

    // Apply opacity to or hide vehicle health
    @WrapOperation(method = "extractVehicleHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void onExtractVehicleHealth(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, Operation<Void> original) {
        if (!StatChangeTracker.isVehicleHealthActive()) {
            switch (AutoHideHotbarConfig.healthInactivityMode) {
                case AutoHideHotbarConfig.ElementInactivityMode.OPACITY -> {
                    instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
                    return;
                }
                case AutoHideHotbarConfig.ElementInactivityMode.HIDE -> {
                    return;
                }
            }
        }
        if (AutoHideHotbarConfig.healthInactivityMode != AutoHideHotbarConfig.ElementInactivityMode.OPACITY) {
            instance.blitSprite(renderPipeline, location, x, y, width, height, ARGB.white(AutoHideHotbarConfig.hotbarStatsOpacity));
        } else {
            original.call(instance, renderPipeline, location, x, y, width, height);
        }
    }
}
