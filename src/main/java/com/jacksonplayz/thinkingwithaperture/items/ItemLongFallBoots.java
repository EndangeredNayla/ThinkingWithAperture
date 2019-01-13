package com.jacksonplayz.thinkingwithaperture.items;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.KeyBinds;
import com.jacksonplayz.thinkingwithaperture.proxy.CommonProxy.ModelType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLongFallBoots extends ItemArmor {

    public ItemLongFallBoots(ArmorMaterial materialIn, int renderIndexIn, String name) {
        super(materialIn, renderIndexIn, EntityEquipmentSlot.FEET);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setCreativeTab(ThinkingWithAperture.TAB);
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
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return ThinkingWithAperture.MODID + ":textures/models/armor/longfallboots.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped biped) {
        if (!itemStack.isEmpty()) {
            if (itemStack.getItem() instanceof ItemArmor) {

                ModelBiped armorModel = ThinkingWithAperture.proxy.getModel(ModelType.LONG_FALL_BOOTS);
                if(armorModel == null)
                    return null;

                armorModel.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;
                armorModel.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;

                armorModel.isSneak = biped.isSneak;
                armorModel.isRiding = biped.isRiding;
                armorModel.isChild = biped.isChild;

                return armorModel;
            }
        }
        return null;
    }
}
