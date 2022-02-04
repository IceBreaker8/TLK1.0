// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.Listener;

public class DefendersBuff implements Listener
{
    @EventHandler
    public void playerBuff(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (ClassesPerms.redteam.contains(p.getUniqueId())) {
            if (this.playerNearbyNexusR(p)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2));
            }
        }
        else if (ClassesPerms.blueteam.contains(p.getUniqueId()) && this.playerNearbyNexusB(p)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2));
        }
    }
    
    public boolean playerNearbyNexusB(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, 139.0, 64.0, 77.0);
        return p.getLocation().distance(loc) < 10.0;
    }
    
    public boolean playerNearbyNexusR(final Player p) {
        final World w = Bukkit.getServer().getWorld("world");
        final Location loc = new Location(w, -160.0, 121.0, -56.0);
        return p.getLocation().distance(loc) < 10.0;
    }
}
