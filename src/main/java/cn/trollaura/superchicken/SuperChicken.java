package cn.trollaura.superchicken;

import cn.trollaura.superchicken.Commands.BaseCommand;
import cn.trollaura.superchicken.Commands.KillChickens;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static cn.trollaura.superchicken.Utils.Colors;

public final class SuperChicken extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listener(),this);
        getCommand("superchicken").setExecutor(new BaseCommand());
        getCommand("removechickens").setExecutor(new KillChickens());
        Bukkit.getLogger().info(Colors("&c--------------------------------------------"));
        Bukkit.getLogger().info(Colors("&c- =========     ======         =======     -"));
        Bukkit.getLogger().info(Colors("&c-    ==        ==     ==      ==      ==   -"));
        Bukkit.getLogger().info(Colors("&c-    ==        ========      ==        == -"));
        Bukkit.getLogger().info(Colors("&c-    ==      ==     ==        ==      ==   -"));
        Bukkit.getLogger().info(Colors("&c-    ==     ==        ==       ========    -"));
        Bukkit.getLogger().info(Colors("&c--------------------------------------------"));
        Bukkit.getLogger().info(Colors("&eSuperChicken v1.0 By TrollAura"));
        Bukkit.getLogger().info(Colors("&c[SuperChicken] &aEnabled the Plugin"));
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(Colors("&c--------------------------------------------"));
        Bukkit.getLogger().info(Colors("&c- =========     ======         =======     -"));
        Bukkit.getLogger().info(Colors("&c-    ==        ==     ==      ==      ==   -"));
        Bukkit.getLogger().info(Colors("&c-    ==        ========      ==        == -"));
        Bukkit.getLogger().info(Colors("&c-    ==      ==     ==        ==      ==   -"));
        Bukkit.getLogger().info(Colors("&c-    ==     ==        ==       ========    -"));
        Bukkit.getLogger().info(Colors("&c--------------------------------------------"));
        Bukkit.getLogger().info(Colors("&c[SuperChicken] &aDisable the Plugin"));
        Bukkit.getLogger().info(Colors("&c[SuperChicken] &aBye-bye ~ヾ(≧▽≦*)o"));
    }
}
