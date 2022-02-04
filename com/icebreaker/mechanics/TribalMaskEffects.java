// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.icebreaker.GUI.ClassesPerms;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class TribalMaskEffects implements Listener
{
    @EventHandler
    public void onArmorWear(final PlayerInteractEvent e) {
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
        if (im.getLore() == null) {
            return;
        }
        if (!stack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!stack.hasItemMeta()) {
            return;
        }
        if (!stack.getItemMeta().hasLore()) {
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("Tribal")) {
            e.setCancelled(true);
            this.title(p);
            p.getInventory().remove(stack);
            final ItemStack helmet = p.getInventory().getHelmet();
            final ItemStack item2 = new ItemStack(Material.SKULL_ITEM, 1, (short)1);
            final ItemMeta im2 = item2.getItemMeta();
            im2.setDisplayName(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.BOLD).append("Tribal Mask").toString());
            im2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            final ArrayList<String> lore = new ArrayList<String>();
            lore.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
            im2.setLore((List)lore);
            item2.setItemMeta(im2);
            p.getInventory().setHelmet(item2);
            ClassesPerms.nofalldmg.add(p.getUniqueId());
            this.scareTheShit(p);
            ClassesPerms.mask.add(p.getUniqueId());
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 2));
            new BukkitRunnable() {
                public void run() {
                    ClassesPerms.nofalldmg.remove(p.getUniqueId());
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_DEATH, 10.0f, 1.0f);
                    p.getInventory().setHelmet(helmet);
                    ClassesPerms.mask.remove(p.getUniqueId());
                }
            }.runTaskLater((Plugin)Test.plugin, 600L);
            for (final Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10.0f, 1.0f);
            }
        }
    }
    
    public void title(final Player p1) {
        for (final Player p2 : Bukkit.getOnlinePlayers()) {
            p2.sendTitle(ChatColor.DARK_AQUA + p1.getName() + ChatColor.WHITE + " used the ", new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" Tribal Mask").append(ChatColor.WHITE).append(" Powers!").toString(), 0, 80, 20);
        }
    }
    
    private void scareTheShit(final Player p) {
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                this.t += 0.5;
                for (final Entity e : p.getLocation().getWorld().getEntities()) {
                    if (e.getLocation().distance(p.getLocation()) < 5.0 && e.getType().isAlive() && !e.equals(p) && TribalMaskEffects.this.noTeamFire(p, e)) {
                        final LivingEntity b1 = (LivingEntity)e;
                        b1.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 0));
                    }
                }
                if (this.t == 30.0) {
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
