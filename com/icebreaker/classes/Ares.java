// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.entity.FallingBlock;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.Particle;
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

public class Ares implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Ares() {
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
        if (stack.getItemMeta().getDisplayName().contains("Chaos")) {
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            ClassesPerms.nofalldmg.add(p.getUniqueId());
            this.chaosSpell(p);
            this.cooldownTimeP.put(p.getUniqueId(), 40);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Ares.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.FLINT) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Chaos")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Chaos " + ChatColor.RED + ChatColor.BOLD + Ares.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Ares.this.cooldownTimeP.put(p.getUniqueId(), Ares.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Ares.this.cooldownTimeP.get(p.getUniqueId()) <= -1) {
                        im.setDisplayName(ChatColor.AQUA + "Chaos" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.FLINT) && item1.getItemMeta().getDisplayName().contains("Chaos")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Chaos" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                Ares.this.cooldownTimeP.remove(p.getUniqueId());
                                break;
                            }
                        }
                        Ares.this.cooldownTimeP.remove(p.getUniqueId());
                        Ares.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    private void chaosSpell(final Player p) {
        new BukkitRunnable() {
            public void run() {
                p.getLocation().getWorld().spawnParticle(Particle.FALLING_DUST, p.getLocation(), 100, 0.5, 0.5, 0.5, 0.001);
                if (p.isOnGround()) {
                    final Location loc = p.getLocation();
                    for (final Entity e : loc.getWorld().getEntities()) {
                        if (e.getLocation().distance(loc) < 6.0 && e.getType().isAlive() && !e.equals(p) && Ares.this.noTeamFire(p, e)) {
                            final LivingEntity b1 = (LivingEntity)e;
                            b1.damage(6.0, (Entity)p);
                            b1.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 1));
                            final Vector direction = b1.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
                            direction.setX(direction.getX() * 0.7);
                            direction.setY(direction.getY() * 0.7 + 0.5);
                            direction.setZ(direction.getZ() * 0.7);
                            b1.setVelocity(direction);
                        }
                    }
                    p.getLocation().getWorld().playSound(loc, Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 5.0f, 1.0f);
                    final FallingBlock b2 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b3 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b4 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b5 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b6 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b7 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b8 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    final FallingBlock b9 = p.getWorld().spawnFallingBlock(loc, Material.OBSIDIAN, (byte)0);
                    b2.setDropItem(false);
                    b3.setDropItem(false);
                    b4.setDropItem(false);
                    b5.setDropItem(false);
                    b6.setDropItem(false);
                    b7.setDropItem(false);
                    b8.setDropItem(false);
                    b9.setDropItem(false);
                    b2.setVelocity(new Vector(0.0, 0.5, 0.25));
                    b3.setVelocity(new Vector(0.25, 0.5, 0.0));
                    b4.setVelocity(new Vector(0.25, 0.5, 0.25));
                    b5.setVelocity(new Vector(0.0, 0.5, -0.25));
                    b6.setVelocity(new Vector(-0.25, 0.5, 0.0));
                    b7.setVelocity(new Vector(-0.25, 0.5, -0.25));
                    b8.setVelocity(new Vector(-0.25, 0.5, 0.25));
                    b9.setVelocity(new Vector(0.25, 0.5, -0.25));
                    new BukkitRunnable() {
                        public void run() {
                            ClassesPerms.nofalldmg.remove(p.getUniqueId());
                        }
                    }.runTaskLater((Plugin)Test.plugin, 20L);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 2L);
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_SHOOT, 5.0f, 1.0f);
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 4));
        p.setVelocity(new Vector(0, 1, 0));
        new BukkitRunnable() {
            public void run() {
                p.setVelocity(p.getLocation().getDirection().multiply(2.0f).add(new Vector(0.0, 0.25, 0.0)));
                p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 5.0f, 1.0f);
            }
        }.runTaskLater((Plugin)Test.plugin, 10L);
    }
    
    @EventHandler
    public void onFallingBlockLand(final EntityChangeBlockEvent e) {
        e.setCancelled(true);
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
