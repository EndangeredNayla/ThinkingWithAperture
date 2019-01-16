package com.jacksonplayz.thinkingwithaperture.entity;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCube extends EntityLiving
{
    public static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityCube.class, DataSerializers.VARINT);

    public EntityCube(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TYPE, 0);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (this.isEntityAlive() && !this.world.isRemote) {
                if(this.dataManager.get(TYPE) == 1) {
                    ((WorldServer) this.world).spawnParticle(EnumParticleTypes.HEART, posX, posY, posZ, 10, 0.5, 0.8, 0.5, 0.1);
                }
        }
        return true;
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
        Block.spawnAsEntity(this.world, this.getPosition(), new ItemStack(ModItems.WEIGHTED_CUBE, 1, this.dataManager.get(TYPE)));
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target)
    {
        return new ItemStack(ModItems.WEIGHTED_CUBE, 1, this.dataManager.get(TYPE));
    }

    // Cube Metadata

    public CubeType getType()
    {
        return CubeType.byMetadata(this.dataManager.get(TYPE));
    }

    public void setType(CubeType type)
    {
        this.dataManager.set(TYPE, type.getMetadata());
    }

    public enum CubeType implements IStringSerializable
    {
        STORAGE("storage"), COMPANION("companion");

        private String name;

        private CubeType(String name)
        {
            this.name = name;
        }

        @Override
        public String getName()
        {
            return name;
        }

        public int getMetadata()
        {
            return this.ordinal();
        }

        public static CubeType byMetadata(int metadata)
        {
            if (metadata < 0 || metadata >= values().length)
                metadata = 0;
            return values()[metadata];
        }
    }

    protected boolean canDespawn() {
        return false;
    }
}
