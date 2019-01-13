package com.jacksonplayz.thinkingwithaperture.items;

import java.util.List;

import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret.TurretType;
import com.jacksonplayz.thinkingwithaperture.init.CustomModelRegistry;
import com.jacksonplayz.thinkingwithaperture.init.MetaItem;
import com.jacksonplayz.thinkingwithaperture.init.ModSounds;
import com.jacksonplayz.thinkingwithaperture.init.ModelHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemTurret extends ItemBase implements MetaItem, CustomModelRegistry
{
    public ItemTurret(String name)
    {
        super(name);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (TurretType type : TurretType.values())
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
            EntityTurret turret = new EntityTurret(world);
            
            ItemMonsterPlacer.applyItemEntityDataToEntity(world, player, stack, turret);
            
            turret.setLocationAndAngles((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + this.getYOffset(world, blockpos), (double) blockpos.getZ() + 0.5D, player.rotationYaw - 180, 0.0F);
            turret.rotationYawHead = turret.rotationYaw;
            turret.renderYawOffset = turret.rotationYaw;
            turret.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(turret)), (IEntityLivingData) null);
            world.spawnEntity(turret);
            turret.setType(TurretType.byMetadata(stack.getMetadata()));
            if(stack.getMetadata() == 3) {
                turret.playSound(ModSounds.ORACLE_TURRET_HELLO, 1.0F, 1.0F);
            }
            else {
                turret.playLivingSound();
            }

            if (!player.isCreative())
            {
                stack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    private double getYOffset(World world, BlockPos pos)
    {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(pos)).expand(0.0D, -1.0D, 0.0D);
        List<AxisAlignedBB> list = world.getCollisionBoxes((Entity) null, axisalignedbb);

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

            return d0 - (double) pos.getY();
        }
    }

    @Override
    public String getName(ItemStack stack)
    {
        return TurretType.byMetadata(stack.getMetadata()).getName();
    }

    @Override
    public void registerModels()
    {
        for (TurretType type : TurretType.values())
        {
            ModelHandler.registerModel(this, type.getMetadata(), "turret_" + type.getName());
        }
    }
}