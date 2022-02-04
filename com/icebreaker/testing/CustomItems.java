// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.testing;

import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CustomItems
{
    public void CustomItem(final Player p) {
        final ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("GameMenu").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Manipulating the gamemode.").toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        p.getInventory().addItem(new ItemStack[] { item });
    }
    
    public void CustomItem2(final Player p) {
        final ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Guardian").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item.setItemMeta(im);
        p.getInventory().addItem(new ItemStack[] { item });
    }
}
