// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mech2;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import com.icebreaker.testing.Test;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class PerksXp implements Listener
{
    private FileConfiguration config;
    
    public PerksXp() {
        this.config = Test.plugin.getConfig();
    }
    
    public void setSouls(final Player p, final int soul) {
        this.config.set("Souls." + p.getUniqueId(), (Object)(this.getSouls(p) + soul));
    }
    
    public int getSouls(final Player player) {
        if (this.config.getString("Souls." + player.getUniqueId()) == null) {
            return 0;
        }
        final int val = Integer.parseInt(this.config.getString("Souls." + player.getUniqueId()));
        return val;
    }
    
    public void setBurp(final Player p) {
        this.config.set("Burp." + p.getUniqueId(), (Object)"burp");
    }
    
    public String getBurp(final HumanEntity humanEntity) {
        if (this.config.getString("Burp." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Burp." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setWolf(final Player p) {
        this.config.set("Wolf." + p.getUniqueId(), (Object)"wolf");
    }
    
    public String getWolf(final HumanEntity humanEntity) {
        if (this.config.getString("Wolf." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Wolf." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setVil(final Player p) {
        this.config.set("Villager." + p.getUniqueId(), (Object)"Villager");
    }
    
    public String getVil(final HumanEntity humanEntity) {
        if (this.config.getString("Villager." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Villager." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setBlaze(final Player p) {
        this.config.set("Blaze." + p.getUniqueId(), (Object)"Blaze");
    }
    
    public String getBlaze(final HumanEntity humanEntity) {
        if (this.config.getString("Blaze." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Blaze." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setThunder(final Player p) {
        this.config.set("Thunderstrike." + p.getUniqueId(), (Object)"Thunderstrike");
    }
    
    public String getThunder(final HumanEntity humanEntity) {
        if (this.config.getString("Thunderstrike." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Thunderstrike." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setG(final Player p) {
        this.config.set("Guardian." + p.getUniqueId(), (Object)"Guardian");
    }
    
    public String getG(final HumanEntity humanEntity) {
        if (this.config.getString("Guardian." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Guardian." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setShadow(final Player p) {
        this.config.set("Shadow." + p.getUniqueId(), (Object)"Shadow");
    }
    
    public String getShadow(final HumanEntity humanEntity) {
        if (this.config.getString("Shadow." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Shadow." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setEx(final Player p) {
        this.config.set("Explosion." + p.getUniqueId(), (Object)"Explosion");
    }
    
    public String getEx(final HumanEntity humanEntity) {
        if (this.config.getString("Explosion." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Explosion." + humanEntity.getUniqueId());
        return val;
    }
    
    public void setChikken(final Player p) {
        this.config.set("Chikken." + p.getUniqueId(), (Object)"Chikken");
    }
    
    public String getChikken(final HumanEntity humanEntity) {
        if (this.config.getString("Chikken." + humanEntity.getUniqueId()) == null) {
            return "kek";
        }
        final String val = this.config.getString("Chikken." + humanEntity.getUniqueId());
        return val;
    }
}
