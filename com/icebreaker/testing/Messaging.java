// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.testing;

import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Sound;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.command.CommandExecutor;

public class Messaging implements CommandExecutor
{
    public HashMap<UUID, Integer> cooldownTimeP;
    public HashMap<UUID, BukkitRunnable> cooldownTaskP;
    
    public Messaging() {
        this.cooldownTimeP = new HashMap<UUID, Integer>();
        this.cooldownTaskP = new HashMap<UUID, BukkitRunnable>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You cannot send messages via console!");
            return true;
        }
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("msg")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Enter a Player name!");
                return true;
            }
            final Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(ChatColor.RED + "Player doesn't exist!");
                return true;
            }
            if (args.length <= 1) {
                p.sendMessage(ChatColor.RED + "Enter a valid Message!");
                return true;
            }
            if (target.getName() == p.getName()) {
                p.sendMessage(ChatColor.RED + "Are you talking to yourself? Weird... ");
                return true;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }
            final String allArgs = sb.toString().trim();
            p.sendMessage(ChatColor.WHITE + p.getName() + ChatColor.DARK_GREEN + " \u27a4 " + ChatColor.WHITE + target.getName() + ChatColor.WHITE + " : " + allArgs);
            target.sendMessage(ChatColor.WHITE + p.getName() + ChatColor.DARK_GREEN + " \u27a4 " + ChatColor.WHITE + target.getName() + ChatColor.WHITE + " : " + allArgs);
            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_PLING, 20.0f, 1.0f);
            return true;
        }
        else {
            if (!cmd.getName().equalsIgnoreCase("shout")) {
                return true;
            }
            if (this.cooldownTaskP.containsKey(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You need to wait " + this.cooldownTimeP.get(p.getUniqueId()) + " seconds to use this command!");
                return true;
            }
            this.cooldownTimeP.put(p.getUniqueId(), 4);
            this.cooldownTaskP.put(p.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    Messaging.this.cooldownTimeP.put(p.getUniqueId(), Messaging.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (Messaging.this.cooldownTimeP.get(p.getUniqueId()) == 0) {
                        Messaging.this.cooldownTimeP.remove(p.getUniqueId());
                        Messaging.this.cooldownTaskP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            });
            this.cooldownTaskP.get(p.getUniqueId()).runTaskTimer((Plugin)JavaPlugin.getPlugin((Class)Test.class), 0L, 20L);
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Enter a valid message!");
                return true;
            }
            final StringBuilder sb2 = new StringBuilder();
            for (int j = 0; j < args.length; ++j) {
                sb2.append(args[j]).append(" ");
            }
            final String allArgs2 = sb2.toString().trim();
            if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("[SHAMAN] ").append(ChatColor.WHITE).append(p.getName()).append(": ").append(allArgs2).toString());
            }
            else if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("[HUMAN] ").append(ChatColor.WHITE).append(p.getName()).append(": ").append(allArgs2).toString());
            }
            else {
                p.sendMessage(ChatColor.RED + "You are not in a team!");
            }
            return true;
        }
    }
}
