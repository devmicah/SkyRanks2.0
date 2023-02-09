package dev.micah.skyranks.conversation.implementation;

import dev.micah.skyranks.conversation.RankPrompt;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class ConversationSetNameColor extends RankPrompt {

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new name color code(s) into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (!getRank().verifyColorCode(input)) {
            input = "&r";
        }
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged name color from " + getRank().getNameColor() + "THIS" + " &rto " + input + "THIS"));
        getRank().setNameColor(input);
        return null;
    }

}
