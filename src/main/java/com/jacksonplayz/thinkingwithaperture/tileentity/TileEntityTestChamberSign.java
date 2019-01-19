package com.jacksonplayz.thinkingwithaperture.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import scala.actors.threadpool.Arrays;

public class TileEntityTestChamberSign extends TileEntity
{
    private short chamber;
    private short maxChambers;
    private byte[] skills;

    public TileEntityTestChamberSign()
    {
        this.setMaxChambers(1);
        this.setChamber(0);
        this.skills = new byte[10];
        Arrays.fill(this.skills, (byte) 0);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("chamber", this.chamber);
        nbt.setShort("maxChambers", this.maxChambers);
        nbt.setByteArray("skills", this.skills);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.chamber = nbt.getShort("chamber");
        this.maxChambers = nbt.getShort("maxChambers");
        this.skills = nbt.getByteArray("skills");
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public int getChamber()
    {
        return chamber;
    }

    public int getMaxChambers()
    {
        return maxChambers;
    }

    public boolean hasSkill(int id)
    {
        if (id < 0 || id >= this.skills.length)
            return false;
        return this.skills[id] == 1;
    }

    public void setChamber(int chamber)
    {
        if (chamber < 1)
            chamber = 1;
        if (chamber > this.maxChambers)
            chamber = this.maxChambers;
        this.chamber = (short) chamber;
    }

    public void setMaxChambers(int maxChambers)
    {
        if (maxChambers < 0)
            maxChambers = 0;
        if (maxChambers > 99)
            maxChambers = 99;
        this.maxChambers = (short) maxChambers;
    }

    public void setSkill(int id, boolean activated)
    {
        if (id < 0 || id >= this.skills.length)
            return;
        this.skills[id] = (byte) (activated ? 1 : 0);
    }
}