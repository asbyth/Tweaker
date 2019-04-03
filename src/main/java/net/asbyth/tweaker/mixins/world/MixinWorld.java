package net.asbyth.tweaker.mixins.world;

import net.asbyth.tweaker.config.Options;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class MixinWorld {

    @Shadow @Final public WorldProvider provider;

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
        if (Options.VOIDFLICKERFIX) {
            return 0.0D;
        } else {
            return provider.getHorizon();
        }
    }
}
