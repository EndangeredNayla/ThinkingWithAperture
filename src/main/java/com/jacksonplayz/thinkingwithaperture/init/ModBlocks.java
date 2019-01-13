package com.jacksonplayz.thinkingwithaperture.init;

import java.util.List;

import com.google.common.collect.Lists;
import com.jacksonplayz.thinkingwithaperture.items.ItemBlockBase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModBlocks
{
    private static final List<Block> BLOCKS = Lists.<Block>newArrayList();

    static
    {
        register();
    }

    private static void register()
    {

    }

    public static void registerBlock(Block block)
    {
        registerBlock(block, new ItemBlockBase(block).setRegistryName(block.getRegistryName()));
    }

    public static void registerBlock(Block block, Item item)
    {
        if (block.getRegistryName() == null)
            throw new IllegalArgumentException("Block " + block.getClass().getName() + " being registered does not have a registry name and could be successfully registered.");
        BLOCKS.add(block);
        ModItems.register(item.setRegistryName(block.getRegistryName()));
    }

    public static Block[] getBlocks()
    {
        return BLOCKS.toArray(new Block[0]);
    }
}