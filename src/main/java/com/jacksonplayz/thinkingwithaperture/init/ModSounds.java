package com.jacksonplayz.thinkingwithaperture.init;

import com.google.common.collect.Lists;
import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ModSounds
{
    private static final List<SoundEvent> REGISTRY = Lists.<SoundEvent>newArrayList();

    public static final SoundEvent STILL_ALIVE;
    public static final SoundEvent WANT_YOU_GONE;
    public static final SoundEvent CARA_MIA_ADDIO;
    public static final SoundEvent RADIO_LOOP;

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation resource = new ResourceLocation(ThinkingWithAperture.MODID, name);
        SoundEvent sound = new SoundEvent(resource).setRegistryName(resource.toString());
        REGISTRY.add(sound);
        return sound;
    }

    static
    {
        STILL_ALIVE = registerSound("record.still_alive");
        WANT_YOU_GONE = registerSound("record.want_you_gone");
        CARA_MIA_ADDIO = registerSound("record.cara_mia_addio");
        RADIO_LOOP = registerSound("record.radio_loop");
    }

    @Mod.EventBusSubscriber(modid = ThinkingWithAperture.MODID)
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
        {
            event.getRegistry().registerAll(REGISTRY.toArray(new SoundEvent[0]));
        }
    }
}