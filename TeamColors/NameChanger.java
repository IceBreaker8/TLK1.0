// 
// Decompiled by Procyon v0.5.36
// 

package TeamColors;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameChanger
{
    private static Team human;
    private static Team shaman;
    private static Scoreboard scoreboard;
    
    public static void changePlayerName(final Player player, final String prefix, final String suffix, final TeamAction action) {
        if (player.getScoreboard() == null || prefix == null || suffix == null || action == null) {
            return;
        }
        NameChanger.scoreboard = player.getScoreboard();
        if (NameChanger.scoreboard.getTeam(player.getName()) == null) {
            NameChanger.scoreboard.registerNewTeam(player.getName());
        }
        (NameChanger.human = NameChanger.scoreboard.getTeam(player.getName())).setPrefix(Color(prefix));
        NameChanger.human.setSuffix(Color(suffix));
        NameChanger.human.setAllowFriendlyFire(false);
        (NameChanger.shaman = NameChanger.scoreboard.getTeam(player.getName())).setPrefix(Color(prefix));
        NameChanger.shaman.setSuffix(Color(suffix));
        NameChanger.shaman.setAllowFriendlyFire(false);
        switch (action) {
            case CREATE1: {
                NameChanger.shaman.addPlayer((OfflinePlayer)player);
                break;
            }
            case CREATE2: {
                NameChanger.human.addPlayer((OfflinePlayer)player);
            }
            case UPDATE: {
                NameChanger.shaman.unregister();
                NameChanger.scoreboard.registerNewTeam(player.getName());
                (NameChanger.shaman = NameChanger.scoreboard.getTeam(player.getName())).setPrefix(Color(prefix));
                NameChanger.shaman.setSuffix(Color(suffix));
                NameChanger.shaman.setNameTagVisibility(NameTagVisibility.ALWAYS);
                NameChanger.shaman.addPlayer((OfflinePlayer)player);
                break;
            }
            case DESTROY: {
                NameChanger.shaman.unregister();
                NameChanger.human.unregister();
                break;
            }
        }
    }
    
    private static String Color(final String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
