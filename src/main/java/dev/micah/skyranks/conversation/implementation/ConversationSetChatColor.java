package dev.micah.skyranks.conversation.implementation;

import dev.micah.skyranks.conversation.RankPrompt;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class ConversationSetChatColor extends RankPrompt {

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new chat color code(s) into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (!getRank().verifyColorCode(input)) {
            input = "&r";
        }
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged chat color from " + getRank().getChatColor() + "THIS" + " &rto " + input + "THIS"));
        getRank().setChatColor(input);
        return null;
    }

}
