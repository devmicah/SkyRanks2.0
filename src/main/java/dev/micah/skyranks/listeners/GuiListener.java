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

            event.setCancelled(true);
            String itemName = ChatColor.stripColor(current.getItemMeta().getDisplayName());

            if (ChatColor.stripColor(event.getView().getTitle()).equals("Ranks")) {
                String pageItem = ChatColor.stripColor(gui.getItem(0).getItemMeta().getDisplayName());
                int page = Integer.parseInt(pageItem.substring(pageItem.length() - 1));
                switch (itemName) {
                    case "Create Rank":
                        Conversation.buildConversation(player, Conversation.ConversationTopic.CREATE_RANK);
                        break;
                    case "Next Page":
                        new GuiRanks(player, page + 1);
                        break;
                    case "Previous Page":
                        new GuiRanks(player, page - 1);
                        break;
                    default:
                        break;
                }

                if (current.getType() == Material.NAME_TAG && (event.getClick() == ClickType.SHIFT_RIGHT || event.getClick() == ClickType.SHIFT_LEFT)) {
                    new GuiEditRank(player, ChatColor.stripColor(current.getItemMeta().getDisplayName()));
                }

            }

            if (ChatColor.stripColor(event.getView().getTitle()).contains("Editing Rank")) {

                Ranks rank = new Ranks(player, event.getView().getTitle().split(" ")[3].replaceAll(" ", ""));

                switch (itemName) {
                    case "Edit Prefix":
                        Conversation.buildConversation(player, Conversation.ConversationTopic.SET_PREFIX);
                        break;
                    case "Edit Suffix":
                        Conversation.buildConversation(player, Conversation.ConversationTopic.SET_SUFFIX);
                        break;
                    case "Change Chat Color":
                        Conversation.buildConversation(player, Conversation.ConversationTopic.SET_CHAT_COLOR);
                        break;
                    case "Change Name Color":
                        Conversation.buildConversation(player, Conversation.ConversationTopic.SET_NAME_COLOR);
                        break;
                    case "Change Nickable":
                        player.closeInventory();
                        rank.setNickable(!rank.isNickable());
                        if (rank.isNickable()) {
                            player.sendMessage(Chat.color("&b[SkyRanks] &rUsers can now /nick as &b" + rank.getRankIdentifier()));
                        } else {
                            player.sendMessage(Chat.color("&b[SkyRanks] &rUsers can no longer /nick as &b" + rank.getRankIdentifier()));
                        }
                        break;
                    case "Set Default":
                        player.closeInventory();
                        if (rank.isDefault()) {
                            player.sendMessage(Chat.color("&b[SkyRanks] &rThis rank is already the default!"));
                            return;
                        }
                        rank.setDefault();
                        player.sendMessage(Chat.color("&b[SkyRanks] &rThe default rank has been changed to &b" + rank.getRankIdentifier()));
                        break;
                    case "Delete Rank":
                        player.closeInventory();
                        player.sendMessage(Chat.color("&b[SkyRanks] &rYou deleted the rank &c" + rank.getRankIdentifier() + "&r!"));
                        rank.destruct();
                    default:
                        break;
                }

            }
        }
    }
}
