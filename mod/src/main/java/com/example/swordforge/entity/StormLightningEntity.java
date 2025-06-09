package com.example.swordforge.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class StormLightningEntity extends Entity {
    private static final EntityDataAccessor<Integer> LIFE =
            SynchedEntityData.defineId(StormLightningEntity.class, EntityDataSerializers.INT);

    public StormLightningEntity(EntityType<? extends StormLightningEntity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.entityData.set(LIFE, 5);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(LIFE, 5);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.entityData.set(LIFE, tag.getInt("Life"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("Life", this.entityData.get(LIFE));
    }

    @Override
    public void tick() {
        super.tick();
        int life = this.entityData.get(LIFE) - 1;
        this.entityData.set(LIFE, life);
        if (life == 4 && !this.level().isClientSide) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER,
                    5.0F, 0.8F + this.level().random.nextFloat() * 0.4F);
            this.level().setSkyFlashTime(2);
        }
        if (life <= 0) {
            this.discard();
        }
    }

    @Override
    public Packet<net.minecraft.network.protocol.game.ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
