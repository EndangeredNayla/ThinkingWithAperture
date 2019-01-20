package com.jacksonplayz.thinkingwithaperture.client.gui;

import java.io.IOException;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.render.tileentity.TileEntityTestChamberSignRenderer;
import com.jacksonplayz.thinkingwithaperture.tileentity.TileEntityTestChamberSign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiTestChamberSign extends GuiScreen
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(ThinkingWithAperture.MODID, "textures/gui/test_chamber_sign.png");

    private BlockPos pos;
    private TileEntityTestChamberSign renderTe;

    private TextureAtlasSprite topTexture;
    private TextureAtlasSprite bottomTexture;

    private int chamber;
    private int maxChambers;
    private boolean[] skills;

    public GuiTestChamberSign(TileEntityTestChamberSign te)
    {
        this.pos = te.getPos();
        this.renderTe = new TileEntityTestChamberSign();

        this.skills = new boolean[10];
        for (int i = 0; i < this.skills.length; i++)
        {
            this.skills[i] = te.hasSkill(i);
        }
        this.setChamberParameters(te.getChamber(), te.getMaxChambers(), this.skills);
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.topTexture = this.mc.getTextureMapBlocks().getAtlasSprite(ThinkingWithAperture.MODID + ":blocks/test_chamber_sign_top");
        this.bottomTexture = this.mc.getTextureMapBlocks().getAtlasSprite(ThinkingWithAperture.MODID + ":blocks/test_chamber_sign_bottom");
        this.addButton(new GuiButton(0, this.width / 2 - 176 / 2 + 72, this.height / 2 - 138 / 2 + 5, 49, 20, "+"));
        this.addButton(new GuiButton(1, this.width / 2 - 176 / 2 + 72, this.height / 2 - 138 / 2 + 27, 49, 20, "-"));
        this.addButton(new GuiButton(2, this.width / 2 - 176 / 2 + 122, this.height / 2 - 138 / 2 + 5, 49, 20, "+"));
        this.addButton(new GuiButton(3, this.width / 2 - 176 / 2 + 122, this.height / 2 - 138 / 2 + 27, 49, 20, "-"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);

        GlStateManager.pushMatrix();
        GlStateManager.translate(this.width / 2 - 176 / 2, this.height / 2 - 138 / 2, 0);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(0, 0, 0, 0, 176, 138);

        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.drawTexturedModalRect(5, 5, this.topTexture, 64, 64);
        this.drawTexturedModalRect(5, 69, this.bottomTexture, 64, 64);

        this.mc.getTextureManager().bindTexture(TileEntityTestChamberSignRenderer.ICONS_LOCATION);
        for (int yPos = 0; yPos < 2; yPos++)
        {
            for (int xPos = 0; xPos < 5; xPos++)
            {
                Gui.drawScaledCustomSizeModalRect(72 + xPos * 20, 49 + yPos * 20, 1 + xPos * 19, 8 + yPos * 19 + (this.skills[xPos + yPos * 5] ? 38 : 0), 18, 18, 18, 18, 128, 128);
            }
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(69, 133, 0);
        GlStateManager.scale(-16 * 4, -16 * 4, 1);
        TileEntityRendererDispatcher.instance.render(this.renderTe, 0, 0, 0, 0, partialTicks);
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.color(1, 1, 1, 1);

        for (GuiButton button : this.buttonList)
        {
            if (mouseX >= button.x && mouseX < button.x + button.width && mouseY >= button.y && mouseY < button.y + button.height)
            {
                this.drawHoveringText(I18n.format("gui." + ThinkingWithAperture.MODID + ".test_chamber_sign.button." + (button.id < 2 ? "chamber" : "maxChambers") + "." + (button.id % 2 == 0 ? "add" : "subtract")), mouseX, mouseY);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (int yPos = 0; yPos < 2; yPos++)
        {
            for (int xPos = 0; xPos < 5; xPos++)
            {
                if (mouseX >= 72 + xPos * 20 + (this.width / 2 - 176 / 2) && mouseX < 72 + xPos * 20 + (this.width / 2 - 176 / 2) + 18 && mouseY >= 49 + yPos * 20 + (this.height / 2 - 138 / 2) && mouseY < 49 + yPos * 20 + (this.height / 2 - 138 / 2) + 18)
                {
                    this.skills[xPos + yPos * 5] = !this.skills[xPos + yPos * 5];
                    this.setChamberParameters(this.chamber, this.maxChambers, this.skills);
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
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
            this.setChamberParameters(this.chamber + 1, this.maxChambers, this.skills);
            break;
        case 1:
            this.setChamberParameters(this.chamber - 1, this.maxChambers, this.skills);
            break;
        case 2:
            this.setChamberParameters(this.chamber, this.maxChambers + 1, this.skills);
            break;
        case 3:
            this.setChamberParameters(this.chamber, this.maxChambers - 1, this.skills);
            break;
        }
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        this.setChamberParameters(this.chamber, this.maxChambers, this.skills);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private void setChamberParameters(int chamber, int maxChambers, boolean... skills)
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

        if (Minecraft.getMinecraft().world.getTileEntity(this.pos) instanceof TileEntityTestChamberSign)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> this.updateTe(Minecraft.getMinecraft().world, this.pos, chamber, maxChambers, skills));
        }
    }

    private void updateTe(World world, BlockPos pos, int chamber, int maxChamber, boolean[] skills)
    {
        TileEntityTestChamberSign te = (TileEntityTestChamberSign) world.getTileEntity(pos);
        te.setMaxChambers(maxChamber);
        te.setChamber(chamber);
        for (int i = 0; i < skills.length; i++)
        {
            te.setSkill(i, skills[i]);
        }
    }
}