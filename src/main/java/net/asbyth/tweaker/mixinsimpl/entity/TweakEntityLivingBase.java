package net.asbyth.tweaker.mixinsimpl.entity;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class TweakEntityLivingBase {

    private EntityLivingBase parent;

    public TweakEntityLivingBase(EntityLivingBase parent) {
        this.parent = parent;
    }

    public void getLook(float partialTicks, CallbackInfoReturnable<Vec3> cir, Vec3 look) {
        EntityLivingBase base = parent;

        if (base instanceof EntityPlayerSP) {
            cir.setReturnValue(look);
        }
    }
}
