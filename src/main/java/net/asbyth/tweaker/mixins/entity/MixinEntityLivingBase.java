package net.asbyth.tweaker.mixins.entity;

import net.asbyth.tweaker.mixinsimpl.entity.TweakEntityLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {

    private TweakEntityLivingBase implementation = new TweakEntityLivingBase((EntityLivingBase) (Object) this);

    /**
     * @author asbyth / prplz
     * @reason mouse delay fix
     *
     * explanation taken from prplz page on https://prplz.io/mousedelayfix
     *
     * fixes the mouse delay bug introduced in Minecraft 1.8 (MC-67665),
     * which causes your actual block and entity interaction
     * position to be one tick behind your crosshair.
     *
     * video showcase (h1nk):
     * https://gfycat.com/tinydrearybluebird
     *
     * video explanation (Semx11):
     * https://www.youtube.com/watch?v=3nlou35U1AA
     */
    @Inject(method = "getLook", at = @At("HEAD"), cancellable = true)
    private void getLook(float partialTicks, CallbackInfoReturnable<Vec3> cir) {
        implementation.getLook(partialTicks, cir, super.getLook(partialTicks));
    }
}
