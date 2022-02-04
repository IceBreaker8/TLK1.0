// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.Particle;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
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

public class Pyromancer implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Pyromancer() {
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
        if (stack.getItemMeta().getDisplayName().contains("Firestorm")) {
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            this.burnSpell(p);
            this.cooldownTimeP.put(p.getUniqueId(), 35);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Pyromancer.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.INK_SACK) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Firestorm")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Firestorm " + ChatColor.RED + ChatColor.BOLD + Pyromancer.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Pyromancer.this.cooldownTimeP.put(p.getUniqueId(), Pyromancer.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Pyromancer.this.cooldownTimeP.get(p.getUniqueId()) == -1) {
                        im.setDisplayName(ChatColor.AQUA + "Firestorm" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.INK_SACK) && item1.getItemMeta().getDisplayName().contains("Firestorm")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Firestorm" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                        Pyromancer.this.cooldownTimeP.remove(p.getUniqueId());
                        Pyromancer.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    private void burnSpell(final Player p) {
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 5.0f, 1.0f);
        final Item fire = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.INK_SACK, 1, (short)DyeColor.RED.getDyeData()));
        fire.setVelocity(p.getEyeLocation().getDirection().multiply(1.5));
        fire.setPickupDelay(9999);
        new BukkitRunnable() {
            public void run() {
                if (fire.isOnGround()) {
                    final Location loc = fire.getLocation();
                    Pyromancer.this.targetFound(loc, p);
                    fire.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 1L);
    }
    
    public void targetFound(final Location loc, final Player p) {
        loc.getWorld().playSound(loc, Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 5.0f, 1.0f);
        loc.getWorld().spawnParticle(Particle.FLAME, loc, 500, 2.0, 2.0, 2.0, 0.1);
        for (final Entity e : loc.getWorld().getEntities()) {
            if (e.getLocation().distance(loc) < 4.0 && e.getType().isAlive() && !e.equals(p) && this.noTeamFire(p, e)) {
                final LivingEntity b1 = (LivingEntity)e;
                b1.damage(4.0, (Entity)p);
                b1.setFireTicks(80);
            }
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
