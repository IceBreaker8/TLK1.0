// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.classes;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.ChatColor;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class AcrobatClass implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeA;
    public HashMap<UUID, BukkitRunnable> cooldownTaskA;
    
    public AcrobatClass() {
        this.cooldownTimeA = new HashMap<UUID, Integer>();
        this.cooldownTaskA = new HashMap<UUID, BukkitRunnable>();
    }
    
    @EventHandler
    public void onFlightToogle(final PlayerToggleFlightEvent e) {
        final Player p = e.getPlayer();
        if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
            p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
            return;
        }
        if (ClassesPerms.acrobat.contains(e.getPlayer().getUniqueId()) && !this.cooldownTaskA.containsKey(p.getUniqueId())) {
            final GameMode gamemode = p.getGameMode();
            if (gamemode != GameMode.CREATIVE) {
                new BukkitRunnable() {
                    public void run() {
                        final Location loc = p.getLocation();
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.0));
                        e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0, e.getPlayer().getVelocity().getZ()));
                        p.playSound(loc, Sound.ENTITY_GHAST_SHOOT, 5.0f, 1.0f);
                        ClassesPerms.acrobat.remove(e.getPlayer().getUniqueId());
                        AcrobatClass.this.cooldownTimeA.put(p.getUniqueId(), 20);
                        AcrobatClass.this.cooldownTaskA.put(p.getUniqueId(), new BukkitRunnable() {
                            public void run() {
                                AcrobatClass.this.cooldownTimeA.put(p.getUniqueId(), AcrobatClass.this.cooldownTimeA.get(p.getUniqueId()) - 1);
                                if (AcrobatClass.this.cooldownTimeA.get(p.getUniqueId()) == -1) {
                                    if (ClassesPerms.nofalldmg.contains(e.getPlayer().getUniqueId())) {
                                        final Location loc2 = p.getLocation();
                                        ClassesPerms.acrobat.add(e.getPlayer().getUniqueId());
                                        p.playSound(loc2, Sound.ENTITY_WITHER_SHOOT, 0.1f, 1.0f);
                                    }
                                    AcrobatClass.this.cooldownTimeA.remove(p.getUniqueId());
                                    AcrobatClass.this.cooldownTaskA.remove(p.getUniqueId());
                                    this.cancel();
                                }
                            }
                        });
                        AcrobatClass.this.cooldownTaskA.get(p.getUniqueId()).runTaskTimer((Plugin)Test.plugin, 0L, 20L);
                    }
                }.runTaskLater((Plugin)Test.plugin, 1L);
            }
        }
    }
    
    @EventHandler
    public void onJump(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (ClassesPerms.acrobat.contains(e.getPlayer().getUniqueId()) && !this.cooldownTaskA.containsKey(p.getUniqueId())) {
            final GameMode gamemode = p.getGameMode();
            if (e.getFrom().getY() < e.getTo().getY() && e.getPlayer().getLocation().subtract(0.0, 0.25, 0.0).getBlock().getType() != Material.AIR && gamemode != GameMode.CREATIVE) {
                p.setAllowFlight(true);
            }
        }
    }
    
    @EventHandler
    public void onCancelFallDamage(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player damaged = (Player)e.getEntity();
        if (damaged instanceof Player && ClassesPerms.nofalldmg.contains(damaged.getUniqueId()) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }
}
