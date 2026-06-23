package com.github.peterservices.autohidehotbar.mixin;

import com.github.peterservices.autohidehotbar.config.AutoHideHotbarConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InventoryScreen.class})
public abstract class InventoryStatsMixin {
    @Inject(method = {"extractRenderState*"}, at = {@At("TAIL")})
    private void renderStats(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if (!AutoHideHotbarConfig.useCustomStatsDisplay) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player != null) {
            HandledScreenAccessor screen = (HandledScreenAccessor)this;
            int x = screen.getX() + 126;
            int y = screen.getY() + 55;
            int textColor = -9474193;
            float rawHearts = player.getHealth() / 2.0F;
            float hearts = Math.round(rawHearts * 2.0F) / 2.0F;
            int food = player.getFoodData().getFoodLevel() / 2;
            int lvl = player.experienceLevel;
            int xpPct = (int)(player.experienceProgress * 100.0F);
            graphics.text(minecraft.font, hearts + "♥", x, y, textColor, false);
            graphics.text(minecraft.font, food + "\ud83c\udf56", x, y + 9, textColor, false);
            graphics.text(minecraft.font, "Lv." + lvl + "(" + xpPct + "%)", x, y + 18, textColor, false);
        }
    }
}
