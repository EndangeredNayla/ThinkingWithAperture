package com.jacksonplayz.thinkingwithaperture.net.message;

import java.util.List;

import com.google.common.base.Predicate;
import com.jacksonplayz.thinkingwithaperture.client.sound.RadioSound;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MessagePlayRadioSoundHandler implements IMessageHandler<MessagePlayRadioSound, IMessage>
{
    @Override
    public IMessage onMessage(MessagePlayRadioSound message, MessageContext ctx) {
        if (!message.valid || ctx.side == Side.SERVER)
            return null;
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;
        List<EntityRadio> radios = world.getEntities(EntityRadio.class, new Predicate<EntityRadio>() {
            @Override
            public boolean apply(EntityRadio input) {
                return message.radioId.equals(input.getUniqueID());
            }
        });
        if (radios.size() > 0 && radios.get(0) instanceof EntityRadio) {
            mc.getSoundHandler().playSound(new RadioSound(radios.get(0)));
        }
        return null;
    }
}