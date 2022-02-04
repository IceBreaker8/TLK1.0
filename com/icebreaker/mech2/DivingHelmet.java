// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.material.MaterialData;
import org.bukkit.inventory.ShapedRecipe;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class DivingHelmet
{
    public static void diveRecipe() {
        final ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Diving suit").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Used to dive into deep oceans.").toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        final ShapedRecipe hrecipe = new ShapedRecipe(item);
        hrecipe.shape(new String[] { "!#!", "!?!", "@.@" });
        hrecipe.setIngredient('?', Material.DIAMOND_CHESTPLATE);
        hrecipe.setIngredient('.', Material.FEATHER);
        hrecipe.setIngredient('!', Material.RAW_FISH);
        hrecipe.setIngredient('@', Material.PRISMARINE_CRYSTALS);
        hrecipe.setIngredient('#', new MaterialData(Material.SKULL_ITEM, (byte)1));
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
    }
    
    public static void spearRecipe() {
        final ItemStack item = new ItemStack(Material.DIAMOND_SPADE, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Poseidon Spear").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.MAGIC).append(ChatColor.BOLD).append("dfgtebdszd").toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        final ShapedRecipe hrecipe = new ShapedRecipe(item);
        hrecipe.shape(new String[] { "@?@", "@.@", "@!@" });
        hrecipe.setIngredient('?', Material.PRISMARINE_SHARD);
        hrecipe.setIngredient('.', Material.STICK);
        hrecipe.setIngredient('!', Material.STICK);
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
    }
}
