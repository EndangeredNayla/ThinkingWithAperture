package com.jacksonplayz.thinkingwithaperture.client.render.entity;

import com.jacksonplayz.thinkingwithaperture.ThinkingWithAperture;
import com.jacksonplayz.thinkingwithaperture.client.model.entity.ModelCube;
import com.jacksonplayz.thinkingwithaperture.entity.EntityCube;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderCube extends RenderLiving<EntityCube> {

    public static final ResourceLocation COMPANION = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/cube/companion_cube.png");
    public static final ResourceLocation STORAGE = new ResourceLocation(ThinkingWithAperture.MODID, "textures/entity/cube/weighted_cube.png");

    public static final ModelCube NORMAL_MODEL = new ModelCube();

    // public static final ModelCube model = new ModelCube();

    public RenderCube(RenderManager renderManager)
    {
        super(renderManager, NORMAL_MODEL, 0.0f);
    }

    @Override
    protected void preRenderCallback(EntityCube entity, float partialTicks)
    {
        this.mainModel = getModel(entity);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityCube entity)
    {
        switch (entity.getType())
        {
            case STORAGE:
                return STORAGE;
            case COMPANION:
                return COMPANION;
        }
        return STORAGE;
    }

    private static <T extends ModelBase> T getModel(EntityCube entity)
    {
        switch (entity.getType())
        {
            case STORAGE:
                return (T) NORMAL_MODEL;
            case COMPANION:
                break;
        }
        return (T) NORMAL_MODEL;
    }
}
