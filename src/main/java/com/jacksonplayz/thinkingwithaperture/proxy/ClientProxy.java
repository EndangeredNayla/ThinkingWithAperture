package com.jacksonplayz.thinkingwithaperture.proxy;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.armor.ModelLongFallBoots;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderCube;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderRadio;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderTurret;
import com.jacksonplayz.thinkingwithaperture.client.render.tileentity.TileEntityTestChamberSignRenderer;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;
import com.jacksonplayz.thinkingwithaperture.tileentity.TileEntityTestChamberSign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    private static final ModelLongFallBoots LONG_FALL_BOOTS_MODEL = new ModelLongFallBoots();

    @Override
    public void init(FMLInitializationEvent event)
    {
        ClientRegistry.registerEntityShader(EntityTurret.class, new ResourceLocation(ThinkingWithAperture.MODID, "shaders/post/turret.json"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTestChamberSign.class, new TileEntityTestChamberSignRenderer());

        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityCube.class, new RenderCube(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityTurret.class, new RenderTurret(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRadio.class, new RenderRadio(renderManager));
    }

    @Override
    public <T extends ModelBase> T getModel(ModelType type)
    {
        switch (type)
        {
        case LONG_FALL_BOOTS:
            return (T) LONG_FALL_BOOTS_MODEL;
        }
        return super.getModel(type);
    }
}
