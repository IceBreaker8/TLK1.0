// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import java.util.Random;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.Listener;

public class OreRespawn implements Listener
{
    @EventHandler
    public void onPlayerPlaceBlock(final BlockPlaceEvent event) {
        final Player p = event.getPlayer();
        if (!ClassesPerms.build.contains(p.getUniqueId())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerBreakBlock(final BlockBreakEvent event) {
        final Player p = event.getPlayer();
        final Block b = event.getBlock();
        if (!ClassesPerms.build.contains(p.getUniqueId())) {
            event.setCancelled(true);
        }
        if (b.getType() == Material.LOG && b.getData() == 3 && (p.getInventory().getItemInMainHand().getType().equals((Object)Material.DIAMOND_AXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.WOOD_AXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.GOLD_AXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_AXE))) {
            event.setCancelled(true);
            b.setType(Material.AIR);
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.LOG, 1, (short)3) });
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
            new BukkitRunnable() {
                public void run() {
                    b.setType(Material.LOG);
                    b.setData((byte)3);
                }
            }.runTaskLater((Plugin)Test.plugin, 200L);
        }
        if ((p.getInventory().getItemInMainHand().getType().equals((Object)Material.WOOD_SPADE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.DIAMOND_SPADE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_SPADE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.WOOD_SPADE)) && b.getType().equals((Object)Material.GRAVEL)) {
            event.setCancelled(true);
            b.setType(Material.COBBLESTONE);
            final Random r1 = new Random();
            final int Low = 0;
            final int High = 2;
            final int Result = r1.nextInt(High - Low) + Low;
            final int Result2 = r1.nextInt(High - Low) + Low;
            final int Result3 = r1.nextInt(High - Low) + Low;
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STRING, Result) });
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.FLINT, Result2) });
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.SUGAR_CANE, Result3) });
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
            new BukkitRunnable() {
                public void run() {
                    b.setType(Material.GRAVEL);
                }
            }.runTaskLater((Plugin)Test.plugin, 200L);
        }
        if ((p.getInventory().getItemInMainHand().getType().equals((Object)Material.GOLD_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.DIAMOND_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.STONE_PICKAXE)) && b.getType().equals((Object)Material.IRON_ORE)) {
            event.setCancelled(true);
            b.setType(Material.COBBLESTONE);
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_ORE) });
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
            new BukkitRunnable() {
                public void run() {
                    b.setType(Material.IRON_ORE);
                }
            }.runTaskLater((Plugin)Test.plugin, 200L);
        }
        if ((p.getInventory().getItemInMainHand().getType().equals((Object)Material.GOLD_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.DIAMOND_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.STONE_PICKAXE)) && b.getType().equals((Object)Material.COAL_ORE)) {
            event.setCancelled(true);
            b.setType(Material.COBBLESTONE);
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.COAL) });
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
            new BukkitRunnable() {
                public void run() {
                    b.setType(Material.COAL_ORE);
                }
            }.runTaskLater((Plugin)Test.plugin, 400L);
        }
        if ((p.getInventory().getItemInMainHand().getType().equals((Object)Material.GOLD_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.DIAMOND_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_PICKAXE)) && b.getType().equals((Object)Material.GOLD_ORE)) {
            event.setCancelled(true);
            b.setType(Material.COBBLESTONE);
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_ORE) });
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
            new BukkitRunnable() {
                public void run() {
                    b.setType(Material.GOLD_ORE);
                }
            }.runTaskLater((Plugin)Test.plugin, 400L);
        }
        if (p.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_PICKAXE) && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Ancient Pickaxe") && b.getType().equals((Object)Material.DIAMOND_ORE)) {
            event.setCancelled(true);
            b.setType(Material.COBBLESTONE);
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND) });
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
            new BukkitRunnable() {
                public void run() {
                    b.setType(Material.DIAMOND_ORE);
                }
            }.runTaskLater((Plugin)Test.plugin, 600L);
        }
    }
}
