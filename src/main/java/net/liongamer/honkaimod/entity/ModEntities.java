package net.liongamer.honkaimod.entity;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HonkaiMod.MOD_ID);

    public static final RegistryObject<EntityType<EdenstarEffectEntity>> EDENSTAR_EFFECT =
            ENTITY_TYPES.register("star_of_eden_effect", () -> EntityType.Builder.of(EdenstarEffectEntity::new, MobCategory.MISC)
                    .sized(5f, 3f).noSave().fireImmune().canSpawnFarFromPlayer().build("star_of_eden_effect"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
