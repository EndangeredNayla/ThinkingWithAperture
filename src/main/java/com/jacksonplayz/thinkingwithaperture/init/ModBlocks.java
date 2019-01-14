package com.jacksonplayz.thinkingwithaperture.init;

import java.util.List;

import com.google.common.collect.Lists;
import com.jacksonplayz.thinkingwithaperture.blocks.BlockBase;
import com.jacksonplayz.thinkingwithaperture.items.ItemBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.apache.commons.lang3.Validate;

public class ModBlocks
{
    private static final List<Block> BLOCKS = Lists.<Block>newArrayList();

    public static final Block WHITE_PORTAL_SURFACE;
    public static final Block METAL_SURFACE;

    static
    {
        WHITE_PORTAL_SURFACE = new BlockBase(Material.ROCK, "white_portal_surface");
        METAL_SURFACE = new BlockBase(Material.IRON, "metal_surface");

        register();
    }

    private static void register()
    {
        registerFullBlock(WHITE_PORTAL_SURFACE);
        registerFullBlock(METAL_SURFACE);
    }

    public static void registerFullBlock(Block block)
    {
        registerFullBlock(block, new ItemBlockBase(block));
    }

    public static void registerFullBlock(Block block, Item item) {
        Validate.notNull(item, "An item could not be registered as it was null!");
        item.setRegistryName(block.getRegistryName());
        ModItems.register(item);
        registerBlock(block);
    }

    public static void registerBlock(Block block) {
        Validate.notNull(block, "A block could not be registered as it was null!");
        if (block.getRegistryName() == null)
            throw new RuntimeException("Block \'" + block.getClass() + "\' could not be registered as it does not have a registry name!");
        BLOCKS.add(block);
    }

    public static Block[] getBlocks()
    {
        return BLOCKS.toArray(new Block[0]);
    }
}