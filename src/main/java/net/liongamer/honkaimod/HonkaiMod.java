package net.liongamer.honkaimod;

import com.mojang.logging.LogUtils;
import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.entity.client.EdenstarEffectRenderer;
import net.liongamer.honkaimod.entity.client.InvisibleProjectileRenderer;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.liongamer.honkaimod.entity.custom.InvisibleProjectileEntity;
import net.liongamer.honkaimod.item.ModCreativeModTabs;
import net.liongamer.honkaimod.item.ModItems;
import net.liongamer.honkaimod.item.custom.EdenstarItem;
import net.liongamer.honkaimod.util.ModItemProperties;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
    }
}
