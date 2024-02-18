package jobieskii.mendingdenied;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.item.Items;

import java.io.IOException;

public class Mendingdenied implements ModInitializer {
    public static final String MODID = "mendingdenied";
    @Override
    public void onInitialize() {
        try {
            Config.readOrCreate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
