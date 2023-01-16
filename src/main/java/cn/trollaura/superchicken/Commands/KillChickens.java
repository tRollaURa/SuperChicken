package cn.trollaura.superchicken.Commands;

import cn.trollaura.superchicken.Utils;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class KillChickens implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int sum = 0;
        if(sender.hasPermission("superchicken.use.command")){
        if(args.length == 0){
            return false;
        }else {
            try {
                Player sender_player = (Player)sender;
                World sender_world = sender_player.getWorld();

                for(int i = 0; i < sender_world.getEntities().size(); ++i) {
                    if (((Entity)sender_world.getEntities().get(i)).getType() == EntityType.DROPPED_ITEM) {
                        ((Entity)sender_world.getEntities().get(i)).remove();
                        ++sum;
                    }
                }

                Utils.plugin().getLogger().info(Utils.Colors("删除了" + sum + "个鸡！"));
                sender_player.sendMessage("删除了" + sum + "个鸡！");
            } catch (ClassCastException var11) {
                Iterator var7 = Utils.plugin().getServer().getWorlds().iterator();

                while (var7.hasNext()) {
                    World world = (World) var7.next();
                    Iterator var9 = world.getEntities().iterator();

                    while (var9.hasNext()) {
                        Entity entities = (Entity) var9.next();
                        if (entities.getType() == EntityType.CHICKEN) {
                            entities.remove();
                            ++sum;
                        }
                    }
                }
            }

                Utils.plugin().getLogger().info("删除了" + sum + "个鸡！");
            }

            return true;
        }else {
            sender.sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " " + Utils.config().getString("Messages.NoPerms")));
        }
        return false;
    }

    }