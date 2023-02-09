package dev.micah.skyranks.conversation.implementation;

import dev.micah.skyranks.conversation.RankPrompt;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class ConversationSetSuffix extends RankPrompt {

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new suffix into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged rank suffix from " + (getRank().getSuffix().isEmpty() ? "NONE" : getRank().getSuffix()) + " &rto " + input));
        getRank().setSuffix(input);
        return null;
    }

}
