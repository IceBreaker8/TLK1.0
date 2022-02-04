// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.Listener;

public class InvClose implements Listener
{
    @EventHandler
    public void onInvClose(final InventoryCloseEvent e) {
        final Player p = (Player)e.getPlayer();
        final Inventory inv = e.getInventory();
        if (inv.getTitle().contains("Class Items") && !this.checkInv(p, inv)) {
            Bukkit.getScheduler().runTaskLater((Plugin)Test.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.openInventory(e.getInventory());
                }
            }, 5L);
        }
    }
    
    public boolean checkInv(final Player player, final Inventory inv) {
        ItemStack[] contents;
        for (int length = (contents = inv.getContents()).length, i = 0; i < length; ++i) {
            final ItemStack it = contents[i];
            if (it != null) {
                return false;
            }
        }
        return true;
    }
}
