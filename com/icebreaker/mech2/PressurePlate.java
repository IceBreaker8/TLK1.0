// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class PressurePlate implements Listener
{
    @EventHandler
    public void guardian(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getAction() == null) {
            return;
        }
        if (e.getAction().equals((Object)Action.PHYSICAL) && e.getClickedBlock().getType() == Material.STONE_PLATE && this.playerIsNearby(p)) {
            p.teleport(new Location(Bukkit.getWorld("world"), -165.0, 28.0, 195.0));
            final Location loc = new Location(Bukkit.getWorld("world"), -183.0, 24.0, 198.0);
            loc.getBlock().setType(Material.REDSTONE_BLOCK);
            new BukkitRunnable() {
                public void run() {
                    loc.getBlock().setType(Material.AIR);
                }
            }.runTaskLater((Plugin)Test.plugin, 20L);
        }
    }
    
    public boolean playerIsNearby(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, -162.0, 27.0, 195.0);
        return p.getLocation().distance(loc) < 8.0;
    }
}
