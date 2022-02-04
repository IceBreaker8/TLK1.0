// 
// Decompiled by Procyon v0.5.36
// 

package com.icebreaker.mechanics;

import net.minecraft.server.v1_11_R1.Packet;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import org.bukkit.entity.Entity;

public class ActionBar
{
    public void send(final Entity p, final String message) {
        try {
            final IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
            final PacketPlayOutChat packet = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
        }
        catch (SecurityException | IllegalArgumentException ex2) {
            final RuntimeException ex;
            final RuntimeException e = ex;
            e.printStackTrace();
        }
    }
}
