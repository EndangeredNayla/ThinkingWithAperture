package com.jacksonplayz.thinkingwithaperture.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.KeyBinds;
import com.jacksonplayz.thinkingwithaperture.init.MetaItem;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(Block block)
    {
        super(block);
        this.setCreativeTab(ThinkingWithAperture.TAB);
        this.setHasSubtypes(this instanceof MetaItem);
    }

    public ItemBlockBase(Block block, String name)
    {
        this(block);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
    {
        if (Keyboard.isKeyDown(KeyBinds.KEY_SHOW_INFO.getKeyCode()))
        {
            String info = I18n.format(this.getUnlocalizedName(stack) + ".info");
            tooltip.addAll(Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(info, 150));
        }
        else
        {
            tooltip.add(TextFormatting.YELLOW + I18n.format("item.show_info", KeyBinds.KEY_SHOW_INFO.getDisplayName()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return this instanceof MetaItem ? this.getUnlocalizedName() + "." + ((MetaItem) this).getName(stack) : super.getUnlocalizedName(stack);
    }

    @Override
    public int getMetadata(int damage)
    {
        return this instanceof MetaItem ? damage : super.getMetadata(damage);
    }
}