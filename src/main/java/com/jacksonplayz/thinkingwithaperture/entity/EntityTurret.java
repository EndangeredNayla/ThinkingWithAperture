package com.jacksonplayz.thinkingwithaperture.entity;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import com.jacksonplayz.thinkingwithaperture.init.ModSounds;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTurret extends EntityLiving {

    public EntityTurret(World worldIn) {
        super(worldIn);
        this.setSize(0.8F, 1.5F);
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
    public float getEyeHeight() {
        return 1.0F;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return ModSounds.TURRET_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.TURRET_DEATH;
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

}
