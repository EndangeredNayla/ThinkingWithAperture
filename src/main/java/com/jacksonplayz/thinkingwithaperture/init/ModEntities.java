package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities
{
    public static void register()
    {
        registerEntity("cube", EntityCube.class, 0, 80);
        registerEntity("turret", EntityTurret.class, 1, 80);
        registerEntity("radio", EntityRadio.class, 2, 80);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int trackingRange)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(ThinkingWithAperture.MODID + ":" + name), entity, name, id, ThinkingWithAperture.instance, trackingRange, 1, true);
    }
}
