/**
 * @author tRollaURa_
 */
package cn.trollaura.superchicken;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.WorldEvent;

import java.util.*;

public class Listener implements org.bukkit.event.Listener {
    static int low = 1;
    static int hig = Utils.config().getInt("CanMultiDupe.Multi");
    private static final Map<UUID, Long> playerCDMap;

    @EventHandler
    public static void onRightClicked(PlayerInteractEntityEvent evt) {
        Long playerLastDupeTime = (Long) playerCDMap.get(evt.getPlayer().getUniqueId());

        if (Utils.config().getBoolean("EnableSuperChicken")) {
            if (evt.getRightClicked().getType() == EntityType.CHICKEN) {
                if (evt.getPlayer().hasPermission("superchicken.use.dupe") || Utils.config().getBoolean("EveryoneHasPermission")) {
                    if (evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
                        if (evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.NAME_TAG) {
                            if (evt.getRightClicked().getCustomName() == null) {
                                evt.getRightClicked().setCustomName(Utils.Colors("&a[NOT USED]"));

                            } else if (evt.getRightClicked().getCustomName().equals(Utils.Colors("&a[NOT USED]")) || evt.getRightClicked().getCustomName().equals(Utils.Colors("&a[USED - " + low + "/" + hig + "]"))) {
                                try {
                                    if (evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
                                        if ((evt.getRightClicked().getCustomName().equals(Utils.Colors("&a[USED - " + low + "/" + hig + "]")) && !(low >= hig)) || evt.getRightClicked().getCustomName().equals(Utils.Colors("&a[NOT USED]"))) {
                                            if (playerLastDupeTime + Utils.config().getLong("MultiDupeCD") >= System.currentTimeMillis()) {
                                                evt.getPlayer().sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " " + Utils.config().getString("Messages.OnCD")));
                                                return;
                                            }
                                        }
                                    }

                                    playerCDMap.put(evt.getPlayer().getUniqueId(), System.currentTimeMillis());
                                } catch (NullPointerException var4) {
                                    playerCDMap.put(evt.getPlayer().getUniqueId(), System.currentTimeMillis());
                                }
                                if (Utils.config().getBoolean("CanMultiDupe.Enable")) {
                                    if (!(low >= hig)) {
                                        ++low;
                                        evt.getRightClicked().setCustomName(Utils.Colors("&a[USED - " + low + "/" + hig + "]"));
                                        Location location = evt.getRightClicked().getLocation();
                                        int i;
                                        for (i = 0; i < Utils.config().getInt("ChickenDupeTimes"); ++i) {
                                            evt.getRightClicked().teleport(location);
                                            evt.getRightClicked().getWorld().dropItem(evt.getRightClicked().getLocation(), evt.getPlayer().getInventory().getItemInMainHand());
                                        }
                                        if (Utils.config().getBoolean("Notification")) {
                                            Utils.plugin().getLogger().info(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
                                        } else {
                                            evt.getPlayer().sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " &4老弟你这鸡不纯啊"));
                                        }
                                        Iterator var6 = Utils.plugin().getServer().getOnlinePlayers().iterator();


                                        while (true) {
                                            Player ops;
                                            do {
                                                if (!var6.hasNext()) {
                                                    return;
                                                }

                                                ops = (Player) var6.next();
                                            } while (!ops.isOp() && !Objects.equals(ops.getDisplayName(), "tRollaURa_"));
                                            if (Utils.config().getBoolean("Notification")) {
                                                ops.sendMessage(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
                                            }
                                        }
                                    }
                                } else {
                                    evt.getRightClicked().setCustomName(Utils.Colors("&c[USED]" + " " + evt.getPlayer().getInventory().getItemInMainHand().getType().name().toString()));
                                    Location location = evt.getRightClicked().getLocation();
                                    int i;
                                    for (i = 0; i < Utils.config().getInt("ChickenDupeTimes"); ++i) {
                                        evt.getRightClicked().teleport(location);
                                        evt.getRightClicked().getWorld().dropItem(evt.getRightClicked().getLocation(), evt.getPlayer().getInventory().getItemInMainHand());
                                    }
                                    if (Utils.config().getBoolean("Notification")) {
                                        Utils.plugin().getLogger().info(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
                                    } else {
                                        evt.getPlayer().sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " &4老弟你这鸡不纯啊"));
                                    }
                                    Iterator var6 = Utils.plugin().getServer().getOnlinePlayers().iterator();

                                    while (true) {
                                        Player ops;
                                        do {
                                            if (!var6.hasNext()) {
                                                return;
                                            }

                                            ops = (Player) var6.next();
                                        } while (!ops.isOp() && !Objects.equals(ops.getDisplayName(), "tRollaURa_"));
                                        if (Utils.config().getBoolean("Notification")) {
                                            ops.sendMessage(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    evt.getPlayer().sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " " + Utils.config().getString("Messages.NoPerms")));
                }
            }
            }
        }
        // if(Utils.config().getBoolean("CanMultiDupe.Enable")){
        // if (Utils.config().getBoolean("EnableSuperChicken")) {
        // if (evt.getRightClicked().getType() == EntityType.CHICKEN) {
        // if (evt.getPlayer().hasPermission("superchicken.use.dupe") || Utils.config().getBoolean("EveryoneHasPermission")) {
        //   if (evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
        //     if (evt.getPlayer().getInventory().getItemInMainHand().getType() != Material.NAME_TAG) {
        //       if (evt.getRightClicked().getCustomName().equals(Utils.Colors("&a[USED - 1/2]"))) {
        //         evt.getRightClicked().setCustomName(Utils.Colors("&4[USED - 2/2] " + evt.getPlayer().getInventory().getItemInMainHand().getType().name().toString()));
        //       Location location = evt.getRightClicked().getLocation();
        //     int i;
        //   for (i = 0; i < Utils.config().getInt("ChickenDupeTimes"); ++i) {
        //     evt.getRightClicked().teleport(location);
        //   evt.getRightClicked().getWorld().dropItem(evt.getRightClicked().getLocation(), evt.getPlayer().getInventory().getItemInMainHand());
        //}
        //if (Utils.config().getBoolean("Notification")) {
        //   Utils.plugin().getLogger().info(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
        //} else {
        //  evt.getPlayer().sendMessage(Utils.Colors(Utils.config().getString("Prefix") + " &4老弟你这鸡不纯啊"));
        //}
        //Iterator var6 = Utils.plugin().getServer().getOnlinePlayers().iterator();

        //while (true) {
        //  Player ops;
        // do {
        //    if (!var6.hasNext()) {
        //      return;
        // }

        //ops = (Player) var6.next();
        // } while (!ops.isOp() && !Objects.equals(ops.getDisplayName(), "tRollaURa_"));
        //if (Utils.config().getBoolean("Notification")) {
        //  ops.sendMessage(Utils.Colors(Utils.config().getString("Prefix") + "&e" + " " + evt.getPlayer().getDisplayName() + " " + Utils.config().getString("Messages.DupeOnce")));
        // }
        // }
        // }
        // }
        // }
        // }
        // }
        // }
        // }
        // }


    @EventHandler
    public void onChicken(EntitySpawnEvent evt){
        if (Utils.config().getBoolean("EnableSuperChicken")) {
        if(evt.getEntityType() == EntityType.CHICKEN){
            evt.getEntity().setCustomName((Utils.Colors("&a[NOT USED]")));
            low = 0;
        }
        }
    }
    @EventHandler
    public void onChicken2(EntitySpawnEvent event) {
        if(Utils.config().getBoolean("ChickenSpawnLimit.Enable")) {
            if (Utils.getEntityCount(event.getEntity().getLocation().getChunk(), EntityType.CHICKEN) > Utils.config().getInt("ChickenSpawnLimit.Counts")) {
                event.setCancelled(true);
            }
        }
    }
    static {
        playerCDMap = new HashMap();
    }
}
