package com.jacksonplayz.thinkingwithaperture.client.model.entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelWheatleyCore - MCvinnyq
 * Created using Tabula 7.0.0
 */
public class ModelCore extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer EyeHole;
    public ModelRenderer TopHandle;
    public ModelRenderer BottomHandle;
    public ModelRenderer Eyelid;
    public ModelRenderer Pupil;

    public ModelCore() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Pupil = new ModelRenderer(this, 0, 5);
        this.Pupil.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Pupil.addBox(-1.0F, -1.0F, -4.5F, 2, 2, 1, 0.0F);
        this.EyeHole = new ModelRenderer(this, 0, 0);
        this.EyeHole.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.EyeHole.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 1, 0.0F);
        this.Eyelid = new ModelRenderer(this, 30, 0);
        this.Eyelid.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Eyelid.addBox(-2.0F, -4.5F, -4.9F, 4, 4, 1, 0.0F);
        this.TopHandle = new ModelRenderer(this, 0, 20);
        this.TopHandle.setRotationPoint(0.0F, -3.0F, -3.0F);
        this.TopHandle.addBox(-5.5F, -1.0F, -6.0F, 11, 1, 6, 0.0F);
        this.setRotateAngle(TopHandle, -0.7853981633974483F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.Body.addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F);
        this.setRotateAngle(Body, 0.4363323129985824F, 0.0F, 0.0F);
        this.BottomHandle = new ModelRenderer(this, 0, 20);
        this.BottomHandle.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.BottomHandle.addBox(-5.5F, 0.0F, -6.0F, 11, 1, 6, 0.0F);
        this.setRotateAngle(BottomHandle, 0.7853981633974483F, 0.0F, 0.0F);
        this.EyeHole.addChild(this.Pupil);
        this.Body.addChild(this.EyeHole);
        this.Body.addChild(this.Eyelid);
        this.Body.addChild(this.TopHandle);
        this.Body.addChild(this.BottomHandle);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
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
