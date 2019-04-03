package net.asbyth.tweaker.mixins.client;

import net.asbyth.tweaker.Tweaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow private boolean fullscreen;
    @Shadow public GameSettings gameSettings;
    @Shadow protected abstract void updateDisplayMode() throws LWJGLException;
    @Shadow public int displayWidth;
    @Shadow public int displayHeight;
    @Shadow private int tempDisplayWidth;
    @Shadow private int tempDisplayHeight;
    @Shadow public GuiScreen currentScreen;
    @Shadow public abstract void resize(int width, int height);
    @Shadow protected abstract void updateFramebufferSize();
    @Shadow public abstract void updateDisplay();
    @Shadow @Final private static Logger logger;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;<init>(Lnet/minecraft/client/Minecraft;)V", shift = AFTER))
    private void startGame(CallbackInfo ci) {
        Tweaker.LOGGER.info("Initializing Tweaker");
    }

    /**
     * @author asbyth
     * @reason Fullscreen fix
     *
     * explanation taken from h1nk's github page for the original tweaker
     *
     * The following bug occurs when a player exits fullscreen mode.
     * When exiting from fullscreen mode to windowed mode
     * the game window does not allow resizing anymore.
     * This is a bug specific to Windows users.
     * This bug remains unpatched, despite the Mojang bug tracker ticket being "resolved".
     */
    @Overwrite
    public void toggleFullscreen() {
        try {
            fullscreen = !fullscreen;
            gameSettings.fullScreen = fullscreen;

            if (fullscreen) {
                updateDisplayMode();
                displayWidth = Display.getDisplayMode().getWidth();
                displayHeight = Display.getDisplayMode().getHeight();

                if (displayWidth <= 0) {
                    displayWidth = 1;
                }

                if (displayHeight <= 0) {
                    displayHeight = 1;
                }
            } else {
                Display.setDisplayMode(new DisplayMode(tempDisplayWidth, tempDisplayHeight));
                displayWidth = tempDisplayWidth;
                displayHeight = tempDisplayHeight;

                if (displayWidth <= 0) {
                    displayWidth = 1;
                }

                if (displayHeight <= 0) {
                    displayHeight = 1;
                }
            }

            if (currentScreen != null) {
                resize(displayWidth, displayHeight);
            } else {
                updateFramebufferSize();
            }

            // todo: add a config for this
            // these are below and beneath to show where the {} needs to go
            Display.setResizable(false);
            Display.setResizable(true);
            // these are below and beneath to show where the {} needs to go
            // todo: add a config for this

            Display.setFullscreen(fullscreen);
            Display.setVSyncEnabled(gameSettings.enableVsync);
            updateDisplay();
        } catch (Exception e) {
            logger.error("Couldn't toggle fullscreen", e);
        }
    }
}
