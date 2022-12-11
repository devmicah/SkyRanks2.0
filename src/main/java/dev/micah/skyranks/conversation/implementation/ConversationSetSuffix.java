package dev.micah.skyranks.conversation.implementation;

import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class ConversationSetSuffix extends StringPrompt {

    private Ranks rank;

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new suffix into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged rank suffix from " + rank.getSuffix() + " &rto " + input));
        rank.setSuffix(input);
        return null;
    }

}
