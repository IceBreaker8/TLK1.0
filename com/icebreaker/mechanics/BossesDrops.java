// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.Player;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.WitherSkeleton;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.Listener;

public class BossesDrops implements Listener
{
    @EventHandler
    public void onBossDeath(final EntityDeathEvent e) {
        e.getDrops().clear();
        e.setDroppedExp(0);
        final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.BOLD).append("Tribal Mask").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Grants unlimited power to who wears it!").toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        final ItemStack item2 = new ItemStack(Material.FEATHER, 1);
        final ItemMeta im2 = item2.getItemMeta();
        im2.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Chicken Feather").toString());
        im2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Used for crafting powerful item!").toString());
        im2.setLore((List)lore2);
        item2.setItemMeta(im2);
        final LivingEntity entity = e.getEntity();
        if (entity instanceof WitherSkeleton) {
            entity.getLocation().getWorld().dropItem(entity.getLocation(), item);
        }
        if (entity instanceof Chicken && entity.getKiller() instanceof Player) {
            if (ClassesPerms.blueteam.contains(entity.getKiller().getUniqueId())) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                        p.getInventory().addItem(new ItemStack[] { item2 });
                    }
                }
            }
            if (ClassesPerms.redteam.contains(entity.getKiller().getUniqueId())) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                        p.getInventory().addItem(new ItemStack[] { item2 });
                    }
                }
            }
        }
    }
}
