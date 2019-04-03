package net.asbyth.tweaker.loader;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;

import static org.spongepowered.asm.mixin.MixinEnvironment.Side.CLIENT;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.SortingIndex(-999)
@IFMLLoadingPlugin.TransformerExclusions("net.asbyth.tweaker.loader")
public class TweakLoader implements IFMLLoadingPlugin {

    public TweakLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.tweaker.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        MixinEnvironment.getDefaultEnvironment().setSide(CLIENT);
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
