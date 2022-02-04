// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Sound;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.Listener;

public class GuardianMech implements Listener
{
    @EventHandler
    public void guardianDeath(final EntityDeathEvent e) {
        final LivingEntity entity = e.getEntity();
        if (!(entity.getKiller() instanceof Player)) {
            return;
        }
        if (entity.getType() == EntityType.ELDER_GUARDIAN) {
            if (ClassesPerms.redteam.contains(entity.getKiller().getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Shamans have killed the ").append(ChatColor.AQUA).append(ChatColor.BOLD).append("Guardian Of The Sea").toString());
                final ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
                final ItemMeta im = item.getItemMeta();
                im.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Poseidon Key").toString());
                im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                final ArrayList<String> lore = new ArrayList<String>();
                lore.add(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.MAGIC).append(ChatColor.BOLD).append("dfgtebdszd").toString());
                im.setLore((List)lore);
                item.setItemMeta(im);
                entity.getKiller().getInventory().addItem(new ItemStack[] { item });
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 10.0f, 1.0f);
                }
            }
            if (ClassesPerms.blueteam.contains(entity.getKiller().getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans have killed the ").append(ChatColor.AQUA).append(ChatColor.BOLD).append("Guardian Of The Sea").toString());
                final ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
                final ItemMeta im = item.getItemMeta();
                im.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Poseidon Key").toString());
                im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                final ArrayList<String> lore = new ArrayList<String>();
                lore.add(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.MAGIC).append(ChatColor.BOLD).append("dfgtebdszd").toString());
                im.setLore((List)lore);
                item.setItemMeta(im);
                entity.getKiller().getInventory().addItem(new ItemStack[] { item });
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 10.0f, 1.0f);
                }
            }
        }
    }
    
    @EventHandler
    public void keyDrop(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final Item item = e.getItemDrop();
        if (item.getItemStack().getType().equals((Object)Material.TRIPWIRE_HOOK)) {
            this.searchForLocation(p, item);
        }
    }
    
    private void searchForLocation(final Player p, final Item item) {
        new BukkitRunnable() {
            public void run() {
                final World w = Bukkit.getServer().getWorld("world");
                final Location loc = new Location(w, -192.0, 27.0, 198.0);
                for (final Entity e : loc.getWorld().getEntities()) {
                    if (e.getLocation().distance(loc) < 1.5 && e.equals(item)) {
                        item.remove();
                        p.getWorld().strikeLightningEffect(item.getLocation());
                        GuardianMech.this.openDoor();
                    }
                }
            }
        }.runTaskLater((Plugin)Test.plugin, 40L);
    }
    
    private void openDoor() {
        final World w = Bukkit.getWorld("world");
        final Location loc1 = new Location(w, -193.0, 29.0, 198.0);
        final Location loc2 = new Location(w, -193.0, 30.0, 198.0);
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                ++this.t;
                if (this.t == 1.0) {
                    loc1.getBlock().setType(Material.AIR);
                    loc1.getWorld().playSound(loc1, Sound.ITEM_SHIELD_BREAK, 4.0f, 1.0f);
                    loc1.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc1, 50, 1.0, 1.0, 1.0, 0.01);
                }
                if (this.t == 2.0) {
                    loc2.getBlock().setType(Material.AIR);
                    loc2.getWorld().playSound(loc2, Sound.ITEM_SHIELD_BREAK, 4.0f, 1.0f);
                    loc2.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc2, 50, 1.0, 1.0, 1.0, 0.01);
                }
                if (this.t == 16.0) {
                    loc1.getBlock().setType(Material.PRISMARINE);
                    loc2.getBlock().setType(Material.PRISMARINE);
                    loc1.getWorld().playSound(loc1, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 4.0f, 1.0f);
                    loc2.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc2, 100, 2.0, 2.0, 2.0, 0.01);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 20L, 20L);
    }
}
