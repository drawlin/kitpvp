package me.drawlin.kitpvp.kit;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class Kit {

    private String name, description;

    private List<ItemStack> items = Lists.newArrayList();
    private List<ItemStack> armor = Lists.newArrayList();

    public Kit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addItem(ItemStack itemStack) {
        this.items.add(itemStack);
    }

    public void addArmor(ItemStack... armor) {
        this.armor.addAll(Arrays.asList(armor));
    }

}
