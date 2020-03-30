package me.drawlin.kitpvp.utility;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.List;

public class StringUtil {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    // might be a better method for this idk
    public static List<String> colorArray(String... strings) {
        List<String> list = Lists.newArrayList();
        for (String string : strings) {
            list.add(color(string));
        }
        return list;
    }

}
