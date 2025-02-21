package net.liongamer.honkaimod.item.custom;

import net.liongamer.honkaimod.HonkaiMod;
import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.liongamer.honkaimod.entity.custom.InvisibleProjectileEntity;
import net.liongamer.honkaimod.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = HonkaiMod.MOD_ID)
public class EdenstarItem extends Item {

    public EdenstarItem(Properties pProperties) {
        super(pProperties);
    }

    //check if this worked
    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

        return super.onItemUseFirst(stack, pContext);
    }

    /*
    @SubscribeEvent
    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        Player pPlayer = event.getEntity();
        InteractionHand pHand = event.getHand();
        Level pLevel = event.getLevel();
        EdenstarEffectEntity entity = new EdenstarEffectEntity(ModEntities.EDENSTAR_EFFECT.get(), pLevel);
     //   entity.moveTo(pPlayer.position());
        //entity.moveTo(LookingAt().getX(), LookingAt().getY() + 1f, LookingAt().getZ());
        //pLevel.addFreshEntity(entity);
    }
    */

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide) {
            EdenstarEffectEntity effectEntity = new EdenstarEffectEntity(ModEntities.EDENSTAR_EFFECT.get(), pLevel);
            InvisibleProjectileEntity projectileEntity = new InvisibleProjectileEntity(pLevel, pPlayer);
            projectileEntity.moveTo(pPlayer.position().x,pPlayer.position().y + 1.0F,pPlayer.position().z);
            projectileEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1000.0F, 0.0F);
            pLevel.addFreshEntity(projectileEntity);
        }
        return InteractionResultHolder.success(itemStack);
    }
}
