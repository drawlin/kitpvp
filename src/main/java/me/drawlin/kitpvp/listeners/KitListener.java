package me.drawlin.kitpvp.listeners;

import me.drawlin.kitpvp.kit.event.KitEquipEvent;
import me.drawlin.kitpvp.kit.event.KitUnequipEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class KitListener implements Listener {

    @EventHandler
    public void onEquip(KitEquipEvent event) {
        event.getPlayer().sendMessage(ChatColor.YELLOW + "You have equipped kit " + event.getKit().getName() + ".");
    }

    @EventHandler
    public void onUnequip(KitUnequipEvent event) {
        event.getPlayer().sendMessage(ChatColor.YELLOW + "Kit " + event.getKit().getName() + " has been unequipped.");
        event.getPlayer().sendMessage(ChatColor.GRAY + "You are able to select another kit using /kit!");
    }

}
