package cn.trollaura.superchicken;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String Colors(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }
    public static Plugin plugin() {
        return cn.trollaura.superchicken.SuperChicken.getProvidingPlugin(cn.trollaura.superchicken.SuperChicken.class);
    }
    public static FileConfiguration config() {
        return plugin().getConfig();
    }
    public static int getEntityCount(Chunk chunk, EntityType entityType) {
        return ((List) Arrays.stream(chunk.getEntities()).filter((v) -> {
            return v.getType() == entityType;
        }).collect(Collectors.toList())).size();
    }
}
