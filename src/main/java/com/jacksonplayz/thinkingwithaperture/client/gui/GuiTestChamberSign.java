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
import net.minecraft.util.math.BlockPos;

public class GuiTestChamberSign extends GuiScreen
{
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
        GlStateManager.translate(this.width / 2 - 64, this.height / 2 - 128, 0);

        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.drawTexturedModalRect(0, 0, this.topTexture, 128, 128);
        this.drawTexturedModalRect(0, 128, this.bottomTexture, 128, 128);

        this.mc.getTextureManager().bindTexture(TileEntityTestChamberSignRenderer.ICONS_LOCATION);
        for (int yPos = 0; yPos < 2; yPos++)
        {
            for (int xPos = 0; xPos < 5; xPos++)
            {
                Gui.drawScaledCustomSizeModalRect(24 + xPos * 19, 171 + yPos * 19, 1 + xPos * 19, 8 + yPos * 19 + (this.skills[xPos + yPos * 5] ? 38 : 0), 18, 18, 18, 18, 128, 128);
            }
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
                if (mouseX >= 24 + xPos * 19 + (this.width / 2 - 64) && mouseX < 24 + xPos * 19 + 18 + (this.width / 2 - 64) && mouseY >= 171 + yPos * 19 + (this.height / 2 - 128) && mouseY < 171 + yPos * 19 + 18 + (this.height / 2 - 128))
                {
                    this.skills[xPos + yPos * 5] = !this.skills[xPos + yPos * 5];
                }
            }
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