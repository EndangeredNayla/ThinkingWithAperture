package com.jacksonplayz.thinkingwithaperture;

import com.jacksonplayz.thinkingwithaperture.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Logger;

@Mod(modid = ThinkingWithAperture.MODID, name = ThinkingWithAperture.NAME, version = ThinkingWithAperture.VERSION)
public class ThinkingWithAperture
{
    public static final String MODID = "twa";
    public static final String NAME = "Thinking With Aperture";
    public static final String VERSION = "1.0";

    public static final CreativeTabs TAB = new CreativeTabs("thinkingwithaperture") {
        @Override
        public ItemStack getTabIconItem() {
            return ItemStack.EMPTY;
        }
    };

    @Mod.Instance
    public static ThinkingWithAperture instance;

    @SidedProxy(clientSide = "com.jacksonplayz.thinkingwithportals.proxy.ClientProxy", serverSide = "com.jacksonplayz.thinkingwithportals.proxy.ServerProxy")
    public static CommonProxy proxy;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
