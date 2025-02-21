package net.liongamer.honkaimod.entity;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.liongamer.honkaimod.entity.custom.InvisibleProjectileEntity;
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

    //remember to later set it to be not spawnable and change the size
    public static final RegistryObject<EntityType<InvisibleProjectileEntity>> INVISIBLE_PROJECTILE =
            ENTITY_TYPES.register("invisible_projectile", () -> EntityType.Builder.<InvisibleProjectileEntity>of(InvisibleProjectileEntity::new, MobCategory.MISC)
                    .sized(0.001f, 0.001f).noSave().fireImmune().canSpawnFarFromPlayer().build("invisible_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
