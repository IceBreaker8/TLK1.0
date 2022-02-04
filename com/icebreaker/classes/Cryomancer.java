// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.Particle;
import org.bukkit.util.Vector;
import org.bukkit.Location;
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

public class Cryomancer implements Listener
{
    public HashMap<UUID, Integer> cooldownTime;
    public HashMap<UUID, BukkitRunnable> cooldownTask;
    
    public Cryomancer() {
        this.cooldownTime = new HashMap<UUID, Integer>();
        this.cooldownTask = new HashMap<UUID, BukkitRunnable>();
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
        if (stack.getItemMeta().getDisplayName().contains("Freeze")) {
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            this.freezeSpell(p);
            this.cooldownTime.put(p.getUniqueId(), 40);
            this.cooldownTask.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Cryomancer.this.cooldownTime.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.INK_SACK) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Freeze")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Freeze " + ChatColor.RED + ChatColor.BOLD + Cryomancer.this.cooldownTime.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Cryomancer.this.cooldownTime.put(p.getUniqueId(), Cryomancer.this.cooldownTime.get(p.getUniqueId()) - 1);
                    if (Cryomancer.this.cooldownTime.get(p.getUniqueId()) == -1) {
                        im.setDisplayName(ChatColor.AQUA + "Freeze" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.INK_SACK) && item1.getItemMeta().getDisplayName().contains("Freeze")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Freeze" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                        Cryomancer.this.cooldownTime.remove(p.getUniqueId());
                        Cryomancer.this.cooldownTask.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTask.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    public void freezeSpell(final Player p) {
        final BukkitRunnable task = new BukkitRunnable() {
            double b = 0.0;
            
            public void run() {
                ++this.b;
                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 5.0f, 1.0f);
                if (this.b == 2.0) {
                    this.cancel();
                }
                new BukkitRunnable() {
                    double t = 0.0;
                    Location loc = val$p.getLocation();
                    Vector direction = this.loc.getDirection().normalize().multiply(4.0);
                    
                    public void run() {
                        this.t += 0.5;
                        final double x = this.direction.getX() * this.t;
                        final double y = this.direction.getY() * this.t + 1.5;
                        final double z = this.direction.getZ() * this.t;
                        this.loc.add(x, y, z);
                        p.getWorld().spawnParticle(Particle.SNOWBALL, this.loc, 40);
                        for (final Entity e : this.loc.getWorld().getEntities()) {
                            if (e.getLocation().distance(this.loc) < 2.0 && e != p && e.getType().isAlive() && Cryomancer.this.noTeamFire(p, e)) {
                                final LivingEntity b = (LivingEntity)e;
                                b.damage(1.0, (Entity)p);
                                Cryomancer.this.targetFound((Damageable)b, p);
                                this.cancel();
                            }
                        }
                        this.loc.subtract(x, y, z);
                        if (this.t > 20.0) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer((Plugin)Test.plugin, 0L, 1L);
            }
        };
        task.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    public void targetFound(final Damageable b, final Player p) {
        final Location loc = b.getLocation();
        b.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 5.0f, 1.0f);
        b.getWorld().spawnParticle(Particle.SNOWBALL, loc, 500, 2.0, 2.0, 2.0, 50.0);
        for (final Entity e : loc.getWorld().getEntities()) {
            if (e.getLocation().distance(loc) < 3.0 && e.getType().isAlive() && !e.equals(p)) {
                final LivingEntity b2 = (LivingEntity)e;
                if (!this.noTeamFire(p, (Entity)b2)) {
                    continue;
                }
                b2.damage(1.0, (Entity)p);
                if (ClassesPerms.cryo.contains(b2.getUniqueId())) {
                    continue;
                }
                b2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 4));
                b2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 80, 10));
            }
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
