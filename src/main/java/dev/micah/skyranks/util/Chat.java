package dev.micah.skyranks.util;

import org.bukkit.ChatColor;

public class Chat {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
