package com.jacksonplayz.thinkingwithaperture.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelFatTurret - MCvinnyq
 * Created using Tabula 7.0.0
 */
public class ModelBigTurret extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer RightLeg1;
    public ModelRenderer LeftLeg1;
    public ModelRenderer BackLeg1;
    public ModelRenderer RightPanel1;
    public ModelRenderer LeftPanel1;
    public ModelRenderer RightPanel2;
    public ModelRenderer LeftPanel2;
    public ModelRenderer RightLeg2;
    public ModelRenderer LeftLeg2;
    public ModelRenderer BackLeg2;

    public ModelBigTurret() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.BackLeg1 = new ModelRenderer(this, 0, 48);
        this.BackLeg1.setRotationPoint(0.0F, 18.0F, 3.0F);
        this.BackLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(BackLeg1, -0.9599310885968813F, -3.141592653589793F, 0.0F);
        this.LeftLeg1 = new ModelRenderer(this, 0, 48);
        this.LeftLeg1.setRotationPoint(2.5F, 18.0F, -4.0F);
        this.LeftLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(LeftLeg1, -0.9599310885968813F, -0.7853981633974483F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.Body.addBox(-4.5F, -16.0F, -5.5F, 9, 16, 11, 0.0F);
        this.RightPanel2 = new ModelRenderer(this, 38, 47);
        this.RightPanel2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightPanel2.addBox(-3.0F, -5.0F, -3.5F, 4, 10, 7, 0.0F);
        this.RightLeg2 = new ModelRenderer(this, 8, 57);
        this.RightLeg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightLeg2.addBox(-2.0F, 3.0F, -2.0F, 4, 5, 2, 0.0F);
        this.BackLeg2 = new ModelRenderer(this, 8, 57);
        this.BackLeg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackLeg2.addBox(-2.0F, 3.0F, -2.0F, 4, 5, 2, 0.0F);
        this.LeftPanel2 = new ModelRenderer(this, 38, 47);
        this.LeftPanel2.mirror = true;
        this.LeftPanel2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftPanel2.addBox(-1.0F, -5.0F, -3.5F, 4, 10, 7, 0.0F);
        this.RightPanel1 = new ModelRenderer(this, 0, 27);
        this.RightPanel1.setRotationPoint(-4.0F, -7.0F, 0.0F);
        this.RightPanel1.addBox(-5.0F, -6.0F, -4.5F, 2, 12, 9, 0.0F);
        this.LeftLeg2 = new ModelRenderer(this, 8, 57);
        this.LeftLeg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftLeg2.addBox(-2.0F, 3.0F, -2.0F, 4, 5, 2, 0.0F);
        this.LeftPanel1 = new ModelRenderer(this, 0, 27);
        this.LeftPanel1.mirror = true;
        this.LeftPanel1.setRotationPoint(4.0F, -7.0F, 0.0F);
        this.LeftPanel1.addBox(3.0F, -6.0F, -4.5F, 2, 12, 9, 0.0F);
        this.RightLeg1 = new ModelRenderer(this, 0, 48);
        this.RightLeg1.setRotationPoint(-2.5F, 18.0F, -4.0F);
        this.RightLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(RightLeg1, -0.9599310885968813F, 0.7853981633974483F, 0.0F);
        this.RightPanel1.addChild(this.RightPanel2);
        this.RightLeg1.addChild(this.RightLeg2);
        this.BackLeg1.addChild(this.BackLeg2);
        this.LeftPanel1.addChild(this.LeftPanel2);
        this.Body.addChild(this.RightPanel1);
        this.LeftLeg1.addChild(this.LeftLeg2);
        this.Body.addChild(this.LeftPanel1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 1.5F;
        GlStateManager.pushMatrix();
        GlStateManager.translate(0F, 1.4F-1.5F*scaleFactor, 0F);
        GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
        this.BackLeg1.render(f5);
        this.LeftLeg1.render(f5);
        this.Body.render(f5);
        this.RightLeg1.render(f5);
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
