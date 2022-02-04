// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.Listener;

public class WaterDamage implements Listener
{
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Material m = p.getLocation().getBlock().getType();
        final Location loc = new Location(Bukkit.getWorld("world"), -175.0, 28.0, 196.0);
        for (final Entity e2 : loc.getWorld().getEntities()) {
            if (e2.getLocation().distance(loc) < 5000.0 && e2.getCustomName() != null) {
                if (e2.getCustomName().contains("Guardian")) {
                    e2.setCustomName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Guardian Of The Sea").toString());
                }
                else {
                    if (!e2.getCustomName().contains("Knight")) {
                        continue;
                    }
                    e2.setCustomName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("The Last Knight").toString());
                }
            }
        }
        if (m == Material.STATIONARY_WATER || m == Material.WATER) {
            if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getChestplate().getItemMeta().hasDisplayName() && (p.getInventory().getChestplate().getItemMeta().getDisplayName() != null & p.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Diving"))) {
                return;
            }
            p.damage(4.0);
        }
    }
}
