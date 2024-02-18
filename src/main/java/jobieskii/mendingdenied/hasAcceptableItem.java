package jobieskii.mendingdenied;

import net.minecraft.item.ItemStack;

public interface hasAcceptableItem {
    default boolean isAcceptableItem(ItemStack item) {
        return false;
    }
}
