// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.testing;

import org.bukkit.plugin.Plugin;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import com.icebreaker.mech2.PerkInventory;
import com.icebreaker.mech2.PerksXp;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor
{
    public HashMap<UUID, Integer> cooldownTimeP;
    CustomItems cm;
    
    public Commands() {
        this.cm = new CustomItems();
        this.cooldownTimeP = new HashMap<UUID, Integer>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You cannot execute this command from console!");
            return true;
        }
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("feed")) {
            if (!p.isOp()) {
                return true;
            }
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "You need to specify a player!");
                return true;
            }
            final Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(ChatColor.RED + "Could not find player");
                return true;
            }
            target.setHealth(target.getMaxHealth());
            target.setFoodLevel(20);
            target.sendMessage(ChatColor.GREEN + "You have been Fed!");
            p.sendMessage(ChatColor.GREEN + "You have Fed " + ChatColor.YELLOW + args[0] + ChatColor.GREEN + " !");
            return true;
        }
        else {
            if (cmd.getName().equalsIgnoreCase("kit")) {
                if (!p.isOp()) {
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "Use : /kit <player> <team> !");
                    return true;
                }
                final Player player = Bukkit.getServer().getPlayer(args[0]);
                if (player == null) {
                    p.sendMessage(ChatColor.RED + "Player doesn't exist or Offline!");
                    return true;
                }
                if (args.length == 1) {
                    p.sendMessage(ChatColor.RED + "Specify a team!");
                    return true;
                }
                if (args[1].contains("shaman")) {
                    Bukkit.broadcastMessage(ChatColor.DARK_GREEN + player.getName() + " joined the shamans team!");
                    ClassesPerms.redteam.add(player.getUniqueId());
                    ClassesPerms.blueteam.remove(player.getUniqueId());
                    return true;
                }
                if (args[1].contains("human")) {
                    Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " joined the humans team!");
                    ClassesPerms.redteam.remove(player.getUniqueId());
                    ClassesPerms.blueteam.add(player.getUniqueId());
                    return true;
                }
            }
            if (cmd.getName().equalsIgnoreCase("gm")) {
                if (!p.getName().equals("icebreaker970")) {
                    return true;
                }
                final Player p2 = (Player)sender;
                this.cm.CustomItem(p2);
                this.cm.CustomItem2(p2);
                return true;
            }
            else {
                if (cmd.getName().equalsIgnoreCase("souls")) {
                    final Player p2 = (Player)sender;
                    final PerksXp f = new PerksXp();
                    p2.sendMessage(ChatColor.AQUA + "You have " + ChatColor.YELLOW + f.getSouls(p2) + ChatColor.AQUA + " souls.");
                    return true;
                }
                if (cmd.getName().equalsIgnoreCase("attackperks")) {
                    final Player p2 = (Player)sender;
                    final PerkInventory inv = new PerkInventory();
                    inv.openPerk(p2);
                    return true;
                }
                if (!cmd.getName().equalsIgnoreCase("recall")) {
                    return true;
                }
                final Player p2 = (Player)sender;
                if (!ClassesPerms.redteam.contains(p.getUniqueId()) && !ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    p.sendMessage(ChatColor.RED + "Game didn't start yet!");
                    return true;
                }
                this.tpPlayer(p2);
                return true;
            }
        }
    }
    
    public void tpPlayer(final Player p) {
        if (this.cooldownTimeP.containsKey(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You must wait " + this.cooldownTimeP.get(p.getUniqueId()) + " seconds to use this again!");
            return;
        }
        final Location loc = p.getLocation();
        new BukkitRunnable() {
            int t = 6;
            
            public void run() {
                final Location loc1 = p.getLocation();
                --this.t;
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 19980, 10));
                if (Commands.this.playerMove(p, loc, loc1)) {
                    if (p.hasPotionEffect(PotionEffectType.CONFUSION)) {
                        p.removePotionEffect(PotionEffectType.CONFUSION);
                    }
                    p.sendMessage(ChatColor.RED + "The teleport cancelled as you moved...");
                    this.cancel();
                }
                if (this.t > 0 && !Commands.this.playerMove(p, loc, loc1)) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HAT, 10.0f, 1.0f);
                    p.sendMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Teleporting to base in ").append(ChatColor.WHITE).append(ChatColor.BOLD).append(this.t).append(ChatColor.WHITE).append(ChatColor.BOLD).append("..").toString());
                }
                if (this.t == 0 && !Commands.this.playerMove(p, loc, loc1)) {
                    this.cancel();
                    Commands.this.cooldown(p);
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10));
                    if (p.hasPotionEffect(PotionEffectType.CONFUSION)) {
                        p.removePotionEffect(PotionEffectType.CONFUSION);
                    }
                    if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                        p.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Teleported successfully!").toString());
                        p.teleport(new Location(p.getWorld(), 139.0, 63.0, 98.0));
                    }
                    else if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                        p.sendMessage(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Teleported successfully!").toString());
                        p.teleport(new Location(p.getWorld(), -134.0, 108.0, -52.0));
                    }
                    else {
                        p.teleport(new Location(p.getWorld(), -53.0, 93.0, 623.0));
                    }
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
    
    public boolean playerMove(final Player p, final Location loc, final Location loc1) {
        return loc.distance(loc1) > 0.1;
    }
    
    public void cooldown(final Player p) {
        this.cooldownTimeP.put(p.getUniqueId(), 200);
        new BukkitRunnable() {
            public void run() {
                if (Commands.this.cooldownTimeP.get(p.getUniqueId()) <= 0) {
                    Commands.this.cooldownTimeP.remove(p.getUniqueId());
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                    p.sendMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("You can teleport to base again now!").toString());
                    this.cancel();
                }
                Commands.this.cooldownTimeP.put(p.getUniqueId(), Commands.this.cooldownTimeP.get(p.getUniqueId()) - 1);
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
}
