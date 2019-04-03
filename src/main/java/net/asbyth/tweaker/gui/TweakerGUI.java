package net.asbyth.tweaker.gui;

import cc.luaq.animate.animation.LinearFlux;
import cc.luaq.animate.animation.interfaces.IRenewableFlux;
import net.asbyth.tweaker.Tweaker;
import net.asbyth.tweaker.config.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.io.IOException;

public class TweakerGUI extends GuiScreen {

    private IRenewableFlux flux;

    public TweakerGUI() {
        mc = Minecraft.getMinecraft();
        flux = new LinearFlux(1500, 0F, 75F);
    }

    @Override
    public void initGui() {
        flux.startAnimation();
        buttonList.add(new GuiButton(0, getCenter() - 75, getRowPos(2), 150, 20, getSuffix(Options.RESPAWN_BUTTON) + "Respawn Button"));
        buttonList.add(new GuiButton(1, getCenter() - 75, getRowPos(3), 150, 20, getSuffix(Options.ARMPOSITION) + "Arm Position"));
        buttonList.add(new GuiButton(2, getCenter() - 75, getRowPos(4), 150, 20, getSuffix(Options.FULLSCREENFIX) + "Fullscreen Fix"));
        buttonList.add(new GuiButton(3, getCenter() - 75, getRowPos(5), 150, 20, getSuffix(Options.MOUSEDELAYFIX) + "Mouse Delay Fix"));
        buttonList.add(new GuiButton(4, getCenter() - 75, getRowPos(6), 150, 20, getSuffix(Options.VOIDFLICKERFIX) + "Void Flicker Fix"));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, width, height, new Color(0, 0, 0, (int) flux.calculateValue()).getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawCenteredString(fontRendererObj, "Tweaker", getCenter(), getRowPos(1), 16777215);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                Options.RESPAWN_BUTTON = !Options.RESPAWN_BUTTON;
                button.displayString = getSuffix(Options.RESPAWN_BUTTON) + "Respawn Button";
                break;

            case 1:
                Options.ARMPOSITION = !Options.ARMPOSITION;
                button.displayString = getSuffix(Options.ARMPOSITION) + "Arm Position";
                break;

            case 2:
                Options.FULLSCREENFIX = !Options.FULLSCREENFIX;
                button.displayString = getSuffix(Options.FULLSCREENFIX) + "Fullscreen Fix";
                break;

            case 3:
                Options.MOUSEDELAYFIX = !Options.MOUSEDELAYFIX;
                button.displayString = getSuffix(Options.MOUSEDELAYFIX) + "Mouse Delay Fix";
                break;

            case 4:
                Options.VOIDFLICKERFIX = !Options.VOIDFLICKERFIX;
                button.displayString = getSuffix(Options.VOIDFLICKERFIX) + "Void Flicker Fix";
                break;
        }
    }

    public void show() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        mc.displayGuiScreen(this);
    }

    @Override
    public void onGuiClosed() {
        Tweaker.INSTANCE.saveConfig();
    }

    private int getRowPos(int rowNumber) {
        return height / 4 + (24 * rowNumber - 24) - 16;
    }

    private int getCenter() {
        return width / 2;
    }

    private String getSuffix(boolean enabled) {
        return (enabled ? ("§a") : ("§c"));
    }
}
