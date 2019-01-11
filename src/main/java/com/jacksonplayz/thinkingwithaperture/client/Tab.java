package com.jacksonplayz.thinkingwithaperture.client;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class Tab extends CreativeTabs {

    private ResourceLocation[] frames;
    private long lastTime;
    private int frame;

    public Tab() {
        super("thinkingwithaperture");
        this.frames = new ResourceLocation[8];
        for (int i = 0; i < this.frames.length; i++) {
            this.frames[i] = new ResourceLocation(ThinkingWithAperture.MODID, "textures/gui/container/creative_inventory/tab_aperture" + i + ".png");
        }
        this.lastTime = System.currentTimeMillis();
        this.frame = 0;
    }

    @Override
    public ItemStack getTabIconItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getBackgroundImage() {
        if (System.currentTimeMillis() - this.lastTime >= 100) {
            this.lastTime = System.currentTimeMillis();
            this.frame++;
            if (this.frame >= this.frames.length)
                this.frame = 0;
        }
        return this.frames[this.frame];
    }
}
