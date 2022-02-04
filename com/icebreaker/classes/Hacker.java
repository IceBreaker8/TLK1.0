// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.Location;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.util.Vector;
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
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Hacker implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Hacker() {
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
        if (stack.getItemMeta().getDisplayName().contains("Hack")) {
            this.hackSpell(p);
            e.setCancelled(true);
            this.cooldownTimeP.put(p.getUniqueId(), 30);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Hacker.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.EMERALD) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Hack")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Hack " + ChatColor.RED + ChatColor.BOLD + Hacker.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Hacker.this.cooldownTimeP.put(p.getUniqueId(), Hacker.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Hacker.this.cooldownTimeP.get(p.getUniqueId()) == -1) {
                        im.setDisplayName(ChatColor.AQUA + "Hack" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.EMERALD) && item1.getItemMeta().getDisplayName().contains("Hack")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Hack" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                        Hacker.this.cooldownTimeP.remove(p.getUniqueId());
                        Hacker.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    private void hackSpell(final Player p) {
        final Location loc = p.getLocation();
        p.setVelocity(new Vector(0.0, 0.5, 0.0));
        p.getLocation().getWorld().playSound(loc, Sound.ENTITY_WITHER_SKELETON_HURT, 5.0f, 1.0f);
        loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 500, 4.0, 3.0, 4.0, 0.01);
        for (final Entity e : loc.getWorld().getEntities()) {
            if (e.getLocation().distance(loc) < 7.0 && e instanceof Player && e.getType().isAlive() && !e.equals(p) && this.noTeamFire(p, e)) {
                final LivingEntity b1 = (LivingEntity)e;
                b1.damage(2.0, (Entity)p);
                ClassesPerms.hackedPeople.add(b1.getUniqueId());
                b1.setGlowing(true);
                new BukkitRunnable() {
                    public void run() {
                        b1.setGlowing(false);
                        b1.sendMessage(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("You are no longer Hacked!").toString());
                        ((Player)b1).playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0f, 1.0f);
                        ClassesPerms.hackedPeople.remove(b1.getUniqueId());
                    }
                }.runTaskLater((Plugin)Test.plugin, 120L);
            }
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
