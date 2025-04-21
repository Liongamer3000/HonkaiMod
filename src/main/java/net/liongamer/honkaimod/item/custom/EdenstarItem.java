package net.liongamer.honkaimod.item.custom;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.liongamer.honkaimod.entity.custom.InvisibleProjectileEntity;
import net.liongamer.honkaimod.item.ModItems;
import net.liongamer.honkaimod.particle.ModParticles;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = HonkaiMod.MOD_ID)
public class EdenstarItem extends Item {
    public boolean shot = false;
    public static boolean discardEntity = false;

    public static LivingEntity owner;

    public EdenstarItem(Properties pProperties) {
        super(pProperties);
    }


    //check if this worked
    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {

        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!shot) {
            discardEntity = false;
            if (!pLevel.isClientSide) {
                owner = pPlayer;
                InvisibleProjectileEntity projectileEntity = new InvisibleProjectileEntity(pLevel, pPlayer);
                projectileEntity.moveTo(pPlayer.position().x, pPlayer.position().y + 1.7F, pPlayer.position().z);
                projectileEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1000.0F, 0.0F);
                //System.out.println("projectile owner1 in item ln 58: " +  projectileEntity.owner1);
                pLevel.addFreshEntity(projectileEntity);
                shot = true;
            }
        }
        pPlayer.startUsingItem(pHand);

        return InteractionResultHolder.success(itemStack);
    }
    int doParticle3 = 0;
    int doParticle = 0;
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        Player player = (Player) pEntity;
        if (pStack.is(ModItems.EDENSTAR.get()) && pIsSelected) {
            player.getAbilities().mayfly = true;
        }

        if (pStack.is(ModItems.EDENSTAR.get()) && pIsSelected && player.getAbilities().flying) {
            int b = (int) (Math.random() * 2);
            if (doParticle == 3) {
                if (b == 0) {
                    pLevel.addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES.get(),
                            player.getX(), player.getY() + Math.random() * 1.8, player.getZ(),
                            (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15);
                }
                if (b == 1) {
                    pLevel.addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_2.get(),
                            player.getX(), player.getY() + Math.random() * 1.8, player.getZ(),
                            (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15);
                }
                doParticle = 0;
            }

            int d = (int) (Math.random() * 2);
            if (doParticle3 == 15) {
                if (d == 0) {
                    pLevel.addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_4.get(),
                            -Math.cos(player.yRotO * Math.PI / 180 + 5.5) * 0.4 + player.getX(), player.getY() + 0.6, -Math.sin(player.yRotO * Math.PI / 180 + 5.5) * 0.4 + player.getZ(),
                            (Math.random() - 0.5) / 50, (Math.random() - 0.6) / 50, (Math.random() - 0.5) / 50);
                }
                if (d == 1) {
                    pLevel.addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_5.get(),
                            -Math.cos(player.yRotO * Math.PI / 180 + 5.5) * 0.4 + player.getX(), player.getY() + 0.6, -Math.sin(player.yRotO * Math.PI / 180 + 5.5) * 0.4 + player.getZ(),
                            (Math.random() - 0.5) / 50, (Math.random() - 0.6) / 50, (Math.random() - 0.5) / 50);
                }
                doParticle3 = 0;
            }
            doParticle = doParticle + 1;
            doParticle3 = doParticle3 + 1;
        }

        if (!pStack.is(ModItems.EDENSTAR.get()) || !pIsSelected) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
        }
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);
        shot = false;
        discardEntity = true;
        System.out.println("stopped using");
    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        shot = false;
        discardEntity = true;
        System.out.println("released using");
    }
}
