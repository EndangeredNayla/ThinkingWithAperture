package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ModBlocks
{


    static
    {

    }

    public static void register()
    {

    }


    public static void registerBlock(Block block)
    {
        RegistrationHandler.Blocks.add(block);
        ItemBlock itemBlock = (ItemBlock) new ItemBlockBase(block).setRegistryName(block.getRegistryName());
        RegistrationHandler.Items.add(itemBlock);
    }

    public static void registerBlock(Block block, ItemBlock item)
    {
        if (block.getRegistryName() == null)
            throw new IllegalArgumentException("Block " + block.getClass().getName() + " being registered does not have a registry name and could be successfully registered.");

        RegistrationHandler.Blocks.add(block);
        item.setRegistryName(block.getRegistryName());
        RegistrationHandler.Items.add(item);
    }
}
