// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.GUI;

import org.bukkit.event.EventHandler;
import org.bukkit.Location;
import java.util.Iterator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.Listener;

public class Soulbound implements Listener
{
    @EventHandler
    public void onDrop(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if (item.getItemMeta().hasLore()) {
            for (final String lore : item.getItemMeta().getLore()) {
                if (lore.contains(ChatColor.GOLD + "Soulbound Item")) {
                    final Location loc = p.getLocation();
                    e.getItemDrop().remove();
                    p.playSound(loc, Sound.ENTITY_WITHER_BREAK_BLOCK, 10.0f, 6.0f);
                }
            }
        }
    }
}
