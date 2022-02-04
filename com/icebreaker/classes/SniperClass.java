// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.Particle;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.entity.Snowball;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.icebreaker.testing.Test;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class SniperClass implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public SniperClass() {
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
        if (stack.getItemMeta().getDisplayName().contains("Snipe") && p.isSneaking()) {
            if (ClassesPerms.mask.contains(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You can't snipe while using the tribal mask!");
                return;
            }
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            p.setSneaking(false);
            p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
            this.snipeSpell(p);
            this.cooldownTimeP.put(p.getUniqueId(), 20);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (SniperClass.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.GHAST_TEAR) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Snipe")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Snipe " + ChatColor.RED + ChatColor.BOLD + SniperClass.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    SniperClass.this.cooldownTimeP.put(p.getUniqueId(), SniperClass.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (SniperClass.this.cooldownTimeP.get(p.getUniqueId()) == -1) {
                        im.setDisplayName(ChatColor.AQUA + "Snipe" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.GHAST_TEAR) && item1.getItemMeta().getDisplayName().contains("Snipe")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Snipe" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                        SniperClass.this.cooldownTimeP.remove(p.getUniqueId());
                        SniperClass.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    private void snipeSpell(final Player p) {
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_IRONGOLEM_DEATH, 4.0f, 1.0f);
        final Snowball s = (Snowball)p.launchProjectile((Class)Snowball.class);
        if (ClassesPerms.redteam.contains(p.getUniqueId())) {
            ClassesPerms.redteam.add(s.getUniqueId());
        }
        else if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
            ClassesPerms.blueteam.add(s.getUniqueId());
        }
        s.setVelocity(p.getEyeLocation().getDirection().multiply(100.0));
        new BukkitRunnable(p) {
            double t = 0.0;
            Location loc = player.getLocation();
            Vector direction = this.loc.getDirection().normalize().multiply(10.0);
            
            public void run() {
                this.t += 0.5;
                final double x = this.direction.getX() * this.t;
                final double y = this.direction.getY() * this.t + 1.5;
                final double z = this.direction.getZ() * this.t;
                this.loc.add(x, y, z);
                this.loc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, this.loc, 100, 0.0, 0.0, 0.0, 1.0E-4);
                this.loc.subtract(x, y, z);
                if (this.t > 20.0) {
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 1L);
    }
    
    @EventHandler
    private void playerZoom(final PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();
        if (!ClassesPerms.sniper.contains(p.getUniqueId())) {
            return;
        }
        final ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);
        final ItemMeta im = item.getItemMeta();
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        p.getInventory().setHelmet(item);
        new BukkitRunnable() {
            public void run() {
                if (p.getInventory().getItemInMainHand().getType().equals((Object)Material.GHAST_TEAR) && ClassesPerms.sniper.contains(p.getUniqueId()) && p.isSneaking()) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 6));
                    p.getInventory().setHelmet(new ItemStack(Material.PUMPKIN, 1));
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 2L);
    }
    
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event != null && event.getDamager().getType() == EntityType.SNOWBALL && this.noTeamFire(event.getDamager(), event.getEntity())) {
            event.setDamage(10.0);
        }
    }
    
    public boolean noTeamFire(final Entity entity, final Entity e) {
        return (!ClassesPerms.redteam.contains(entity.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(entity.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
