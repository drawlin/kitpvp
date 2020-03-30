package me.drawlin.kitpvp.command.impl;

import com.google.common.base.Joiner;
import me.drawlin.kitpvp.KitpvpPlugin;
import me.drawlin.kitpvp.command.Command;
import me.drawlin.kitpvp.command.CommandArgs;
import me.drawlin.kitpvp.kit.Kit;
import me.drawlin.kitpvp.kit.KitManager;
import me.drawlin.kitpvp.player.PlayerProfile;
import me.drawlin.kitpvp.player.ProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitCommand {

    @Command(name = "kit", usage = "/kit <kitName>")
    public void onCommand(CommandArgs args) {
        PlayerProfile playerProfile = ProfileManager.getProfileMap().get(args.getPlayer().getUniqueId());
        if (playerProfile.getKit() != null) {
            args.getSender().sendMessage(ChatColor.RED + "You currently have kit '" + playerProfile.getKit().getName() + "' equipped.");
            return;
        }

        if (args.length() != 1) {
            sendHelp(args.getPlayer(), args.getCommand().getUsage());
            return;
        }

        Kit targetKit = KitManager.getKitByName(args.getArgs(0));
        if (targetKit == null) {
            args.getSender().sendMessage(ChatColor.RED + "Kit '" + args.getArgs(0) + "' cannot be found.");
            return;
        }

        KitpvpPlugin.getPlugin().getKitManager().applyKit(args.getPlayer(), targetKit);
    }

    private void sendHelp(Player player, String usage) {
        player.sendMessage(ChatColor.RED + "Usage: " + usage);
        player.sendMessage(ChatColor.RED + "Available Kits: " + Joiner.on(", ").join(KitManager.getKitMap().keySet()));
    }

}
