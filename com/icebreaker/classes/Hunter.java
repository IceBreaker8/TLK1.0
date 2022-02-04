// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Sound;
import com.icebreaker.mech2.PerksXp;
import org.bukkit.entity.LivingEntity;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Entity;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.Location;
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

public class Hunter implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Hunter() {
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
        if (action != Action.RIGHT_CLICK_BLOCK) {
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
        if (stack.getItemMeta().getDisplayName().contains("Trap")) {
            if (!e.getClickedBlock().getType().isSolid()) {
                return;
            }
            if (e.getClickedBlock().getLocation().getY() > p.getLocation().getY()) {
                p.sendMessage(ChatColor.RED + "You can't place traps at that high!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.LOG)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.LOG_2)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.ANVIL)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.LEAVES)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.LEAVES_2)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.GRAVEL)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.COAL_ORE)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.DIAMOND_ORE)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.GOLD_ORE)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.FURNACE)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.WORKBENCH)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.WOOL)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.FENCE)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.LADDER)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.FENCE_GATE)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.ARMOR_STAND)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.WEB)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.COBBLESTONE_STAIRS)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.JUNGLE_WOOD_STAIRS)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.WOOD_STAIRS)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (e.getClickedBlock().getType().equals((Object)Material.DARK_OAK_STAIRS)) {
                p.sendMessage(ChatColor.RED + "You can't place traps here!");
                return;
            }
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            final Location loc1 = e.getClickedBlock().getLocation();
            this.trapSpell(p, loc1, e.getClickedBlock());
            this.cooldownTimeP.put(p.getUniqueId(), 35);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    if (Hunter.this.cooldownTimeP.get(p.getUniqueId()) > 0) {
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                            final ItemStack item1 = contents[i];
                            if (item1 != null && item1.getType().equals((Object)Material.SHULKER_SHELL) && item1.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Trap")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Trap " + ChatColor.RED + ChatColor.BOLD + Hunter.this.cooldownTimeP.get(p.getUniqueId()));
                                item1.setItemMeta(meta1);
                                break;
                            }
                        }
                    }
                    Hunter.this.cooldownTimeP.put(p.getUniqueId(), Hunter.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Hunter.this.cooldownTimeP.get(p.getUniqueId()) <= -1) {
                        im.setDisplayName(ChatColor.AQUA + "Trap" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                        item.setItemMeta(im);
                        p.updateInventory();
                        ItemStack[] contents2;
                        for (int length2 = (contents2 = p.getInventory().getContents()).length, j = 0; j < length2; ++j) {
                            final ItemStack item1 = contents2[j];
                            if (item1 != null && item1.getType().equals((Object)Material.SHULKER_SHELL) && item1.getItemMeta().getDisplayName().contains("Trap")) {
                                final ItemMeta meta1 = item1.getItemMeta();
                                final ArrayList<String> lore1 = new ArrayList<String>();
                                lore1.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").toString());
                                meta1.setLore((List)lore1);
                                meta1.setDisplayName(ChatColor.AQUA + "Trap" + ChatColor.GREEN + ChatColor.BOLD + " READY");
                                item1.setItemMeta(meta1);
                                Hunter.this.cooldownTimeP.remove(p.getUniqueId());
                                break;
                            }
                        }
                        Hunter.this.cooldownTimeP.remove(p.getUniqueId());
                        Hunter.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
        }
    }
    
    @EventHandler
    public void armorstand(final PlayerArmorStandManipulateEvent e) {
        e.setCancelled(true);
    }
    
    private void trapSpell(final Player p, final Location loc, final Block block) {
        final World w = Bukkit.getWorld("world");
        final ArmorStand z = (ArmorStand)w.spawnEntity(loc.add(0.5, -1.0, 0.5), EntityType.ARMOR_STAND);
        p.sendMessage(ChatColor.AQUA + "You placed a trap at " + z.getLocation().getX() + " " + z.getLocation().getY() + " " + z.getLocation().getZ());
        z.setCustomNameVisible(false);
        z.setGravity(false);
        z.setCanPickupItems(false);
        z.setCollidable(false);
        z.setVisible(false);
        z.setInvulnerable(true);
        final Location locA = z.getLocation().add(0.0, 1.0, 0.0);
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                ++this.t;
                for (final Entity e : locA.getWorld().getEntities()) {
                    if (e.getLocation().distance(locA) < 1.5 && e != p && e instanceof Player && ((HumanEntity)e).getGameMode().equals((Object)GameMode.SURVIVAL) && Hunter.this.noTeamFire(p, e)) {
                        final LivingEntity d = (LivingEntity)e;
                        final PerksXp xp = new PerksXp();
                        if (d.getHealth() <= 6.0) {
                            p.sendMessage(ChatColor.WHITE + "+ 1 " + ChatColor.YELLOW + "soul.");
                            xp.setSouls(p, 1);
                            if (ClassesPerms.redteam.contains(d.getUniqueId())) {
                                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("HUMAN ").append(ChatColor.AQUA).append(p.getName()).append(ChatColor.WHITE).append(" eliminated ").append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("SHAMAN ").append(ChatColor.GREEN).append(d.getName()).append(ChatColor.WHITE).append(" using a ").append(ChatColor.AQUA).append(ChatColor.BOLD).append("Trap!").toString());
                            }
                            else if (ClassesPerms.blueteam.contains(d.getUniqueId())) {
                                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("SHAMAN ").append(ChatColor.GREEN).append(p.getName()).append(ChatColor.WHITE).append(" eliminated ").append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("HUMAN ").append(ChatColor.AQUA).append(d.getName()).append(ChatColor.WHITE).append(" using a ").append(ChatColor.AQUA).append(ChatColor.BOLD).append("Trap!").toString());
                            }
                        }
                        d.damage(6.0);
                        p.sendMessage(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("TRAP TRIGGERED!").toString());
                        p.playSound(d.getLocation(), Sound.ENTITY_EVOCATION_ILLAGER_HURT, 10.0f, 1.0f);
                        d.sendMessage(ChatColor.DARK_RED + "You have been trapped!");
                        ClassesPerms.cantmove.add(d.getUniqueId());
                        d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 125));
                        d.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, -125));
                        z.remove();
                        z.getLocation().getWorld().playSound(d.getLocation(), Sound.ENTITY_EVOCATION_FANGS_ATTACK, 10.0f, 1.0f);
                        d.setGlowing(true);
                        final EvokerFangs ev = (EvokerFangs)w.spawnEntity(loc.add(0.0, 1.0, 0.0), EntityType.EVOKER_FANGS);
                        ev.setGravity(false);
                        new BukkitRunnable() {
                            public void run() {
                                d.setGlowing(false);
                                ClassesPerms.cantmove.remove(d.getUniqueId());
                            }
                        }.runTaskLater((Plugin)Test.plugin, 80L);
                        this.cancel();
                    }
                }
                if (this.t == 120.0) {
                    this.cancel();
                    z.remove();
                    p.sendMessage(ChatColor.RED + "Trap was ruined, You need to place a new one!");
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 5L);
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void playerSprintCancel(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (ClassesPerms.cantmove.contains(p.getUniqueId()) && p.isSprinting()) {
            e.setCancelled(true);
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
