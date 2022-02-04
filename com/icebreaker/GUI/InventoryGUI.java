// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.GUI;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Inventory;
import org.bukkit.DyeColor;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.icebreaker.classes.AcrobatClass;
import org.bukkit.event.Listener;

public class InventoryGUI implements Listener
{
    AcrobatClass ac;
    ItemsGUI it;
    
    public InventoryGUI() {
        this.ac = new AcrobatClass();
        this.it = new ItemsGUI();
    }
    
    public void createMenu(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 27, new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Class Selection").toString());
        final ItemStack item = new ItemStack(Material.FEATHER, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Acrobat").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Double Jump");
        lore.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 20 seconds");
        lore.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 20 seconds, acrobat  ");
        lore.add(ChatColor.WHITE + "can double jump by double clicking ");
        lore.add(ChatColor.WHITE + "spacebar , which will launch ");
        lore.add(ChatColor.WHITE + "them in the air!");
        lore.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " No fall damage");
        im.setLore((List)lore);
        item.setItemMeta(im);
        inv.setItem(0, item);
        final ItemStack item2 = new ItemStack(Material.ENDER_PEARL, 1);
        final ItemMeta im2 = item2.getItemMeta();
        im2.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Endermage").toString());
        im2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("");
        lore2.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Recall");
        lore2.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 30 seconds");
        lore2.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 30 seconds, endermage  ");
        lore2.add(ChatColor.WHITE + "can spawn an enderman in his  ");
        lore2.add(ChatColor.WHITE + "location , then teleport ");
        lore2.add(ChatColor.WHITE + "to it after 10 seconds ");
        lore2.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Permanent haste");
        im2.setLore((List)lore2);
        item2.setItemMeta(im2);
        inv.setItem(1, item2);
        final ItemStack item3 = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.LIGHT_BLUE.getDyeData());
        final ItemMeta im3 = item3.getItemMeta();
        im3.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Cryomancer").toString());
        im3.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore3 = new ArrayList<String>();
        lore3.add("");
        lore3.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Freeze");
        lore3.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 40 seconds");
        lore3.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 40 seconds, cryomancer");
        lore3.add(ChatColor.WHITE + "can launch 3 freezing snowflakes  ");
        lore3.add(ChatColor.WHITE + "in his direction , if collides with ");
        lore3.add(ChatColor.WHITE + "an enemy , damage and affect him and nearby ");
        lore3.add(ChatColor.WHITE + "enemies with a radius of 4 blocks with");
        lore3.add(ChatColor.WHITE + "slowness and mining fatigue for 4 seconds ");
        lore3.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Cold resistance");
        im3.setLore((List)lore3);
        item3.setItemMeta(im3);
        inv.setItem(2, item3);
        final ItemStack item4 = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.RED.getDyeData());
        final ItemMeta im4 = item4.getItemMeta();
        im4.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Pyromancer").toString());
        im4.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore4 = new ArrayList<String>();
        lore4.add("");
        lore4.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Firestorm");
        lore4.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 35 seconds");
        lore4.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 35 seconds, Pyromancer");
        lore4.add(ChatColor.WHITE + "can launch a burning ashe  ");
        lore4.add(ChatColor.WHITE + "in his direction , only when it lands ");
        lore4.add(ChatColor.WHITE + "on ground ,damage and affect nearby enemies");
        lore4.add(ChatColor.WHITE + " with a radius of 4 blocks with");
        lore4.add(ChatColor.WHITE + "burning for 4 seconds ");
        lore4.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Fire resistance");
        im4.setLore((List)lore4);
        item4.setItemMeta(im4);
        inv.setItem(3, item4);
        p.openInventory(inv);
        final ItemStack item5 = new ItemStack(Material.REDSTONE);
        final ItemMeta im5 = item5.getItemMeta();
        im5.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("WarHead").toString());
        im5.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore5 = new ArrayList<String>();
        lore5.add("");
        lore5.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Shield");
        lore5.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 40 seconds");
        lore5.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 40 seconds, WarHead");
        lore5.add(ChatColor.WHITE + "can summon a spheric shield , targets  ");
        lore5.add(ChatColor.WHITE + "when getting on contact with it , get  ");
        lore5.add(ChatColor.WHITE + "launched back , away from it . WarHead have ");
        lore5.add(ChatColor.WHITE + "immunity to all damage and knockback inside ");
        lore5.add(ChatColor.WHITE + "the shield.");
        lore5.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Increased Max Health");
        lore5.add(ChatColor.WHITE + "and Immunity to knockback.");
        im5.setLore((List)lore5);
        item5.setItemMeta(im5);
        inv.setItem(4, item5);
        final ItemStack item6 = new ItemStack(Material.GHAST_TEAR);
        final ItemMeta im6 = item6.getItemMeta();
        im6.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Sniper").toString());
        im6.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore6 = new ArrayList<String>();
        lore6.add("");
        lore6.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Snipe");
        lore6.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 15 seconds");
        lore6.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 15 seconds, Sniper");
        lore6.add(ChatColor.WHITE + "can zoom while sneaking , gaining more accuracy   ");
        lore6.add(ChatColor.WHITE + " shooting enemies from far distances.");
        lore6.add(ChatColor.WHITE + "Headshot insta-kill enemies.");
        lore6.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Lowered Max Health");
        im6.setLore((List)lore6);
        item6.setItemMeta(im6);
        inv.setItem(5, item6);
        final ItemStack item7 = new ItemStack(Material.FLINT);
        final ItemMeta im7 = item7.getItemMeta();
        im7.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Ares").toString());
        im7.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore7 = new ArrayList<String>();
        lore7.add("");
        lore7.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Chaos");
        lore7.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 40 seconds");
        lore7.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 40 seconds, Ares");
        lore7.add(ChatColor.WHITE + "jump into the air, unleashing the rage of the");
        lore7.add(ChatColor.WHITE + "ancient dragon , smashing the land , causing");
        lore7.add(ChatColor.WHITE + "shattered land.");
        lore7.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " No fall Damage");
        im7.setLore((List)lore7);
        item7.setItemMeta(im7);
        inv.setItem(6, item7);
        final ItemStack item8 = new ItemStack(Material.EMERALD);
        final ItemMeta im8 = item8.getItemMeta();
        im8.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Hacker").toString());
        im8.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore8 = new ArrayList<String>();
        lore8.add("");
        lore8.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Hack");
        lore8.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 30 seconds");
        lore8.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 30 seconds, Hacker");
        lore8.add(ChatColor.WHITE + "releases a virus around him , affecting ");
        lore8.add(ChatColor.WHITE + "all nearby enemies , hacking their spells.");
        lore8.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Immunity to hacks");
        im8.setLore((List)lore8);
        item8.setItemMeta(im8);
        inv.setItem(7, item8);
        final ItemStack item9 = new ItemStack(Material.SHULKER_SHELL);
        final ItemMeta im9 = item9.getItemMeta();
        im9.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Hunter").toString());
        im9.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore9 = new ArrayList<String>();
        lore9.add("");
        lore9.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Trap");
        lore9.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 35 seconds");
        lore9.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 35 seconds, Hunter");
        lore9.add(ChatColor.WHITE + "can place a trap on a block and wait 30 seconds.");
        lore9.add(ChatColor.WHITE + "If players step on it before those 30 seconds,");
        lore9.add(ChatColor.WHITE + "they will not be able to move for 4 seconds.");
        lore9.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Get 2 hearts on each kill.");
        im9.setLore((List)lore9);
        item9.setItemMeta(im9);
        inv.setItem(9, item9);
        final ItemStack item10 = new ItemStack(Material.TOTEM);
        final ItemMeta im10 = item10.getItemMeta();
        im10.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Mercy").toString());
        im10.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore10 = new ArrayList<String>();
        lore10.add("");
        lore10.add(ChatColor.RED + "Skill:" + ChatColor.WHITE + " Revive");
        lore10.add(ChatColor.RED + "Cooldown:" + ChatColor.WHITE + " 300 seconds");
        lore10.add(ChatColor.RED + "Description:" + ChatColor.WHITE + " Every 300 seconds, Mercy");
        lore10.add(ChatColor.WHITE + "can revive nearby teammates , with full ");
        lore10.add(ChatColor.WHITE + "health and hunger");
        lore10.add(ChatColor.RED + "Talents:" + ChatColor.WHITE + " Permanent regeneration");
        im10.setLore((List)lore10);
        item10.setItemMeta(im10);
        inv.setItem(8, item10);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onInvClick(final InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("Recipes")) {
            e.setCancelled(true);
        }
        if (!e.getInventory().getTitle().equalsIgnoreCase(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Class Selection").toString())) {
            return;
        }
        if (!e.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (ClassesPerms.oneHeroOnly.contains(e.getWhoClicked().getUniqueId())) {
            e.getWhoClicked().sendMessage(ChatColor.RED + "You can only pick one hero at a time!");
            e.setCancelled(true);
            return;
        }
        for (final PotionEffect effect : e.getWhoClicked().getActivePotionEffects()) {
            e.getWhoClicked().removePotionEffect(effect.getType());
        }
        e.getWhoClicked().getInventory().clear();
        e.getWhoClicked().setMaxHealth(20.0);
        this.removeFlight((Player)e.getWhoClicked());
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Acrobat").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            this.it.acrobatItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Endermage").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            this.it.endermageItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Mercy").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));
            e.setCancelled(true);
            this.it.mercyItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Cryomancer").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            this.it.cryoItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Pyromancer").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999, 0));
            e.setCancelled(true);
            this.it.pyroItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("WarHead").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.add(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            e.getWhoClicked().setMaxHealth(26.0);
            e.getWhoClicked().setHealth(26.0);
            this.it.warheadItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Sniper").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            final ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);
            final ItemMeta im = item.getItemMeta();
            final ArrayList<String> lore = new ArrayList<String>();
            lore.add(new StringBuilder().append(ChatColor.DARK_PURPLE).append(ChatColor.BOLD).append("Class Item").append(ChatColor.DARK_RED).toString());
            im.setLore((List)lore);
            item.setItemMeta(im);
            e.getWhoClicked().getInventory().setHelmet(item);
            e.setCancelled(true);
            e.getWhoClicked().setMaxHealth(18.0);
            e.getWhoClicked().setHealth(18.0);
            this.it.sniperItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Ares").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            this.it.ariesItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Hacker").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            this.it.hackerItems(e.getWhoClicked());
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Hunter").toString())) {
            ClassesPerms.oneHeroOnly.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.hunter.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.acrobat.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.nofalldmg.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.endermage.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.cryo.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.pyro.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.war.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.sniper.remove(e.getWhoClicked().getUniqueId());
            e.setCancelled(true);
            this.it.hunterItems(e.getWhoClicked());
        }
    }
    
    public void removeFlight(final Player p) {
        p.setFlying(false);
        p.setAllowFlight(false);
    }
}
