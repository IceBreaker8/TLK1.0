// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.icebreaker.ranks.RankingSystem;
import org.bukkit.command.CommandExecutor;

public class CommandsOfClasses implements CommandExecutor
{
    RankingSystem r;
    
    public CommandsOfClasses() {
        this.r = new RankingSystem();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("class")) {
            if (!p.getName().equals("icebreaker970")) {
                p.sendMessage(ChatColor.RED + "You don't have the permission to execute this command!");
                return true;
            }
            final InventoryGUI GUI = new InventoryGUI();
            GUI.createMenu(p);
        }
        return true;
    }
}
