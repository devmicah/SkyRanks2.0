package dev.micah.skyranks.gui.ranks;

import dev.micah.skyranks.gui.Gui;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiEditRank extends Gui {

    private Player player;
    private Inventory gui;
    private Ranks rank;

    public GuiEditRank(Player player, String rank) {
        this.rank = new Ranks(player, rank);
        this.player = player;
        this.gui = Bukkit.createInventory(null, 36, Chat.color("&b&lEditing Rank - " + rank));
        this.setOuterBorder(gui);
        gui.setItem(0, createRankItem(rank, true));
        gui.setItem(27, build("&aGo Back", Material.ARROW));

        gui.setItem(gui.firstEmpty(), build("&aEdit Prefix", Material.PAPER, " ", "&7The prefix is a string set to", "&7appear before the users name", "&7in chat."));
        gui.setItem(gui.firstEmpty(), build("&aEdit Suffix", Material.PAPER, " ", "&7The suffix is a string set to", "&7appear after the users name", "&7in chat."));
        gui.setItem(gui.firstEmpty(), build("&aChange Name Color", Material.WHITE_DYE, " ", "&7The name color is the color code", "&7of users name with this rank."));
        gui.setItem(gui.firstEmpty(), build("&aChange Chat Color", Material.GRAY_DYE, " ", "&7The chat color is the color code", "&7of users messages with this rank."));
        gui.setItem(gui.firstEmpty(), build("&aChange Nickable", Material.PLAYER_HEAD, " ", "&7The option 'Nickable' refers", "&7to if the rank will be seen", "&7when using the /nick command."));
        gui.setItem(gui.firstEmpty(), build("&aSet Default", Material.COMPASS, " ", "&7Setting a rank to default means", "&7all new players will be automatically ", "&7set to this rank."));
        gui.setItem(35, build("&c&lDelete Rank", Material.LAVA_BUCKET, " ", "&cSelecting this option removes", "&cthis rank permanently!"));

        player.openInventory(gui);
    }



}
