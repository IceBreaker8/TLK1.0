// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapelessRecipe;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class PickaxeRecipe
{
    public static void pickRecipe() {
        final ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Ancient Pickaxe").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        im.setUnbreakable(true);
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Used the mine to rarest minerals");
        im.setLore((List)lore);
        item.setItemMeta(im);
        final ShapelessRecipe precipe = new ShapelessRecipe(item);
        precipe.addIngredient(1, Material.FEATHER);
        precipe.addIngredient(1, Material.IRON_PICKAXE);
        Bukkit.getServer().addRecipe((Recipe)precipe);
    }
}
