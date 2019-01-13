package com.jacksonplayz.thinkingwithaperture.entity;

import javax.annotation.Nullable;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import com.jacksonplayz.thinkingwithaperture.init.ModSounds;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTurret extends EntityLiving
{
    public static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityTurret.class, DataSerializers.VARINT);

    public EntityTurret(World world)
    {
        super(world);
        this.setSize(this.getType().getWidth(), this.getType().getHeight());
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TYPE, 0);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
    }

    @Override
    public float getEyeHeight()
    {
        return this.getType().getEyeHeight();
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
        return this.getType().getAmbientSound();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return this.getType().getHurtSound();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return this.getType().getDeathSound();
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        EntityItem entityitem = this.dropItem(ModItems.TURRET, 1);

        if (entityitem != null)
        {
            entityitem.setNoDespawn();
        }
    }

    @Nullable
    public EntityItem dropItemWithOffset(Item item, int size, float offsetY)
    {
        return this.entityDropItem(new ItemStack(item, size, this.dataManager.get(TYPE)), offsetY);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target)
    {
        return new ItemStack(ModItems.TURRET, 1, this.dataManager.get(TYPE));
    }

    public TurretType getType()
    {
        return TurretType.byMetadata(this.dataManager.get(TYPE));
    }

    public void setType(TurretType type)
    {
        this.dataManager.set(TYPE, type.getMetadata());
    }

    public enum TurretType implements IStringSerializable
    {
        NORMAL("normal", 0.8F, 1.5F, 1.0f, 0.5f, null, ModSounds.TURRET_HURT, ModSounds.TURRET_DEATH), FAT("fat", 1.6F, 1.7F, 0.925F, 1.5f, null, ModSounds.BIG_TURRET_HURT, ModSounds.BIG_TURRET_DEATH), DEFECTIVE("defective", 0.8F, 1.5F, 1.0f, 0.5f, null, ModSounds.DEFECTIVE_TURRET_HURT, ModSounds.DEFECTIVE_TURRET_DEATH), ORACLE("oracle", 0.8F, 1.5F, 1.0f, 0.5f, ModSounds.ORACLE_TURRET_AMBIENT, ModSounds.ORACLE_TURRET_HURT, ModSounds.ORACLE_TURRET_DEATH);

        private String name;
        private float width;
        private float height;
        private float eyeHeight;
        private float shadowSize;
        @Nullable
        private SoundEvent ambientSound;
        @Nullable
        private SoundEvent hurtSound;
        @Nullable
        private SoundEvent deathSound;

        private TurretType(String name, float width, float height, float eyeHeight, float shadowSize, @Nullable SoundEvent ambientSound, @Nullable SoundEvent hurtSound, @Nullable SoundEvent deathSound)
        {
            this.name = name;
            this.width = width;
            this.height = height;
            this.eyeHeight = eyeHeight;
            this.shadowSize = shadowSize;
            this.ambientSound = ambientSound;
            this.hurtSound = hurtSound;
            this.deathSound = deathSound;
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

        public float getWidth()
        {
            return width;
        }

        public float getHeight()
        {
            return height;
        }

        public float getEyeHeight()
        {
            return eyeHeight;
        }

        public float getShadowSize()
        {
            return shadowSize;
        }

        public SoundEvent getAmbientSound()
        {
            return ambientSound;
        }

        public SoundEvent getHurtSound()
        {
            return hurtSound;
        }

        public SoundEvent getDeathSound()
        {
            return deathSound;
        }

        public static TurretType byMetadata(int metadata)
        {
            if (metadata < 0 || metadata >= values().length)
                metadata = 0;
            return values()[metadata];
        }
    }
}
