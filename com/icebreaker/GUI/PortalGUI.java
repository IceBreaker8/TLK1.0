// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.GUI;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.Listener;

public class PortalGUI implements Listener
{
    @EventHandler
    public void onPortal(final PlayerPortalEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            final Player p = e.getPlayer();
            e.setCancelled(true);
            new BukkitRunnable() {
                public void run() {
                    if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                        p.teleport(new Location(p.getWorld(), -134.0, 108.0, -52.0));
                        new BukkitRunnable() {
                            public void run() {
                                final InventoryGUI gui = new InventoryGUI();
                                gui.createMenu(p);
                            }
                        }.runTaskLater((Plugin)Test.plugin, 20L);
                    }
                    if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                        p.teleport(new Location(p.getWorld(), 139.0, 63.0, 98.0));
                        new BukkitRunnable() {
                            public void run() {
                                final InventoryGUI gui = new InventoryGUI();
                                gui.createMenu(p);
                            }
                        }.runTaskLater((Plugin)Test.plugin, 20L);
                    }
                }
            }.runTaskLater((Plugin)Test.plugin, 40L);
        }
    }
}
