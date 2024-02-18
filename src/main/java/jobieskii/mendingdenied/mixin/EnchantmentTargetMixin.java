package jobieskii.mendingdenied.mixin;

import jobieskii.mendingdenied.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentTargetMixin {
    @Inject(
            method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean isMending = (Enchantment)(Object) this == Enchantments.MENDING;
        cir.setReturnValue(
            cir.getReturnValue() &&
            (!isMending || !Config.blacklist.contains(stack.getItem()))
        );

    }
}
