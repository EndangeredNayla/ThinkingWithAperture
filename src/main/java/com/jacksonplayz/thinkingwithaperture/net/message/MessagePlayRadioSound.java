package com.jacksonplayz.thinkingwithaperture.net.message;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.sound.RadioSound;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessagePlayRadioSound implements IMessage, IMessageHandler<MessagePlayRadioSound, IMessage> {

    private boolean valid;
    private int radioId;

    public MessagePlayRadioSound() {
        this.valid = false;
    }

    public MessagePlayRadioSound(EntityRadio radio) {
        this.radioId = radio.getEntityId();
        this.valid = true;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (this.valid) {
            buf.writeInt(this.radioId);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            this.radioId = buf.readInt();
            this.valid = true;
        } catch (Exception e) {
            ThinkingWithAperture.logger().fatal("Invalid \'" + this.getClass().getName() + "\' Packet!", e);
        }
    }

    @Override
    public IMessage onMessage(MessagePlayRadioSound message, MessageContext ctx) {
        if(!message.valid || ctx.side == Side.SERVER)
            return null;
        Minecraft.getMinecraft().getSoundHandler().playSound(new RadioSound((EntityRadio) Minecraft.getMinecraft().world.getEntityByID(message.radioId)));
        return null;
    }
}