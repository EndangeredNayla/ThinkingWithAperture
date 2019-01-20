package com.jacksonplayz.thinkingwithaperture.client.gui;

import java.io.IOException;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.net.NetworkHandler;
import com.jacksonplayz.thinkingwithaperture.net.message.MessageUpdateTestChamberSign;
import com.jacksonplayz.thinkingwithaperture.tileentity.TileEntityTestChamberSign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiTestChamberSign extends GuiScreen
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(ThinkingWithAperture.MODID, "textures/gui/test_chamber_sign.png");

    private TileEntityTestChamberSign te;
    private BlockPos pos;
    private TileEntityTestChamberSign renderTe;

    private TextureAtlasSprite topTexture;
    private TextureAtlasSprite bottomTexture;

    private int chamber;
    private int maxChambers;
    private boolean[] skills;

    public GuiTestChamberSign(TileEntityTestChamberSign te)
    {
        this.te = te;
        this.pos = te.getPos();
        this.renderTe = new TileEntityTestChamberSign();

        this.skills = new boolean[10];
        for (int i = 0; i < this.skills.length; i++)
        {
            this.skills[i] = this.te.hasSkill(i);
        }
        this.setChamberParameters(false, te.getChamber(), te.getMaxChambers(), this.skills);
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.topTexture = this.mc.getTextureMapBlocks().getAtlasSprite(ThinkingWithAperture.MODID + ":blocks/test_chamber_sign_top");
        this.bottomTexture = this.mc.getTextureMapBlocks().getAtlasSprite(ThinkingWithAperture.MODID + ":blocks/test_chamber_sign_bottom");
        this.addButton(new GuiButton(0, this.width / 2 - 176 / 2 + 71, this.height / 2 - 138 / 2 + 5, 49, 20, "+"));
        this.addButton(new GuiButton(1, this.width / 2 - 176 / 2 + 71, this.height / 2 - 138 / 2 + 27, 49, 20, "-"));
        this.addButton(new GuiButton(2, this.width / 2 - 176 / 2 + 122, this.height / 2 - 138 / 2 + 5, 49, 20, "+"));
        this.addButton(new GuiButton(3, this.width / 2 - 176 / 2 + 122, this.height / 2 - 138 / 2 + 27, 49, 20, "-"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawDefaultBackground();

        GlStateManager.pushMatrix();
        GlStateManager.translate(this.width / 2 - 176 / 2, this.height / 2 - 138 / 2, 0);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(0, 0, 0, 0, 176, 138);

        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.drawTexturedModalRect(5, 5, this.topTexture, 64, 64);
        this.drawTexturedModalRect(5, 69, this.bottomTexture, 64, 64);

        GlStateManager.pushMatrix();
        GlStateManager.translate(69, 133, 0);
        GlStateManager.scale(-16 * 4, -16 * 4, 1);
        TileEntityRendererDispatcher.instance.render(this.renderTe, 0, 0, 0, 0, partialTicks);
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
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
                    this.setChamberParameters(true, this.chamber, this.maxChambers, this.skills);
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
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
        case 0:
            this.setChamberParameters(true, this.chamber + 1, this.maxChambers, this.skills);
            break;
        case 1:
            this.setChamberParameters(true, this.chamber - 1, this.maxChambers, this.skills);
            break;
        case 2:
            this.setChamberParameters(true, this.chamber, this.maxChambers + 1, this.skills);
            break;
        case 3:
            this.setChamberParameters(true, this.chamber, this.maxChambers - 1, this.skills);
            break;
        }
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        this.setChamberParameters(true, this.chamber, this.maxChambers, this.skills);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private void setChamberParameters(boolean updateServer, int chamber, int maxChambers, boolean... skills)
    {        
        this.renderTe.setMaxChambers(maxChambers);
        this.maxChambers = this.renderTe.getMaxChambers();
        this.renderTe.setChamber(chamber);
        this.chamber = this.renderTe.getChamber();
        for (int i = 0; i < this.skills.length; i++)
        {
            this.renderTe.setSkill(i, i >= skills.length ? false : skills[i]);
            this.skills[i] = this.renderTe.hasSkill(i);
        }

        this.te.setMaxChambers(maxChambers);
        this.te.setChamber(chamber);
        for (int i = 0; i < skills.length; i++)
        {
            this.te.setSkill(i, skills[i]);
        }

        if (updateServer)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> NetworkHandler.INSTANCE.sendToServer(new MessageUpdateTestChamberSign(this.pos, chamber, maxChambers, skills)));
        }
    }
}