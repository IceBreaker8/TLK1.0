// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.testing;

import com.icebreaker.ranks.Rank;
import org.bukkit.event.EventHandler;
import org.bukkit.command.ConsoleCommandSender;
import java.util.Iterator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.icebreaker.ranks.RankingSystem;
import org.bukkit.event.Listener;

public class PlayerJoinRank implements Listener
{
    RankingSystem r;
    
    public PlayerJoinRank() {
        this.r = new RankingSystem();
    }
    
    @EventHandler
    public void playerChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        e.setCancelled(true);
        switch (this.r.getRank(p)) {
            case OWNER: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.AQUA).append("[").append(ChatColor.DARK_AQUA).append(this.r.getRank(p)).append(ChatColor.AQUA).append("] ").append(ChatColor.AQUA).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.AQUA).append("[").append(ChatColor.DARK_AQUA).append(this.r.getRank(p)).append(ChatColor.AQUA).append("] ").append(ChatColor.AQUA).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + this.r.getRank(p) + ChatColor.AQUA + "] " + ChatColor.AQUA + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + this.r.getRank(p) + ChatColor.AQUA + "] " + ChatColor.AQUA + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            case ADMIN: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.RED).append("[").append(ChatColor.DARK_RED).append(this.r.getRank(p)).append(ChatColor.RED).append("] ").append(ChatColor.RED).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.RED).append("[").append(ChatColor.DARK_RED).append(this.r.getRank(p)).append(ChatColor.RED).append("] ").append(ChatColor.RED).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.RED + "[" + ChatColor.DARK_RED + this.r.getRank(p) + ChatColor.RED + "] " + ChatColor.RED + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.RED + "[" + ChatColor.DARK_RED + this.r.getRank(p) + ChatColor.RED + "] " + ChatColor.RED + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            case MOD: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.YELLOW).append("[").append(ChatColor.GOLD).append(this.r.getRank(p)).append(ChatColor.YELLOW).append("] ").append(ChatColor.YELLOW).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.YELLOW).append("[").append(ChatColor.GOLD).append(this.r.getRank(p)).append(ChatColor.YELLOW).append("] ").append(ChatColor.YELLOW).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + this.r.getRank(p) + ChatColor.YELLOW + "] " + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + this.r.getRank(p) + ChatColor.YELLOW + "] " + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            case YOUTUBE: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.LIGHT_PURPLE).append("[").append(ChatColor.DARK_PURPLE).append("YT").append(ChatColor.LIGHT_PURPLE).append("] ").append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.LIGHT_PURPLE).append("[").append(ChatColor.DARK_PURPLE).append("YT").append(ChatColor.LIGHT_PURPLE).append("] ").append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + ChatColor.DARK_PURPLE + "YT" + ChatColor.LIGHT_PURPLE + "] " + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.LIGHT_PURPLE + "[" + ChatColor.DARK_PURPLE + "YT" + ChatColor.LIGHT_PURPLE + "] " + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            case BUILDER: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GREEN).append("[").append(ChatColor.DARK_GREEN).append("BUILDER").append(ChatColor.GREEN).append("] ").append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GREEN).append("[").append(ChatColor.DARK_GREEN).append("BUILDER").append(ChatColor.GREEN).append("] ").append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "BUILDER" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "BUILDER" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            case ALPHA: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GRAY).append("[").append(ChatColor.DARK_GRAY).append("ALPHA").append(ChatColor.GRAY).append("] ").append(ChatColor.GRAY).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GRAY).append("[").append(ChatColor.DARK_GRAY).append("ALPHA").append(ChatColor.GRAY).append("] ").append(ChatColor.GRAY).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_GRAY + "ALPHA" + ChatColor.GRAY + "] " + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_GRAY + "ALPHA" + ChatColor.GRAY + "] " + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            case CMD: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.WHITE).append("[").append(ChatColor.AQUA).append("CMD").append(ChatColor.WHITE).append("] ").append(ChatColor.WHITE).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.WHITE).append("[").append(ChatColor.AQUA).append("CMD").append(ChatColor.WHITE).append("] ").append(ChatColor.WHITE).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "CMD" + ChatColor.WHITE + "] " + ChatColor.WHITE + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "CMD" + ChatColor.WHITE + "] " + ChatColor.WHITE + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
            default: {
                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.redteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shaman ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GRAY).append("[").append(ChatColor.DARK_GRAY).append("MEMBER").append(ChatColor.GRAY).append("] ").append(ChatColor.GRAY).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (ClassesPerms.blueteam.contains(player.getUniqueId())) {
                            player.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Human ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GRAY).append("[").append(ChatColor.DARK_GRAY).append("MEMBER").append(ChatColor.GRAY).append("] ").append(ChatColor.GRAY).append(p.getName()).append(ChatColor.WHITE).append(": ").append(ChatColor.GRAY).append(e.getMessage()).toString());
                        }
                    }
                    break;
                }
                final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_GRAY + "MEMBER" + ChatColor.GRAY + "] " + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    player2.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_GRAY + "MEMBER" + ChatColor.GRAY + "] " + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
                }
                break;
            }
        }
    }
}
