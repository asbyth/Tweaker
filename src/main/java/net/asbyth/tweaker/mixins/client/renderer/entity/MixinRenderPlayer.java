package net.asbyth.tweaker.mixins.client.renderer.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer {

    @Shadow public abstract ModelPlayer getMainModel();

    /**
     * @author asbyth
     * @reason fix arm position when sitting on entity
     *
     * explanation taken from h1nk's github page for the original tweaker
     *
     * This bug is a minor visual render glitch that occurs with the first person perspective of the player model.
     * The bug affects Minecraft versions 1.4.2-1.8.8+.
     * The bug is caused by incorrect logic to reset the hand
     * after the player enters a ridable entity such as a boat,
     * horse, minecart, etc.
     */
    @Inject(method = "renderRightArm", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPlayer;isSneak:Z", ordinal = 0))
    private void renderRightArm(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        ModelPlayer modelplayer = getMainModel();
        // todo: add a config for this
        // these are below and beneath to show where the {} needs to go
        modelplayer.isRiding = modelplayer.isSneak = false;
        // todo: add a config for this
        // these are below and beneath to show where the {} needs to go

        // needs an else modelplayer.isSneak = false;
    }
}
