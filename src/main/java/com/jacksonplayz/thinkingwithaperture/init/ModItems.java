package com.jacksonplayz.thinkingwithaperture.init;

import com.jacksonplayz.thinkingwithaperture.items.ItemModRecord;
import net.minecraft.item.Item;

public class ModItems
{

    public static final Item RECORD_STILL_ALIVE;
    public static final Item RECORD_WANT_YOU_GONE;
    public static final Item RECORD_CARA_MIA_ADDIO;
    public static final Item RECORD_RADIO_LOOP;

    static
    {
        RECORD_STILL_ALIVE = new ItemModRecord("still_alive", ModSounds.STILL_ALIVE);
        RECORD_WANT_YOU_GONE = new ItemModRecord("want_you_gone", ModSounds.WANT_YOU_GONE);
        RECORD_CARA_MIA_ADDIO = new ItemModRecord("cara_mia_addio", ModSounds.CARA_MIA_ADDIO);
        RECORD_RADIO_LOOP = new ItemModRecord("radio_loop", ModSounds.RADIO_LOOP);
    }

    public static void register()
    {
        register(RECORD_STILL_ALIVE);
        register(RECORD_WANT_YOU_GONE);
        register(RECORD_CARA_MIA_ADDIO);
        register(RECORD_RADIO_LOOP);
    }

    private static void register(Item item)
    {
        RegistrationHandler.Items.add(item);
    }
}