package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities
{
    public static void register()
    {

    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int trackingRange)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(ThinkingWithAperture.MODID + ":" + name), entity, name, id, ThinkingWithAperture.instance, trackingRange, 1, true);
    }
}
