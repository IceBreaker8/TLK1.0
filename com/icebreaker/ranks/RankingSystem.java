// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.ranks;

import org.bukkit.entity.Player;
import com.icebreaker.testing.Test;
import org.bukkit.configuration.file.FileConfiguration;

public class RankingSystem
{
    private FileConfiguration config;
    
    public RankingSystem() {
        this.config = Test.plugin.getConfig();
    }
    
    public void setRank(final Player player, final Rank rank) {
        this.config.set("Ranks." + player.getUniqueId(), (Object)rank.toString());
    }
    
    public Rank getRank(final Player player) {
        final String val = this.config.getString("Ranks." + player.getUniqueId());
        return (val == null) ? Rank.MEMBER : Rank.valueOf(val);
    }
    
    public boolean hasRank(final Player player, final Rank rank) {
        return this.getRank(player).compareTo(rank) >= 0;
    }
}
