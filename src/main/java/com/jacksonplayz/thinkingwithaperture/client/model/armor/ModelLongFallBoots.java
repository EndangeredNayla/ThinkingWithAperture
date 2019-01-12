package com.jacksonplayz.thinkingwithaperture.client.model.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;

/**
 * ModelLongFallBoots - MCvinnyq Created using Tabula 7.0.0
 */
public class ModelLongFallBoots extends ModelBiped {
    public ModelRenderer RightBoot1;
    public ModelRenderer LeftBoot1;
    public ModelRenderer RightBoot2;
    public ModelRenderer LeftBoot2;

    public ModelLongFallBoots() {

        this.textureWidth = 64;
        this.textureHeight = 32;

        this.RightBoot2 = new ModelRenderer(this, 0, 16);
        this.RightBoot2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightBoot2.addBox(-1.0F, 0.0F, 0.0F, 2, 12, 4, 0.0F);
        this.setRotateAngle(RightBoot2, 0.17453292519943295F, 0.0F, 0.0F);
        this.LeftBoot2 = new ModelRenderer(this, 0, 16);
        this.LeftBoot2.mirror = true;
        this.LeftBoot2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftBoot2.addBox(-1.0F, 0.0F, 0.0F, 2, 12, 4, 0.0F);
        this.setRotateAngle(LeftBoot2, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightBoot1 = new ModelRenderer(this, 0, 0);
        this.RightBoot1.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightBoot1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.5F);
        this.LeftBoot1 = new ModelRenderer(this, 0, 0);
        this.LeftBoot1.mirror = true;
        this.LeftBoot1.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftBoot1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.5F);

        this.RightBoot1.addChild(this.RightBoot2);
        this.LeftBoot1.addChild(this.LeftBoot2);

        this.bipedLeftLeg.addChild(LeftBoot1);
        this.bipedRightLeg.addChild(RightBoot1);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entity instanceof EntityPlayer) {
            super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

            copyModelAngles(this.bipedRightLeg, this.RightBoot1);
            copyModelAngles(this.bipedLeftLeg, this.LeftBoot1);
        }
        if (entity instanceof EntityArmorStand) {
            EntityArmorStand armorstand = (EntityArmorStand) entity;

            // Right Leg
            this.RightBoot1.rotateAngleX = 0.017453292F * armorstand.getRightLegRotation().getX();
            this.RightBoot1.rotateAngleY = 0.017453292F * armorstand.getRightLegRotation().getY();
            this.RightBoot1.rotateAngleZ = 0.017453292F * armorstand.getRightLegRotation().getZ();

            // Left Leg
            this.LeftBoot1.rotateAngleX = 0.017453292F * armorstand.getLeftLegRotation().getX();
            this.LeftBoot1.rotateAngleY = 0.017453292F * armorstand.getLeftLegRotation().getY();
            this.LeftBoot1.rotateAngleZ = 0.017453292F * armorstand.getLeftLegRotation().getZ();

            copyModelAngles(this.bipedHead, this.bipedHeadwear);
        }

        GlStateManager.pushMatrix();
        if (entity.isSneaking())
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
        this.RightBoot1.render(scale);
        this.LeftBoot1.render(scale);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
