// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.util.Vector;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Entity;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class BonFireSystem implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public BonFireSystem() {
        this.cooldownTimeP = new HashMap<UUID, Integer>();
        this.cooldownTaskP = new HashMap<UUID, BukkitRunnable>();
    }
    
    public boolean playerIsNearbyToSmelter(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, 144.0, 73.0, -10.0);
        return p.getLocation().distance(loc) < 10.0;
    }
    
    public void bonfireSH() {
        final World w = Bukkit.getServer().getWorld("world");
        new BukkitRunnable() {
            Location loc = new Location(val$w, -150.0, 68.5, -28.0);
            double coalS = 0.0;
            
            public void run() {
                for (final Entity e : this.loc.getWorld().getEntities()) {
                    if (e.getLocation().distance(this.loc) < 1.2 && e instanceof Item && ((Item)e).getItemStack().getType().equals((Object)Material.COAL)) {
                        e.remove();
                        ++this.coalS;
                    }
                }
                if (this.coalS == 6.0) {
                    final Location loc = new Location(w, -150.0, 69.0, -28.0);
                    new BukkitRunnable() {
                        public void run() {
                            loc.getBlock().setType(Material.FIRE);
                        }
                    }.runTaskTimer((Plugin)Test.plugin, 0L, 8L);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    public boolean playerIsNearby1(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, 120.0, 63.0, 101.0);
        return p.getLocation().distance(loc) < 3.0;
    }
    
    public boolean playerIsNearby2(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, -150.0, 69.0, -28.0);
        return p.getLocation().distance(loc) < 3.0;
    }
    
    public void bonfire() {
        final World w = Bukkit.getServer().getWorld("world");
        new BukkitRunnable() {
            Location loc = new Location(val$w, 120.0, 62.5, 101.0);
            double coal = 0.0;
            
            public void run() {
                for (final Entity e : this.loc.getWorld().getEntities()) {
                    if (e.getLocation().distance(this.loc) < 1.2 && e instanceof Item && ((Item)e).getItemStack().getType().equals((Object)Material.COAL)) {
                        e.remove();
                        ++this.coal;
                    }
                }
                if (this.coal == 6.0) {
                    final Location loc = new Location(w, 120.0, 63.0, 101.0);
                    new BukkitRunnable() {
                        public void run() {
                            loc.getBlock().setType(Material.FIRE);
                        }
                    }.runTaskTimer((Plugin)Test.plugin, 0L, 8L);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    @EventHandler
    public void cookFish(final PlayerInteractEvent e) {
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
        if (stack.getType().equals((Object)Material.RAW_FISH) && this.cooldownTaskP.containsKey(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You can't throw fish rapidly!");
            return;
        }
        if (stack.getType().equals((Object)Material.RAW_FISH) && !this.cooldownTaskP.containsKey(p.getUniqueId())) {
            e.setCancelled(true);
            final Item fish = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.RAW_FISH, 1));
            fish.setVelocity(p.getEyeLocation().getDirection().multiply(0.3f));
            fish.setPickupDelay(60);
            this.cookingTimer(p, fish);
            ItemStack[] contents;
            for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                final ItemStack item2 = contents[i];
                if (item2 != null && item2.getType().equals((Object)Material.RAW_FISH)) {
                    item2.setAmount(item2.getAmount() - 1);
                }
            }
            this.cooldownTimeP.put(p.getUniqueId(), 3);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    BonFireSystem.this.cooldownTimeP.get(p.getUniqueId());
                    BonFireSystem.this.cooldownTimeP.put(p.getUniqueId(), BonFireSystem.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (BonFireSystem.this.cooldownTimeP.get(p.getUniqueId()) == -1) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                        BonFireSystem.this.cooldownTimeP.remove(p.getUniqueId());
                        BonFireSystem.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    public void cookingTimer(final Player p, final Item fish) {
        new BukkitRunnable() {
            public void run() {
                if (fish.getFireTicks() > 0) {
                    this.cancel();
                    BonFireSystem.this.cookingAnimation(p, fish);
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 5L);
    }
    
    private void cookingAnimation(final Player p, final Item fish) {
        final Location loc = fish.getLocation();
        fish.remove();
        final Item animation = loc.getWorld().dropItem(loc, new ItemStack(Material.RAW_FISH, 1));
        animation.setInvulnerable(true);
        animation.setPickupDelay(1980);
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                ++this.t;
                animation.setVelocity(new Vector(0.0, 0.5, 0.0));
                if (this.t == 2.0) {
                    animation.remove();
                    if (BonFireSystem.this.playerIsNearby1(p) || BonFireSystem.this.playerIsNearby2(p)) {
                        p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.COOKED_FISH, 1) });
                        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
                    }
                    else if (BonFireSystem.this.playerIsNearbyToSmelter(p)) {
                        p.sendMessage(ChatColor.RED + "Are you trying to cook a fish using a smelter?.. ");
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "Your fish got burnt as you were too far to pick it up... ");
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 40L);
    }
    
    @EventHandler
    public void brokenFurnace(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Action action = e.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals((Object)Material.FURNACE)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "This furnace seems broken, I need to find another way to cook..");
            return;
        }
        if (action == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals((Object)Material.ENCHANTMENT_TABLE)) {
            e.setCancelled(true);
            final EnchRecipes ench = new EnchRecipes();
            ench.recipesPage1(p);
            return;
        }
        if (action == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals((Object)Material.CHEST)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "I can't open this chest, something is blocking it..");
        }
    }
}
