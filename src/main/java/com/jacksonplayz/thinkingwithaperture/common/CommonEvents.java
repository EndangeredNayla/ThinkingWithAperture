package com.jacksonplayz.thinkingwithaperture.common;

import com.jacksonplayz.thinkingwithaperture.items.ItemLongFallBoots;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEvents {

    @SubscribeEvent
    public void cancelPlayerFallDamage(LivingFallEvent event) {
        if (event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemLongFallBoots)
            event.setDamageMultiplier(0);
    }
}
