package com.jacksonplayz.thinkingwithaperture.blocks;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.init.ModBlocks;
import com.jacksonplayz.thinkingwithaperture.util.Bounds;
import com.jacksonplayz.thinkingwithaperture.util.CollisionHelper;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSignage extends BlockRotateable {


    private static final AxisAlignedBB[] BOUNDING_BOX_BOTTOM = new Bounds(16, -1, -1, 15, 33 , 17).getRotatedBounds();
    private static final AxisAlignedBB[] BOUNDING_BOX_TOP = new Bounds(16, -17, -1, 15, 17, 17).getRotatedBounds();

    public static final PropertyBool TOP = PropertyBool.create("top");

    public BlockSignage(String name, boolean top) {
        super(Material.GLASS, name);
        this.setLightLevel(2.0F);
        this.setSoundType(SoundType.GLASS);
        if(top){
            this.setCreativeTab(null);
        }
        else  {
            this.setCreativeTab(ThinkingWithAperture.TAB);
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if(this == ModBlocks.SIGNAGE)
        {
            if(worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.SIGNAGE_TOP)
            {
                worldIn.destroyBlock(pos.up(), false);
            }
        }
        else
        {
            if(worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.SIGNAGE)
            {
                worldIn.destroyBlock(pos.down(), false);
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.up());
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if(this == ModBlocks.SIGNAGE)
        {
            world.setBlockState(pos.up(), ModBlocks.SIGNAGE_TOP.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()));
        }
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if(this == ModBlocks.SIGNAGE)
        {
            return BOUNDING_BOX_BOTTOM[state.getValue(FACING).getHorizontalIndex()];
        }
        else
        {
            return BOUNDING_BOX_TOP[state.getValue(FACING).getHorizontalIndex()];
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return new ItemStack(ModBlocks.SIGNAGE).getItem();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(ModBlocks.SIGNAGE);
    }
}
