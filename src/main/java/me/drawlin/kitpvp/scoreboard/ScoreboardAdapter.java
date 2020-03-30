package me.drawlin.kitpvp.scoreboard;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.drawlin.kitpvp.player.PlayerProfile;
import me.drawlin.kitpvp.player.ProfileManager;
import me.drawlin.kitpvp.utility.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardAdapter implements AssembleAdapter {
    @Override
    public String getTitle(Player player) {
        return ChatColor.GOLD + "Kit PvP";
    }

    @Override
    public List<String> getLines(Player player) {
        PlayerProfile playerProfile = ProfileManager.getProfileMap().get(player.getUniqueId());
        return StringUtil.colorArray(
                "&8&m-----------------",
                "&6Kills: &f" + playerProfile.getKills(),
                "&6Deaths: &f" + playerProfile.getDeaths(),
                "&6Killstreak: &f" + playerProfile.getKillStreak(),
                "&6KDR: &f" + playerProfile.getKDR(),
                "&8&m-----------------",
                (playerProfile.inCombat() ? "&cCombat: &f" + ((playerProfile.getAttack() - System.currentTimeMillis()) / 1000) + "s" : "&7www.kitpvp.net")
        );
    }
}
