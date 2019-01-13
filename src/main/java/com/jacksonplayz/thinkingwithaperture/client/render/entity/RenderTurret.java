package com.jacksonplayz.thinkingwithaperture.client.render.entity;

import javax.annotation.Nullable;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelTurret;
import com.jacksonplayz.thinkingwithaperture.entity.EntityTurret;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTurret extends RenderLiving<EntityTurret>
{
    public static final ResourceLocation NORMAL = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/turret/turret.png");
    public static final ResourceLocation FAT = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/turret/big_turret.png");

    public static final ModelTurret NORMAL_MODEL = new ModelTurret();
    public static final ModelTurret FAT_MODEL = new ModelTurret();

    public RenderTurret(RenderManager renderManager)
    {
        super(renderManager, NORMAL_MODEL, 0.0f);
    }

    @Override
    protected void preRenderCallback(EntityTurret entity, float partialTicks)
    {
        this.mainModel = getModel(entity);
        this.shadowSize = entity.getType().getShadowSize();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTurret entity)
    {
        switch (entity.getType())
        {
        case DEFECTIVE:
            return NORMAL;
        case FAT:
            return FAT;
        case NORMAL:
            break;
        case ORACLE:
            break;
        }
        return NORMAL;
    }

    private static <T extends ModelBase> T getModel(EntityTurret entity)
    {
        switch (entity.getType())
        {
        case NORMAL:
            return (T) NORMAL_MODEL;
        case FAT:
            return (T) FAT_MODEL;
        case DEFECTIVE:
            break;
        case ORACLE:
            break;
        }
        return (T) NORMAL_MODEL;
    }
}
