package dev.micah.skyranks.conversation;

import dev.micah.skyranks.SkyRanks;
import dev.micah.skyranks.conversation.implementation.*;
import dev.micah.skyranks.ranks.Ranks;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class Conversation {

    public enum ConversationTopic {

        CREATE_RANK(new ConversationCreateRank()),
        SET_NAME_COLOR(new ConversationSetNameColor()),
        SET_CHAT_COLOR(new ConversationSetChatColor()),
        SET_PREFIX(new ConversationSetPrefix()),
        SET_SUFFIX(new ConversationSetSuffix());

        StringPrompt prompt;
        ConversationTopic(StringPrompt prompt) {
            this.prompt = prompt;
        }

        public StringPrompt getPrompt() {
            return prompt;
        }

    }

    public static void buildConversation(Player player, ConversationTopic topic) {
        player.closeInventory();
        new ConversationFactory(SkyRanks.getPluginInstance())
                .withFirstPrompt(topic.getPrompt())
                .withLocalEcho(false)
                        .buildConversation(player)
                .begin();
    }

}
