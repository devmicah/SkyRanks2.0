package dev.micah.skyranks.conversation;

import dev.micah.skyranks.ranks.Ranks;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public abstract class RankPrompt extends StringPrompt {

    @Setter @Getter
    private Ranks rank;

    @Override
    public String getPromptText(ConversationContext context) {
        return null;
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        return null;
    }
}
