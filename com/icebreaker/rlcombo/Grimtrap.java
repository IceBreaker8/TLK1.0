// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.rlcombo;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import com.icebreaker.mech2.PoseidonSpell;
import org.bukkit.util.Vector;
import com.icebreaker.mechanics.RespawnMech;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.icebreaker.testing.Test;
import java.util.Iterator;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import net.md_5.bungee.api.ChatColor;
import com.icebreaker.mechanics.ActionBar;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Grimtrap implements Listener
{
    public HashMap<UUID, Integer> clicks;
    
    public Grimtrap() {
        this.clicks = new HashMap<UUID, Integer>();
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
        if (!stack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("25ml98de52")) {
            e.setCancelled(true);
            if (this.clicks.get(p.getUniqueId()) == null) {
                this.clicks.put(p.getUniqueId(), 1);
            }
            final ActionBar a = new ActionBar();
            if (this.clicks.get(p.getUniqueId()) == 1) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "- ? - ?");
                p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), 2);
                return;
            }
            if (this.clicks.get(p.getUniqueId()) == 2) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Right " + ChatColor.GRAY + "- ?");
                p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), 4);
                return;
            }
            if (this.clicks.get(p.getUniqueId()) == 4) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Right");
                p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), null);
                if (p.getFoodLevel() < 12) {
                    p.sendMessage(ChatColor.RED + "You don't have enough mana to cast this spell!");
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10.0f, 1.0f);
                    return;
                }
                p.setFoodLevel(p.getFoodLevel() - 12);
                this.dashes(p);
            }
            else if (this.clicks.get(p.getUniqueId()) == 3) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Left " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Right");
                p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), null);
                this.particleSpell(p);
                p.setFoodLevel(p.getFoodLevel() - 14);
            }
        }
    }
    
    private void particleSpell(final Player p) {
        final Location loc = p.getLocation();
        loc.getWorld().playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 10.0f, 1.0f);
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
                    loc.getWorld().spawnParticle(Particle.WATER_WAKE, loc, 4, 0.0, 0.0, 0.0, 1.0E-4);
                    loc.subtract(x, y, z);
                }
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getLocation().distance(loc) < 5.0) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 1));
                    }
                }
                if (this.phi > 18.84955592153876) {
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 1L);
    }
    
    public void dashes(final Player p) {
        ClassesPerms.nofalldmg.add(p.getUniqueId());
        final RespawnMech r = new RespawnMech();
        p.setVelocity(new Vector(0, 1, 0));
        r.guardianDeath(p);
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                if (p.isOnGround()) {
                    ++this.t;
                    p.setVelocity(p.getLocation().getDirection().add(new Vector(0.0, 0.5, 0.0)).multiply(1.5f));
                    r.guardianDeath(p);
                }
                if (p.isOnGround() && this.t == 2.0) {
                    r.guardianDeath(p);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 5L, 2L);
    }
    
    @EventHandler
    public void ons(final PlayerInteractEvent e) {
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
        if (action != Action.LEFT_CLICK_AIR && action != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (!stack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("25ml98de52")) {
            e.setCancelled(true);
            if (this.clicks.get(p.getUniqueId()) == null) {
                this.clicks.put(p.getUniqueId(), 1);
            }
            final ActionBar a = new ActionBar();
            if (this.clicks.get(p.getUniqueId()) == 2) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Left " + ChatColor.GRAY + "- ?");
                p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), 3);
                return;
            }
            if (this.clicks.get(p.getUniqueId()) == 3) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Left " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Left");
                p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), null);
                final PoseidonSpell pos = new PoseidonSpell();
                pos.poseidonSpell(p);
                p.setFoodLevel(p.getFoodLevel() - 10);
                return;
            }
            if (this.clicks.get(p.getUniqueId()) == 4) {
                a.send((Entity)p, ChatColor.GREEN + "Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Right " + ChatColor.GRAY + "-" + ChatColor.GREEN + " Left");
                p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 10.0f, 1.0f);
                this.clicks.put(p.getUniqueId(), null);
                this.freezeSpell(p);
                p.setFoodLevel(p.getFoodLevel() - 14);
            }
        }
    }
    
    public void freezeSpell(final Player p) {
        final BukkitRunnable task = new BukkitRunnable() {
            double b = 0.0;
            
            public void run() {
                ++this.b;
                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 5.0f, 1.0f);
                if (this.b == 3.0) {
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
                            if (e.getLocation().distance(this.loc) < 2.0 && e != p && e.getType().isAlive()) {
                                final LivingEntity b = (LivingEntity)e;
                                b.damage(1.0, (Entity)p);
                                Grimtrap.this.targetFound((Damageable)b, p);
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
                b2.damage(1.0, (Entity)p);
                if (ClassesPerms.cryo.contains(b2.getUniqueId())) {
                    continue;
                }
                b2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 4));
                b2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 80, 10));
            }
        }
    }
}
