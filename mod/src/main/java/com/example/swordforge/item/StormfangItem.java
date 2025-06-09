package com.example.swordforge.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import com.example.swordforge.entity.ModEntities;
import com.example.swordforge.entity.StormLightningEntity;
import net.minecraft.world.phys.BlockHitResult;

public class StormfangItem extends SwordItem {
    public StormfangItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            BlockHitResult hit = level.clip(new ClipContext(player.getEyePosition(1.0F),
                    player.getEyePosition(1.0F).add(player.getLookAngle().scale(50)),
                    ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
            com.example.swordforge.entity.StormLightningEntity bolt = com.example.swordforge.entity.ModEntities.STORM_LIGHTNING.get().create(level);
            if (bolt != null) {
                bolt.moveTo(hit.getLocation());
                level.addFreshEntity(bolt);
            }
            player.getCooldowns().addCooldown(this, 100);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void summonLightning(Player player) {
        if (player == null) return;
        if (!player.level().isClientSide) {
            Level level = player.level();
            if (!player.getCooldowns().isOnCooldown(player.getMainHandItem().getItem())) {
                BlockHitResult hit = level.clip(new ClipContext(player.getEyePosition(1.0F),
                        player.getEyePosition(1.0F).add(player.getLookAngle().scale(50)),
                        ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
                com.example.swordforge.entity.StormLightningEntity bolt = com.example.swordforge.entity.ModEntities.STORM_LIGHTNING.get().create(level);
                if (bolt != null) {
                    bolt.moveTo(hit.getLocation());
                    level.addFreshEntity(bolt);
                }
                player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 100);
            }
        }
    }
}
