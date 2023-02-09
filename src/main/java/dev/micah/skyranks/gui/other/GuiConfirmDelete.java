package dev.micah.skyranks.gui.other;

import dev.micah.skyranks.gui.Gui;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiConfirmDelete extends Gui {

    private Player player;
    private Inventory gui;
    private Ranks rank;

    public GuiConfirmDelete(Player player, Ranks rank) {
        this.player = player;
        this.rank = rank;
        this.gui = Bukkit.createInventory(null, 9, Chat.color("&b&lConfirm Delete"));

        gui.setItem(0, build("&cCancel", Material.RED_STAINED_GLASS));
        gui.setItem(8, build("&aConfirm", Material.GREEN_STAINED_GLASS));
        gui.setItem(4, createRankItem(rank.getRankIdentifier(), true));

        player.openInventory(gui);
    }

}
