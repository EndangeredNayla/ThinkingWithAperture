package com.jacksonplayz.thinkingwithaperture.init;

import java.util.List;

import com.google.common.collect.Lists;
import com.jacksonplayz.thinkingwithaperture.items.ItemCube;
import com.jacksonplayz.thinkingwithaperture.items.ItemLongFallBoots;
import com.jacksonplayz.thinkingwithaperture.items.ItemModRecord;
import com.jacksonplayz.thinkingwithaperture.items.ItemPotatOS;
import com.jacksonplayz.thinkingwithaperture.items.ItemRadio;
import com.jacksonplayz.thinkingwithaperture.items.ItemTurret;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ModItems
{
    private static final List<Item> ITEMS = Lists.<Item>newArrayList();

    public static final Item RECORD_STILL_ALIVE;
    public static final Item RECORD_WANT_YOU_GONE;
    public static final Item RECORD_CARA_MIA_ADDIO;
    public static final Item RECORD_RADIO_LOOP;

    public static final Item LONG_FALL_BOOTS;

    public static final Item WEIGHTED_CUBE;
    public static final Item RADIO;
    public static final Item TURRET;

    public static final Item POTATOS;

    static
    {
        RECORD_STILL_ALIVE = new ItemModRecord("still_alive", ModSounds.STILL_ALIVE);
        RECORD_WANT_YOU_GONE = new ItemModRecord("want_you_gone", ModSounds.WANT_YOU_GONE);
        RECORD_CARA_MIA_ADDIO = new ItemModRecord("cara_mia_addio", ModSounds.CARA_MIA_ADDIO);
        RECORD_RADIO_LOOP = new ItemModRecord("radio_loop", ModSounds.RADIO_LOOP_RECORD);

        LONG_FALL_BOOTS = new ItemLongFallBoots(ItemArmor.ArmorMaterial.DIAMOND, 1, "long_fall_boots");

        WEIGHTED_CUBE = new ItemCube("weighted_cube");
        RADIO = new ItemRadio("radio");
        TURRET = new ItemTurret("turret");

        POTATOS = new ItemPotatOS("potatos");

        register();
    }

    private static void register()
    {
        register(RECORD_STILL_ALIVE);
        register(RECORD_WANT_YOU_GONE);
        register(RECORD_CARA_MIA_ADDIO);
        register(RECORD_RADIO_LOOP);

        register(LONG_FALL_BOOTS);

        register(WEIGHTED_CUBE);
        register(RADIO);
        register(TURRET);

        register(POTATOS);
    }

    public static void register(Item item)
    {
        ITEMS.add(item);
    }

    public static Item[] getItems()
    {
        return ITEMS.toArray(new Item[0]);
    }
}