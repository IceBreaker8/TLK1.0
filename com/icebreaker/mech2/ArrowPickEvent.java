// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.Listener;

public class ArrowPickEvent implements Listener
{
    @EventHandler
    public void onArrowPick(final PlayerPickupItemEvent e) {
        final Player p = e.getPlayer();
        final Item item1 = e.getItem();
        if (item1 == null) {
            return;
        }
        if (item1.getItemStack().getType().equals((Object)Material.ARROW)) {
            e.setCancelled(true);
            item1.remove();
            final ItemStack item2 = new ItemStack(Material.ARROW, 1);
            final ItemMeta im = item2.getItemMeta();
            im.setDisplayName(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Arrow").toString());
            im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            item2.setItemMeta(im);
            p.getInventory().addItem(new ItemStack[] { item2 });
        }
    }
}
