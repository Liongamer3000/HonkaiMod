package net.liongamer.honkaimod.util;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemProperties {
    public static void addCustomItemProperties() {

        ItemProperties.register(ModItems.EDENSTAR.get(),
                new ResourceLocation(HonkaiMod.MOD_ID, "using"), (stack, level, living, id) -> {
                    return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
                });
    }
}
