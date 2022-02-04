// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.World;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.GameMode;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.Listener;

public class CloseToPrismarine implements Listener
{
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (ClassesPerms.prisGet.contains(p.getUniqueId())) {
            return;
        }
        if (this.playerIsNearby(p) && p.getGameMode().equals((Object)GameMode.SURVIVAL)) {
            ClassesPerms.prisGet.add(p.getUniqueId());
            final ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);
            final ItemMeta im = item.getItemMeta();
            im.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Poseidon Shard").toString());
            im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            final ArrayList<String> lore = new ArrayList<String>();
            lore.add(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.MAGIC).append(ChatColor.BOLD).append("dfgtebdszd").toString());
            im.setLore((List)lore);
            item.setItemMeta(im);
            p.getInventory().addItem(new ItemStack[] { item });
            final World w = Bukkit.getServer().getWorld("world");
            final Location loc = new Location(w, -198.0, 37.0, 198.0);
            loc.getWorld().strikeLightningEffect(loc);
            for (final Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 10.0f, 1.0f);
            }
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("> ").append(ChatColor.GOLD).append(ChatColor.BOLD).append(p.getName()).append(ChatColor.WHITE).append(" has obtained the ").append(ChatColor.AQUA).append(ChatColor.BOLD).append("Poseidon Shard!").toString());
        }
    }
    
    public boolean playerIsNearby(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, -198.0, 37.0, 198.0);
        return p.getLocation().distance(loc) < 3.0;
    }
}
