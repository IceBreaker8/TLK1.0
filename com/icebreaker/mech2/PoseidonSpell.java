// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.util.Vector;
import org.bukkit.Location;
import java.util.Iterator;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.Particle;
import org.bukkit.entity.FallingBlock;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class PoseidonSpell implements Listener
{
    public HashMap<UUID, Integer> cooldownTimeP;
    
    public PoseidonSpell() {
        this.cooldownTimeP = new HashMap<UUID, Integer>();
    }
    
    @EventHandler
    public void onUse(final PlayerInteractEvent e) {
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
        if (stack.getItemMeta().getDisplayName().contains("Poseidon Spear")) {
            if (this.cooldownTimeP.containsKey(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You need to wait " + this.cooldownTimeP.get(p.getUniqueId()) + " seconds to use that again!");
                return;
            }
            if (ClassesPerms.hackedPeople.contains(p.getUniqueId())) {
                p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").append(ChatColor.DARK_RED).append(ChatColor.BOLD).append(" You got hacked! ").append(ChatColor.RED).append(ChatColor.MAGIC).append("mlkjh").toString());
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            ClassesPerms.nofalldmg.add(p.getUniqueId());
            this.poseidonSpell(p);
            this.cooldownTimeP.put(p.getUniqueId(), 4);
            new BukkitRunnable() {
                public void run() {
                    PoseidonSpell.this.cooldownTimeP.put(p.getUniqueId(), PoseidonSpell.this.cooldownTimeP.get(p.getUniqueId()) - 1);
                    if (PoseidonSpell.this.cooldownTimeP.get(p.getUniqueId()) <= 0) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                        PoseidonSpell.this.cooldownTimeP.remove(p.getUniqueId());
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
        }
    }
    
    public void poseidonSpell(final Player p) {
        final FallingBlock b1 = p.getWorld().spawnFallingBlock(p.getLocation().add(0.0, 1.5, 0.0), Material.PRISMARINE, (byte)0);
        b1.setVelocity(p.getEyeLocation().getDirection().multiply(2.0f));
        b1.setDropItem(false);
        b1.getWorld().playSound(b1.getLocation(), Sound.WEATHER_RAIN_ABOVE, 10.0f, 1.0f);
        new BukkitRunnable() {
            double t = 0.0;
            
            public void run() {
                ++this.t;
                b1.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, b1.getLocation(), 250, 0.20000000298023224, 0.20000000298023224, 0.20000000298023224, 100.0);
                for (final Entity e : b1.getLocation().getWorld().getEntities()) {
                    if (e.getLocation().distance(b1.getLocation()) < 2.0 && e != p && !(e instanceof ArmorStand) && e.getType().isAlive() && PoseidonSpell.this.noTeamFire(p, e)) {
                        final LivingEntity b = (LivingEntity)e;
                        final Location locb = b1.getLocation();
                        b.damage(2.0, (Entity)p);
                        PoseidonSpell.this.targetFound((Damageable)b, p, locb);
                        b1.remove();
                        this.cancel();
                    }
                }
                if (this.t == 50.0) {
                    b1.remove();
                    this.cancel();
                }
                if (b1.isOnGround()) {
                    b1.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 2L);
    }
    
    public void targetFound(final Damageable b, final Player p, final Location locb) {
        p.getLocation().getWorld().playSound(locb, Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 5.0f, 1.0f);
        locb.getWorld().spawnParticle(Particle.WATER_DROP, locb, 1000, 2.0, 2.0, 2.0, 0.1);
        final FallingBlock b2 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b3 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b4 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b5 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b6 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b7 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b8 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        final FallingBlock b9 = p.getWorld().spawnFallingBlock(locb, Material.PRISMARINE, (byte)0);
        b2.setDropItem(false);
        b3.setDropItem(false);
        b4.setDropItem(false);
        b5.setDropItem(false);
        b6.setDropItem(false);
        b7.setDropItem(false);
        b8.setDropItem(false);
        b9.setDropItem(false);
        b2.setVelocity(new Vector(0.0, 0.5, 0.25));
        b3.setVelocity(new Vector(0.25, 0.5, 0.0));
        b4.setVelocity(new Vector(0.25, 0.5, 0.25));
        b5.setVelocity(new Vector(0.0, 0.5, -0.25));
        b6.setVelocity(new Vector(-0.25, 0.5, 0.0));
        b7.setVelocity(new Vector(-0.25, 0.5, -0.25));
        b8.setVelocity(new Vector(-0.25, 0.5, 0.25));
        b9.setVelocity(new Vector(0.25, 0.5, -0.25));
        final Vector direction = b.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
        direction.setX(direction.getX() * 1.0);
        direction.setY(direction.getY() * 1.0 + 0.5);
        direction.setZ(direction.getZ() * 1.0);
        b.setVelocity(direction);
        final Location loc = b.getLocation();
        b.getWorld().spawnParticle(Particle.WATER_BUBBLE, loc, 500, 2.0, 2.0, 2.0, 50.0);
        for (final Entity e : loc.getWorld().getEntities()) {
            if (e.getLocation().distance(loc) < 3.0 && e.getType().isAlive() && !e.equals(p) && !(e instanceof ArmorStand)) {
                final LivingEntity b10 = (LivingEntity)e;
                if (!this.noTeamFire(p, (Entity)b10)) {
                    continue;
                }
                b10.damage(34.0, (Entity)p);
            }
        }
    }
    
    public boolean noTeamFire(final Player p, final Entity e) {
        return (!ClassesPerms.redteam.contains(p.getUniqueId()) || !ClassesPerms.redteam.contains(e.getUniqueId())) && (!ClassesPerms.blueteam.contains(p.getUniqueId()) || !ClassesPerms.blueteam.contains(e.getUniqueId()));
    }
}
