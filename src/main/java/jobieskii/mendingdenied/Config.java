package jobieskii.mendingdenied;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Config {
    public static Set<Item> blacklist;
    public static void readOrCreate() throws IOException, JsonSyntaxException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String filename = FabricLoader.getInstance().getConfigDir() + "/" + Mendingdenied.MODID + ".json";
        blacklist = new HashSet<>();

        try {
            FileReader fr = new FileReader(filename);
            parse(fr, gson);
        } catch (FileNotFoundException e) {
            FileWriter fw = new FileWriter(filename);
            fw.write(defaultConfig(gson));
            fw.close();
        }
    }
    private static String defaultConfig(Gson gson) {
        blacklist.add(Registries.ITEM.get(new Identifier("minecraft", "elytra")));

        String[] denied = {"minecraft:elytra"};
        return gson.toJson(denied);
    }
    private static void parse(FileReader file, Gson gson) throws JsonSyntaxException, JsonIOException{
        String[] itemNames = gson.fromJson(file, String[].class);
        for (String name : itemNames) {
            Optional<Item> item = Registries.ITEM.getOrEmpty(new Identifier(name));
            if (!item.isPresent()) Log.error(LogCategory.GENERAL, Mendingdenied.MODID + ": incorrect item in config \""+name+"\"");
            else blacklist.add(item.get());
        }
    }
}
