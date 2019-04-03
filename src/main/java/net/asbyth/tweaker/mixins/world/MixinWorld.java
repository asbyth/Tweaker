package net.asbyth.tweaker.mixins.world;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(World.class)
public abstract class MixinWorld {

    /**
     * @author asbyth  / 2pi / prplz
     * @reason void flicker fix
     *
     * explanation taken from https://2pi.pw/mods/voidflickerfix
     *
     * Sets the void y value to 0 so that you can view more sky texture in your world.
     * It was originally made for Arcane,
     * where the void would flicker constantly
     * when playing since the arenas were placed at the y coordinate
     * where the void starts.
     */
    @SideOnly(Side.CLIENT)
    @Overwrite
    public double getHorizon() {
        // todo: add a config for this
        // these are below and beneath to show where the {} needs to go
        return 0.0D;
        // todo: add a config for this
        // these are below and beneath to show where the {} needs to go

        // needs an else return provider.getHorizon();
        // would be a return worldsettings ? 0.0D : 63.0D
        // but forge uses provider.getHorizon();
    }
}
