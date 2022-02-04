// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Particle;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.icebreaker.testing.Test;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Warhead implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Warhead() {
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
        if (!p.isOnGround() && stack.getType() == Material.REDSTONE) {
            p.sendMessage(ChatColor.RED + "You can't use the spell in mid air!");
            return;
        }
        if (!stack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("Shield")) {
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            this.particleSpell(p);
            this.shieldSpell(p);
            this.cooldownTimeP.put(p.getUniqueId(), 40);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Warhead.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.REDSTONE) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Shield")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Shield " + ChatColor.RED + ChatColor.BOLD + Warhead.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Warhead.this.cooldownTimeP.put(p.getUniqueId(), Warhead.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Warhead.this.cooldownTimeP.get(p.getUniqueId()) <= -1) {
                        im.setDisplayName(ChatColor.AQUA + "Shield" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.REDSTONE) && item1.getItemMeta().getDisplayName().contains("Shield")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Shield" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                        Warhead.this.cooldownTimeP.remove(p.getUniqueId());
                        Warhead.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    private void particleSpell(final Player p) {
        final Location loc = p.getLocation();
        new BukkitRunnable() {
            double phi = 0.0;
            
            public void run() {
                this.phi += 0.15707963267948966;
                for (double theta = 0.0; theta <= 6.283185307179586; theta += 0.05235987755982988) {
                    final double r = 4.0;
                    final double x = r * Math.cos(theta) * Math.sin(this.phi);
                    final double y = r * Math.cos(this.phi);
                    final double z = r * Math.sin(theta) * Math.sin(this.phi);
                    loc.add(x, y, z);
                    loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 2, 0.0, 0.0, 0.0, 1.0E-4);
                    loc.subtract(x, y, z);
                }
                if (this.phi > 18.84955592153876) {
                    ClassesPerms.KB.remove(p.getUniqueId());
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 1L);
    }
    
    private void shieldSpell(final Player p) {
        ClassesPerms.cantmove.add(p.getUniqueId());
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 180));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, -128));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 180));
        ClassesPerms.KB.add(p.getUniqueId());
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0f, 1.0f);
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                this.t += 0.5;
                for (final Entity e : p.getLocation().getWorld().getEntities()) {
                    if (e.getLocation().distance(p.getLocation()) < 5.0 && e != p && e instanceof Player && Warhead.this.noTeamFire(p, e)) {
                        final LivingEntity d = (LivingEntity)e;
                        final Vector direction = d.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
                        direction.setX(direction.getX() * 2.0);
                        direction.setY(direction.getY() * 1.0 + 1.0);
                        direction.setZ(direction.getZ() * 2.0);
                        d.setVelocity(direction);
                        d.getLocation().getWorld().playSound(d.getLocation(), Sound.BLOCK_ANVIL_PLACE, 5.0f, 1.0f);
                    }
                }
                if (this.t >= 6.0) {
                    ClassesPerms.KB.remove(p.getUniqueId());
                    ClassesPerms.cantmove.remove(p.getUniqueId());
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER && ClassesPerms.KB.contains(e.getEntity().getUniqueId())) {
            e.setCancelled(true);
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
