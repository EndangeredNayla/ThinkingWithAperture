package com.jacksonplayz.thinkingwithaperture.entity;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCube extends EntityLiving {

    public EntityCube(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected void initEntityAI()
    {
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.world.isRemote && !source.FALL.equals(source))
            {
                if(!source.isCreativePlayer()) {
                    this.playBrokenSound();
                    this.playParticles();
                    this.dropBlock();
                    this.setDead();
                }
                else if(source.isCreativePlayer()) {
                    this.playBrokenSound();
                    this.playParticles();
                    this.setDead();
                }
            }
            return true;
        }
    }

    private void playParticles()
    {
        if (this.world instanceof WorldServer)
        {
            ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + (double)this.height / 1.5D, this.posZ, 10, (double)(this.width / 4.0F), (double)(this.height / 4.0F), (double)(this.width / 4.0F), 0.05D, Block.getStateId(Blocks.STONE.getDefaultState()));
        }
    }

    private void playBrokenSound()
    {
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_METAL_BREAK, this.getSoundCategory(), 1.0F, 1.0F);
    }

    private void dropBlock()
    {
            Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(ModItems.WEIGHTED_CUBE));
    }
}
