package com.jacksonplayz.thinkingwithaperture.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBinds
{
    public static final String CATEGORY = "key.categories.twa";

    public static final KeyBinding KEY_SHOW_INFO;

    public static void register()
    {
        ClientRegistry.registerKeyBinding(KeyBinds.KEY_SHOW_INFO);
    }

    static
    {
        KEY_SHOW_INFO = new KeyBinding("key.show_info", Keyboard.KEY_LSHIFT, KeyBinds.CATEGORY);
    }
}