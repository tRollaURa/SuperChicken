package cn.trollaura.superchicken.Commands;

import cn.trollaura.superchicken.Listener;
import cn.trollaura.superchicken.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommand implements CommandExecutor {
    int i;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("trollanticheat.command")) {
            if (args.length == 0) {
                sender.sendMessage(Utils.Colors("&c[SuperChicken] - [Release]"));
                sender.sendMessage(Utils.Colors("&c[SuperChicken] &b你可以使用 /superchicken help."));
                sender.sendMessage(Utils.Colors("&cBy tRollaURa_"));
            } else if (args.length == 1) {
                if (args[0].equals("reload")) {
                    Utils.plugin().reloadConfig();
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &aConfigs Reloaded."));
                } else if (args[0].equals("help")) {
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &e指令列表 - By tRollaURa"));
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &b/sc help - 显示这个列表"));
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &b/sc reload - 重载插件配置"));
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &b/sc notify <on/off> - 打开/关闭通知"));
                } else {
                    sender.sendMessage(Utils.Colors("未知的命令!请输入/superchicken help"));
                }

            } else if (args.length == 2) {
                if (args[0].equals("notify") && args[1].equals("on")) {
                    Utils.plugin().getConfig().set("Notification", true);
                    Utils.plugin().saveConfig();
                    Utils.plugin().reloadConfig();
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &aNotification is on."));
                } else if (args[0].equals("notify") && args[1].equals("off")) {
                    Utils.plugin().getConfig().set("Notification", false);
                    Utils.plugin().saveConfig();
                    Utils.plugin().reloadConfig();
                    sender.sendMessage(Utils.Colors("&c[SuperChicken] &aNotification is &coff."));
                }else if(args[0].equals("dumptimes")){
                    try {
                        Utils.config().set("ChickenDupeTimes", args[1]);
                    } catch (Exception e) {
                        sender.sendMessage(Utils.Colors("&c[SuperChicken] &4出错了!请使用数字作为修改值!"));
                    }
                }
                else {
                    sender.sendMessage(Utils.Colors("未知的命令!请输入/superchicken help"));
                }

            } else {
                sender.sendMessage("未知的命令!请输入/superchicken help");
            }


            return false;
        }
        return false;
    }
}
