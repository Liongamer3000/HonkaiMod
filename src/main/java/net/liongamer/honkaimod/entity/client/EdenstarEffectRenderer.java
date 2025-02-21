package net.liongamer.honkaimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EdenstarEffectRenderer extends EntityRenderer<EdenstarEffectEntity> {
    public EdenstarEffectRenderer(EntityRendererProvider.Context pContext) {

        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(EdenstarEffectEntity pEntity) {
        return new ResourceLocation(HonkaiMod.MOD_ID, "textures/entity/rhino.png");
    }

    @Override
    public void render(EdenstarEffectEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {



        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
