// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.gamemenu;

import com.icebreaker.mechanics.ActionBar;
import org.bukkit.entity.Wither;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.LivingEntity;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Entity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Iterator;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.event.Listener;

public class ScoreboardsF implements Listener
{
    public static boolean chickenstop;
    public static boolean stop;
    public static Scoreboard b;
    private Objective o;
    public static Score LB;
    public static Score sB;
    public static Score sR;
    public static Score sCH;
    private Scoreboard bnull;
    private Objective onull;
    private int shamansWither;
    private int humansWither;
    public boolean attack;
    public boolean bleed;
    
    static {
        ScoreboardsF.chickenstop = false;
        ScoreboardsF.stop = false;
    }
    
    public ScoreboardsF() {
        this.shamansWither = 2000;
        this.humansWither = 2000;
    }
    
    public int getShamanScore() {
        return this.shamansWither;
    }
    
    public void setShamanScore(final int shamansWither) {
        this.shamansWither = shamansWither;
    }
    
    public int getHumanScore() {
        return this.humansWither;
    }
    
    public void setHumanScore(final int humansWither) {
        this.humansWither = humansWither;
    }
    
    public void scoreboard() {
        final ScoreboardManager m = Bukkit.getScoreboardManager();
        ScoreboardsF.b = m.getNewScoreboard();
        (this.o = ScoreboardsF.b.registerNewObjective("gameObjective", "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
        this.o.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("\u2694 The last Knight \u2694").toString());
        (ScoreboardsF.sR = this.o.getScore(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Shamans Team ").append(ChatColor.WHITE).toString())).setScore(this.getShamanScore());
        (ScoreboardsF.sB = this.o.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans Team ").append(ChatColor.WHITE).toString())).setScore(this.getHumanScore());
        (ScoreboardsF.LB = this.o.getScore(new StringBuilder().append(ChatColor.GRAY).append(ChatColor.BOLD).append("Knight Spawn Time ").append(ChatColor.WHITE).toString())).setScore(600);
        (ScoreboardsF.sCH = this.o.getScore(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Chicken Spawn Time ").append(ChatColor.WHITE).toString())).setScore(400);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(ScoreboardsF.b);
        }
    }
    
    public void appearScoreboard() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (ScoreboardsF.b == null) {
                return;
            }
            p.setScoreboard(ScoreboardsF.b);
        }
    }
    
    public void vanishScoreboard() {
        final ScoreboardManager m = Bukkit.getScoreboardManager();
        this.bnull = m.getNewScoreboard();
        (this.onull = this.bnull.registerNewObjective("nullObjective", "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
        this.onull.setDisplayName("");
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(this.bnull);
        }
    }
    
    public static void chickenXXX() {
        new BukkitRunnable() {
            int chickenSpawn = 401;
            
            public void run() {
                if (ScoreboardsF.chickenstop) {
                    ScoreboardsF.chickenstop = false;
                    ScoreboardsF.sR.setScore(2000);
                    ScoreboardsF.sB.setScore(2000);
                    this.chickenSpawn = 401;
                    this.cancel();
                }
                --this.chickenSpawn;
                ScoreboardsF.sCH.setScore(this.chickenSpawn);
                if (this.chickenSpawn == 0) {
                    this.cancel();
                    final MobNexus m = new MobNexus();
                    m.spawnChicken();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
    
    @EventHandler
    public void chickenDeath(final EntityDeathEvent e) {
        final Entity entity = (Entity)e.getEntity();
        if (entity instanceof Chicken && entity.getCustomName() != null && entity.getCustomName().contains("Ancient chicken")) {
            chickenXXX();
            entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENTITY_CHICKEN_DEATH, 10.0f, 1.0f);
            entity.getLocation().getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 500, 2.0, 2.0, 2.0, 0.1);
        }
    }
    
    public void bossTimer() {
        this.setAttack(false);
        this.setBleed(false);
        new BukkitRunnable() {
            int bossSpawn = 601;
            
            public void run() {
                if (ScoreboardsF.stop) {
                    ScoreboardsF.stop = false;
                    ScoreboardsF.sR.setScore(2000);
                    ScoreboardsF.sB.setScore(2000);
                    this.bossSpawn = 601;
                    this.cancel();
                }
                --this.bossSpawn;
                ScoreboardsF.LB.setScore(this.bossSpawn);
                if (this.bossSpawn == 0) {
                    this.cancel();
                    final MobNexus m = new MobNexus();
                    m.spawnKnight();
                    ScoreboardsF.this.waitForKnightDeath();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
    
    @EventHandler
    public void knightDeath(final EntityDeathEvent e) {
        final LivingEntity entity = e.getEntity();
        if (!(entity.getKiller() instanceof Player)) {
            return;
        }
        if (entity.getType() == EntityType.CHICKEN) {
            if (ClassesPerms.redteam.contains(entity.getKiller().getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Shamans have killed the ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("Ancient Chicken").toString());
            }
            if (ClassesPerms.blueteam.contains(entity.getKiller().getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans have killed the ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("Ancient Chicken").toString());
            }
        }
        if (entity.getType() == EntityType.WITHER_SKELETON) {
            if (ClassesPerms.redteam.contains(entity.getKiller().getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Shamans have killed the last knight!").toString());
                if (!this.getAttack()) {
                    Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Wither nexuses lost their immunity!").toString());
                    this.setAttack(true);
                }
            }
            if (ClassesPerms.blueteam.contains(entity.getKiller().getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans team have killed the last knight!").toString());
                if (!this.getAttack()) {
                    Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Wither nexuses lost their immunity!").toString());
                    this.setAttack(true);
                }
            }
        }
    }
    
    public void waitForKnightDeath() {
        new BukkitRunnable() {
            public void run() {
                for (final Entity e : Bukkit.getServer().getWorld("world").getEntities()) {
                    if (e instanceof WitherSkeleton && e.isDead()) {
                        e.getLocation().getWorld().playSound(e.getLocation(), Sound.ENTITY_WITHER_DEATH, 10.0f, 1.0f);
                        e.getLocation().getWorld().spawnParticle(Particle.FLAME, e.getLocation(), 500, 2.0, 2.0, 2.0, 0.1);
                        ScoreboardsF.this.bossTimer();
                        this.cancel();
                    }
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
    
    public boolean getAttack() {
        return this.attack;
    }
    
    public void setAttack(final boolean attack) {
        this.attack = attack;
    }
    
    public boolean getBleed() {
        return this.bleed;
    }
    
    public void setBleed(final boolean bleed) {
        this.bleed = bleed;
    }
    
    @EventHandler
    public void onScoreSub(final EntityDamageByEntityEvent e) {
        final Entity p = e.getEntity();
        if (p instanceof Wither && p.getCustomName().contains("Nexus")) {
            e.setCancelled(true);
            final LivingEntity kek = (LivingEntity)p;
            if (!this.getAttack()) {
                return;
            }
            if (ClassesPerms.redteam.contains(kek.getUniqueId()) && ClassesPerms.blueteam.contains(e.getDamager().getUniqueId())) {
                kek.damage(e.getDamage());
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                        final ActionBar a = new ActionBar();
                        a.send((Entity)player, ChatColor.AQUA + e.getDamager().getName() + ChatColor.DARK_RED + " is attacking your Nexus!");
                        player.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 10.0f, 1.0f);
                    }
                    else {
                        if (!ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            continue;
                        }
                        final ActionBar a = new ActionBar();
                        a.send((Entity)player, ChatColor.BLUE + e.getDamager().getName() + ChatColor.AQUA + " is attacking shamans!");
                        player.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 2.0f);
                    }
                }
                ScoreboardsF.sR.setScore((int)(ScoreboardsF.sR.getScore() - e.getDamage() * 10.0));
                this.setShamanScore(ScoreboardsF.sR.getScore());
                if (ScoreboardsF.sR.getScore() <= 0) {
                    this.setBleed(false);
                }
                if (!this.getBleed()) {
                    this.nexusBleed();
                    this.setBleed(true);
                }
            }
            if (ClassesPerms.blueteam.contains(kek.getUniqueId()) && ClassesPerms.redteam.contains(e.getDamager().getUniqueId())) {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                        final ActionBar a = new ActionBar();
                        a.send((Entity)player, ChatColor.DARK_GREEN + e.getDamager().getName() + ChatColor.DARK_RED + " is attacking your Nexus!");
                        player.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 10.0f, 1.0f);
                    }
                    else {
                        if (!ClassesPerms.redteam.contains(player.getUniqueId())) {
                            continue;
                        }
                        final ActionBar a = new ActionBar();
                        a.send((Entity)player, ChatColor.DARK_GREEN + e.getDamager().getName() + ChatColor.GREEN + " is attacking humans!");
                        player.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 2.0f);
                    }
                }
                kek.damage(e.getDamage());
                ScoreboardsF.sB.setScore((int)(ScoreboardsF.sB.getScore() - e.getDamage() * 10.0));
                this.setHumanScore(ScoreboardsF.sB.getScore());
                if (ScoreboardsF.sB.getScore() <= 0) {
                    this.setBleed(false);
                }
                if (!this.getBleed()) {
                    this.nexusBleed();
                    this.setBleed(true);
                }
            }
        }
    }
    
    public void nexusBleed() {
        new BukkitRunnable() {
            public void run() {
                ScoreboardsF.sB.setScore(ScoreboardsF.sB.getScore() - 5);
                ScoreboardsF.sR.setScore(ScoreboardsF.sR.getScore() - 5);
                ScoreboardsF.this.setShamanScore(ScoreboardsF.sR.getScore());
                ScoreboardsF.this.setHumanScore(ScoreboardsF.sB.getScore());
                if (ScoreboardsF.sB.getScore() < 10 || ScoreboardsF.sR.getScore() < 10) {
                    this.cancel();
                }
                if (!ScoreboardsF.this.getBleed()) {
                    ScoreboardsF.sR.setScore(0);
                    ScoreboardsF.sB.setScore(0);
                    final GameGUI g = new GameGUI();
                    g.spawnFireworks();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 100L);
    }
}
