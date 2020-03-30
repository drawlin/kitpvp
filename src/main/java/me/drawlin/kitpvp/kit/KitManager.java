package me.drawlin.kitpvp.kit;

import com.google.common.collect.Maps;
import lombok.Getter;
import me.drawlin.kitpvp.kit.event.KitEquipEvent;
import me.drawlin.kitpvp.kit.impl.PvPKit;
import me.drawlin.kitpvp.player.PlayerProfile;
import me.drawlin.kitpvp.player.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Getter
public class KitManager {

    @Getter
    private static Map<String, Kit> kitMap = Maps.newHashMap();

    public KitManager() {
        // TODO: find a better way to add kits, preferable automatically by class/package
        kitMap.put("PVP", new PvPKit());
    }

    public void applyKit(Player player, Kit kit) {
        PlayerProfile playerProfile = ProfileManager.getProfileMap().get(player.getUniqueId());
        playerProfile.setKit(kit);
        player.getInventory().clear();
        for (ItemStack itemStack : kit.getItems()) {
            player.getInventory().addItem(itemStack);
        }

        // Find a better way to apply armor
        if (!kit.getArmor().isEmpty()) {
            player.getInventory().setHelmet(kit.getArmor().get(0));
            player.getInventory().setChestplate(kit.getArmor().get(1));
            player.getInventory().setLeggings(kit.getArmor().get(2));
            player.getInventory().setBoots(kit.getArmor().get(3));
        }

        Bukkit.getServer().getPluginManager().callEvent(new KitEquipEvent(player, kit));
    }

    public static Kit getKitByName(String kitName) {
        // TODO: find a better way because this is case sensitive
        return kitMap.getOrDefault(kitName.toUpperCase(), null);
    }

}
