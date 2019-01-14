package com.jacksonplayz.thinkingwithaperture.items;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import com.jacksonplayz.thinkingwithaperture.init.ModSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class ItemPotatOS extends ItemBase
{
    public static final int MIN_TALK_DELAY = 60;
    public static final int MAX_TALK_DELAY = 90;

    public ItemPotatOS(String name)
    {
        super(name);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        if (!item.getEntityWorld().isRemote)
        {
            this.update(item.getItem(), item.getEntityWorld(), item.getPosition(), false);
        }
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (!world.isRemote)
        {
            this.update(stack, world, entity.getPosition(), true);
        }
    }

    private void onTalk(ItemStack stack, World world, BlockPos pos, boolean held)
    {
        world.playSound(null, pos, ModSounds.POTATOS_AMBIENT, SoundCategory.VOICE, 1.0f, 1.0f);
    }

    private void update(ItemStack stack, World world, BlockPos pos, boolean held)
    {
        long playTimer = getPlayTimer(stack);
        if (playTimer - world.getTotalWorldTime() <= 0)
        {
            this.onTalk(stack, world, pos, held);
            setPlayTimer(stack, world.getTotalWorldTime() + (MIN_TALK_DELAY + world.rand.nextInt(MAX_TALK_DELAY - MIN_TALK_DELAY)) * 20);
        }
    }

    public static long getPlayTimer(ItemStack stack)
    {
        if (stack.getItem() == ModItems.POTATOS && stack.hasTagCompound())
        {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt.hasKey("playTimer", Constants.NBT.TAG_LONG))
            {
                return nbt.getLong("playTimer");
            }
        }
        return -1;
    }

    public static void setPlayTimer(ItemStack stack, long playTimer)
    {
        if (stack.getItem() == ModItems.POTATOS)
        {
            if (!stack.hasTagCompound())
                stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setLong("playTimer", playTimer);
        }
    }
}
