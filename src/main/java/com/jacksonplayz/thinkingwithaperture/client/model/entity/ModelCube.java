package com.jacksonplayz.thinkingwithaperture.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCompanionCube - MCvinnyq
 * Created using Tabula 7.0.0
 */
public class ModelCube extends ModelBase {
    public ModelRenderer CubeOuter;
    public ModelRenderer CubeInner;

    public ModelCube() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.CubeInner = new ModelRenderer(this, 0, 32);
        this.CubeInner.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.CubeInner.addBox(-7.5F, -7.5F, -7.5F, 15, 15, 15, 0.0F);
        this.CubeOuter = new ModelRenderer(this, 0, 0);
        this.CubeOuter.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.CubeOuter.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.CubeInner.render(scale);
        this.CubeOuter.render(scale);
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
