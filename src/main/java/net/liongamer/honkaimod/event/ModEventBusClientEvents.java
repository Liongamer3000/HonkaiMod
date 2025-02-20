package net.liongamer.honkaimod.event;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.entity.client.ModModelLayers;
import net.liongamer.honkaimod.entity.client.StarOfEdenEffectModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HonkaiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
       //event.registerLayerDefinition(ModModelLayers.STAR_OF_EDEN_EFFECT_LAYER, StarOfEdenEffectModel::createBodyLayer);
    }
}
