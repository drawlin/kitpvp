package me.drawlin.kitpvp.kit.impl;

import me.drawlin.kitpvp.kit.Kit;
import me.drawlin.kitpvp.utility.ItemBuilder;
import org.bukkit.Material;

public class PvPKit extends Kit {

    public PvPKit() {
        super("PvP", "Default PvP kit!");
        this.addItem(new ItemBuilder(Material.DIAMOND_SWORD).name("Excalibur").build());
        this.addArmor(
                new ItemBuilder(Material.IRON_HELMET).build(),
                new ItemBuilder(Material.IRON_CHESTPLATE).build(),
                new ItemBuilder(Material.IRON_LEGGINGS).build(),
                new ItemBuilder(Material.IRON_BOOTS).build());
    }
}
