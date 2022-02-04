// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import java.util.Map;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import java.util.Iterator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Entity;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class SmelterMech implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public SmelterMech() {
        this.cooldownTimeP = new HashMap<UUID, Integer>();
        this.cooldownTaskP = new HashMap<UUID, BukkitRunnable>();
    }
    
    public void smelter() {
        final World w = Bukkit.getServer().getWorld("world");
        new BukkitRunnable() {
            Location loc = new Location(val$w, 60.0, 94.5, -80.0);
            
            public void run() {
                for (final Entity e : this.loc.getWorld().getEntities()) {
                    if (e.getLocation().distance(this.loc) < 1.2 && e instanceof Item) {
                        e.remove();
                        if (!((Item)e).getItemStack().getType().equals((Object)Material.COAL_BLOCK)) {
                            continue;
                        }
                        e.remove();
                        final Location loc = new Location(w, 68.0, 106.0, -86.0);
                        loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_USE, 60.0f, 1.0f);
                        Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.DARK_AQUA).append("The").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" Ancient Smelter").append(ChatColor.DARK_AQUA).append(" got activated!").toString());
                        loc.getBlock().setType(Material.LAVA);
                        this.cancel();
                    }
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    @EventHandler
    public void smeltOres(final PlayerInteractEvent e) {
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
        if ((stack.getType().equals((Object)Material.COAL_ORE) || stack.getType().equals((Object)Material.DIAMOND_ORE) || stack.getType().equals((Object)Material.GOLD_ORE) || stack.getType().equals((Object)Material.IRON_ORE)) && this.cooldownTaskP.containsKey(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You can't throw ores rapidly!");
            return;
        }
        if (stack.getType().equals((Object)Material.COAL_ORE) || stack.getType().equals((Object)Material.DIAMOND_ORE) || stack.getType().equals((Object)Material.GOLD_ORE) || stack.getType().equals((Object)Material.IRON_ORE)) {
            e.setCancelled(true);
            final ItemStack ore = p.getInventory().getItemInMainHand();
            if (ore.getType().equals((Object)Material.DIAMOND_ORE)) {
                final Item thrown = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.DIAMOND_ORE, 1));
                thrown.setVelocity(p.getEyeLocation().getDirection().multiply(0.5f));
                thrown.setPickupDelay(60);
                thrown.setInvulnerable(true);
                new BukkitRunnable() {
                    public void run() {
                        if (thrown.getFireTicks() > 0 && SmelterMech.this.playerIsNearby(p)) {
                            thrown.remove();
                            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 1) });
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0f, 1.0f);
                        }
                    }
                }.runTaskLater((Plugin)Test.plugin, 40L);
            }
            if (ore.getType().equals((Object)Material.IRON_ORE)) {
                final Item thrown = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.IRON_ORE, 1));
                thrown.setVelocity(p.getEyeLocation().getDirection().multiply(0.5f));
                thrown.setPickupDelay(60);
                thrown.setInvulnerable(true);
                new BukkitRunnable() {
                    public void run() {
                        if (thrown.getFireTicks() > 0 && SmelterMech.this.playerIsNearby(p)) {
                            thrown.remove();
                            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0f, 1.0f);
                        }
                    }
                }.runTaskLater((Plugin)Test.plugin, 40L);
            }
            if (ore.getType().equals((Object)Material.COAL_ORE)) {
                final Item thrown = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.COAL_ORE, 1));
                thrown.setVelocity(p.getEyeLocation().getDirection().multiply(0.5f));
                thrown.setPickupDelay(80);
                thrown.setInvulnerable(true);
                new BukkitRunnable() {
                    public void run() {
                        if (thrown.getFireTicks() > 0 && SmelterMech.this.playerIsNearby(p)) {
                            thrown.remove();
                            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.COAL, 1) });
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0f, 1.0f);
                        }
                    }
                }.runTaskLater((Plugin)Test.plugin, 40L);
            }
            if (ore.getType().equals((Object)Material.GOLD_ORE)) {
                final Item thrown = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.GOLD_ORE, 1));
                thrown.setVelocity(p.getEyeLocation().getDirection().multiply(0.5f));
                thrown.setPickupDelay(60);
                thrown.setInvulnerable(true);
                new BukkitRunnable() {
                    public void run() {
                        if (thrown.getFireTicks() > 0 && SmelterMech.this.playerIsNearby(p)) {
                            thrown.remove();
                            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0f, 1.0f);
                        }
                    }
                }.runTaskLater((Plugin)Test.plugin, 40L);
            }
            if (ore.getType().equals((Object)Material.DIAMOND_ORE)) {
                this.consumeItem(p, 1, Material.DIAMOND_ORE);
            }
            if (ore.getType().equals((Object)Material.IRON_ORE)) {
                this.consumeItem(p, 1, Material.IRON_ORE);
            }
            if (ore.getType().equals((Object)Material.COAL_ORE)) {
                this.consumeItem(p, 1, Material.COAL_ORE);
            }
            if (ore.getType().equals((Object)Material.GOLD_ORE)) {
                this.consumeItem(p, 1, Material.GOLD_ORE);
            }
            this.cooldownTimeP.put(p.getUniqueId(), 3);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    SmelterMech.this.cooldownTimeP.get(p.getUniqueId());
                    SmelterMech.this.cooldownTimeP.put(p.getUniqueId(), SmelterMech.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (SmelterMech.this.cooldownTimeP.get(p.getUniqueId()) == -1) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                        SmelterMech.this.cooldownTimeP.remove(p.getUniqueId());
                        SmelterMech.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    public boolean playerIsNearby(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, 63.0, 95.0, -78.0);
        return p.getLocation().distance(loc) < 3.0;
    }
    
    public boolean consumeItem(final Player player, int count, final Material mat) {
        final Map<Integer, ? extends ItemStack> ammo = (Map<Integer, ? extends ItemStack>)player.getInventory().all(mat);
        int found = 0;
        for (final ItemStack stack : ammo.values()) {
            found += stack.getAmount();
        }
        if (count > found) {
            return false;
        }
        for (final Integer index : ammo.keySet()) {
            final ItemStack stack2 = (ItemStack)ammo.get(index);
            final int removed = Math.min(count, stack2.getAmount());
            count -= removed;
            if (stack2.getAmount() == removed) {
                player.getInventory().setItem((int)index, (ItemStack)null);
            }
            else {
                stack2.setAmount(stack2.getAmount() - removed);
            }
            if (count <= 0) {
                break;
            }
        }
        player.updateInventory();
        return true;
    }
}
