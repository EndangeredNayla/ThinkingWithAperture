package com.jacksonplayz.thinkingwithaperture.blocks;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block {

    public BlockBase(Material material, String name) {
        this(material, material.getMaterialMapColor(), name, name);
    }

    public BlockBase(Material material, MapColor mapColor, String name) {
        this(material, mapColor, name, name);
    }

    public BlockBase(Material material, String registryName, String unlocalizedName) {
        this(material, material.getMaterialMapColor(), registryName, unlocalizedName);
    }

    public BlockBase(Material material, MapColor mapColor, String registryName, String unlocalizedName) {
        super(material, mapColor);
        this.setRegistryName(new ResourceLocation(ThinkingWithAperture.MODID, registryName));
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ThinkingWithAperture.TAB);
    }
}
