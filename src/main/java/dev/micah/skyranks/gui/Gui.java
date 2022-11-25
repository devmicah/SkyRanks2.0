package dev.micah.skyranks.gui;

import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Gui {

    protected void setOuterBorder(Inventory gui) {
        int[] slots = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,28,29,30,31,32,33,34,35};
        for (int s : slots) {
            gui.setItem(s, build(" ", Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    protected ItemStack build(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color(name));
        item.setItemMeta(meta);
        return item;
    }

    protected ItemStack build(String name, Material material, String... lore) {
        ItemStack item = build(name, material);
        ItemMeta meta = item.getItemMeta();
        List<String> loreList = new ArrayList<>();
        for (String s : lore) {
            loreList.add(Chat.color(s));
        }
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    protected ItemStack createRankItem(String rank) {
        Ranks r = new Ranks(null, rank);
        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color("&b" + rank));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(Chat.color("&f- &bPrefix ➤ &f" + (r.getPrefix().isEmpty() ? "&fNONE" : r.getPrefix())));
        lore.add(Chat.color("&f- &bSuffix ➤ &f" + (r.getSuffix().isEmpty() ? "&fNONE" : r.getSuffix())));
        lore.add(Chat.color("&f- &bName Color ➤ &f" + r.getNameColor() + "THIS"));
        lore.add(Chat.color("&f- &bChat Color ➤ &f" + r.getChatColor() + "THIS"));
        lore.add(Chat.color("&f- &bNickable ➤ &f" + (r.isNickable() ? "&aTrue" : "&cFalse")));
        lore.add(" ");
        if (r.isDefault()) {
            lore.add(Chat.color("&7This is the default rank"));
        }
        lore.add(Chat.color("&b&lShift + Click &r&bto edit this rank"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    protected ItemStack createRankItem(String rank, boolean display) {
        Ranks r = new Ranks(null, rank);
        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color("&b" + rank));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(Chat.color("&f- &bPrefix ➤ &f" + (r.getPrefix().isEmpty() ? "&fNONE" : r.getPrefix())));
        lore.add(Chat.color("&f- &bSuffix ➤ &f" + (r.getSuffix().isEmpty() ? "&fNONE" : r.getSuffix())));
        lore.add(Chat.color("&f- &bName Color ➤ &f" + r.getNameColor() + "THIS"));
        lore.add(Chat.color("&f- &bChat Color ➤ &f" + r.getChatColor() + "THIS"));
        lore.add(Chat.color("&f- &bNickable ➤ &f" + (r.isNickable() ? "&aTrue" : "&cFalse")));
        if (r.isDefault()) {
            lore.add(" ");
            lore.add(Chat.color("&7This is the default rank"));
        }
        if (display) {
            lore.add(Chat.color("&b&lShift + Click &r&bto edit this rank"));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
