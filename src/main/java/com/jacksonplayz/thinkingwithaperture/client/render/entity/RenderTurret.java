package com.jacksonplayz.thinkingwithaperture.client.render.entity;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderTurret extends RenderLiving<EntityTurret> {

    public static final ResourceLocation NORMAL = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/turret/turret.png");

    // public static final ModelTurret model = new ModelTurret();

    public RenderTurret(RenderManager renderManager) {
        super(renderManager, new ModelTurret(), 0.5F);
    }



    @Override
    public void doRender(EntityTurret entity, double x, double y, double z, float entityYaw, float partialTicks) {
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
    protected ResourceLocation getEntityTexture(EntityTurret entity) {
        return NORMAL;
    }
}
