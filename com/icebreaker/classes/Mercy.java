// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.icebreaker.testing.Test;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Mercy implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Mercy() {
        this.cooldownTimeP = new HashMap<UUID, Integer>();
        this.cooldownTaskP = new HashMap<UUID, BukkitRunnable>();
    }
    
    @EventHandler
    public void onUse(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItem();
        if (item == null) {
            return;
        }
        final ItemMeta im = e.getItem().getItemMeta();
        if (im == null) {
            return;
        }
        final Action action = e.getAction();
        final ItemStack stack = e.getItem();
        if (stack == null) {
            return;
        }
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (im.getLore() == null) {
            return;
        }
        for (final String lore : im.getLore()) {
            if (lore.contains(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString())) {
                return;
            }
        }
        if (!stack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("Revive")) {
            if (this.checkForCorpses(p) <= 0) {
                p.sendMessage(ChatColor.RED + "There are no dead teammates nearby!");
                e.setCancelled(true);
                return;
            }
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            this.reviveSpell(p);
            this.cooldownTimeP.put(p.getUniqueId(), 300);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Mercy.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.TOTEM) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Revive")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Revive " + ChatColor.RED + ChatColor.BOLD + Mercy.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Mercy.this.cooldownTimeP.put(p.getUniqueId(), Mercy.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Mercy.this.cooldownTimeP.get(p.getUniqueId()) <= -1) {
                        im.setDisplayName(ChatColor.AQUA + "Revive" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.TOTEM) && item1.getItemMeta().getDisplayName().contains("Revive")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Revive" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                        Mercy.this.cooldownTimeP.remove(p.getUniqueId());
                        Mercy.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    @EventHandler
    public void rez(final EntityResurrectEvent e) {
        e.setCancelled(true);
    }
    
    public int checkForCorpses(final Player p) {
        int corpse = 0;
        for (final Entity e : p.getLocation().getWorld().getEntities()) {
            if (e.getLocation().distance(p.getLocation()) < 6.0 && !this.noTeamFire(p, e) && e.getCustomName() != null && e.getCustomName().contains(" Corpse")) {
                ++corpse;
            }
        }
        return corpse;
    }
    
    public void reviveSpell(final Player p) {
        p.getWorld().playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0f, 1.0f);
        for (final Entity e : p.getLocation().getWorld().getEntities()) {
            if (e.getLocation().distance(p.getLocation()) < 6.0 && !this.noTeamFire(p, e) && e.getCustomName() != null && e.getCustomName().contains(" Corpse")) {
                e.remove();
            }
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
