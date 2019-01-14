package com.jacksonplayz.thinkingwithaperture.blocks;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSurface extends BlockBase {

    public boolean canPortalOn;

    public BlockSurface(Material material, boolean canPortalOn, String name) {
        super(material, canPortalOn ? MapColor.SNOW : MapColor.GRAY, name, name);
        this.canPortalOn = canPortalOn;
        this.setCreativeTab(ThinkingWithAperture.TAB);
    }

    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return this.canPortalOn;
    }
}
