package net.liongamer.honkaimod;

import com.mojang.logging.LogUtils;
import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.entity.client.EdenstarEffectRenderer;
import net.liongamer.honkaimod.entity.client.InvisibleProjectileRenderer;
import net.liongamer.honkaimod.item.ModCreativeModTabs;
import net.liongamer.honkaimod.item.ModItems;
import net.liongamer.honkaimod.particle.EdenstarEffectParticles;
import net.liongamer.honkaimod.particle.EdenstarEffectParticles2;
import net.liongamer.honkaimod.particle.EdenstarEffectParticles3;
import net.liongamer.honkaimod.particle.ModParticles;
import net.liongamer.honkaimod.util.ModItemProperties;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HonkaiMod.MOD_ID)
public class HonkaiMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "honkaimod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public HonkaiMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModEntities.register(modEventBus);

        ModItems.register(modEventBus);

        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.EDENSTAR_EFFECT.get(), EdenstarEffectRenderer::new);
            EntityRenderers.register(ModEntities.INVISIBLE_PROJECTILE.get(), InvisibleProjectileRenderer::new);
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.EDENSTAR_EFFECT_PARTICLES.get(), EdenstarEffectParticles.Provider::new);
            event.registerSpriteSet(ModParticles.EDENSTAR_EFFECT_PARTICLES_2.get(), EdenstarEffectParticles2.Provider::new);
            event.registerSpriteSet(ModParticles.EDENSTAR_EFFECT_PARTICLES_3.get(), EdenstarEffectParticles3.Provider::new);
            event.registerSpriteSet(ModParticles.EDENSTAR_EFFECT_PARTICLES_4.get(), EdenstarEffectParticles3.Provider::new);
            event.registerSpriteSet(ModParticles.EDENSTAR_EFFECT_PARTICLES_5.get(), EdenstarEffectParticles3.Provider::new);
        }
    }
}
