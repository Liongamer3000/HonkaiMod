package net.liongamer.honkaimod.entity.custom;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.liongamer.honkaimod.entity.ModEntities;
import net.liongamer.honkaimod.entity.client.EdenstarEffectRenderer;
import net.liongamer.honkaimod.item.custom.EdenstarItem;
import net.liongamer.honkaimod.particle.ModParticles;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.AxisAlignedLinearPosTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderHighlightEvent;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Nullable;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class EdenstarEffectEntity extends Entity {
    private final Map<Entity, Integer> victims = Maps.newHashMap();
    private Potion potion = Potions.POISON;
    private final List<MobEffectInstance> effects = Lists.newArrayList();
    private int reapplicationDelay = 20;
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(AreaEffectCloud.class, EntityDataSerializers.FLOAT);
    public LivingEntity owner1;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private float radiusOnUse;
    private int durationOnUse;
    private int duration = 600;
    private int doParticle = 0;
    private int doParticle2 = 0;
    private int doParticle3 = 0;
    private boolean isSwimming = false;
    private boolean isCrouching = false;
    private List<Player> swimmingEntities = new ArrayList<>();

    private List<Player> players = new ArrayList<>();

    public float lifeTime = 0;
    @Nullable
    private BlockState feetBlockState = null;

    public EdenstarEffectEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        owner = InvisibleProjectileEntity.owner;
        //System.out.println("effect owner on start ln 71: " + owner);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_RADIUS, 5.0F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    public void setRadius(float pRadius) {
        if (!this.level().isClientSide) {
            this.getEntityData().set(DATA_RADIUS, Mth.clamp(pRadius, 0.0F, 32.0F));
        }

    }

    public float getRadius() {
        return this.getEntityData().get(DATA_RADIUS);
    }

    public void baseTick() {
        float f = this.getRadius();
        lifeTime = lifeTime + 1;
        this.level().getProfiler().push("entityBaseTick");
        this.feetBlockState = null;

        double x = (double) this.position().x - 2.5;
        double y = (double) this.position().y;
        double z = (double) this.position().z - 2.5;
        double x2 = (double) this.position().x + 2.5;
        double y2 = (double) this.position().y + 3;
        double z2 = (double) this.position().z + 2.5;
        AABB scanAbove = new AABB(x, y, z, x2, y2, z2);
        /*
        double x3 = (double) this.position().x - 20;
        double y3 = (double) this.position().y - 20;
        double z3 = (double) this.position().z - 20;
        double x4 = (double) this.position().x + 20;
        double y4 = (double) this.position().y + 20;
        double z4 = (double) this.position().z + 20;
        AABB scanAbove2 = new AABB(x3, y3, z3, x4, y4, z4);
        */
        // List entities = level().getEntities(owner, scanAbove);
        List<Entity> entities1 = level().getEntities(null, scanAbove);
        //List<Entity> entities2 = level().getEntities(null, scanAbove2);
        System.out.println(entities1.size());

        for (int s = 0; s < players.size(); s++) {
            try {
                LivingEntity player = players.get(s);
                //if(entities1.indexOf(player)!=-1){
                    ((Player) player).setForcedPose(null);
                //}
            }catch(Exception ex){
                System.out.println("Error " + ex.getMessage());
            }
        }
        players.clear();
        for (int s = 0; s < entities1.size(); s++) {
            System.out.println("for");
            try{
            //if (!entities1.get(s).equals(ModEntities.EDENSTAR_EFFECT.get())) {
                LivingEntity livingEntity = (LivingEntity) entities1.get(s);

                System.out.println(livingEntity);
                System.out.println(entities1.get(s));
                //livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON));
                LivingEntity pOwner = owner;
                //System.out.println("effect owner ln 137: " + owner);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, (int) (0.1 * lifeTime), false, false));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, (int) (-0.1 * lifeTime), false, false));
                if (lifeTime > 100) {
                    livingEntity.hurt(pOwner.damageSources().flyIntoWall(), 1f);
                }
                if (lifeTime > 300) {
                    livingEntity.hurt(pOwner.damageSources().flyIntoWall(), 5f);
                }
                if (lifeTime > 60) {
                    if (livingEntity instanceof Player) {
                        players.add((Player) livingEntity);
                        ((Player) livingEntity).setForcedPose(Pose.CROUCHING);
                    }
                }
                if (lifeTime > 70) {
                    if (livingEntity instanceof Player) {

                        ((Player) livingEntity).setForcedPose(Pose.SWIMMING);
                        /*for (int i = 0; i < swimmingEntities.size(); i++) {
                            if (!players.contains(swimmingEntities.get(i))) {
                                swimmingEntities.get(i).setForcedPose(null);
                            }
                            System.out.println("inforswimnull");
                            swimmingEntities.get(i).setForcedPose(null);
                        }
                        swimmingEntities.clear();
                        swimmingEntities.add((Player) livingEntity);
                        //((Player) livingEntity).setPose(Pose.SWIMMING);
                        ((Player) livingEntity).setForcedPose(Pose.SWIMMING);
                        //array of new players, compare arrays
                        //System.out.println(swimmingEntities);*/
                    }
                }
                System.out.println("past if");
            }catch(Exception ex){
                System.out.println("Error " + ex.getMessage());
            }
        }
/*
        try {
            for (int b = 0; b < swimmingEntities.size(); b++) {
                System.out.println("FORK");
                swimmingEntities.get(b).setForcedPose(null);
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
*/
/*

        for (int k = 0; k < entities2.size(); k++) {
            System.out.println("for");
            try{
                //if (!entities1.get(s).equals(ModEntities.EDENSTAR_EFFECT.get())) {
                LivingEntity livingEntity = (LivingEntity) entities2.get(k);
                System.out.println(livingEntity);
                System.out.println(entities2.get(k));
                //livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON));
                LivingEntity pOwner = owner;
                //System.out.println("effect owner ln 137: " + owner);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10, (int) 0.1,false,false));
                System.out.println("past if");
            }catch(Exception ex){
                System.out.println("Error " + ex.getMessage());
            }
        }
*/
        if (lifeTime > 200) {
            System.out.println("if");
            //BlockPos blocksToRemove = new BlockPos(this.getBlockX(), this.getBlockY(), this.getBlockZ());
            for(int i = this.getBlockX() - 2; i <= this.getBlockX() + 2; i++){
                for(int j = this.getBlockY(); j <= this.getBlockY() + 2; j++) {
                    for(int k = this.getBlockZ() - 2; k <= this.getBlockZ() + 2; k++) {
                        this.level().destroyBlock(new BlockPos(i,j,k), true);
                    }
                }
            }

        }


        if (EdenstarItem.discardEntity == true && EdenstarItem.owner == owner) {
            try {
                for (int i = 0; i < players.size(); i++) {
                   players.get(i).setForcedPose(null);
                }
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }


            this.discard();
            // EdenstarItem.discardEntity = true;
        }

        this.walkDistO = this.walkDist;
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
        int d = (int) (Math.random() * 2);
        if (doParticle3 == 15) {
            if (d == 0) {
                this.level().addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_4.get(),
                        -Math.cos(owner.yRotO * Math.PI / 180 + 5) * 0.4 + owner.getX(), -Math.sin(owner.xRotO * Math.PI / 180) * 0.2 + owner.getY() + 1.3, -Math.sin(owner.yRotO * Math.PI / 180 + 5) * 0.4 + owner.getZ(),
                        (Math.random() - 0.5) / 50, (Math.random() - 0.5) / 50, (Math.random() - 0.5) / 50);
            }
            if (d == 1){
                this.level().addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_5.get(),
                        -Math.cos(owner.yRotO * Math.PI / 180 + 5) * 0.4 + owner.getX(), -Math.sin(owner.xRotO * Math.PI / 180) * 0.2 + owner.getY() + 1.3, -Math.sin(owner.yRotO * Math.PI / 180 + 5) * 0.4 + owner.getZ(),
                        (Math.random() - 0.5) / 50, (Math.random() - 0.5) / 50, (Math.random() - 0.5) / 50);
            }
            doParticle3 = 0;
        }

        int b = (int) (Math.random() * 2);

            for (int i = 0; i < 360; i++) {
                double angle = Math.random() * Math.PI * 2;
                double distance = Math.random() * 5f;
                if (i % 20 == 0) {
                    if (doParticle == 1) {
                        if (b == 0) {
                            this.level().addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES.get(),
                                    this.getX() + distance * Math.cos(angle) + 0d, this.getY() + Math.random() * 5, this.getZ() + distance * Math.sin(angle) + 0d,
                                    (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15);
                        }
                        if (b == 1) {
                            this.level().addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_2.get(),
                                    this.getX() + distance * Math.cos(angle) + 0d, this.getY() + Math.random() * 5, this.getZ() + distance * Math.sin(angle) + 0d,
                                    (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15, (Math.random() - 0.5) / 15);
                        }
                        doParticle = 0;
                    }
                    if (doParticle2 == 7) {
                        this.level().addParticle(ModParticles.EDENSTAR_EFFECT_PARTICLES_3.get(),
                                this.getX() + distance * Math.cos(angle) + 0d, this.getY() + Math.random() * 5, this.getZ() + distance * Math.sin(angle) + 0d,
                                (Math.random() - 0.5) / 50, (Math.random() - 0.5) / 50, (Math.random() - 0.5) / 50);
                        doParticle2 = 0;
                    }
                }

                this.firstTick = false;
                //this.level().getProfiler().pop();
            }


        doParticle = doParticle + 1;
        doParticle2 = doParticle2 + 1;
        doParticle3 = doParticle3 + 1;
    }

/*
    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }
    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }
*/

}
