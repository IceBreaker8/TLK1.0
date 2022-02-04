// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.ranks;

import java.util.Iterator;
import org.bukkit.Sound;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class RanksCommands implements CommandExecutor
{
    RankingSystem r;
    
    public RanksCommands() {
        this.r = new RankingSystem();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        final Player p = (Player)sender;
        if (!cmd.getName().equalsIgnoreCase("rank")) {
            return true;
        }
        if (this.r.getRank(p) != Rank.OWNER) {
            sender.sendMessage(ChatColor.RED + "You don't have the permission to use this command!");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "You need to specify a player!");
            return true;
        }
        final Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(ChatColor.RED + "You have enter an Invalid/Offline player!");
            return true;
        }
        if (args.length == 1) {
            p.sendMessage(ChatColor.RED + "Please enter a valid rank!");
            return true;
        }
        final String arg = args[1];
        final String lowerCase;
        switch (lowerCase = arg.toLowerCase()) {
            case "member": {
                this.r.setRank(target, Rank.MEMBER);
                ClassesPerms.build.remove(target.getUniqueId());
                return true;
            }
            case "youtube": {
                this.r.setRank(target, Rank.YOUTUBE);
                ClassesPerms.build.remove(target.getUniqueId());
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ranks").append(ChatColor.WHITE).append(ChatColor.BOLD).append(" > ").append(ChatColor.DARK_AQUA).append(target.getName()).append(ChatColor.WHITE).append(" got the ").append(ChatColor.LIGHT_PURPLE).append(ChatColor.BOLD).append("YOUTUBE").append(ChatColor.WHITE).append(" rank!").toString());
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    p2.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 10.0f, 1.0f);
                }
                return true;
            }
            case "cmd": {
                this.r.setRank(target, Rank.CMD);
                ClassesPerms.build.remove(target.getUniqueId());
                return true;
            }
            case "mod": {
                this.r.setRank(target, Rank.MOD);
                ClassesPerms.build.remove(target.getUniqueId());
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ranks").append(ChatColor.WHITE).append(ChatColor.BOLD).append(" > ").append(ChatColor.DARK_AQUA).append(target.getName()).append(ChatColor.WHITE).append(" got the ").append(ChatColor.YELLOW).append(ChatColor.BOLD).append("MOD").append(ChatColor.WHITE).append(" rank!").toString());
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    p2.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 10.0f, 1.0f);
                }
                return true;
            }
            case "admin": {
                this.r.setRank(target, Rank.ADMIN);
                ClassesPerms.build.remove(target.getUniqueId());
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ranks").append(ChatColor.WHITE).append(ChatColor.BOLD).append(" > ").append(ChatColor.DARK_AQUA).append(target.getName()).append(ChatColor.WHITE).append(" got the ").append(ChatColor.RED).append(ChatColor.BOLD).append("ADMIN").append(ChatColor.WHITE).append(" rank!").toString());
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    p2.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 10.0f, 1.0f);
                }
                return true;
            }
            case "alpha": {
                this.r.setRank(target, Rank.ALPHA);
                ClassesPerms.build.remove(target.getUniqueId());
                return true;
            }
            case "builder": {
                this.r.setRank(target, Rank.BUILDER);
                ClassesPerms.build.add(target.getUniqueId());
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ranks").append(ChatColor.WHITE).append(ChatColor.BOLD).append(" > ").append(ChatColor.DARK_AQUA).append(target.getName()).append(ChatColor.WHITE).append(" got the ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("BUILDER").append(ChatColor.WHITE).append(" rank!").toString());
                return true;
            }
            default:
                break;
        }
        p.sendMessage(ChatColor.RED + "Please enter a valid rank!");
        return true;
    }
}
