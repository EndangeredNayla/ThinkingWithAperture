package com.jacksonplayz.thinkingwithaperture.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Handles registration in this mod.
 * 
 * @author Ocelot5836
 */
public class RegistrationHandler
{

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.getItems());
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.getBlocks());
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event)
    {
        Item[] items = ModItems.getItems();
        Block[] blocks = ModBlocks.getBlocks();

        for (Item item : items)
        {
            if (!(Block.getBlockFromItem(item) instanceof CustomModelRegistry))
            {
                if (item instanceof CustomModelRegistry)
                {
                    ((CustomModelRegistry) item).registerModels();
                }
                else
                {
                    ModelHandler.registerModel(item);
                }
            }
        }

        for (Block block : blocks)
        {
            if (block instanceof CustomModelRegistry)
            {
                ((CustomModelRegistry) block).registerModels();
            }
        }
    }
}