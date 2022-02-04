// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.event.EventHandler;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Inventory;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PerkInventory implements Listener
{
    public void openPerk(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9, new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Kill Perks").toString());
        final ItemStack item = new ItemStack(Material.RAW_FISH, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Burp").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Requires 60 souls.");
        im.setLore((List)lore);
        item.setItemMeta(im);
        inv.setItem(0, item);
        final ItemStack item2 = new ItemStack(Material.BONE, 1);
        final ItemMeta im2 = item2.getItemMeta();
        im2.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Wolf Howl").toString());
        im2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add(ChatColor.GREEN + "Requires 150 souls.");
        im2.setLore((List)lore2);
        item2.setItemMeta(im2);
        inv.setItem(1, item2);
        p.openInventory(inv);
        final ItemStack item3 = new ItemStack(Material.EMERALD, 1);
        final ItemMeta im3 = item3.getItemMeta();
        im3.setDisplayName(new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("Villager Death").toString());
        im3.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore3 = new ArrayList<String>();
        lore3.add(ChatColor.GREEN + "Requires 180 souls.");
        im3.setLore((List)lore3);
        item3.setItemMeta(im3);
        inv.setItem(2, item3);
        p.openInventory(inv);
        final ItemStack item4 = new ItemStack(Material.BLAZE_POWDER, 1);
        final ItemMeta im4 = item4.getItemMeta();
        im4.setDisplayName(new StringBuilder().append(ChatColor.BLUE).append(ChatColor.BOLD).append("Blaze Death").toString());
        im4.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore4 = new ArrayList<String>();
        lore4.add(ChatColor.GREEN + "Requires 200 souls.");
        im4.setLore((List)lore4);
        item4.setItemMeta(im4);
        inv.setItem(3, item4);
        p.openInventory(inv);
        final ItemStack item5 = new ItemStack(Material.BLAZE_ROD, 1);
        final ItemMeta im5 = item5.getItemMeta();
        im5.setDisplayName(new StringBuilder().append(ChatColor.DARK_BLUE).append(ChatColor.BOLD).append("ThunderStrike").toString());
        im5.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore5 = new ArrayList<String>();
        lore5.add(ChatColor.GREEN + "Requires 250 souls.");
        im5.setLore((List)lore5);
        item5.setItemMeta(im5);
        inv.setItem(4, item5);
        p.openInventory(inv);
        final ItemStack item6 = new ItemStack(Material.PRISMARINE_SHARD, 1);
        final ItemMeta im6 = item6.getItemMeta();
        im6.setDisplayName(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Guardian").toString());
        im6.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore6 = new ArrayList<String>();
        lore6.add(ChatColor.GREEN + "Requires 300 souls.");
        im6.setLore((List)lore6);
        item6.setItemMeta(im6);
        inv.setItem(5, item6);
        p.openInventory(inv);
        final ItemStack item7 = new ItemStack(Material.COAL, 1);
        final ItemMeta im7 = item7.getItemMeta();
        im7.setDisplayName(new StringBuilder().append(ChatColor.DARK_GRAY).append(ChatColor.BOLD).append("Shadow").toString());
        im7.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore7 = new ArrayList<String>();
        lore7.add(ChatColor.GREEN + "Requires 360 souls.");
        im7.setLore((List)lore7);
        item7.setItemMeta(im7);
        inv.setItem(6, item7);
        p.openInventory(inv);
        final ItemStack item8 = new ItemStack(Material.TNT, 1);
        final ItemMeta im8 = item8.getItemMeta();
        im8.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("RIP").toString());
        im8.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore8 = new ArrayList<String>();
        lore8.add(ChatColor.GREEN + "Requires 420 souls.");
        im8.setLore((List)lore8);
        item8.setItemMeta(im8);
        inv.setItem(7, item8);
        p.openInventory(inv);
        final ItemStack item9 = new ItemStack(Material.EGG, 1);
        final ItemMeta im9 = item9.getItemMeta();
        im9.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Da Last Chikken").toString());
        im9.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore9 = new ArrayList<String>();
        lore9.add(ChatColor.GREEN + "Requires 500 souls.");
        im9.setLore((List)lore9);
        item9.setItemMeta(im9);
        inv.setItem(8, item9);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onInvClick(final InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("Kill Perks")) {
            e.setCancelled(true);
        }
        if (e.getInventory().getTitle().equalsIgnoreCase(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Kill Perks").toString())) {
            if (!e.getCurrentItem().hasItemMeta()) {
                return;
            }
            if (e.getCurrentItem() == null) {
                return;
            }
            final PerksXp f = new PerksXp();
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Burp")) {
                if (ClassesPerms.burp.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getBurp(e.getWhoClicked()).equals("burp")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.burp.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Burp effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 60) {
                    if (f.getBurp(e.getWhoClicked()).contains("burp")) {
                        return;
                    }
                    f.setBurp((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -60);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Burp attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 60) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Wolf")) {
                if (ClassesPerms.wolf.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getWolf(e.getWhoClicked()).contains("wolf")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.wolf.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Wolf effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 150) {
                    if (f.getWolf(e.getWhoClicked()).contains("wolf")) {
                        return;
                    }
                    f.setWolf((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -150);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Wolf attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 150) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Villager")) {
                if (ClassesPerms.vil.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getVil(e.getWhoClicked()).contains("Villager")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.vil.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Villager Death effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 180) {
                    if (f.getVil(e.getWhoClicked()).contains("Villager")) {
                        return;
                    }
                    f.setVil((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -180);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Villager Death attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 180) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Blaze")) {
                if (ClassesPerms.blaze.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getBlaze(e.getWhoClicked()).contains("Blaze")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.blaze.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Blaze Death effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 200) {
                    if (f.getBlaze(e.getWhoClicked()).contains("Blaze")) {
                        return;
                    }
                    f.setBlaze((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -200);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Blaze Death attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 200) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("ThunderStrike")) {
                if (ClassesPerms.thunder.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getThunder(e.getWhoClicked()).contains("Thunderstrike")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.thunder.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Thunderstrike effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 250) {
                    if (f.getThunder(e.getWhoClicked()).contains("Thunderstrike")) {
                        return;
                    }
                    f.setThunder((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -250);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Thunderstrike attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 250) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Guardian")) {
                if (ClassesPerms.guardian.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getG(e.getWhoClicked()).contains("Guardian")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.guardian.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Guardian effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 300) {
                    if (f.getG(e.getWhoClicked()).contains("Guardian")) {
                        return;
                    }
                    f.setG((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -300);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Guardian attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 300) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Shadow")) {
                if (ClassesPerms.Shadow.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getShadow(e.getWhoClicked()).contains("Shadow")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.Shadow.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Shadow effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 360) {
                    if (f.getShadow(e.getWhoClicked()).contains("Shadow")) {
                        return;
                    }
                    f.setShadow((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -360);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Shadow attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 360) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("RIP")) {
                if (ClassesPerms.Explosion.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getEx(e.getWhoClicked()).contains("Explosion")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.Explosion.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Explosion effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 420) {
                    if (f.getEx(e.getWhoClicked()).contains("Explosion")) {
                        return;
                    }
                    f.setEx((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -420);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Explosion attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 420) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Da Last Chikken")) {
                if (ClassesPerms.chikken.contains(e.getWhoClicked().getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You already have this perk active!");
                    return;
                }
                if (f.getChikken(e.getWhoClicked()).contains("Chikken")) {
                    this.removeAllPerks(e.getWhoClicked());
                    ClassesPerms.chikken.add(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(new StringBuilder().append(ChatColor.YELLOW).append(ChatColor.BOLD).append("Da Last Chikken effect on!").toString());
                    return;
                }
                if (f.getSouls((Player)e.getWhoClicked()) >= 500) {
                    if (f.getChikken(e.getWhoClicked()).contains("Chikken")) {
                        return;
                    }
                    f.setChikken((Player)e.getWhoClicked());
                    f.setSouls((Player)e.getWhoClicked(), -500);
                    e.getWhoClicked().sendMessage(ChatColor.AQUA + "You purchased the Da Last Chikken attack effect!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
                }
                else if (f.getSouls((Player)e.getWhoClicked()) < 500) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have enough souls to purchase this perk!");
                    ((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
                }
            }
        }
    }
    
    public void removeAllPerks(final HumanEntity e) {
        ClassesPerms.blaze.remove(e.getUniqueId());
        ClassesPerms.wolf.remove(e.getUniqueId());
        ClassesPerms.burp.remove(e.getUniqueId());
        ClassesPerms.vil.remove(e.getUniqueId());
        ClassesPerms.thunder.remove(e.getUniqueId());
        ClassesPerms.guardian.remove(e.getUniqueId());
        ClassesPerms.Shadow.remove(e.getUniqueId());
        ClassesPerms.Explosion.remove(e.getUniqueId());
        ClassesPerms.chikken.remove(e.getUniqueId());
    }
}
