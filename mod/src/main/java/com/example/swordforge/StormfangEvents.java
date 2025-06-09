package com.example.swordforge;

import com.example.swordforge.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SwordForge.MOD_ID)
public class StormfangEvents {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.getMainHandItem().getItem() == ModItems.STORMFANG.get()) {
                LivingEntity target = event.getEntity();
                if (player.isInWater() || player.level().isThundering()) {
                    event.setAmount(event.getAmount() * 1.5F);
                }
            }
        }
    }
}
