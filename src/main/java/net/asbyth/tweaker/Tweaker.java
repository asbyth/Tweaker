package net.asbyth.tweaker;

import net.asbyth.tweaker.command.TweakerCommand;
import net.asbyth.tweaker.config.Options;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

@Mod(
        modid = Tweaker.MOD_ID,
        name = Tweaker.MOD_NAME,
        version = Tweaker.VERSION,
        clientSideOnly = true,
        acceptedMinecraftVersions = "1.8.9"
)
public class Tweaker {

    public static final String MOD_ID = "tweakermod";
    public static final String MOD_NAME = "Tweaker";
    public static final String VERSION = "1.0";

    public static Logger LOGGER;

    private File file;

    @Mod.Instance(MOD_ID)
    public static Tweaker INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new TweakerCommand());
        file = new File(Minecraft.getMinecraft().mcDataDir, "tweaker/Tweaker.config");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER = LogManager.getLogger("Tweaker");
        loadConfig();
    }

    public void saveConfig() {
        Configuration config = new Configuration(file);
        updateConfig(config, false);
        config.save();
    }

    public void loadConfig() {
        Configuration config = new Configuration(file);
        config.load();
        updateConfig(config, true);
    }

    private void updateConfig(Configuration config, boolean load) {
        Property prop = config.get("General", "respawnButton", true);
        if (load) {
            Options.RESPAWN_BUTTON = prop.getBoolean();
        } else {
            prop.setValue(Options.RESPAWN_BUTTON);
        }

        prop = config.get("General" , "armPosition", true);
        if (load) {
            Options.ARMPOSITION = prop.getBoolean();
        } else {
            prop.setValue(Options.ARMPOSITION);
        }

        prop = config.get("General" , "fullscreenFix", true);
        if (load) {
            Options.FULLSCREENFIX = prop.getBoolean();
        } else {
            prop.setValue(Options.FULLSCREENFIX);
        }

        prop = config.get("General" , "mouseDelayFix", true);
        if (load) {
            Options.MOUSEDELAYFIX = prop.getBoolean();
        } else {
            prop.setValue(Options.MOUSEDELAYFIX);
        }

        prop = config.get("General" , "voidFlickerFix", true);
        if (load) {
            Options.VOIDFLICKERFIX = prop.getBoolean();
        } else {
            prop.setValue(Options.VOIDFLICKERFIX);
        }
    }
}
