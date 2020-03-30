package me.drawlin.kitpvp.listeners;

import me.drawlin.kitpvp.KitpvpPlugin;
import me.drawlin.kitpvp.kit.event.KitUnequipEvent;
import me.drawlin.kitpvp.player.PlayerProfile;
import me.drawlin.kitpvp.player.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerProfile playerProfile = KitpvpPlugin.getPlugin().getProfileManager().createProfile(event.getPlayer().getUniqueId());
        playerProfile.loadProfile().thenRun(() -> event.getPlayer().sendMessage(ChatColor.GREEN + "Your profile has been loaded successfully."));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PlayerProfile playerProfile = ProfileManager.getProfileMap().get(event.getPlayer().getUniqueId());
        playerProfile.setKit(null);
        playerProfile.saveProfile();
        ProfileManager.getProfileMap().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        PlayerProfile playerProfile = ProfileManager.getProfileMap().get(event.getEntity().getUniqueId());
        playerProfile.setDeaths(playerProfile.getDeaths() + 1);
        playerProfile.setKillStreak(0);

        Bukkit.getServer().getPluginManager().callEvent(new KitUnequipEvent(event.getEntity(), playerProfile.getKit()));
        playerProfile.setKit(null);
    }

    @EventHandler
    public void onEntityDeath(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player damager = (Player) event.getDamager();
        Player damagee = (Player) event.getEntity();
        PlayerProfile damageProfile = ProfileManager.getProfileMap().get(damager.getUniqueId());
        damageProfile.setAttack(System.currentTimeMillis());
        if (event.getDamage() > damagee.getHealth()) {
            damageProfile.setKills(damageProfile.getKills() + 1);
            damageProfile.setKillStreak(damageProfile.getKillStreak() + 1);
        }
    }

}
