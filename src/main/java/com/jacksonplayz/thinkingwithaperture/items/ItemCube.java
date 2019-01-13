package com.jacksonplayz.thinkingwithaperture.items;

import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube.CubeType;
import com.jacksonplayz.thinkingwithaperture.init.CustomModelRegistry;
import com.jacksonplayz.thinkingwithaperture.init.MetaItem;
import com.jacksonplayz.thinkingwithaperture.init.ModelHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCube extends ItemBase implements MetaItem, CustomModelRegistry
{
    public ItemCube(String name)
    {
        super(name);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (CubeType type : CubeType.values())
            {
                items.add(new ItemStack(this, 1, type.getMetadata()));
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItem(hand);

        if (world.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (!player.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {

            BlockPos blockpos = pos.offset(facing);
            double d0 = (double)blockpos.getX();
            double d1 = (double)blockpos.getY();
            double d2 = (double)blockpos.getZ();

            EntityCube cube = new EntityCube(world);
            
            ItemMonsterPlacer.applyItemEntityDataToEntity(world, player, stack, cube);

            cube.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(cube);
            world.playSound((EntityPlayer)null, cube.posX, cube.posY, cube.posZ, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
            cube.setType(CubeType.byMetadata(stack.getMetadata()));

            if (!player.isCreative())
            {
                stack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    @Override
    public String getName(ItemStack stack)
    {
        return CubeType.byMetadata(stack.getMetadata()).getName();
    }

    @Override
    public void registerModels()
    {
        for (CubeType type : CubeType.values())
        {
            ModelHandler.registerModel(this, type.getMetadata(), "weighted_" + type.getName() + "_cube");
        }
    }
}