package net.liongamer.honkaimod.particle;

import net.liongamer.honkaimod.HonkaiMod;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HonkaiMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> EDENSTAR_EFFECT_PARTICLES =
            PARTICLE_TYPES.register("edenstar_effect_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> EDENSTAR_EFFECT_PARTICLES_2 =
            PARTICLE_TYPES.register("edenstar_effect_particles_2", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> EDENSTAR_EFFECT_PARTICLES_3 =
            PARTICLE_TYPES.register("edenstar_effect_particles_3", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> EDENSTAR_EFFECT_PARTICLES_4 =
            PARTICLE_TYPES.register("edenstar_effect_particles_4", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> EDENSTAR_EFFECT_PARTICLES_5 =
            PARTICLE_TYPES.register("edenstar_effect_particles_5", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
