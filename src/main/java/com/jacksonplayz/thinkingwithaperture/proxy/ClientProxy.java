package com.jacksonplayz.thinkingwithaperture.proxy;

import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderBigTurret;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderCube;
import com.jacksonplayz.thinkingwithaperture.client.render.entity.RenderTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityBigTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);

        ClientRegistry.registerEntityShader(EntityTurret.class, new ResourceLocation("shaders/post/sobel.json"));
        ClientRegistry.registerEntityShader(EntityBigTurret.class, new ResourceLocation("shaders/post/sobel.json"));

        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityCube.class, new RenderCube(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityTurret.class, new RenderTurret(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityBigTurret.class, new RenderBigTurret(renderManager));

    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
