package net.asbyth.tweaker.mixins.client.gui;

import net.asbyth.tweaker.config.Options;
import net.minecraft.client.gui.GuiGameOver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGameOver.class)
public class MixinGuiGameOver {

    @Shadow private int enableButtonsTimer;

    /**
     * @author asbyth
     * @reason fix greyed out button when fullscreening after death
     *
     * this bug occurs when you die ingame and you end up
     * going in or out of fullscreen, you'll see the
     * respawn button is greyed out, and you'll have
     * to restart the game in order to fix that.
     * setting enableButtonsTimer to 0 as soon as the gui
     * appears will stop that from happening, and it'll
     * function normally.
     */
    @Inject(method = "initGui", at = @At("HEAD"))
    private void initGui(CallbackInfo ci) {
        if (Options.RESPAWN_BUTTON) {
            enableButtonsTimer = 0;
        }
    }
}
