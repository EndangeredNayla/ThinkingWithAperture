package com.jacksonplayz.thinkingwithaperture.net.message;

import java.util.UUID;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessagePlayRadioSound implements IMessage
{

    protected boolean valid;
    protected UUID radioId;

    public MessagePlayRadioSound()
    {
        this.valid = false;
    }

    public MessagePlayRadioSound(EntityRadio radio)
    {
        this.radioId = radio.getUniqueID();
        this.valid = true;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        if (this.valid)
        {
            buf.writeLong(this.radioId.getMostSignificantBits());
            buf.writeLong(this.radioId.getLeastSignificantBits());
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        try
        {
            this.radioId = new UUID(buf.readLong(), buf.readLong());
            this.valid = true;
        }
        catch (Exception e)
        {
            ThinkingWithAperture.logger().fatal("Invalid \'" + this.getClass().getName() + "\' Packet!", e);
        }
    }
}