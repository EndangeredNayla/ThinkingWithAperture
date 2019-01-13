package com.jacksonplayz.thinkingwithaperture.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelTurret - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelTurret extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Leg1;
    public ModelRenderer Leg2;
    public ModelRenderer Leg3;
    public ModelRenderer RightPlate;
    public ModelRenderer LeftPlate;
    public ModelRenderer RightPlateInner;
    public ModelRenderer LeftPlateInner;

    public ModelTurret() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftPlateInner = new ModelRenderer(this, 14, 20);
        this.LeftPlateInner.mirror = true;
        this.LeftPlateInner.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftPlateInner.addBox(-4.0F, -4.0F, -2.0F, 4, 8, 4, 0.0F);
        this.RightPlate = new ModelRenderer(this, 30, 16);
        this.RightPlate.setRotationPoint(-2.5F, -6.0F, 0.0F);
        this.RightPlate.addBox(-1.0F, -5.0F, -3.0F, 1, 10, 6, 0.0F);
        this.Body = new ModelRenderer(this, 38, 0);
        this.Body.setRotationPoint(0.0F, 14.5F, 0.0F);
        this.Body.addBox(-2.5F, -12.0F, -4.0F, 5, 12, 8, 0.0F);
        this.LeftPlate = new ModelRenderer(this, 30, 16);
        this.LeftPlate.mirror = true;
        this.LeftPlate.setRotationPoint(2.5F, -6.0F, 0.0F);
        this.LeftPlate.addBox(0.0F, -5.0F, -3.0F, 1, 10, 6, 0.0F);
        this.Leg3 = new ModelRenderer(this, 0, 18);
        this.Leg3.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.Leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(Leg3, -0.5235987755982988F, -3.141592653589793F, 0.0F);
        this.Leg2 = new ModelRenderer(this, 0, 18);
        this.Leg2.setRotationPoint(1.0F, 14.0F, -2.0F);
        this.Leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(Leg2, -0.5235987755982988F, -0.7853981633974483F, 0.0F);
        this.Leg1 = new ModelRenderer(this, 0, 18);
        this.Leg1.setRotationPoint(-1.0F, 14.0F, -2.0F);
        this.Leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(Leg1, -0.5235987755982988F, 0.7853981633974483F, 0.0F);
        this.RightPlateInner = new ModelRenderer(this, 14, 20);
        this.RightPlateInner.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightPlateInner.addBox(0.0F, -4.0F, -2.0F, 4, 8, 4, 0.0F);
        this.LeftPlate.addChild(this.LeftPlateInner);
        this.Body.addChild(this.RightPlate);
        this.Body.addChild(this.LeftPlate);
        this.RightPlate.addChild(this.RightPlateInner);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        this.Leg3.render(f5);
        this.Leg2.render(f5);
        this.Leg1.render(f5);
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
