package net.liongamer.honkaimod.entity.custom;


import net.liongamer.honkaimod.item.custom.EdenstarItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EdenstarEffectEntity extends Entity {
    public EdenstarEffectEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {

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
}
