package dev.micah.skyranks.conversation.implementation;

import dev.micah.skyranks.conversation.Conversation;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class ConversationSetChatColor extends StringPrompt {

    private Ranks rank;

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new chat color code(s) into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (!rank.verifyColorCode(input)) {
            input = "&r";
        }
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged chat color from " + rank.getChatColor() + "THIS" + " &rto " + input + "THIS"));
        rank.setChatColor(input);
        return null;
    }

}
