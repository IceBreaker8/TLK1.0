// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.gamemenu;

import org.bukkit.Chunk;
import org.bukkit.inventory.Recipe;
import org.bukkit.material.MaterialData;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.World;
import TeamColors.NameChanger;
import TeamColors.TeamAction;
import com.icebreaker.mech2.PerksXp;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import com.icebreaker.testing.Test;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.HumanEntity;
import java.util.Iterator;
import org.bukkit.GameMode;
import com.icebreaker.lobbyscoreboard.PlayerJoinCount;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Entity;
import com.icebreaker.ranks.Rank;
import com.icebreaker.ranks.RankingSystem;
import com.icebreaker.GUI.ClassesPerms;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.icebreaker.mech2.DivingHelmet;
import com.icebreaker.mechanics.BlowDartsRecipe;
import com.icebreaker.mechanics.GlidersCrafting;
import com.icebreaker.mechanics.SmelterMech;
import com.icebreaker.mechanics.BonFireSystem;
import com.icebreaker.mechanics.PickaxeRecipe;
import org.bukkit.event.Listener;

public class GameGUI implements Listener
{
    public static boolean verif;
    public boolean dontClickAgain;
    
    static {
        GameGUI.verif = false;
    }
    
    public GameGUI() {
        this.dontClickAgain = true;
    }
    
    private void allMethodStart() {
        final ScoreboardsF score = new ScoreboardsF();
        score.bossTimer();
        ScoreboardsF.chickenXXX();
        PickaxeRecipe.pickRecipe();
        final BonFireSystem bonfire = new BonFireSystem();
        bonfire.bonfire();
        bonfire.bonfireSH();
        final SmelterMech smelter = new SmelterMech();
        smelter.smelter();
        GlidersCrafting.hrecipe();
        BlowDartsRecipe.hrecipe();
        BlowDartsRecipe.Rrecipe();
        revivalRecipe();
        DivingHelmet.diveRecipe();
        DivingHelmet.spearRecipe();
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
        if (stack.getItemMeta().getDisplayName().contains("GameMenu") && p.getName().equals("icebreaker970")) {
            if (!p.getName().equals("icebreaker970")) {
                p.sendMessage(ChatColor.RED + "You don't have the permission to use that!");
                return;
            }
            this.createMenu(p);
        }
    }
    
    public void createMenu(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 27, new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Game Menu").toString());
        final ItemStack item20 = new ItemStack(Material.LAPIS_BLOCK, 1);
        final ItemMeta im20 = item20.getItemMeta();
        im20.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans").toString());
        item20.setItemMeta(im20);
        inv.setItem(19, item20);
        final ItemStack item21 = new ItemStack(Material.EMERALD_BLOCK, 1);
        final ItemMeta im21 = item21.getItemMeta();
        im21.setDisplayName(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Shamans").toString());
        item21.setItemMeta(im21);
        inv.setItem(18, item21);
        final ItemStack item22 = new ItemStack(Material.NETHER_STAR, 1);
        final ItemMeta im22 = item22.getItemMeta();
        im22.setDisplayName(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Recipes").toString());
        item22.setItemMeta(im22);
        inv.setItem(17, item22);
        final ItemStack item23 = new ItemStack(Material.FIREBALL, 1);
        final ItemMeta im23 = item23.getItemMeta();
        im23.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Harm").toString());
        item23.setItemMeta(im23);
        inv.setItem(16, item23);
        final ItemStack item24 = new ItemStack(Material.SKULL_ITEM, 1, (short)1);
        final ItemMeta im24 = item24.getItemMeta();
        im24.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Tribal Mask").toString());
        item24.setItemMeta(im24);
        inv.setItem(15, item24);
        final ItemStack item25 = new ItemStack(Material.FEATHER, 1);
        final ItemMeta im25 = item25.getItemMeta();
        im25.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Chicken Spawn").toString());
        item25.setItemMeta(im25);
        inv.setItem(14, item25);
        final ItemStack item26 = new ItemStack(Material.OBSIDIAN, 1);
        final ItemMeta im26 = item26.getItemMeta();
        im26.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Knight Spawn").toString());
        item26.setItemMeta(im26);
        inv.setItem(13, item26);
        final ItemStack item27 = new ItemStack(Material.REDSTONE, 1);
        final ItemMeta im27 = item27.getItemMeta();
        im27.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("No Harm").toString());
        im27.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item27.setItemMeta(im27);
        inv.setItem(12, item27);
        final ItemStack item28 = new ItemStack(Material.WOOD, 1);
        final ItemMeta im28 = item28.getItemMeta();
        im28.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Builder Perm").toString());
        im28.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item28.setItemMeta(im28);
        inv.setItem(11, item28);
        final ItemStack item29 = new ItemStack(Material.STICK, 1);
        final ItemMeta im29 = item29.getItemMeta();
        im29.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Owner Perm").toString());
        im29.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item29.setItemMeta(im29);
        inv.setItem(10, item29);
        final ItemStack item30 = new ItemStack(Material.MONSTER_EGG, 1);
        final ItemMeta im30 = item30.getItemMeta();
        im30.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Wither spawn").toString());
        im30.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item30.setItemMeta(im30);
        inv.setItem(9, item30);
        final ItemStack item31 = new ItemStack(Material.SAND, 1);
        final ItemMeta im31 = item31.getItemMeta();
        im31.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Island Teleport").toString());
        im31.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore7 = new ArrayList<String>();
        lore7.add("");
        im31.setLore((List)lore7);
        item31.setItemMeta(im31);
        inv.setItem(6, item31);
        final ItemStack item32 = new ItemStack(Material.BEDROCK, 1);
        final ItemMeta im32 = item32.getItemMeta();
        im32.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Nexus kill").toString());
        im32.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore8 = new ArrayList<String>();
        lore8.add("");
        im32.setLore((List)lore8);
        item32.setItemMeta(im32);
        inv.setItem(8, item32);
        final ItemStack item33 = new ItemStack(Material.QUARTZ, 1);
        final ItemMeta im33 = item33.getItemMeta();
        im33.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Lobby Teleport").toString());
        im33.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore9 = new ArrayList<String>();
        lore9.add("");
        im33.setLore((List)lore9);
        item33.setItemMeta(im33);
        inv.setItem(7, item33);
        final ItemStack item34 = new ItemStack(Material.EMERALD_BLOCK, 1);
        final ItemMeta im34 = item34.getItemMeta();
        im34.setDisplayName(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Start the game").toString());
        im34.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore10 = new ArrayList<String>();
        lore10.add("");
        im34.setLore((List)lore10);
        item34.setItemMeta(im34);
        inv.setItem(0, item34);
        final ItemStack item35 = new ItemStack(Material.REDSTONE_BLOCK, 1);
        final ItemMeta im35 = item35.getItemMeta();
        im35.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("End the game").toString());
        im35.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore11 = new ArrayList<String>();
        lore11.add("");
        im35.setLore((List)lore11);
        item35.setItemMeta(im35);
        inv.setItem(1, item35);
        final ItemStack item36 = new ItemStack(Material.SIGN, 1);
        final ItemMeta im36 = item36.getItemMeta();
        im36.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Scoreboard Appear").toString());
        im36.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore12 = new ArrayList<String>();
        lore12.add("");
        im36.setLore((List)lore12);
        item36.setItemMeta(im36);
        inv.setItem(2, item36);
        final ItemStack item37 = new ItemStack(Material.BOOK, 1);
        final ItemMeta im37 = item37.getItemMeta();
        im37.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Scoreboard Vanish").toString());
        im37.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore13 = new ArrayList<String>();
        lore13.add("");
        im37.setLore((List)lore13);
        item37.setItemMeta(im37);
        inv.setItem(3, item37);
        final ItemStack item38 = new ItemStack(Material.TNT, 1);
        final ItemMeta im38 = item38.getItemMeta();
        im38.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Scoreboard Reset").toString());
        im38.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore14 = new ArrayList<String>();
        lore14.add("");
        im38.setLore((List)lore14);
        item38.setItemMeta(im38);
        inv.setItem(4, item38);
        final ItemStack item39 = new ItemStack(Material.DIAMOND_SWORD, 1);
        final ItemMeta im39 = item39.getItemMeta();
        im39.setDisplayName(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("bonfireShut").toString());
        im39.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore15 = new ArrayList<String>();
        lore15.add("");
        im39.setLore((List)lore15);
        item39.setItemMeta(im39);
        inv.setItem(5, item39);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onInvClick(final InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equalsIgnoreCase(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Game Menu").toString())) {
            return;
        }
        if (!e.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Shamans")) {
            e.setCancelled(true);
            ClassesPerms.redteam.add(e.getWhoClicked().getUniqueId());
            ClassesPerms.blueteam.remove(e.getWhoClicked().getUniqueId());
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Humans")) {
            e.setCancelled(true);
            ClassesPerms.redteam.remove(e.getWhoClicked().getUniqueId());
            ClassesPerms.blueteam.add(e.getWhoClicked().getUniqueId());
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Recipes")) {
            e.setCancelled(true);
            this.allMethodStart();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Tribal Mask")) {
            e.setCancelled(true);
            this.createTribalMask(e.getWhoClicked());
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Harm")) {
            e.setCancelled(true);
            for (final Player p : Bukkit.getOnlinePlayers()) {
                ClassesPerms.noharm.remove(p.getUniqueId());
            }
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Wither")) {
            e.setCancelled(true);
            final MobNexus m = new MobNexus();
            m.spawnNexuses();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Chicken")) {
            e.setCancelled(true);
            final MobNexus m = new MobNexus();
            m.spawnChicken();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Knight")) {
            e.setCancelled(true);
            final MobNexus m = new MobNexus();
            m.spawnKnight();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("No Harm")) {
            e.setCancelled(true);
            for (final Player p : Bukkit.getOnlinePlayers()) {
                ClassesPerms.noharm.add(p.getUniqueId());
            }
            DivingHelmet.diveRecipe();
            DivingHelmet.spearRecipe();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Builder Perm")) {
            e.setCancelled(true);
            final RankingSystem r = new RankingSystem();
            r.setRank((Player)e.getWhoClicked(), Rank.BUILDER);
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Owner Perm")) {
            e.setCancelled(true);
            final RankingSystem r = new RankingSystem();
            r.setRank((Player)e.getWhoClicked(), Rank.OWNER);
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby")) {
            e.setCancelled(true);
            this.tpEnd();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Island")) {
            e.setCancelled(true);
            this.islandTp();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Nexus kill")) {
            e.setCancelled(true);
            for (final Entity e2 : Bukkit.getServer().getWorld("world").getEntities()) {
                if (e2 instanceof Wither) {
                    final LivingEntity ent = (LivingEntity)e2;
                    ent.damage(9999.0);
                }
            }
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Scoreboard Appear")) {
            e.setCancelled(true);
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Scoreboard has appeared.").toString());
            for (final Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
            }
            final ScoreboardsF s = new ScoreboardsF();
            s.appearScoreboard();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Scoreboard Reset")) {
            e.setCancelled(true);
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Scoreboard has been reset.").toString());
            for (final Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10.0f, 1.0f);
            }
            ScoreboardsF.stop = true;
            ScoreboardsF.chickenstop = true;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Scoreboard Vanish")) {
            e.setCancelled(true);
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Scoreboard has vanished.").toString());
            for (final Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
            }
            final ScoreboardsF s = new ScoreboardsF();
            s.vanishScoreboard();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("bonfireShut")) {
            e.setCancelled(true);
            final PlayerJoinCount kek = new PlayerJoinCount();
            kek.checkScoreboard();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Start the game")) {
            if (this.dontClickAgain) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.getInventory().clear();
                }
                Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Starting Game...").toString());
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    p.getInventory().clear();
                }
                this.dontClickAgain = false;
                GameGUI.verif = true;
                this.gameStart();
            }
            e.setCancelled(true);
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("End the game")) {
            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Shutting down Game...").toString());
            for (final Player p : Bukkit.getOnlinePlayers()) {
                ClassesPerms.blueteam.remove(p.getUniqueId());
                ClassesPerms.redteam.remove(p.getUniqueId());
                if (!p.getName().equals("icebreaker970")) {
                    p.getInventory().clear();
                }
                for (final Entity e3 : Bukkit.getServer().getWorld("world").getEntities()) {
                    if (e3 instanceof Wither) {
                        final LivingEntity ent2 = (LivingEntity)e3;
                        ent2.damage(9999.0);
                    }
                }
                p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 10.0f, 1.0f);
            }
            GameGUI.verif = false;
            e.setCancelled(this.dontClickAgain = true);
            this.tpEnd();
        }
    }
    
    private void createTribalMask(final HumanEntity p) {
        final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.BOLD).append("Tribal Mask").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Grants unlimited power to who wears it!").toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        p.getInventory().addItem(new ItemStack[] { item });
    }
    
    public void gameStart() {
        new BukkitRunnable() {
            int t = 41;
            
            public void run() {
                if (!GameGUI.verif) {
                    this.cancel();
                    return;
                }
                --this.t;
                if (this.t == 40) {
                    GameGUI.revivalRecipe();
                    GameGUI.this.tpStart();
                    Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Game starts in 40 seconds.").toString());
                    final ScoreboardsF s = new ScoreboardsF();
                    s.scoreboard();
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        for (final PotionEffect effect : p.getActivePotionEffects()) {
                            p.removePotionEffect(effect.getType());
                        }
                        final GameTeamItems item = new GameTeamItems();
                        item.CustomItem(p);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 100.0f, 1.0f);
                    }
                }
                if (this.t == 20) {
                    Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Game starts in 20 seconds. ").toString());
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        p2.playSound(p2.getLocation(), Sound.BLOCK_NOTE_PLING, 100.0f, 1.0f);
                    }
                }
                if (this.t <= 10) {
                    Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Game starts in ").append(this.t).toString());
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        p2.playSound(p2.getLocation(), Sound.BLOCK_NOTE_HAT, 100.0f, 1.0f);
                    }
                }
                if (this.t <= 0) {
                    this.cancel();
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        GameGUI.this.teamCompare(p2);
                        p2.getInventory().clear();
                        p2.getActivePotionEffects().clear();
                    }
                    GameGUI.this.makeTeams();
                    ScoreboardsF.stop = false;
                    GameGUI.this.allMethodStart();
                    GameGUI.this.onGameStart();
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        ClassesPerms.noharm.remove(p2.getUniqueId());
                        p2.setGameMode(GameMode.SURVIVAL);
                        p2.playSound(p2.getLocation(), Sound.BLOCK_NOTE_PLING, 100.0f, 1.0f);
                        p2.getInventory().clear();
                    }
                    Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Game has started. ").toString());
                    GameGUI.this.dontClickAgain = true;
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 20L, 20L);
    }
    
    public void teamCompare(final Player p) {
        if (ClassesPerms.redteam.contains(p.getUniqueId()) || ClassesPerms.blueteam.contains(p.getUniqueId())) {
            return;
        }
        if (ClassesPerms.redteam.size() < 6 && ClassesPerms.redteam.size() < ClassesPerms.blueteam.size()) {
            ClassesPerms.redteam.add(p.getUniqueId());
            p.sendMessage("You were send to shamans team as you didn't choose a team!");
        }
        else if (ClassesPerms.blueteam.size() < 6 && ClassesPerms.redteam.size() > ClassesPerms.blueteam.size()) {
            ClassesPerms.blueteam.add(p.getUniqueId());
            p.sendMessage(ChatColor.RED + "You were send to humans team as you didn't choose a team!");
        }
        else {
            p.sendMessage(ChatColor.AQUA + "Teams are full or you didn't choose one , sorry but you need to wait the next round.");
        }
    }
    
    public void tpStart() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.getGameMode() == GameMode.SPECTATOR) {
                return;
            }
            p.teleport(new Location(p.getWorld(), 564.0, 128.0, 113.0));
        }
    }
    
    public void tpEnd() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.teleport(new Location(p.getWorld(), -53.0, 93.0, 623.0));
        }
    }
    
    public void islandTp() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                p.teleport(new Location(p.getWorld(), -134.0, 108.0, -52.0));
            }
            else if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                p.teleport(new Location(p.getWorld(), 139.0, 63.0, 98.0));
            }
            else {
                p.teleport(new Location(p.getWorld(), -53.0, 93.0, 623.0));
            }
        }
    }
    
    public void onGameStart() {
        final MobNexus m = new MobNexus();
        m.spawnNexuses();
        this.islandTp();
        new BukkitRunnable() {
            public void run() {
                if (ScoreboardsF.sR.getScore() <= 0) {
                    for (final Entity en : Bukkit.getServer().getWorld("world").getEntities()) {
                        if (en instanceof Wither && en.getCustomName().contains("Red")) {
                            ((Wither)en).damage(9999.0);
                            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Humans won the game!").toString());
                            ScoreboardsF.sR.setScore(0);
                            en.getLocation().getWorld().playSound(en.getLocation(), Sound.ENTITY_WITHER_DEATH, 30.0f, 1.0f);
                            for (final Player p : Bukkit.getOnlinePlayers()) {
                                if (ClassesPerms.blueteam.contains(p.getUniqueId())) {
                                    final PerksXp f = new PerksXp();
                                    f.setSouls(p, 20);
                                    p.sendMessage(ChatColor.WHITE + "+ 20 " + ChatColor.YELLOW + "soul.");
                                }
                                else {
                                    if (!ClassesPerms.redteam.contains(p.getUniqueId())) {
                                        continue;
                                    }
                                    final PerksXp f = new PerksXp();
                                    f.setSouls(p, 10);
                                    p.sendMessage(ChatColor.WHITE + "+ 10 " + ChatColor.YELLOW + "soul.");
                                }
                            }
                            GameGUI.this.spawnFireworks();
                            this.cancel();
                        }
                    }
                }
                if (ScoreboardsF.sB.getScore() <= 0) {
                    for (final Entity en : Bukkit.getServer().getWorld("world").getEntities()) {
                        if (en instanceof Wither && en.getCustomName().contains("Blue")) {
                            ((Wither)en).damage(9999.0);
                            ScoreboardsF.sB.setScore(0);
                            Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Shamans won the game!").toString());
                            en.getLocation().getWorld().playSound(en.getLocation(), Sound.ENTITY_WITHER_DEATH, 30.0f, 1.0f);
                            GameGUI.this.spawnFireworks();
                            for (final Player p : Bukkit.getOnlinePlayers()) {
                                if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                                    final PerksXp f = new PerksXp();
                                    f.setSouls(p, 20);
                                    p.sendMessage(ChatColor.WHITE + "+ 20 " + ChatColor.YELLOW + "soul.");
                                }
                                else {
                                    if (!ClassesPerms.blueteam.contains(p.getUniqueId())) {
                                        continue;
                                    }
                                    final PerksXp f = new PerksXp();
                                    f.setSouls(p, 10);
                                    p.sendMessage(ChatColor.WHITE + "+ 10 " + ChatColor.YELLOW + "soul.");
                                }
                            }
                            this.cancel();
                        }
                    }
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 10L);
    }
    
    public void spawnFireworks() {
        final Location loc = new Location(Bukkit.getWorld("world"), 68.0, 106.0, -86.0);
        loc.getBlock().setType(Material.AIR);
        Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Game ends in 60 seconds...").toString());
        final ScoreboardsF s = new ScoreboardsF();
        s.setBleed(false);
        ScoreboardsF.stop = true;
        ScoreboardsF.chickenstop = true;
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.setGameMode(GameMode.ADVENTURE);
            p.getInventory().clear();
            ClassesPerms.noharm.add(p.getUniqueId());
            p.teleport(new Location(Bukkit.getWorld("world"), -188.0, 64.0, -137.0));
            p.setAllowFlight(true);
            p.setFlying(true);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 10.0f, 1.0f);
        }
        new BukkitRunnable() {
            int t = 60;
            
            public void run() {
                --this.t;
                if (this.t == 0) {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        final World w = Bukkit.getWorld("world");
                        final double x = 347.0;
                        final double y = 67.0;
                        final double z = 275.0;
                        final Location loc = new Location(w, x, y, z);
                        p.teleport(loc);
                        ClassesPerms.noharm.add(p.getUniqueId());
                        p.setMaxHealth(20.0);
                        p.setHealth(20.0);
                        for (final PotionEffect effect : p.getActivePotionEffects()) {
                            if (effect != null) {
                                p.removePotionEffect(effect.getType());
                            }
                        }
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.setGameMode(GameMode.ADVENTURE);
                        p.getInventory().clear();
                        p.setMaxHealth(20.0);
                        NameChanger.changePlayerName(p, null, null, TeamAction.UPDATE);
                        p.setPlayerListName(p.getName());
                    }
                    final MobNexus m = new MobNexus();
                    m.killEntities();
                    ClassesPerms.blueteam.clear();
                    ClassesPerms.redteam.clear();
                    final ScoreboardsF s = new ScoreboardsF();
                    s.vanishScoreboard();
                    MobNexus.knightstop = true;
                    new BukkitRunnable() {
                        public void run() {
                            MobNexus.knightstop = false;
                        }
                    }.runTaskLater((Plugin)Test.plugin, 100L);
                    GameGUI.this.bonfireShut();
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        ClassesPerms.noharm.add(p2.getUniqueId());
                        p2.kickPlayer(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Want to play again? Then rejoin the server!").toString());
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Test.plugin, 0L, 20L);
    }
    
    public static void revivalRecipe() {
        final ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Ancient Revival").toString());
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD).append("Used to revive ancient mythical weapons").toString());
        im.setLore((List)lore);
        item.setItemMeta(im);
        final ShapedRecipe hrecipe = new ShapedRecipe(item);
        hrecipe.shape(new String[] { "!#!", "@@@", "!@!" });
        hrecipe.setIngredient('@', Material.DIAMOND_BLOCK);
        hrecipe.setIngredient('#', new MaterialData(Material.SKULL_ITEM, (byte)1));
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
    }
    
    public void makeTeams() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (ClassesPerms.redteam.contains(p.getUniqueId())) {
                NameChanger.changePlayerName(p, new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("SHAMAN ").append(ChatColor.GREEN).toString(), "", TeamAction.CREATE1);
                p.setPlayerListName(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("[SHAMAN] ").append(ChatColor.WHITE).append(p.getName()).toString());
            }
            else {
                if (!ClassesPerms.blueteam.contains(p.getUniqueId())) {
                    continue;
                }
                NameChanger.changePlayerName(p, new StringBuilder().append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("HUMAN ").append(ChatColor.AQUA).toString(), "", TeamAction.CREATE1);
                p.setPlayerListName(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("[HUMAN] ").append(ChatColor.WHITE).append(p.getName()).toString());
            }
        }
    }
    
    public void bonfireShut() {
        Bukkit.getServer().reload();
        final World w = Bukkit.getWorld("world");
        final Chunk c = w.getChunkAt(new Location(w, -150.0, 69.0, -28.0));
        w.loadChunk(c);
        final Chunk c2 = w.getChunkAt(new Location(w, 120.0, 63.0, 101.0));
        w.loadChunk(c2);
        final Location loc1 = new Location(w, -150.0, 69.0, -28.0);
        loc1.getBlock().setType(Material.AIR);
        final Location loc2 = new Location(w, 120.0, 63.0, 101.0);
        loc2.getBlock().setType(Material.AIR);
    }
}
