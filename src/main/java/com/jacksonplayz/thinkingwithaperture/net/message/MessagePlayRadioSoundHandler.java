package com.jacksonplayz.thinkingwithaperture.net.message;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayRadioSoundHandler implements IMessageHandler<MessagePlayRadioSound, IMessage>
{
    @Override
    public IMessage onMessage(MessagePlayRadioSound message, MessageContext ctx)
    {
        // if (!message.valid || ctx.side == Side.SERVER)
        // return null;
        // List<EntityRadio> radios = net.minecraft.client.Minecraft.getMinecraft().world.getEntities(EntityRadio.class, new Predicate<EntityRadio>()
        // {
        // @Override
        // public boolean apply(EntityRadio input)
        // {
        // return message.radioId.equals(input.getUniqueID());
        // }
        // });
        // if (radios.size() > 0 && radios.get(0) instanceof EntityRadio)
        // {
        // net.minecraft.client.Minecraft.getMinecraft().getSoundHandler().playSound(new com.jacksonplayz.thinkingwithaperture.client.sound.RadioSound(radios.get(0)));
        // }
        return null;
    }
}