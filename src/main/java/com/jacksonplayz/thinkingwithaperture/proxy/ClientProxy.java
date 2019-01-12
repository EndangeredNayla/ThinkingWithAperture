package com.jacksonplayz.thinkingwithaperture.proxy;

import com.jacksonplayz.thinkingwithaperture.client.model.armor.ModelLongFallBoots;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderBigTurret;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderCube;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityBigTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class ClientProxy extends CommonProxy {
    
    private static final ModelLongFallBoots LONG_FALL_BOOTS_MODEL = new ModelLongFallBoots();

    @Override
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerEntityShader(EntityTurret.class, new ResourceLocation("shaders/post/sobel.json"));
        ClientRegistry.registerEntityShader(EntityBigTurret.class, new ResourceLocation("shaders/post/sobel.json"));

        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityCube.class, new RenderCube(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityTurret.class, new RenderTurret(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityBigTurret.class, new RenderBigTurret(renderManager));

    }

    @Override
    public <T> T getModel(ModelType type) {
        switch(type) {
        case LONG_FALL_BOOTS:
            break;
        }
        return super.getModel(type);
    }
}
