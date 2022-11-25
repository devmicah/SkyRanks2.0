package dev.micah.skyranks.gui.ranks;

import dev.micah.skyranks.gui.Gui;
import dev.micah.skyranks.gui.Page;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiRanks extends Gui {

    private final Inventory gui;
    private final Player player;

    private final List<String> ranks;

    public GuiRanks(Player player, int page) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, 36, Chat.color("&b&lRanks"));
        this.ranks = Ranks.getRanks();
        this.setOuterBorder(gui);

        if (ranks.isEmpty()) {
            gui.setItem(13, build("&cNo Ranks Found!", Material.BARRIER));
        }

        List<ItemStack> rankItems = new ArrayList<>();
        ranks.forEach(r -> rankItems.add(createRankItem(r)));

        ItemStack left, right;
        if (Page.isPageValid(rankItems, page - 1, 14)) {
            left = build("&aPrevious Page", Material.ARROW);
        } else {
            left = build(" ", Material.GRAY_STAINED_GLASS_PANE);
        }
        if (Page.isPageValid(rankItems, page + 1, 14)) {
            right = build("&aNext Page", Material.ARROW);
        } else {
            right = build(" ", Material.GRAY_STAINED_GLASS_PANE);
        }
        gui.setItem(35, right);
        gui.setItem(27, left);

        for (ItemStack item : Page.getPageItems(rankItems, page, 14)) {
            gui.setItem(gui.firstEmpty(), item);
        }
        gui.setItem(0, build(Chat.color("&bPage ➤ &f" + page), Material.PAPER));
        gui.setItem(8, build(Chat.color("&aCreate Rank"), Material.WRITABLE_BOOK));
        player.openInventory(gui);
    }

}
