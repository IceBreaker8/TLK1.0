// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.lobbyscoreboard;

import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import java.util.Iterator;
import com.icebreaker.gamemenu.GameGUI;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.GameMode;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.event.Listener;

public class PlayerJoinCount implements Listener
{
    private int players;
    public static Scoreboard b1;
    private Objective o;
    public static Score first;
    public static Score second;
    public static Score third;
    public static Score fourth;
    public static Score fifth;
    public static Score sixth;
    public static Score seventh;
    public static Score eighth;
    public String message;
    private boolean startedAlready;
    public static boolean gameStarted;
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public int getPlayers() {
        return this.players;
    }
    
    public void setPlayers(final int players) {
        this.players = players;
    }
    
    public boolean getStartedAlready() {
        return this.startedAlready;
    }
    
    public void setStartedAlready(final boolean c) {
        this.startedAlready = c;
    }
    
    @EventHandler
    public void quit(final PlayerQuitEvent e) {
        if (e.getPlayer() != null && e instanceof Player) {
            this.setPlayers(this.getPlayers() - 1);
            this.checkScoreboard();
        }
    }
    
    public static void setGameStarted(final boolean gameStarted) {
        PlayerJoinCount.gameStarted = gameStarted;
    }
    
    public boolean getGameStarted() {
        return PlayerJoinCount.gameStarted;
    }
    
    @EventHandler
    public void playerCount(final PlayerJoinEvent e) {
        this.setPlayers(0);
        this.setMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Waiting for players...").toString());
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.damage(0.0);
            this.setPlayers(this.getPlayers() + 1);
            if (!ClassesPerms.redteam.contains(p.getUniqueId()) && !ClassesPerms.blueteam.contains(p.getUniqueId())) {
                this.checkScoreboard();
            }
        }
        if (this.getPlayers() >= 13 || this.getGameStarted()) {
            e.getPlayer().sendMessage(ChatColor.RED + "Game is full or already started, you need to wait for the game to end..");
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
        if (this.getGameStarted()) {
            return;
        }
        if (this.getStartedAlready()) {
            return;
        }
        if (this.checkCount()) {
            this.setStartedAlready(true);
            new BukkitRunnable() {
                int start = 60;
                
                public void run() {
                    if (!PlayerJoinCount.this.checkCount()) {
                        PlayerJoinCount.this.setStartedAlready(false);
                        PlayerJoinCount.this.setMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Waiting for players...").toString());
                        PlayerJoinCount.this.checkScoreboard();
                        this.cancel();
                    }
                    --this.start;
                    PlayerJoinCount.this.setMessage(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Starting in ").append(this.start).append(" seconds...").toString());
                    PlayerJoinCount.this.checkScoreboard();
                    if (this.start == 0) {
                        PlayerJoinCount.setGameStarted(true);
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.getInventory().clear();
                        }
                        Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Starting Game...").toString());
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            p.getInventory().clear();
                        }
                        GameGUI.verif = true;
                        final GameGUI game = new GameGUI();
                        game.gameStart();
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
        }
    }
    
    public void checkScoreboard() {
        final ScoreboardManager m = Bukkit.getScoreboardManager();
        PlayerJoinCount.b1 = m.getNewScoreboard();
        (this.o = PlayerJoinCount.b1.registerNewObjective("gameObjective", "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
        this.o.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Welcome to our server").toString());
        (PlayerJoinCount.first = this.o.getScore(ChatColor.WHITE + "       ")).setScore(7);
        (PlayerJoinCount.second = this.o.getScore(this.getMessage())).setScore(6);
        (PlayerJoinCount.third = this.o.getScore(ChatColor.RED + "             ")).setScore(5);
        (PlayerJoinCount.fourth = this.o.getScore(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Players").toString())).setScore(4);
        (PlayerJoinCount.fifth = this.o.getScore(new StringBuilder().append(ChatColor.WHITE).append(this.getPlayers()).append("/12").toString())).setScore(3);
        (PlayerJoinCount.sixth = this.o.getScore("             ")).setScore(2);
        (PlayerJoinCount.seventh = this.o.getScore(new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(ChatColor.BOLD).append("Game Mode:").toString())).setScore(1);
        (PlayerJoinCount.eighth = this.o.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("The Last Knight").toString())).setScore(0);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(PlayerJoinCount.b1);
        }
    }
    
    public boolean checkCount() {
        return this.getPlayers() >= 4;
    }
}
