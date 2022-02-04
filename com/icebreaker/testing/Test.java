// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.testing;

import org.bukkit.plugin.PluginManager;
import com.icebreaker.GUI.InventoryGUI;
import com.icebreaker.GUI.PortalGUI;
import com.icebreaker.GUI.Soulbound;
import com.icebreaker.classes.Endermage;
import com.icebreaker.GUI.ClassItemBind;
import com.icebreaker.classes.Cryomancer;
import com.icebreaker.classes.AcrobatClass;
import com.icebreaker.classes.Pyromancer;
import com.icebreaker.classes.Warhead;
import com.icebreaker.classes.SniperClass;
import com.icebreaker.classes.Ares;
import com.icebreaker.classes.Hacker;
import com.icebreaker.gamemenu.GameGUI;
import com.icebreaker.gamemenu.TeamChoose;
import com.icebreaker.gamemenu.FriendlyFire;
import com.icebreaker.gamemenu.ScoreboardsF;
import com.icebreaker.mechanics.FishingEvent;
import com.icebreaker.mechanics.OreRespawn;
import com.icebreaker.mechanics.TribalMaskEffects;
import com.icebreaker.mechanics.BossesDrops;
import com.icebreaker.mechanics.BonFireSystem;
import com.icebreaker.mechanics.SmelterMech;
import com.icebreaker.mechanics.AncientSword;
import com.icebreaker.mech2.PoseidonSpell;
import com.icebreaker.mechanics.RespawnMech;
import com.icebreaker.mechanics.EnchRecipes;
import com.icebreaker.mechanics.WaterDamage;
import com.icebreaker.lobbyscoreboard.PlayerJoinCount;
import com.icebreaker.classes.Mercy;
import com.icebreaker.mechanics.DefendersBuff;
import com.icebreaker.mech2.CloseToPrismarine;
import com.icebreaker.mech2.ArrowPickEvent;
import com.icebreaker.mech2.GuardianMech;
import com.icebreaker.rlcombo.Grimtrap;
import com.icebreaker.mech2.InvClose;
import com.icebreaker.mech2.PressurePlate;
import com.icebreaker.mech2.PerkInventory;
import org.bukkit.plugin.Plugin;
import com.icebreaker.classes.Hunter;
import com.icebreaker.GUI.CommandsOfClasses;
import com.icebreaker.ranks.RanksCommands;
import com.icebreaker.mechanics.ConsoleCommands;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin implements Listener
{
    public static Test plugin;
    
    public void onEnable() {
        (Test.plugin = this).saveDefaultConfig();
        this.getServer().getLogger().info("plugin loaded succesfully!");
        this.registerCommands();
        this.registerEvent();
    }
    
    public void onDisable() {
        this.getServer().getLogger().info("plugin unloaded succesfully!");
        this.saveConfig();
        Test.plugin = null;
    }
    
    public void registerCommands() {
        final Commands cmds = new Commands();
        this.getCommand("attackperks").setExecutor((CommandExecutor)cmds);
        this.getCommand("souls").setExecutor((CommandExecutor)cmds);
        this.getCommand("jabsay").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("wizsay").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("ethsay").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("kirdowsay").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("icesay").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("build").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("start").setExecutor((CommandExecutor)new ConsoleCommands());
        this.getCommand("rank").setExecutor((CommandExecutor)new RanksCommands());
        this.getCommand("feed").setExecutor((CommandExecutor)cmds);
        this.getCommand("kit").setExecutor((CommandExecutor)cmds);
        this.getCommand("gm").setExecutor((CommandExecutor)cmds);
        this.getCommand("recall").setExecutor((CommandExecutor)cmds);
        this.getCommand("class").setExecutor((CommandExecutor)new CommandsOfClasses());
        this.getCommand("msg").setExecutor((CommandExecutor)new Messaging());
        this.getCommand("shout").setExecutor((CommandExecutor)new Messaging());
    }
    
    public void registerEvent() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents((Listener)new Hunter(), (Plugin)this);
        pm.registerEvents((Listener)new PerkInventory(), (Plugin)this);
        pm.registerEvents((Listener)new PressurePlate(), (Plugin)this);
        pm.registerEvents((Listener)new InvClose(), (Plugin)this);
        pm.registerEvents((Listener)new Grimtrap(), (Plugin)this);
        pm.registerEvents((Listener)new GuardianMech(), (Plugin)this);
        pm.registerEvents((Listener)new ArrowPickEvent(), (Plugin)this);
        pm.registerEvents((Listener)new CloseToPrismarine(), (Plugin)this);
        pm.registerEvents((Listener)new DefendersBuff(), (Plugin)this);
        pm.registerEvents((Listener)new Mercy(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerJoinCount(), (Plugin)this);
        pm.registerEvents((Listener)new WaterDamage(), (Plugin)this);
        pm.registerEvents((Listener)new ConsoleCommands(), (Plugin)this);
        pm.registerEvents((Listener)new EnchRecipes(), (Plugin)this);
        pm.registerEvents((Listener)new RespawnMech(), (Plugin)this);
        pm.registerEvents((Listener)new PoseidonSpell(), (Plugin)this);
        pm.registerEvents((Listener)new AncientSword(), (Plugin)this);
        pm.registerEvents((Listener)new SmelterMech(), (Plugin)this);
        pm.registerEvents((Listener)new BonFireSystem(), (Plugin)this);
        pm.registerEvents((Listener)new BossesDrops(), (Plugin)this);
        pm.registerEvents((Listener)new TribalMaskEffects(), (Plugin)this);
        pm.registerEvents((Listener)new OreRespawn(), (Plugin)this);
        pm.registerEvents((Listener)new FishingEvent(), (Plugin)this);
        pm.registerEvents((Listener)new ScoreboardsF(), (Plugin)this);
        pm.registerEvents((Listener)new FriendlyFire(), (Plugin)this);
        pm.registerEvents((Listener)new TeamChoose(), (Plugin)this);
        pm.registerEvents((Listener)new GameGUI(), (Plugin)this);
        pm.registerEvents((Listener)new Hacker(), (Plugin)this);
        pm.registerEvents((Listener)new Ares(), (Plugin)this);
        pm.registerEvents((Listener)new SniperClass(), (Plugin)this);
        pm.registerEvents((Listener)new Warhead(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerJoinRank(), (Plugin)this);
        pm.registerEvents((Listener)new Pyromancer(), (Plugin)this);
        pm.registerEvents((Listener)new AcrobatClass(), (Plugin)this);
        pm.registerEvents((Listener)new Cryomancer(), (Plugin)this);
        pm.registerEvents((Listener)new ClassItemBind(), (Plugin)this);
        pm.registerEvents((Listener)new Endermage(), (Plugin)this);
        pm.registerEvents((Listener)new Soulbound(), (Plugin)this);
        pm.registerEvents((Listener)new PortalGUI(), (Plugin)this);
        pm.registerEvents((Listener)new InventoryGUI(), (Plugin)this);
    }
}
