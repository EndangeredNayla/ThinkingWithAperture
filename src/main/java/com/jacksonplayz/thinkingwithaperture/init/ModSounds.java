package com.jacksonplayz.thinkingwithaperture.init;

import java.util.List;

import com.google.common.collect.Lists;
import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModSounds
{
    private static final List<SoundEvent> REGISTRY = Lists.<SoundEvent>newArrayList();

    public static final SoundEvent STILL_ALIVE;
    public static final SoundEvent WANT_YOU_GONE;
    public static final SoundEvent CARA_MIA_ADDIO;
    public static final SoundEvent RADIO_LOOP_RECORD;

    public static final SoundEvent RADIO_LOOP;

    public static final SoundEvent TURRET_HURT;
    public static final SoundEvent TURRET_DEATH;

    public static final SoundEvent DEFECTIVE_TURRET_HURT;
    public static final SoundEvent DEFECTIVE_TURRET_DEATH;

    public static final SoundEvent BIG_TURRET_HURT;
    public static final SoundEvent BIG_TURRET_DEATH;

    public static final SoundEvent ORACLE_TURRET_AMBIENT;
    public static final SoundEvent ORACLE_TURRET_HELLO;
    public static final SoundEvent ORACLE_TURRET_HURT;
    public static final SoundEvent ORACLE_TURRET_DEATH;

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
        RADIO_LOOP_RECORD = registerSound("record.radio_loop");

        RADIO_LOOP = registerSound("entity.radio.loop");

        TURRET_HURT = registerSound("entity.turret.hurt");
        TURRET_DEATH = registerSound("entity.turret.death");

        BIG_TURRET_HURT = registerSound("entity.big_turret.hurt");
        BIG_TURRET_DEATH = registerSound("entity.big_turret.death");

        DEFECTIVE_TURRET_HURT = registerSound("entity.defective_turret.hurt");
        DEFECTIVE_TURRET_DEATH = registerSound("entity.defective_turret.death");

        ORACLE_TURRET_AMBIENT = registerSound("entity.oracle_turret.ambient");
        ORACLE_TURRET_HELLO = registerSound("entity.oracle_turret.hello");
        ORACLE_TURRET_HURT = registerSound("entity.oracle_turret.hurt");
        ORACLE_TURRET_DEATH = registerSound("entity.oracle_turret.death");
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