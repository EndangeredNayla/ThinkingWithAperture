package com.jacksonplayz.thinkingwithaperture.client.gui;

import java.io.IOException;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.render.tileentity.TileEntityTestChamberSignRenderer;
import com.jacksonplayz.thinkingwithaperture.net.NetworkHandler;
import com.jacksonplayz.thinkingwithaperture.net.message.MessageUpdateTestChamberSign;
import com.jacksonplayz.thinkingwithaperture.tileentity.TileEntityTestChamberSign;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiTestChamberSign extends GuiScreen
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(ThinkingWithAperture.MODID, "textures/gui/test_chamber_sign.png");

    private TileEntityTestChamberSign te;
    private BlockPos pos;

    private TextureAtlasSprite topTexture;
    private TextureAtlasSprite bottomTexture;

    private int chamber;
    private int maxChamber;
    private boolean[] skills;

    public GuiTestChamberSign(TileEntityTestChamberSign te)
    {
        this.te = te;
        this.pos = te.getPos();
        this.chamber = te.getChamber();
        this.maxChamber = te.getMaxChambers();
        this.skills = new boolean[10];
        for (int i = 0; i < this.skills.length; i++)
        {
            this.skills[i] = this.te.hasSkill(i);
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.topTexture = this.mc.getTextureMapBlocks().getAtlasSprite(ThinkingWithAperture.MODID + ":blocks/test_chamber_sign_top");
        this.bottomTexture = this.mc.getTextureMapBlocks().getAtlasSprite(ThinkingWithAperture.MODID + ":blocks/test_chamber_sign_bottom");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.pushMatrix();
        GlStateManager.translate(this.width / 2 - 176 / 2, this.height / 2 - 138 / 2, 0);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(0, 0, 0, 0, 176, 138);

        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.drawTexturedModalRect(5, 5, this.topTexture, 64, 64);
        this.drawTexturedModalRect(5, 69, this.bottomTexture, 64, 64);

        this.mc.getTextureManager().bindTexture(TileEntityTestChamberSignRenderer.ICONS_LOCATION);
        
        GlStateManager.translate(0, 0.5, 0);
        for (int yPos = 0; yPos < 2; yPos++)
        {
            GlStateManager.pushMatrix();
            for (int xPos = 0; xPos < 5; xPos++)
            {
                Gui.drawScaledCustomSizeModalRect(17, 90, 1 + xPos * 19, 8 + yPos * 19 + (this.skills[xPos + yPos * 5] ? 38 : 0), 18, 18, 9, 9, 128, 128);
                GlStateManager.translate(9.5, 0, 0);
            }
            GlStateManager.popMatrix();
            GlStateManager.translate(0, 9.5, 0);
        }

        GlStateManager.popMatrix();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (int yPos = 0; yPos < 2; yPos++)
        {
            for (int xPos = 0; xPos < 5; xPos++)
            {
                if (mouseX >= 17 + xPos * 9.5 + (this.width / 2 - 176 / 2) && mouseX < 17 + xPos * 9.5 + (this.width / 2 - 176 / 2) + 9.5 && mouseY >= 90 + yPos * 9.5 + (this.height / 2 - 138 / 2) && mouseY < 90 + yPos * 9.5 + (this.height / 2 - 138 / 2) + 9.5)
                {
                    this.skills[xPos + yPos * 5] = !this.skills[xPos + yPos * 5];
                }
            }
        }
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
        {
            this.mc.player.closeScreen();
        }
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        this.setChamberParameters(this.chamber, this.maxChamber, this.skills);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private void setChamberParameters(int chamber, int maxChamber, boolean... skills)
    {
        NetworkHandler.INSTANCE.sendToServer(new MessageUpdateTestChamberSign(this.pos, 7, 10, skills));
        TileEntityTestChamberSign te = (TileEntityTestChamberSign) this.mc.world.getTileEntity(this.pos);
        te.setMaxChambers(maxChamber);
        te.setChamber(chamber);
        for (int i = 0; i < skills.length; i++)
        {
            te.setSkill(i, skills[i]);
        }
    }
}