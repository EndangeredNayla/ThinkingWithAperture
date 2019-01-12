package com.jacksonplayz.thinkingwithaperture.client.render.entity;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelCube;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelRadio;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderRadio extends RenderLiving<EntityRadio> {

    public static final ResourceLocation NORMAL = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/radio/radio.png");

    public RenderRadio(RenderManager renderManager) {
        super(renderManager, new ModelRadio(), 0.3f);
    }



    @Override
    public void doRender(EntityRadio entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        super.bindEntityTexture(entity);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRadio entity) {
        return NORMAL;
    }
}
