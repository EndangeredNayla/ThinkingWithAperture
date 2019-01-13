package com.jacksonplayz.thinkingwithaperture.net;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.net.message.MessagePlayRadioSound;
import com.jacksonplayz.thinkingwithaperture.net.message.MessagePlayRadioSoundHandler;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ThinkingWithAperture.MODID);
    private static int discriminator;

    public static void init() {
        registerMessage(MessagePlayRadioSoundHandler.class, MessagePlayRadioSound.class, Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        INSTANCE.registerMessage(messageHandler, requestMessageType, discriminator++, side);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(IMessageHandler<? super REQ, ? extends REPLY> messageHandler, Class<REQ> requestMessageType, Side side) {
        INSTANCE.registerMessage(messageHandler, requestMessageType, discriminator++, side);
    }
}