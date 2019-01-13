package com.jacksonplayz.thinkingwithaperture.entity;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.init.ModItems;
import com.jacksonplayz.thinkingwithaperture.net.NetworkHandler;
import com.jacksonplayz.thinkingwithaperture.net.message.MessagePlayRadioSound;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityRadio extends EntityLiving {

    public static final int SOUND_LENGTH = 440;
    public static final DataParameter<Boolean> ACTIVATED = EntityDataManager.<Boolean>createKey(EntityRadio.class, DataSerializers.BOOLEAN);

    public EntityRadio(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.8F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ACTIVATED, true);
    }
    
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.setActivated(true);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (this.isEntityAlive() && !this.world.isRemote) {
            this.setActivated(!this.isActivated());
        }
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            if (!this.isDead && !this.world.isRemote && !source.FALL.equals(source)) {
                if (!source.isCreativePlayer()) {
                    this.playBrokenSound();
                    this.playParticles();
                    this.dropBlock();
                    this.setDead();
                } else if (source.isCreativePlayer()) {
                    this.playBrokenSound();
                    this.playParticles();
                    this.setDead();
                }
            }
            return true;
        }
    }
    
    @Override
    public void setDead() {
        this.setActivated(false);
        super.setDead();
    }

    public void setActivated(boolean activated) {
        this.dataManager.set(ACTIVATED, activated);
        if (activated) {
           NetworkHandler.INSTANCE.sendToAll(new MessagePlayRadioSound(this));
        }
    }

    public boolean isActivated() {
        return this.dataManager.get(ACTIVATED);
    }

    private void playParticles() {
        if (this.world instanceof WorldServer) {
            ((WorldServer) this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + (double) this.height / 1.5D, this.posZ, 10, (double) (this.width / 4.0F), (double) (this.height / 4.0F), (double) (this.width / 4.0F), 0.05D, Block.getStateId(Blocks.IRON_BLOCK.getDefaultState()));
        }
    }

    private void playBrokenSound() {
        this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_METAL_BREAK, this.getSoundCategory(), 1.0F, 1.0F);
    }

    private void dropBlock() {
        Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(ModItems.RADIO));
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(ModItems.RADIO);
    }
}
