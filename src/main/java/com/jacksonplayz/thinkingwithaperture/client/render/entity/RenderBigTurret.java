package com.jacksonplayz.thinkingwithaperture.client.render.entity;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelBigTurret;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityBigTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderBigTurret extends RenderLiving<EntityBigTurret> {

    public static final ResourceLocation NORMAL = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/turret/big_turret.png");

    // public static final ModelTurret model = new ModelTurret();

    public RenderBigTurret(RenderManager renderManager) {
        super(renderManager, new ModelBigTurret(), 1.5F);
    }



    @Override
    public void doRender(EntityBigTurret entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        super.bindEntityTexture(entity);
       /* GlStateManager.pushMatrix();
        GlStateManager.translate(x, y - 0.5, z);
        GlStateManager.scale(0.0625, 0.0625, 0.0625);
        model.render(entity, 0.0F, 0.0F, 0.0F,0.0F, 0.0F,1.0F);
        GlStateManager.popMatrix(); */

    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBigTurret entity) {
        return NORMAL;
    }
}
