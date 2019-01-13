package com.jacksonplayz.thinkingwithaperture.items;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemRadio extends ItemBase {

    public ItemRadio(String name) {
        super(name);
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
            EntityRadio radio = new EntityRadio(world);

            ItemMonsterPlacer.applyItemEntityDataToEntity(world, player, stack, radio);

            radio.setLocationAndAngles((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + this.getYOffset(world, blockpos), (double) blockpos.getZ() + 0.5D, player.rotationYaw - 180, 0.0F);
            radio.rotationYawHead = radio.rotationYaw;
            radio.renderYawOffset = radio.rotationYaw;
            radio.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(radio)), (IEntityLivingData) null);
            world.spawnEntity(radio);

            if (!player.isCreative())
            {
                stack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    private double getYOffset(World p_190909_1_, BlockPos p_190909_2_)
    {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(p_190909_2_)).expand(0.0D, -1.0D, 0.0D);
        List<AxisAlignedBB> list = p_190909_1_.getCollisionBoxes((Entity) null, axisalignedbb);

        if (list.isEmpty())
        {
            return 0.0D;
        }
        else
        {
            double d0 = axisalignedbb.minY;

            for (AxisAlignedBB axisalignedbb1 : list)
            {
                d0 = Math.max(axisalignedbb1.maxY, d0);
            }

            return d0 - (double) p_190909_2_.getY();
        }
    }
}
