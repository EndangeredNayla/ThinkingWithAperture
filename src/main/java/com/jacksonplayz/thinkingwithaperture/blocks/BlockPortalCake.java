package com.jacksonplayz.thinkingwithaperture.blocks;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.block.BlockCake;

public class BlockPortalCake extends BlockCake {

    public BlockPortalCake() {
        this.setUnlocalizedName("portal_cake");
        this.setRegistryName("portal_cake");
        this.setCreativeTab(ThinkingWithAperture.TAB);
    }
}
