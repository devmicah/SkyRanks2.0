package dev.micah.skyranks.listeners;

import dev.micah.skyranks.conversation.Conversation;
import dev.micah.skyranks.gui.ranks.GuiEditRank;
import dev.micah.skyranks.gui.ranks.GuiRanks;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory gui = event.getClickedInventory();
        ItemStack current = event.getCurrentItem();
        if (current != null) {
            String itemName = ChatColor.stripColor(current.getItemMeta().getDisplayName());
            if (ChatColor.stripColor(event.getView().getTitle()).equals("Ranks")) {
                event.setCancelled(true);
                String pageItem = ChatColor.stripColor(gui.getItem(0).getItemMeta().getDisplayName());
                int page = Integer.parseInt(pageItem.substring(pageItem.length() - 1));
                if (itemName.equals("Create Rank")) {
                    Conversation.startConversationCreateRank(player);
                }
                if (itemName.equals("Next Page")) {
                    new GuiRanks(player, page + 1);
                }
                if (itemName.equals("Previous Page")) {
                    new GuiRanks(player, page - 1);
                }
                if (current.getType() == Material.NAME_TAG && (event.getClick() == ClickType.SHIFT_RIGHT || event.getClick() == ClickType.SHIFT_LEFT)) {
                    new GuiEditRank(player, ChatColor.stripColor(current.getItemMeta().getDisplayName()));
                }
            }
            if (ChatColor.stripColor(event.getView().getTitle()).contains("Editing Rank")) {
                Ranks rank = new Ranks(player, event.getView().getTitle().split(" ")[3].replaceAll(" ", ""));
                event.setCancelled(true);
                if (itemName.equals("Edit Prefix")) {
                    Conversation.startConversationSetPrefix(player, rank);
                }
                if (itemName.equals("Edit Suffix")) {
                    Conversation.startConversationSetSuffix(player, rank);
                }
                if (itemName.equals("Change Chat Color")) {
                    Conversation.startConversationSetChatColor(player, rank);
                }
                if (itemName.equals("Change Name Color")) {
                    Conversation.startConversationSetNameColor(player, rank);
                }
                if (itemName.equals("Change Nickable")) {
                    rank.setNickable(!rank.isNickable());
                    player.closeInventory();
                    if (rank.isNickable()) {
                        player.sendMessage(Chat.color("&b[SkyRanks] &rUsers can now /nick as &b" + rank.getRankIdentifier()));
                    } else {
                        player.sendMessage(Chat.color("&b[SkyRanks] &rUsers can no longer /nick as &b" + rank.getRankIdentifier()));
                    }
                }
                if (itemName.equals("Set Default")) {
                    player.closeInventory();
                    if (rank.isDefault()) {
                        player.sendMessage(Chat.color("&b[SkyRanks] &rThis rank is already the default!"));
                        return;
                    }
                    rank.setDefault();
                    player.sendMessage(Chat.color("&b[SkyRanks] &rThe default rank has been changed to &b" + rank.getRankIdentifier()));
                }
                if (itemName.equals("Delete Rank")) {
                    player.closeInventory();
                    player.sendMessage(Chat.color("&b[SkyRanks] &rYou deleted the rank &c" + rank.getRankIdentifier() + "&r!"));
                    rank.destruct();
                }
            }
        }



    }

}
