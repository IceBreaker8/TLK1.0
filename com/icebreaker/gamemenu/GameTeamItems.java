// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.gamemenu;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GameTeamItems
{
    public void CustomItem(final Player p) {
        final ItemStack item = new ItemStack(Material.LAPIS_BLOCK, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans Team").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item.setItemMeta(im);
        final ItemStack item2 = new ItemStack(Material.REDSTONE_BLOCK, 1);
        final ItemMeta im2 = item2.getItemMeta();
        im2.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Shamans Team").toString());
        im2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item2.setItemMeta(im2);
        p.getInventory().addItem(new ItemStack[] { item });
        p.getInventory().addItem(new ItemStack[] { item2 });
    }
}
