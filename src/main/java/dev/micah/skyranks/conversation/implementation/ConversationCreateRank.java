package dev.micah.skyranks.conversation.implementation;

import dev.micah.skyranks.conversation.RankPrompt;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class ConversationCreateRank extends RankPrompt {

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease type the name identifier for your new rank...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (new Ranks((Player) context.getForWhom(), input).build()) {
            context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rSuccessfully created the rank &a" + input + "&r!"));
        } else {
            context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rFailed to create rank &a" + input + "&r! &c(Rank already exists)"));
        }
        return null;
    }

}
