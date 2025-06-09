package com.example.swordforge.entity;

import com.example.swordforge.SwordForge;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SwordForge.MOD_ID);

    public static final RegistryObject<EntityType<StormLightningEntity>> STORM_LIGHTNING = ENTITIES.register(
            "storm_lightning",
            () -> EntityType.Builder.<StormLightningEntity>of(StormLightningEntity::new, MobCategory.MISC)
                    .sized(0.0F, 0.0F).clientTrackingRange(8).updateInterval(1)
                    .build("storm_lightning"));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
