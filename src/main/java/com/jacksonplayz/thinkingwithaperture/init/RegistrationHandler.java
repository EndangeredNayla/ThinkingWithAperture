package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CoffeeCatRailway
 * Created: 14/01/2019
 */
@Mod.EventBusSubscriber(modid = ThinkingWithAperture.MODID)
public class RegistrationHandler {

    private static final Set<Item> ITEMS = new HashSet<>();
    private static final Set<Block> BLOCKS = new HashSet<>();

    public static void init() {
        //ModItems.init();
        //ThinkingWithAperture.logger.info("Items registered");
        ModBlocks.init();
        ThinkingWithAperture.logger.info("Blocks registered");
    }

    @SubscribeEvent
    public void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();

        for (Item item : ITEMS)
            reg.register(item);
    }

    @SubscribeEvent
    public void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> reg = event.getRegistry();

        for (Block block : BLOCKS)
            reg.register(block);
    }

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    public static void registerItem(Item... items) {
        for (Item item : items)
            registerItem(item);
    }

    public static void registerBlock(ItemGroup group, Block block) {
        BLOCKS.add(block);
        ItemBlock itemBlock = new ItemBlock(block, new Item.Builder().group(group));
        itemBlock.setRegistryName(block.getRegistryName());
        registerItem(itemBlock);
    }

    public static void registerBlock(Block block) {
        registerBlock(null, block);
    }

    public static void registerBlock(ItemGroup group, Block... blocks) {
        for (Block block : blocks) {
            registerBlock(group, block);
        }
    }

    public static void registerBlock(Block... blocks) {
        for (Block block : blocks) {
            registerBlock(null, block);
        }
    }
}