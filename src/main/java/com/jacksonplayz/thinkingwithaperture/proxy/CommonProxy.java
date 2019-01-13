package com.jacksonplayz.thinkingwithaperture.proxy;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
    
    @Nullable
    public <T> T getModel(ModelType type){
        return null;
    }
    
    public void playEntitySound(Entity entity) {
    }

    public enum ModelType {
        LONG_FALL_BOOTS;
    }
}
