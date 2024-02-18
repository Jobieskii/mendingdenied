package jobieskii.mendingdenied.mixin;

import jobieskii.mendingdenied.Config;
import jobieskii.mendingdenied.hasAcceptableItem;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MendingEnchantment.class)
public class EnchantmentTargetMixin implements hasAcceptableItem {

    @Override
    public boolean isAcceptableItem(ItemStack item) {
        return !Config.blacklist.contains(item.getItem()) && item.getItem().isDamageable();
    }
}
