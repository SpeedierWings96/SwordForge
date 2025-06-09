package com.example.swordforge.item;

import com.example.swordforge.SwordForge;
import net.minecraft.world.item.Item;
import com.example.swordforge.item.StormfangItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SwordForge.MOD_ID);

    public static final RegistryObject<Item> STORMFANG = ITEMS.register("stormfang",
            () -> new StormfangItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
