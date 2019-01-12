package com.jacksonplayz.thinkingwithaperture.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRadio - MCvinnyq
 * Created using Tabula 7.0.0
 */
public class ModelRadio extends ModelBase {
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Antenna;

    public ModelRadio() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Antenna = new ModelRenderer(this, 0, 12);
        this.Antenna.setRotationPoint(-1.5F, -4.0F, 0.0F);
        this.Antenna.addBox(-0.5F, -6.0F, 0.0F, 1, 6, 1, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 0);
        this.Body1.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.Body1.addBox(-4.0F, -3.0F, -1.5F, 8, 6, 3, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 9);
        this.Body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body2.addBox(-3.0F, -4.0F, -1.0F, 6, 1, 2, 0.0F);
        this.Body1.addChild(this.Antenna);
        this.Body1.addChild(this.Body2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body1.render(f5);
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
