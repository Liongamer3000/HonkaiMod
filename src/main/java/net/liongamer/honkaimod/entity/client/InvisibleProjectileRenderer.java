package net.liongamer.honkaimod.entity.client;

import net.liongamer.honkaimod.entity.custom.InvisibleProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class InvisibleProjectileRenderer extends EntityRenderer<InvisibleProjectileEntity> {
    public InvisibleProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(InvisibleProjectileEntity pEntity) {
        return null;
    }
}
