package com.github.peterservices.autohidehotbar.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InventoryScreen.class})
public abstract class InventoryStatsMixin {
    @Inject(method = {"extractRenderState*"}, at = {@At("TAIL")})
    private void autohidehotbar$renderStats(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            HandledScreenAccessor screen = (HandledScreenAccessor)this;
            int x = screen.autohidehotbar$getX() + 126;
            int y = screen.autohidehotbar$getY() + 55;
            int textColor = -9474193;
            float rawHearts = player.getHealth() / 2.0F;
            float hearts = (float)Math.round(rawHearts * 2.0F) / 2.0F;
            int food = player.getFoodData().getFoodLevel() / 2;
            int lvl = player.experienceLevel;
            int xpPct = (int)(player.experienceProgress * 100.0F);
            graphics.text(minecraft.font, hearts + "♥", x, y, textColor, false);
            graphics.text(minecraft.font, food + "\ud83c\udf56", x, y + 9, textColor, false);
            graphics.text(minecraft.font, "Lv." + lvl + "(" + xpPct + "%)", x, y + 18, textColor, false);
        }
    }
}
