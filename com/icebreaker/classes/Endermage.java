// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.Location;
import org.bukkit.entity.Enderman;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.icebreaker.testing.Test;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
import org.bukkit.event.Listener;

public class Endermage implements Listener
{
    private Map<UUID, Boolean> verif;
    public HashMap<UUID, Integer> cooldownTimeE;
    public HashMap<UUID, BukkitRunnable> cooldownTaskE;
    
    public Endermage() {
        this.verif = new HashMap<UUID, Boolean>();
        this.cooldownTimeE = new HashMap<UUID, Integer>();
        this.cooldownTaskE = new HashMap<UUID, BukkitRunnable>();
    }
    
    private boolean isVerif(final Player p) {
        return this.verif.containsKey(p.getUniqueId()) && this.verif.get(p.getUniqueId());
    }
    
    private boolean setVerif(final Player p, final boolean verif) {
        final boolean prev = this.isVerif(p);
        this.verif.put(p.getUniqueId(), verif);
        return prev;
    }
    
    @EventHandler
    public void onRightClick(final PlayerInteractEvent e) {
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
        if (!p.isOnGround() && stack.getType() == Material.EYE_OF_ENDER) {
            p.sendMessage(ChatColor.RED + "You can't use the spell in mid air!");
            return;
        }
        if (stack.getType() != Material.EYE_OF_ENDER && stack.getItemMeta().getDisplayName() != ChatColor.AQUA + "Recall" + ChatColor.GREEN + ChatColor.BOLD + " READY") {
            return;
        }
        if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
            p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
            e.setCancelled(true);
            return;
        }
        e.setCancelled(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 280, 2));
        p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SHOOT, 15.0f, 1.0f);
        ItemStack[] contents;
        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack item2 = contents[i];
            if (item2 != null && item2.getType().equals((Object)Material.EYE_OF_ENDER) && item2.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Recall")) {
                final ItemMeta meta1 = item2.getItemMeta();
                final ArrayList<String> lore2 = new ArrayList<String>();
                lore2.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                meta1.setLore((List)lore2);
                meta1.setDisplayName(ChatColor.AQUA + "Recall" + ChatColor.GOLD + ChatColor.BOLD + " USED");
                item2.setItemMeta(meta1);
                break;
            }
        }
        this.mobSpawn(p);
        new BukkitRunnable() {
            public void run() {
                if (!Endermage.this.isVerif(p)) {
                    for (final Entity e : p.getLocation().getWorld().getEntities()) {
                        if (e.getType().isAlive() && e != null && e.getCustomName() != null && e.getCustomName().contains(p.getName())) {
                            p.teleport(e);
                            e.remove();
                        }
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_SCREAM, 10.0f, 1.0f);
                }
                else {
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_DEATH, 10.0f, 1.0f);
                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Endermage" + ChatColor.GRAY + "]" + ChatColor.GOLD + ChatColor.BOLD + " > " + ChatColor.DARK_RED + "Recall source has been terminated!");
                    Endermage.this.setVerif(p, false);
                }
                Endermage.this.cooldownTimeE.put(p.getUniqueId(), 30);
                Endermage.this.cooldownTaskE.put(p.getUniqueId(), new BukkitRunnable() {
                    public void run() {
                        if (Endermage.this.cooldownTimeE.get(p.getUniqueId()) > 0) {
                            ItemStack[] contents;
                            for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                                final ItemStack item1 = contents[i];
                                if (item1 != null && item1.getType().equals((Object)Material.EYE_OF_ENDER) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Recall")) {
                                    final ItemMeta meta1 = item1.getItemMeta();
                                    final ArrayList<String> lore1 = new ArrayList<String>();
                                    lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                    meta1.setLore((List)lore1);
                                    meta1.setDisplayName(ChatColor.AQUA + "Recall " + ChatColor.RED + ChatColor.BOLD + Endermage.this.cooldownTimeE.get(p.getUniqueId()));
                                    item1.setItemMeta(meta1);
                                    break;
                                }
                            }
                        }
                        Endermage.this.cooldownTimeE.put(p.getUniqueId(), Endermage.this.cooldownTimeE.get(p.getUniqueId()) - 1);
                        if (Endermage.this.cooldownTimeE.get(p.getUniqueId()) == -1) {
                            im.setDisplayName(ChatColor.AQUA + "Recall" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                            item.setItemMeta(im);
                            p.updateInventory();
                            ItemStack[] contents2;
                            for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                                final ItemStack item1 = contents2[j];
                                if (item1 != null && item1.getType().equals((Object)Material.EYE_OF_ENDER) && item1.getItemMeta().getDisplayName().contains("Recall")) {
                                    final ItemMeta meta1 = item1.getItemMeta();
                                    final ArrayList<String> lore1 = new ArrayList<String>();
                                    lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                    meta1.setLore((List)lore1);
                                    meta1.setDisplayName(ChatColor.AQUA + "Recall" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                    item1.setItemMeta(meta1);
                                    break;
                                }
                            }
                            Endermage.this.cooldownTimeE.remove(p.getUniqueId());
                            Endermage.this.cooldownTaskE.remove(p.getUniqueId());
                            this.cancel();
                        }
                    }
                });
                Endermage.this.cooldownTaskE.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
            }
        }.runTaskLater((Plugin)Test.plugin, 200L);
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        final Action action = e.getAction();
        final ItemStack stack = e.getItem();
        if (stack == null) {
            return;
        }
        if ((action.equals((Object)Action.RIGHT_CLICK_AIR) || action.equals((Object)Action.RIGHT_CLICK_BLOCK)) && stack.getType() == Material.EYE_OF_ENDER) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEndermanDeath(final EntityDeathEvent e) {
        e.getDrops().clear();
    }
    
    public void mobSpawn(final Player p) {
        final Location loc = p.getLocation();
        final Enderman enderman = (Enderman)p.getWorld().spawn(loc, (Class)Enderman.class);
        enderman.setHealth(0.1);
        enderman.setCustomName(ChatColor.DARK_PURPLE + p.getName() + "'s Recall ");
        enderman.setCustomNameVisible(true);
        enderman.setAI(false);
        enderman.setCanPickupItems(false);
        new BukkitRunnable() {
            public void run() {
                if (enderman.isDead() && enderman.getCustomName().contains(p.getName())) {
                    Endermage.this.setVerif(p, true);
                }
            }
        }.runTaskLater((Plugin)Test.plugin, 200L);
    }
}
