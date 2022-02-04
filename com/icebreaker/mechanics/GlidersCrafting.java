// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class GlidersCrafting
{
    public static void hrecipe() {
        final ItemStack item = new ItemStack(Material.ELYTRA, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Gliders").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        im.setUnbreakable(true);
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Grants the ability to surf the skies");
        im.setLore((List)lore);
        item.setItemMeta(im);
        final ShapedRecipe hrecipe = new ShapedRecipe(item);
        hrecipe.shape(new String[] { "@?@", "@!@", "@#@" });
        hrecipe.setIngredient('?', Material.IRON_BLOCK);
        hrecipe.setIngredient('@', Material.IRON_INGOT);
        hrecipe.setIngredient('!', Material.FEATHER);
        hrecipe.setIngredient('#', Material.GOLD_BLOCK);
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
    }
}
