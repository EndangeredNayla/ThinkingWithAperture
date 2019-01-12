package com.jacksonplayz.thinkingwithaperture.entity;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCube extends EntityLiving {

    public EntityCube(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected void initEntityAI() {
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            if (!this.isDead && !this.world.isRemote && !DamageSource.FALL.equals(source)) {
                this.playBrokenSound();
                this.playParticles();
                if (!source.isCreativePlayer())
                    this.dropBlock();
                this.setDead();
            }
            return true;
        }
    }

    private void playParticles() {
        if (this.world instanceof WorldServer) {
            this.world.playEvent(2001, this.getPosition(), Block.getStateId(Blocks.IRON_BLOCK.getDefaultState()));
        }
    }

    private void playBrokenSound() {
        this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_STONE_BREAK, this.getSoundCategory(), 1.0F, 1.0F);
    }

    private void dropBlock() {
        Block.spawnAsEntity(this.world, this.getPosition(), new ItemStack(ModItems.WEIGHTED_CUBE));
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(ModItems.WEIGHTED_CUBE);
    }
}
