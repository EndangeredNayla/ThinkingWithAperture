package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ModelHandler
{

    public static void registerModel(Item item)
    {
        registerModel(item, 0, item.getRegistryName().getResourcePath());
    }

    public static void registerModel(Item item, int metadata)
    {
        registerModel(item, metadata, item.getRegistryName().getResourcePath());
    }

    public static void registerModel(Item item, int metadata, String fileName)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(new ResourceLocation(ThinkingWithAperture.MODID, fileName), "inventory"));
    }

    public static void bindTESRs()
    {
    }
}