package com.jacksonplayz.thinkingwithaperture.net.message;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Predicate;
import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.sound.RadioSound;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessagePlayRadioSound implements IMessage, IMessageHandler<MessagePlayRadioSound, IMessage> {

    private boolean valid;
    private UUID radioId;

    public MessagePlayRadioSound() {
        this.valid = false;
    }

    public MessagePlayRadioSound(EntityRadio radio) {
        this.radioId = radio.getUniqueID();
        this.valid = true;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (this.valid) {
            buf.writeLong(this.radioId.getMostSignificantBits());
            buf.writeLong(this.radioId.getLeastSignificantBits());
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            this.radioId = new UUID(buf.readLong(), buf.readLong());
            this.valid = true;
        } catch (Exception e) {
            ThinkingWithAperture.logger().fatal("Invalid \'" + this.getClass().getName() + "\' Packet!", e);
        }
    }

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