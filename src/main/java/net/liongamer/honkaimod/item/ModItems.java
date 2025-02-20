package net.liongamer.honkaimod.item;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.item.custom.EdenstarItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HonkaiMod.MOD_ID);

    public static final RegistryObject<Item> EDENSTAR = ITEMS.register("edenstar",
            () -> new EdenstarItem(new Item.Properties().fireResistant().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
