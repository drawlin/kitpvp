package me.drawlin.kitpvp.kit.impl;

import me.drawlin.kitpvp.kit.Kit;
import me.drawlin.kitpvp.utility.ItemBuilder;
import org.bukkit.Material;

public class ArcherKit extends Kit {

    public ArcherKit() {
        super("Archer", "Shoot your opponent with arrows.");
        this.addItem(new ItemBuilder(Material.IRON_SWORD).build());
        this.addItem(new ItemBuilder(Material.BOW).name("Cupid's Bow").build());
        this.addItem(new ItemBuilder(Material.ARROW).name("Cupid's Arrows").amount(64).build());
        this.addArmor(
                new ItemBuilder(Material.LEATHER_HELMET).build(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).build(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).build(),
                new ItemBuilder(Material.LEATHER_BOOTS).build());
    }
}
