package com.jacksonplayz.thinkingwithaperture.entity;

import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import com.jacksonplayz.thinkingwithaperture.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityRadio extends EntityLiving {

    public static final int SOUND_LENGTH = 440;

    private static final DataParameter<Boolean> ACTIVATED = EntityDataManager.<Boolean>createKey(EntityRadio.class, DataSerializers.BOOLEAN);

    int repeatDelay;

    public EntityRadio(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.repeatDelay = 0;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ACTIVATED, true);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if(this.isEntityAlive()) {
            boolean activated = this.dataManager.get(ACTIVATED);
            this.dataManager.set(ACTIVATED, !activated);
            this.repeatDelay = activated ? 0 : SOUND_LENGTH;
            if(activated) {

            }
        }
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(this.dataManager.get(ACTIVATED)) {
            this.repeatDelay--;
            if(this.repeatDelay <= 0) {
                this.repeatDelay = SOUND_LENGTH;
                this.playSound(ModSounds.RADIO_LOOP, 3, 1);
            }
        }
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
            Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(ModItems.RECORD_RADIO_LOOP));
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(ModItems.RECORD_RADIO_LOOP);
    }
}
