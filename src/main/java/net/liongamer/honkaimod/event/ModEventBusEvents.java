package net.liongamer.honkaimod.event;

import net.liongamer.honkaimod.HonkaiMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HonkaiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    public static void registerAttributes(EntityAttributeCreationEvent event) {

    }
}
