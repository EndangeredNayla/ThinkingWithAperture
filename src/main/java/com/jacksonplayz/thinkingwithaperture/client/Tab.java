package com.jacksonplayz.thinkingwithaperture.client;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Tab extends CreativeTabs {

    public Tab() {
        super("thinkingwithaperture");
        this.setBackgroundImageName("aperture.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return ItemStack.EMPTY;
    }
}
