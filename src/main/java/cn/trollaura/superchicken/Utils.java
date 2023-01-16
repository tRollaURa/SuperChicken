package cn.trollaura.superchicken;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

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
}
