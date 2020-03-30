package me.drawlin.kitpvp.command.impl;

import me.drawlin.kitpvp.command.Command;
import me.drawlin.kitpvp.command.CommandArgs;
import me.drawlin.kitpvp.kit.event.KitUnequipEvent;
import me.drawlin.kitpvp.player.PlayerProfile;
import me.drawlin.kitpvp.player.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class RemoveKitCommand {

    @Command(name = "removekit", usage = "/removekit")
    public void onCommand(CommandArgs args) {
        PlayerProfile playerProfile = ProfileManager.getProfileMap().get(args.getPlayer().getUniqueId());
        if (playerProfile.getKit() == null) {
            args.getPlayer().sendMessage(ChatColor.RED + "You do not have a kit equipped.");
            return;
        }

        if (playerProfile.inCombat()) {
            args.getPlayer().sendMessage(ChatColor.RED + "You are not able to remove your kit whilst in combat.");
            return;
        }

        args.getPlayer().getInventory().clear();
        Bukkit.getServer().getPluginManager().callEvent(new KitUnequipEvent(args.getPlayer(), playerProfile.getKit()));
        playerProfile.setKit(null);
    }

}
