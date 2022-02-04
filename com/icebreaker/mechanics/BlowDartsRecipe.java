// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import java.util.Iterator;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class BlowDartsRecipe
{
    public static void hrecipe() {
        final ItemStack item = new ItemStack(Material.ARROW, 3);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Arrow").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item.setItemMeta(im);
        final ShapedRecipe hrecipe = new ShapedRecipe(item);
        hrecipe.shape(new String[] { "@?@", "@!@", "@#@" });
        hrecipe.setIngredient('?', Material.FLINT);
        hrecipe.setIngredient('!', Material.STICK);
        hrecipe.setIngredient('#', Material.SUGAR_CANE);
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
    }
    
    public static void Rrecipe() {
        final ItemStack item = new ItemStack(Material.BOW, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Bow").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item.setItemMeta(im);
        final ShapedRecipe hrecipe = new ShapedRecipe(item);
        hrecipe.shape(new String[] { "?!@", "!?@", "?!@" });
        hrecipe.setIngredient('@', Material.STRING);
        hrecipe.setIngredient('!', Material.STICK);
        hrecipe.setIngredient('?', Material.SUGAR_CANE);
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
        final ItemStack sugar = new ItemStack(Material.BOW);
        final ItemStack sugar2 = new ItemStack(Material.ARROW);
        final ItemStack sugar3 = new ItemStack(Material.BUCKET);
        final ItemStack sugar4 = new ItemStack(Material.FLINT_AND_STEEL);
        final ItemStack sugar5 = new ItemStack(Material.BOAT);
        final ItemStack sugar6 = new ItemStack(Material.SHIELD);
        final ItemStack sugar7 = new ItemStack(Material.DIAMOND_AXE);
        final ItemStack sugar8 = new ItemStack(Material.IRON_AXE);
        final ItemStack sugar9 = new ItemStack(Material.GOLD_AXE);
        final Iterator<Recipe> a = (Iterator<Recipe>)Bukkit.getServer().recipeIterator();
        while (a.hasNext()) {
            final ItemStack result = a.next().getResult();
            if (result.isSimilar(sugar) || result.isSimilar(sugar2) || result.isSimilar(sugar3) || result.isSimilar(sugar4) || result.isSimilar(sugar5) || result.isSimilar(sugar6) || result.isSimilar(sugar7) || result.isSimilar(sugar8) || result.isSimilar(sugar9)) {
                a.remove();
            }
        }
    }
}
