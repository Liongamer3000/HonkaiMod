package net.liongamer.honkaimod.item;

import net.liongamer.honkaimod.HonkaiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HonkaiMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DIVINE_KEYS = CREATIVE_MODE_TABS.register("divine_keys",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EDENSTAR.get()))
                    .title(Component.translatable("creativetab.honkaimod.divine_keys"))
                    .displayItems((pParameters, pOutput) -> {
                      pOutput.accept(ModItems.EDENSTAR.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
