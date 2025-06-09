package com.example.swordforge.client;

import com.example.swordforge.SwordForge;
import com.example.swordforge.item.ModItems;
import com.example.swordforge.item.StormfangItem;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = SwordForge.MOD_ID, value = Dist.CLIENT)
public class StormfangKeybind {
    public static final String CATEGORY = "key.category.swordforge";
    public static KeyMapping LIGHTNING_KEY;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        LIGHTNING_KEY = new KeyMapping("key.swordforge.stormfang_lightning", GLFW.GLFW_KEY_G, CATEGORY);
        event.register(LIGHTNING_KEY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (LIGHTNING_KEY != null && LIGHTNING_KEY.consumeClick()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                if (stack.getItem() == ModItems.STORMFANG.get()) {
                    StormfangItem.summonLightning(player);
                }
            }
        }
    }
}
