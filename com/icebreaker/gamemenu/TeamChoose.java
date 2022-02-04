// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.gamemenu;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class TeamChoose implements Listener
{
    @EventHandler
    public void onRightClickTeams(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItem();
        if (item == null) {
            return;
        }
        final ItemMeta im = e.getItem().getItemMeta();
        if (im == null) {
            return;
        }
        final Action action = e.getAction();
        final ItemStack stack = e.getItem();
        if (stack == null) {
            return;
        }
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!stack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("Humans Team")) {
            if (ClassesPerms.blueteam.size() <= ClassesPerms.redteam.size()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                p.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("You joined the Blue team!").toString());
                ClassesPerms.redteam.remove(p.getUniqueId());
                ClassesPerms.blueteam.add(p.getUniqueId());
                return;
            }
            if (ClassesPerms.blueteam.size() >= 6) {
                p.sendMessage(ChatColor.RED + "You cannot join a full team!");
                return;
            }
            if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You are already on this team!");
                return;
            }
            if (ClassesPerms.blueteam.size() > ClassesPerms.redteam.size()) {
                p.sendMessage(ChatColor.RED + "You cannot join a team with more members than the other team!");
                return;
            }
        }
        if (stack.getItemMeta().getDisplayName().contains("Shamans Team")) {
            if (ClassesPerms.blueteam.size() >= ClassesPerms.redteam.size()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("You joined the Red team!").toString());
                ClassesPerms.redteam.add(p.getUniqueId());
                ClassesPerms.blueteam.remove(p.getUniqueId());
                return;
            }
            if (ClassesPerms.redteam.size() >= 6) {
                p.sendMessage(ChatColor.RED + "You cannot join a full team!");
                return;
            }
            if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You are already on this team!");
                return;
            }
            if (ClassesPerms.blueteam.size() < ClassesPerms.redteam.size()) {
                p.sendMessage(ChatColor.RED + "You cannot join a team with more members than the other team!");
            }
        }
    }
}
