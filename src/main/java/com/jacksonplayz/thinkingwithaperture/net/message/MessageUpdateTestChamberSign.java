package com.jacksonplayz.thinkingwithaperture.net.message;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.tileentity.TileEntityTestChamberSign;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageUpdateTestChamberSign implements IMessage, IMessageHandler<MessageUpdateTestChamberSign, IMessage>
{
    private boolean valid;
    private BlockPos pos;
    private short chamber;
    private short maxChambers;
    private byte[] skills;

    public MessageUpdateTestChamberSign()
    {
        this.valid = false;
    }

    public MessageUpdateTestChamberSign(BlockPos pos, int chamber, int maxChambers, boolean... skills)
    {
        this.pos = pos;
        this.chamber = (short) chamber;
        this.maxChambers = (short) maxChambers;
        this.skills = new byte[10];
        for (int i = 0; i < this.skills.length; i++)
        {
            if (i < skills.length)
            {
                this.skills[i] = (byte) (skills[i] ? 1 : 0);
            }
            else
            {
                this.skills[i] = -1;
            }
        }
        this.valid = true;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        if (this.valid)
        {
            buf.writeLong(this.pos.toLong());
            buf.writeShort(this.chamber);
            buf.writeShort(this.maxChambers);
            buf.writeBytes(this.skills);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        try
        {
            this.pos = BlockPos.fromLong(buf.readLong());
            this.chamber = buf.readShort();
            this.maxChambers = buf.readShort();
            this.skills = new byte[10];
            buf.readBytes(this.skills);
            this.valid = true;
        }
        catch (Exception e)
        {
            ThinkingWithAperture.logger().fatal("Invalid \'" + this.getClass().getName() + "\' Packet!", e);
        }
    }

    @Override
    public IMessage onMessage(MessageUpdateTestChamberSign message, MessageContext ctx)
    {
        if (ctx.side == Side.CLIENT || !message.valid)
            return null;
        World world = ctx.getServerHandler().player.world;
        System.out.println(world.getTileEntity(message.pos));
        if (world.getTileEntity(message.pos) instanceof TileEntityTestChamberSign)
        {
            world.getMinecraftServer().addScheduledTask(() -> this.process(world, message.pos, message.chamber, message.maxChambers, message.skills));
        }
        return null;
    }

    private void process(World world, BlockPos pos, int chamber, int maxChamber, byte[] skills)
    {
        TileEntityTestChamberSign te = (TileEntityTestChamberSign) world.getTileEntity(pos);
        te.setMaxChambers(maxChamber);
        te.setChamber(chamber);
        for(int i = 0; i < skills.length; i++) {
            if(skills[i] != -1) {
                te.setSkill(i, skills[i] == 1);
            }
        }
    }
}