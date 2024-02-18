package jobieskii.mendingdenied;

import net.fabricmc.api.ModInitializer;

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
