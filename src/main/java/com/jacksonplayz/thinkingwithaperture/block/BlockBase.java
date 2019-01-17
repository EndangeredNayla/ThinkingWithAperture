package com.jacksonplayz.thinkingwithaperture.block;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block {

    public BlockBase(String id, Material material) {
        this(id, Builder.create(material));
    }

    public BlockBase(String id, Builder builder) {
        super(builder);
        this.setRegistryName(new ResourceLocation(ThinkingWithAperture.MODID, id));
    }
}
