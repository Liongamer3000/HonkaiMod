package net.liongamer.honkaimod.item.custom;

import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.entity.custom.EdenstarEffectEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class EdenstarItem extends Item {

    final int MAX_DISTANCE = 320;

    public EdenstarItem(Properties pProperties) {
        super(pProperties);
    }

    //check if this worked
    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    public BlockPos LookingAt(){
        HitResult rt = Minecraft.getInstance().hitResult;

        double x = (rt.getLocation().x);
        double y = (rt.getLocation().y);
        double z = (rt.getLocation().z);

        double xla = Minecraft.getInstance().player.getLookAngle().x;
        double yla = Minecraft.getInstance().player.getLookAngle().y;
        double zla = Minecraft.getInstance().player.getLookAngle().z;

        if ((x%1==0)&&(xla<0))x-=0.01;
        if ((y%1==0)&&(yla<0))y-=0.01;
        if ((z%1==0)&&(zla<0))z-=0.01;

        BlockPos ps = new BlockPos((int) x, (int) y, (int) z);
        BlockState bl = Minecraft.getInstance().level.getBlockState(ps);

        return ps;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos positionClicked = pContext.getClickedPos();
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        BlockPos blockPlayerIsLookingAt = level.clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(MAX_DISTANCE))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).getBlockPos();
        EdenstarEffectEntity entity = new EdenstarEffectEntity(ModEntities.EDENSTAR_EFFECT.get(), level);
        entity.moveTo(LookingAt().getCenter());
        level.addFreshEntity(entity);
        return InteractionResult.SUCCESS;
    }
}
