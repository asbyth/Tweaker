package net.asbyth.tweaker;

import net.asbyth.tweaker.command.TweakerCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tweaker.MOD_ID,
        name = Tweaker.MOD_NAME,
        version = Tweaker.VERSION,
        clientSideOnly = true,
        acceptedMinecraftVersions = "1.8.9"
)
public class Tweaker {

    public static final String MOD_ID = "Tweaker";
    public static final String MOD_NAME = "Tweaker";
    public static final String VERSION = "1.0";

    public static Logger LOGGER;

    @Mod.Instance(MOD_ID)
    public static Tweaker INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new TweakerCommand());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER = LogManager.getLogger("Tweaker");
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
