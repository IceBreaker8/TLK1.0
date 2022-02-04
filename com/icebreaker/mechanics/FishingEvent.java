// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Random;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.Listener;

public class FishingEvent implements Listener
{
    @EventHandler
    public void onPlayerFish(final PlayerFishEvent event) {
        final Player p = event.getPlayer();
        final String state = event.getState().name();
        if (state.equalsIgnoreCase("CAUGHT_FISH")) {
            final Random r1 = new Random();
            final int Low = 0;
            final int High = 20;
            final int Result = r1.nextInt(High - Low) + Low;
            event.getCaught().remove();
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.RAW_FISH, 5) });
            p.updateInventory();
            if (Result == 10) {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.PRISMARINE_CRYSTALS) });
            }
        }
    }
}
