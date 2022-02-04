// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.util.Vector;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.entity.Fireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import java.util.Iterator;
import org.bukkit.Sound;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerDropItemEvent;
import java.util.UUID;
import java.util.HashSet;
import org.bukkit.event.Listener;

public class AncientSword implements Listener
{
    public static HashSet<UUID> CD;
    
    static {
        AncientSword.CD = new HashSet<UUID>();
    }
    
    @EventHandler
    public void ancientSwordDrop(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final Item item = e.getItemDrop();
        if (item.getItemStack().getType().equals((Object)Material.NETHER_STAR)) {
            this.searchForLocation(p, item);
        }
    }
    
    private void searchForLocation(final Player p, final Item item) {
        new BukkitRunnable() {
            public void run() {
                final World w = Bukkit.getServer().getWorld("world");
                final Location loc = new Location(w, -9.0, 70.0, -85.0);
                for (final Entity e : loc.getWorld().getEntities()) {
                    if (e.getLocation().distance(loc) < 1.5 && e.equals(item)) {
                        Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append(">").append(ChatColor.DARK_AQUA).append(" ").append(p.getName()).append(ChatColor.WHITE).append(" revived the ").append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ancient Sword!").toString());
                        item.remove();
                        p.getWorld().strikeLightningEffect(item.getLocation());
                        AncientSword.this.givePlayerAncientSword(p);
                        new BukkitRunnable() {
                            public void run() {
                                for (final Player p : Bukkit.getOnlinePlayers()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 10.0f, 1.0f);
                                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_SCREAM, 10.0f, 1.0f);
                                }
                            }
                        }.runTaskLater((Plugin)Test.plugin, 10L);
                    }
                }
            }
        }.runTaskLater((Plugin)Test.plugin, 40L);
    }
    
    private void givePlayerAncientSword(final Player p) {
        final ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Ancient Sword").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Only heroes can wield this sword");
        im.setLore((List)lore);
        item.setItemMeta(im);
        item.addEnchantment(Enchantment.DAMAGE_ALL, 4);
        item.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        item.addEnchantment(Enchantment.KNOCKBACK, 2);
        p.getInventory().addItem(new ItemStack[] { item });
    }
    
    @EventHandler
    public void onLeftClickSword(final PlayerInteractEvent e) {
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
        if (action != Action.LEFT_CLICK_AIR && action != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (im.getLore() == null) {
            return;
        }
        if (AncientSword.CD.contains(p.getUniqueId()) && stack.getItemMeta().getDisplayName().contains("Ancient Sword")) {
            e.setCancelled(true);
            final Fireball f = (Fireball)p.launchProjectile((Class)Fireball.class);
            if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                ClassesPerms.redteam.add(f.getUniqueId());
            }
            else if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                ClassesPerms.blueteam.add(f.getUniqueId());
            }
            f.setVelocity(p.getLocation().getDirection().multiply(2.0));
            f.setIsIncendiary(false);
            p.setFoodLevel(20);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 10.0f, 0.0f);
        }
    }
    
    @EventHandler
    public void FireBall(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Fireball && this.noTeamFire(e.getDamager(), e.getEntity())) {
            e.setDamage(10.0);
        }
    }
    
    public boolean noTeamFire(final Entity entity, final Entity e) {
        return (!ClassesPerms.redteam.contains(entity.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(entity.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
    
    public void title(final Player p1) {
        for (final Player p2 : Bukkit.getOnlinePlayers()) {
            p2.sendTitle(ChatColor.DARK_AQUA + p1.getName() + ChatColor.WHITE + " unleashed the ", new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append(" Ancient Sword").append(ChatColor.WHITE).append(" Power!").toString(), 0, 80, 20);
        }
    }
    
    @EventHandler
    public void onRightClickSword(final PlayerInteractEvent e) {
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
        if (AncientSword.CD.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You drained all the ancient sword's power!");
            return;
        }
        if (stack.getItemMeta().getDisplayName().contains("Ancient Sword")) {
            this.title(p);
            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMEN_SCREAM, 10.0f, 1.0f);
            }
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 8));
            p.getWorld().strikeLightning(p.getLocation());
            e.setCancelled(true);
            this.dragonShit(p);
            p.getWorld().setStorm(true);
            AncientSword.CD.add(p.getUniqueId());
        }
    }
    
    private void dragonShit(final Player p) {
        p.setInvulnerable(false);
        p.setVelocity(new Vector(0, 2, 0));
        p.setAllowFlight(true);
        p.setFlying(true);
        new BukkitRunnable() {
            public void run() {
                ClassesPerms.nofalldmg.add(p.getUniqueId());
                p.setAllowFlight(false);
                p.setFlying(false);
                if (ClassesPerms.acrobat.contains(p.getUniqueId())) {
                    p.setAllowFlight(true);
                }
                AncientSword.this.playerFall(p);
                p.getWorld().setStorm(false);
                AncientSword.CD.remove(p.getUniqueId());
                AncientSword.this.removeitem(p);
            }
        }.runTaskLater((Plugin)Test.plugin, 200L);
    }
    
    private void removeitem(final Player p) {
        ItemStack[] contents;
        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack item1 = contents[i];
            if (item1 != null && item1.getType().equals((Object)Material.DIAMOND_SWORD) && item1.getItemMeta().getDisplayName().contains("Ancient Sword")) {
                p.getInventory().remove(item1);
                AncientSword.CD.remove(p.getUniqueId());
                break;
            }
        }
    }
    
    public void playerFall(final Player p) {
        new BukkitRunnable() {
            boolean test = false;
            
            public void run() {
                if (this.test) {
                    if (!ClassesPerms.acrobat.contains(p.getUniqueId())) {
                        ClassesPerms.nofalldmg.remove(p.getUniqueId());
                        p.setAllowFlight(true);
                    }
                    this.cancel();
                }
                if (p.isOnGround() || !p.isOnline()) {
                    this.test = true;
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
}
