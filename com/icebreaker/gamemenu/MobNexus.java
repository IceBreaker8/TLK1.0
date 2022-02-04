// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.gamemenu;

import org.bukkit.entity.Chicken;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Material;
import org.bukkit.Sound;
import java.util.Iterator;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.Chunk;
import org.bukkit.World;
import net.md_5.bungee.api.ChatColor;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wither;
import org.bukkit.Location;
import org.bukkit.Bukkit;

public class MobNexus
{
    public static boolean knightstop;
    
    static {
        MobNexus.knightstop = false;
    }
    
    public void spawnNexuses() {
        final World w = Bukkit.getServer().getWorld("world");
        final Chunk c = w.getChunkAt(new Location(w, -164.0, 121.0, -55.0));
        w.loadChunk(c);
        final Location loc = new Location(w, -164.0, 121.0, -55.0);
        final Wither z = (Wither)w.spawnEntity(loc, EntityType.WITHER);
        ClassesPerms.redteam.add(z.getUniqueId());
        z.setAI(false);
        z.setCustomName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Red team Nexus").toString());
        z.setCustomNameVisible(true);
        z.setMaxHealth(2000.0);
        z.setHealth(2000.0);
        final Chunk c2 = w.getChunkAt(new Location(w, 142.0, 55.0, 84.0));
        w.loadChunk(c2);
        final Location loc2 = new Location(w, 146.55, 55.0, 80.46);
        final Wither w2 = (Wither)w.spawnEntity(loc2, EntityType.WITHER);
        ClassesPerms.blueteam.add(w2.getUniqueId());
        w2.setAI(false);
        w2.setCustomName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Blue team Nexus").toString());
        w2.setCustomNameVisible(true);
        w2.setMaxHealth(2000.0);
        w2.setHealth(2000.0);
    }
    
    public void killEntities() {
        final World w = Bukkit.getServer().getWorld("world");
        final Chunk c = w.getChunkAt(new Location(w, -164.0, 121.0, -55.0));
        final Chunk c2 = w.getChunkAt(new Location(w, -156.0, 58.0, 348.0));
        w.loadChunk(c);
        w.loadChunk(c2);
        final Chunk c3 = w.getChunkAt(new Location(w, -190.0, 71.0, 26.0));
        w.loadChunk(c3);
        final Chunk c4 = w.getChunkAt(new Location(w, 142.0, 55.0, 84.0));
        w.loadChunk(c4);
        for (final Entity e : Bukkit.getWorld("world").getEntities()) {
            if (!(e instanceof Player) && !(e instanceof ArmorStand)) {
                e.remove();
            }
        }
    }
    
    public void spawnKnight() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10.0f, 1.0f);
        }
        Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GRAY).append(ChatColor.BOLD).append("The Last Knight has spawned!").toString());
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc1 = new Location(w, -155.0, 56.0, 348.0);
        final Chunk c4 = w.getChunkAt(new Location(w, -155.0, 56.0, 348.0));
        w.loadChunk(c4);
        loc1.getBlock().setType(Material.REDSTONE_BLOCK);
        new BukkitRunnable() {
            public void run() {
                loc1.getBlock().setType(Material.AIR);
            }
        }.runTaskLater((Plugin)Test.plugin, 10L);
    }
    
    public void spawnChicken() {
        final World w = Bukkit.getServer().getWorld("world");
        final Chunk c = w.getChunkAt(new Location(w, -190.0, 71.0, 26.0));
        w.loadChunk(c);
        final Location loc = new Location(w, -190.0, 71.0, 26.0);
        final Chicken z = (Chicken)w.spawnEntity(loc, EntityType.CHICKEN);
        z.setCustomName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ancient chicken").toString());
        z.setCustomNameVisible(true);
        z.setMaxHealth(40.0);
        z.setHealth(40.0);
        w.loadChunk(c);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 10.0f, 1.0f);
        }
        Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("The ancient chicken has spawned!").toString());
    }
}
