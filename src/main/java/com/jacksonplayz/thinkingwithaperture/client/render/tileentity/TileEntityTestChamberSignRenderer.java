package com.jacksonplayz.thinkingwithaperture.client.render.tileentity;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.blocks.BlockTestChamberSign;
import com.jacksonplayz.thinkingwithaperture.init.ModBlocks;
import com.jacksonplayz.thinkingwithaperture.tileentity.TileEntityTestChamberSign;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityTestChamberSignRenderer extends TileEntitySpecialRenderer<TileEntityTestChamberSign>
{
    public static final ResourceLocation ICONS_LOCATION = new ResourceLocation(ThinkingWithAperture.MODID, "textures/blocks/test_chamber_sign_icons.png");
    public static final ResourceLocation LOGO_LOCATION = new ResourceLocation(ThinkingWithAperture.MODID, "textures/gui/logo.png");

    @Override
    public void render(TileEntityTestChamberSign te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        IBlockState state = te.getWorld().getBlockState(te.getPos());
        EnumFacing facing = EnumFacing.NORTH;
        Minecraft mc = Minecraft.getMinecraft();
        if (state.getBlock() == ModBlocks.TEST_CHAMBER_SIGN)
        {
            facing = state.getValue(BlockTestChamberSign.FACING);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.5, 0.5, 0.5);
        GlStateManager.rotate(-facing.getHorizontalAngle(), 0, 1, 0);
        GlStateManager.translate(0.5, -0.5, -0.468);
        GlStateManager.rotate(180, 0, 1, 0);
        mc.getTextureManager().bindTexture(ICONS_LOCATION);

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.0625, 0.0625, 1);

        GlStateManager.pushMatrix();

        GlStateManager.translate(13, 10.625, 0);
        for (int yPos = 0; yPos < 2; yPos++)
        {
            GlStateManager.pushMatrix();
            for (int xPos = 0; xPos < 5; xPos++)
            {
                GlStateManager.pushMatrix();
                GlStateManager.scale(2.25, 2.25, 1);
                Gui.drawScaledCustomSizeModalRect(0, 0, 1 + xPos * 19, 8 + yPos * 19 + (te.hasSkill(xPos + yPos * 5) ? 38 : 0), 18, 18, -1, -1, 128, 128);
                GlStateManager.popMatrix();

                GlStateManager.translate(-2.38, 0, 0);
            }
            GlStateManager.popMatrix();
            GlStateManager.translate(0, -2.38, 0);
        }
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        int max = (int) ((double) te.getChamber() / (double) te.getMaxChambers() * 47.0);
        GlStateManager.translate(13.125, 15.4, 0);
        GlStateManager.scale(0.25, 1, 1);
        for (int i = 0; i < max; i++)
        {
            GlStateManager.translate(-1, 0, 0);
            Gui.drawScaledCustomSizeModalRect(0, 0, 1 + i * 2, 0, 2, 8, -1, -1, 128, 128);
        }
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.scale(-1, -1, 1);
        String chamber = Integer.toString(te.getChamber()).length() == 1 ? "0" + Integer.toString(te.getChamber()) : Integer.toString(te.getChamber());
        GlStateManager.translate(-1 - mc.fontRenderer.getStringWidth(chamber), -26, 0);
        mc.fontRenderer.drawString(chamber, 0, 0, 0xff000000);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(LOGO_LOCATION);
        GlStateManager.translate(14.5, 3.5, 0);
        GlStateManager.scale(-1, -1, 1);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, 8, 2, 1, 1);
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
    }
}