package cn.trollaura.superchicken;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.*;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onR(PlayerInteractEntityEvent evt) {
        if(Utils.config().getBoolean("EnableSuperChicken")){
            if(evt.getRightClicked().getType() == EntityType.CHICKEN && evt.getRightClicked().getTicksLived() > 24000) {
             if (evt.getPlayer().hasPermission("superchicken.use.dupe")) {
            if(evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR){
              if(evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.NAME_TAG) {
                  if (evt.getRightClicked().getCustomName().equals(Utils.Colors("&a[NOT USED]"))) {
                      evt.getRightClicked().setCustomName(Utils.Colors("&c[USED]" + " " + evt.getPlayer().getInventory().getItemInMainHand().getType().name().toString()));
                      Location location = evt.getRightClicked().getLocation();
                      int i;
                      for (i = 0; i < Utils.config().getInt("ChickenDupeTimes"); ++i) {
                          evt.getRightClicked().teleport(location);
                          evt.getRightClicked().getWorld().dropItem(evt.getRightClicked().getLocation(), evt.getPlayer().getInventory().getItemInMainHand());
                      }
                      if (Utils.config().getBoolean("Notification")) {
                          Utils.plugin().getLogger().info(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
                      }
                      Iterator var6 = Utils.plugin().getServer().getOnlinePlayers().iterator();

                      while(true) {
                          Player ops;
                          do {
                              if (!var6.hasNext()) {
                                  return;
                              }

                              ops = (Player) var6.next();
                          } while (!ops.isOp() && !Objects.equals(ops.getDisplayName(), "nope") && !Objects.equals(ops.getDisplayName(), "nope"));
                          if (Utils.config().getBoolean("Notification")) {
                              ops.sendMessage(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
                          }
                      }

                  }
              }
            }
        }else{
                 evt.getPlayer().sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " " + Utils.config().getString("Messages.NoPerms")));}
            }
    }
    }
    @EventHandler
    public void onSpawn(EntitySpawnEvent evt){
        if(Utils.config().getBoolean("EnableSuperChicken")) {
            if (evt.getEntityType() == EntityType.CHICKEN) {
                evt.getEntity().setCustomName(Utils.Colors("&a[NOT USED]"));
            }

        }
        }
    }
