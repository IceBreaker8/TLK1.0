// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import com.icebreaker.gamemenu.GameGUI;
import com.icebreaker.gamemenu.ScoreboardsF;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.GameMode;
import java.util.Iterator;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Chicken;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import com.icebreaker.GUI.ClassesPerms;
import com.icebreaker.mech2.PerksXp;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.Listener;

public class RespawnMech implements Listener
{
    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (p instanceof Player) {
            final PerksXp f = new PerksXp();
            if (p.getKiller() instanceof Player && (ClassesPerms.redteam.contains(p.getKiller().getUniqueId()) || ClassesPerms.blueteam.contains(p.getKiller().getUniqueId()))) {
                if (ClassesPerms.burp.contains(p.getKiller().getUniqueId())) {
                    p.getKiller().getWorld().playSound(p.getKiller().getLocation(), Sound.ENTITY_PLAYER_BURP, 7.0f, 1.0f);
                }
                else if (ClassesPerms.wolf.contains(p.getKiller().getUniqueId())) {
                    p.getKiller().getWorld().playSound(p.getKiller().getLocation(), Sound.ENTITY_WOLF_HOWL, 7.0f, 1.0f);
                }
                else if (ClassesPerms.vil.contains(p.getKiller().getUniqueId())) {
                    p.getKiller().getWorld().playSound(p.getKiller().getLocation(), Sound.ENTITY_VILLAGER_DEATH, 7.0f, 1.0f);
                }
                else if (ClassesPerms.blaze.contains(p.getKiller().getUniqueId())) {
                    p.getKiller().getWorld().playSound(p.getKiller().getLocation(), Sound.ENTITY_BLAZE_DEATH, 7.0f, 1.0f);
                    this.blazeDeath(p);
                }
                else if (ClassesPerms.thunder.contains(p.getKiller().getUniqueId())) {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                }
                else if (ClassesPerms.guardian.contains(p.getKiller().getUniqueId())) {
                    this.guardianDeath(p);
                }
                else if (ClassesPerms.Shadow.contains(p.getKiller().getUniqueId())) {
                    p.getLocation().getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 500, 0.75, 2.0, 0.75, 1.0E-4);
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 7.0f, 1.0f);
                }
                if (p.getKiller() == p) {
                    p.getKiller().sendMessage(ChatColor.RED + "Why do you want to kill yourself so badly?");
                }
                if (p.getKiller() != p) {
                    f.setSouls(p.getKiller(), 1);
                    p.getKiller().sendMessage(ChatColor.WHITE + "+ 1 " + ChatColor.YELLOW + "soul.");
                }
                if (ClassesPerms.hunter.contains(p.getKiller())) {
                    p.getKiller().setHealth(p.getKiller().getHealth() + 4.0);
                    p.getKiller().sendMessage(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.BOLD).append("You got 2 hearts!").toString());
                }
            }
            this.particles(p);
            e.setDeathMessage((String)null);
            p.setHealth(p.getMaxHealth());
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 120));
            this.respawnRun(p);
            ItemStack[] contents;
            for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
                final ItemStack item1 = contents[i];
                if (item1 != null && (item1.getType().equals((Object)Material.IRON_ORE) || item1.getType().equals((Object)Material.GOLD_ORE) || item1.getType().equals((Object)Material.FEATHER))) {
                    item1.setAmount(0);
                }
            }
        }
    }
    
    public void blazeDeath(final Player p) {
        final Location loc = p.getLocation();
        final Item b1 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b2 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b3 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b4 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b5 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b6 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b7 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b8 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        final Item b9 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.BLAZE_POWDER, 1));
        b1.setPickupDelay(199980);
        b2.setPickupDelay(199980);
        b3.setPickupDelay(199980);
        b4.setPickupDelay(199980);
        b5.setPickupDelay(199980);
        b6.setPickupDelay(199980);
        b7.setPickupDelay(199980);
        b8.setPickupDelay(199980);
        b9.setPickupDelay(199980);
        b1.setVelocity(new Vector(0.0, 0.2, 0.1));
        b2.setVelocity(new Vector(0.1, 0.2, 0.0));
        b3.setVelocity(new Vector(0.1, 0.2, 0.1));
        b4.setVelocity(new Vector(0.0, 0.2, -0.1));
        b5.setVelocity(new Vector(-0.1, 0.2, 0.0));
        b6.setVelocity(new Vector(-0.1, 0.2, -0.1));
        b7.setVelocity(new Vector(-0.1, 0.2, 0.1));
        b8.setVelocity(new Vector(0.1, 0.2, -0.1));
        b9.setVelocity(new Vector(0.1, 0.2, -0.1));
        new BukkitRunnable() {
            public void run() {
                b1.remove();
                b2.remove();
                b3.remove();
                b4.remove();
                b5.remove();
                b6.remove();
                b7.remove();
                b8.remove();
                b9.remove();
            }
        }.runTaskLater((Plugin)Test.plugin, 30L);
    }
    
    public void guardianDeath(final Player p) {
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_FLOP, 7.0f, 1.0f);
        p.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 1000, 1.0, 2.0, 1.0, 0.01);
        final Location loc = p.getLocation();
        final Item b1 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b2 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b3 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b4 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b5 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b6 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b7 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b8 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        final Item b9 = p.getWorld().dropItem(loc.add(0.0, 0.25, 0.0), new ItemStack(Material.PRISMARINE_SHARD, 1));
        b1.setPickupDelay(199980);
        b2.setPickupDelay(199980);
        b3.setPickupDelay(199980);
        b4.setPickupDelay(199980);
        b5.setPickupDelay(199980);
        b6.setPickupDelay(199980);
        b7.setPickupDelay(199980);
        b8.setPickupDelay(199980);
        b9.setPickupDelay(199980);
        b1.setVelocity(new Vector(0.0, 0.2, 0.1));
        b2.setVelocity(new Vector(0.1, 0.2, 0.0));
        b3.setVelocity(new Vector(0.1, 0.2, 0.1));
        b4.setVelocity(new Vector(0.0, 0.2, -0.1));
        b5.setVelocity(new Vector(-0.1, 0.2, 0.0));
        b6.setVelocity(new Vector(-0.1, 0.2, -0.1));
        b7.setVelocity(new Vector(-0.1, 0.2, 0.1));
        b8.setVelocity(new Vector(0.1, 0.2, -0.1));
        b9.setVelocity(new Vector(0.1, 0.2, -0.1));
        new BukkitRunnable() {
            public void run() {
                b1.remove();
                b2.remove();
                b3.remove();
                b4.remove();
                b5.remove();
                b6.remove();
                b7.remove();
                b8.remove();
                b9.remove();
            }
        }.runTaskLater((Plugin)Test.plugin, 30L);
    }
    
    private void particles(final Player p) {
        if (ClassesPerms.redteam.contains(p.getUniqueId())) {
            final World w = Bukkit.getServer().getWorld("world");
            final Chicken z = (Chicken)w.spawnEntity(p.getLocation(), EntityType.CHICKEN);
            z.setCustomName(ChatColor.DARK_GREEN + p.getName() + " Corpse");
            z.setCustomNameVisible(true);
            z.setInvulnerable(true);
            z.setAI(false);
            ClassesPerms.redteam.add(z.getUniqueId());
            new BukkitRunnable() {
                int t = 0;
                
                public void run() {
                    ++this.t;
                    if (z.isDead()) {
                        p.teleport((Entity)z);
                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERMITE_HURT, 5.0f, 1.0f);
                        p.getLocation().getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 80, 0.5, 0.5, 0.5, 0.01);
                        ClassesPerms.rezplayer.add(p.getUniqueId());
                        this.cancel();
                    }
                    if (this.t == 7) {
                        z.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
        }
        else if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
            final World w = Bukkit.getServer().getWorld("world");
            final Chicken z = (Chicken)w.spawnEntity(p.getLocation(), EntityType.CHICKEN);
            z.setCustomName(ChatColor.DARK_AQUA + p.getName() + " Corpse");
            z.setCustomNameVisible(true);
            z.setInvulnerable(true);
            z.setAI(false);
            ClassesPerms.blueteam.add(z.getUniqueId());
            new BukkitRunnable() {
                int t = 0;
                
                public void run() {
                    ++this.t;
                    if (z.isDead()) {
                        p.teleport((Entity)z);
                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERMITE_HURT, 5.0f, 1.0f);
                        p.getLocation().getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 80, 0.5, 0.5, 0.5, 0.01);
                        ClassesPerms.rezplayer.add(p.getUniqueId());
                        this.cancel();
                    }
                    if (this.t == 7) {
                        z.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
        }
    }
    
    @EventHandler
    public void onDeathMessage(final PlayerDeathEvent e) {
        final Player victim = e.getEntity();
        final Player killer = victim.getKiller();
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 10.0f, 1.0f);
        }
        if (!(killer instanceof Player)) {
            return;
        }
        if (ClassesPerms.blueteam.contains(killer.getUniqueId()) && ClassesPerms.redteam.contains(victim.getUniqueId())) {
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("HUMAN ").append(ChatColor.AQUA).append(killer.getName()).append(ChatColor.WHITE).append(" eliminated ").append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("SHAMAN ").append(ChatColor.GREEN).append(victim.getName()).toString());
        }
        else if (ClassesPerms.redteam.contains(killer.getUniqueId()) && ClassesPerms.blueteam.contains(victim.getUniqueId())) {
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("SHAMAN ").append(ChatColor.GREEN).append(killer.getName()).append(ChatColor.WHITE).append(" eliminated ").append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("HUMAN ").append(ChatColor.AQUA).append(victim.getName()).toString());
        }
    }
    
    public void respawnRun(final Player p) {
        p.sendTitle(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.RED).append("You have Died!").toString(), new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Respawning in ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("6").append(ChatColor.WHITE).append(ChatColor.BOLD).append("...").toString(), 0, 60, 20);
        p.teleport(p.getLocation().add(0.0, 50.0, 0.0));
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            int t = 6;
            
            public void run() {
                if (this.t > 0) {
                    p.sendTitle(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.RED).append("You have Died!").toString(), new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Respawning in ").append(ChatColor.GREEN).append(ChatColor.BOLD).append(this.t).append(ChatColor.WHITE).append(ChatColor.BOLD).append("...").toString(), 0, 60, 20);
                }
                if (ClassesPerms.rezplayer.contains(p.getUniqueId())) {
                    ClassesPerms.rezplayer.remove(p.getUniqueId());
                    p.sendMessage(ChatColor.GOLD + "You have been revived!");
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    if (ClassesPerms.acrobat.contains(p.getUniqueId())) {
                        p.setAllowFlight(true);
                    }
                    p.sendTitle((String)null, (String)null, 0, 0, 0);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 125));
                    this.cancel();
                }
                if (this.t == 0) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    if (ClassesPerms.acrobat.contains(p.getUniqueId())) {
                        p.setAllowFlight(true);
                    }
                    p.showPlayer(p);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 80));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 80));
                    p.sendTitle((String)null, (String)null, 0, 0, 0);
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                    if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                        p.teleport(new Location(p.getWorld(), -134.0, 108.0, -52.0));
                    }
                    else if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                        p.teleport(new Location(p.getWorld(), 139.0, 63.0, 98.0));
                    }
                    else {
                        final World w = Bukkit.getWorld("world");
                        final double x = 347.0;
                        final double y = 67.0;
                        final double z = 275.0;
                        final Location loc = new Location(w, x, y, z);
                        p.teleport(loc);
                    }
                    this.cancel();
                }
                --this.t;
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 3.0f);
        }
        final ScoreboardsF s = new ScoreboardsF();
        s.appearScoreboard();
        final GameGUI g = new GameGUI();
        g.makeTeams();
        ItemStack[] contents;
        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack item1 = contents[i];
            if (item1 != null && (item1.getType().equals((Object)Material.IRON_ORE) || item1.getType().equals((Object)Material.GOLD_ORE) || item1.getType().equals((Object)Material.FEATHER))) {
                item1.setAmount(0);
            }
        }
        if (!ClassesPerms.redteam.contains(p.getUniqueId()) && !ClassesPerms.blueteam.contains(p.getUniqueId())) {
            ClassesPerms.noharm.add(p.getUniqueId());
            p.getInventory().clear();
            final World w = Bukkit.getWorld("world");
            final double x = 347.0;
            final double y = 67.0;
            final double z = 275.0;
            final Location loc = new Location(w, x, y, z);
            p.teleport(loc);
            return;
        }
        if (ClassesPerms.redteam.contains(p.getUniqueId())) {
            p.setGameMode(GameMode.SURVIVAL);
            ClassesPerms.noharm.remove(p.getUniqueId());
            p.teleport(new Location(p.getWorld(), -134.0, 108.0, -52.0));
            s.appearScoreboard();
            g.makeTeams();
            return;
        }
        if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
            p.setGameMode(GameMode.SURVIVAL);
            ClassesPerms.noharm.remove(p.getUniqueId());
            p.teleport(new Location(p.getWorld(), 139.0, 63.0, 98.0));
            s.appearScoreboard();
            g.makeTeams();
        }
    }
}
