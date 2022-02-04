// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.GUI;

import org.bukkit.inventory.ItemStack;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class ClassItemBind implements Listener
{
    @EventHandler
    public void onClickInventory(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (!e.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (!e.getCurrentItem().getItemMeta().hasLore()) {
            if (!e.getCurrentItem().getItemMeta().hasDisplayName()) {
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Humans") || e.getCurrentItem().getItemMeta().getDisplayName().contains("Shamans")) {
                e.setCancelled(true);
                return;
            }
        }
        if (e.getCurrentItem().getItemMeta().hasLore()) {
            for (final String lore : e.getCurrentItem().getItemMeta().getLore()) {
                if (lore.contains(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString())) {
                    e.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onDrop(final PlayerDropItemEvent e) {
        final ItemStack item = e.getItemDrop().getItemStack();
        if (item == null) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (item.getItemMeta().hasDisplayName() && (item.getItemMeta().getDisplayName().contains("Shamans Team") || item.getItemMeta().getDisplayName().contains("Humans Team"))) {
            e.setCancelled(true);
        }
        if (item.getItemMeta().hasLore()) {
            for (final String lore : item.getItemMeta().getLore()) {
                if (lore.contains(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString()) || lore.contains(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
