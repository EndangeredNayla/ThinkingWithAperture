package com.jacksonplayz.thinkingwithaperture.items;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.armor.ModelLongFallBoots;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return ThinkingWithAperture.MODID + ":textures/models/armor/longfallboots.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped biped) {
        if (!itemStack.isEmpty()) {
            if (itemStack.getItem() instanceof ItemArmor) {

                ModelBiped armorModel = new ModelLongFallBoots(armorSlot);

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
