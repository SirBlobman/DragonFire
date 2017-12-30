package com.DragonFire.network.gui;

import com.DragonFire.DragonFire;
import com.DragonFire.block.tile.TileEntityNetherBrickFurnace;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiNetherBrickFurnace extends GuiContainer {
    private static final ResourceLocation GUI = new ResourceLocation(DragonFire.MODID, "textures/gui/nether_brick_furnace.png");
    private TileEntityNetherBrickFurnace tileEntity;
    
    public GuiNetherBrickFurnace(InventoryPlayer ip, TileEntityNetherBrickFurnace te) {
        super(new ContainerNetherBrickFurnace(ip, te));
        this.tileEntity = te;
        xSize = 176;
        ySize = 166;
    }
    
    private static final int
    PROGRESS_BAR_X = 73,
    PROGRESS_BAR_Y = 35,
    PROGRESS_BAR_U = 176,
    PROGRESS_BAR_V = 14,
    PROGRESS_BAR_W = 24,
    PROGRESS_BAR_H = 17;
    
    private static final int
    FLAME_X = 48,
    FLAME_Y = 53,
    FLAME_U = 176,
    FLAME_V = 0,
    FLAME_W = 14,
    FLAME_H = 14;
    
    private static final int
    NETHER_INDICATOR_X = 37,
    NETHER_INDICATOR_Y = 38,
    NETHER_INDICATOR_U = 176,
    NETHER_INDICATOR_V = 31,
    NETHER_INDICATOR_W = 7,
    NETHER_INDICATOR_H = 7;
    
    private static final int
    LABEL_X = 5,
    LABEL_Y = 5;
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        TextureManager tm = mc.getTextureManager();
        tm.bindTexture(GUI);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        
        double progress = tileEntity.cookTimeCompleteFraction();
        drawTexturedModalRect(guiLeft + PROGRESS_BAR_X, guiTop + PROGRESS_BAR_Y, PROGRESS_BAR_U, PROGRESS_BAR_V, (int) (progress * PROGRESS_BAR_W), PROGRESS_BAR_H);
        
        double fuel = tileEntity.fuelRemainingFraction();
        drawTexturedModalRect(guiLeft + FLAME_X, guiTop + FLAME_Y, FLAME_U, FLAME_V, FLAME_W, (int) (fuel * FLAME_H));
        
        if(tileEntity.isInNether()) drawTexturedModalRect(guiLeft + NETHER_INDICATOR_X, guiTop + NETHER_INDICATOR_Y, NETHER_INDICATOR_U, NETHER_INDICATOR_V, NETHER_INDICATOR_W, NETHER_INDICATOR_H);
    }
    
    @Override
    public void drawGuiContainerForegroundLayer(int mx, int my) {
        super.drawGuiContainerForegroundLayer(mx, my);
        FontRenderer font = fontRenderer;
        String label = I18n.format(tileEntity.getName());
        font.drawString(label, LABEL_X, LABEL_Y, Color.GRAY.getRGB());
    }
}