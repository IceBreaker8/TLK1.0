// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.gamemenu;

import org.bukkit.event.EventHandler;
import java.util.UUID;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class FriendlyFire implements Listener
{
    @EventHandler
    public void onHit(final EntityDamageByEntityEvent e) {
        final UUID p = e.getEntity().getUniqueId();
        if (e.getEntity() instanceof Player) {
            if (ClassesPerms.noharm.contains(p) && ClassesPerms.noharm.contains(e.getDamager().getUniqueId())) {
                e.setCancelled(true);
            }
            if (ClassesPerms.redteam.contains(p) && ClassesPerms.redteam.contains(e.getDamager().getUniqueId())) {
                e.setCancelled(true);
            }
            if (ClassesPerms.blueteam.contains(p) && ClassesPerms.blueteam.contains(e.getDamager().getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }
}
