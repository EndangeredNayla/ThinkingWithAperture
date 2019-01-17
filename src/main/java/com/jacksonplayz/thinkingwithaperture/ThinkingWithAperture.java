package com.jacksonplayz.thinkingwithaperture;

import com.jacksonplayz.thinkingwithaperture.client.Tab;
import com.jacksonplayz.thinkingwithaperture.init.RegistrationHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ThinkingWithAperture.MODID)
public class ThinkingWithAperture {

    public static final String MODID = "twa";
    public static final ItemGroup TWAGroup = new Tab();

    public static final Logger logger = LogManager.getLogger("Thinking With Aperture");

    public ThinkingWithAperture() {
        MinecraftForge.EVENT_BUS.register(this);

        RegistrationHandler.init();
        MinecraftForge.EVENT_BUS.register(new RegistrationHandler());
    }
}