// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.ranks;

import org.bukkit.ChatColor;

public enum Rank
{
    OWNER("OWNER", 0, ChatColor.DARK_RED + "OWNER"), 
    ADMIN("ADMIN", 1, ChatColor.RED + "ADMIN"), 
    MOD("MOD", 2, ChatColor.GREEN + "MOD"), 
    YOUTUBE("YOUTUBE", 3, ChatColor.LIGHT_PURPLE + "YT"), 
    BUILDER("BUILDER", 4, ""), 
    ALPHA("ALPHA", 5, ""), 
    MEMBER("MEMBER", 6, ""), 
    CMD("CMD", 7, "");
    
    private String prefix;
    
    private Rank(final String name, final int ordinal, final String prefix) {
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
}
