package net.liongamer.honkaimod.entity.custom;

import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.item.custom.EdenstarItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InvisibleProjectileEntity extends Projectile {

    public static LivingEntity owner;

    public LivingEntity owner1;

    public InvisibleProjectileEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        owner = EdenstarItem.owner;
        //System.out.println("owner in projectile ln 37: " + owner);
    }

    public InvisibleProjectileEntity(Level pLevel) {
        super(ModEntities.INVISIBLE_PROJECTILE.get(), pLevel);
    }

    public InvisibleProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.INVISIBLE_PROJECTILE.get(), pLevel);
    }

    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return pDistance < d0 * d0;
    }

    public void tick() {
        super.tick();
        //System.out.println("projectile owner ln 60: " + owner);
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level().getBlockState(blockpos);
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
            f = 0.99F;

        this.setDeltaMovement(vec3.scale((double)f));
        this.setPos(d2, d0, d1);
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        Level pLevel = entity.level();
        EdenstarEffectEntity effectEntity = new EdenstarEffectEntity(ModEntities.EDENSTAR_EFFECT.get(), this.level());
        effectEntity.moveTo(entity.position().x, entity.position().y, entity.position().z);
        //pLevel.addFreshEntity(effectEntity);
        this.level().addFreshEntity(effectEntity);
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */

    protected void onHit(HitResult pResult) {
        EdenstarEffectEntity effectEntity = new EdenstarEffectEntity(ModEntities.EDENSTAR_EFFECT.get(), this.level());
        effectEntity.moveTo(pResult.getLocation().x, pResult.getLocation().y, pResult.getLocation().z);
        //effectEntity.setOwner(owner);
        this.level().addFreshEntity(effectEntity);
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return super.canHitEntity(pTarget);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData() {

    }
}
