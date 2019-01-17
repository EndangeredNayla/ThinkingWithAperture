package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.block.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author CoffeeCatRailway
 * Created: 14/01/2019
 */
public class ModBlocks {

    public static final Block WHITE_PORTAL_SURFACE = new BlockBase("white_portal_surface", Material.ROCK);
    public static final Block METAL_SURFACE = new BlockBase("metal_surface", Material.IRON);
    public static final Block STONE_FLOOR = new BlockBase("stone_floor", Material.ROCK);

    public static void init() {
        RegistrationHandler.registerBlock(ThinkingWithAperture.TWAGroup, WHITE_PORTAL_SURFACE, METAL_SURFACE, STONE_FLOOR);
    }
}
