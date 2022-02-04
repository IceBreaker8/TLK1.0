// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import java.util.Iterator;
import com.icebreaker.GUI.ClassesPerms;
import com.icebreaker.gamemenu.GameGUI;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;

public class ConsoleCommands implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {
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
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("build")) {
            if (args.length == 0) {
                return true;
            }
            final Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                return true;
            }
            ClassesPerms.build.add(target.getUniqueId());
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("icesay")) {
            if (args.length == 0) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "icebreaker970 joined the game");
                return true;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }
            final String allArgs = sb.toString().trim();
            Bukkit.broadcastMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "OWNER" + ChatColor.AQUA + "] " + ChatColor.AQUA + "icebreaker970" + ChatColor.WHITE + ": " + ChatColor.WHITE + allArgs);
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("kirdowsay")) {
            if (args.length == 0) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Kirdow joined the game");
                return true;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }
            final String allArgs = sb.toString().trim();
            Bukkit.broadcastMessage(ChatColor.RED + "[" + ChatColor.DARK_RED + "ADMIN" + ChatColor.RED + "] " + ChatColor.RED + "Kirdow" + ChatColor.WHITE + ": " + ChatColor.WHITE + allArgs);
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("Ethsay")) {
            if (args.length == 0) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Ethilion joined the game");
                return true;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }
            final String allArgs = sb.toString().trim();
            Bukkit.broadcastMessage(ChatColor.RED + "[" + ChatColor.DARK_RED + "ADMIN" + ChatColor.RED + "] " + ChatColor.RED + "Ethilion" + ChatColor.WHITE + ": " + ChatColor.WHITE + allArgs);
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("wizsay")) {
            if (args.length == 0) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "wizkid518 joined the game");
                return true;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }
            final String allArgs = sb.toString().trim();
            Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "CMD" + ChatColor.WHITE + "] " + ChatColor.WHITE + "wizkid518" + ChatColor.WHITE + ": " + ChatColor.WHITE + allArgs);
            return true;
        }
        else {
            if (!cmd.getName().equalsIgnoreCase("jabsay")) {
                return false;
            }
            if (args.length == 0) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Jabberdrake joined the game");
                return true;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }
            final String allArgs = sb.toString().trim();
            Bukkit.broadcastMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "BUILDER" + ChatColor.GREEN + "] " + "Jabberdrake" + ChatColor.WHITE + ": " + ChatColor.WHITE + allArgs);
            return true;
        }
    }
    
    @EventHandler
    public void onPlayerCommand(final PlayerCommandPreprocessEvent e) {
        final String msg = e.getMessage().toLowerCase();
        if (msg.startsWith("/start") || msg.startsWith("/build") || msg.startsWith("/icesay") || msg.startsWith("/kirdowsay") || msg.startsWith("/wizsay") || msg.startsWith("/ethsay") || msg.startsWith("/jabsay")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "This command is for the console only!");
        }
    }
}
