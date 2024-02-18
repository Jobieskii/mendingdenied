package jobieskii.mendingdenied;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.item.Item;

import java.io.IOException;

public class Mendingdenied implements ModInitializer {
    public static final String MODID = "mendingdenied";
    @Override
    public void onInitialize() {
        try {
            Config.readOrCreate();

            String list = Config.blacklist.stream().map(Item::toString).reduce((a, b) -> {return a + ", " + b;}).get();
            Log.info(LogCategory.GENERAL,"blacklisted from mending: [" + list + "]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
