package dev.micah.skyranks.conversation;

import dev.micah.skyranks.SkyRanks;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class Conversation {

    public static void startConversationCreateRank(Player player) {
        player.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getPluginInstance());
        org.bukkit.conversations.Conversation conversation = factory.withFirstPrompt(new CreateRank()).withLocalEcho(false).buildConversation(player);
        conversation.begin();
    }

    public static void startConversationSetPrefix(Player player, Ranks rank) {
        player.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getPluginInstance());
        org.bukkit.conversations.Conversation conversation = factory.withFirstPrompt(new SetPrefix(rank)).withLocalEcho(false).buildConversation(player);
        conversation.begin();
    }

    public static void startConversationSetSuffix(Player player, Ranks rank) {
        player.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getPluginInstance());
        org.bukkit.conversations.Conversation conversation = factory.withFirstPrompt(new SetSuffix(rank)).withLocalEcho(false).buildConversation(player);
        conversation.begin();
    }

    public static void startConversationSetNameColor(Player player, Ranks rank) {
        player.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getPluginInstance());
        org.bukkit.conversations.Conversation conversation = factory.withFirstPrompt(new SetNameColor(rank)).withLocalEcho(false).buildConversation(player);
        conversation.begin();
    }

    public static void startConversationSetChatColor(Player player, Ranks rank) {
        player.closeInventory();
        ConversationFactory factory = new ConversationFactory(SkyRanks.getPluginInstance());
        org.bukkit.conversations.Conversation conversation = factory.withFirstPrompt(new SetChatColor(rank)).withLocalEcho(false).buildConversation(player);
        conversation.begin();
    }

}

class CreateRank extends StringPrompt {

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

class SetPrefix extends StringPrompt {

    private Ranks rank;
    public SetPrefix(Ranks rank) {
        this.rank = rank;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new prefix into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged rank prefix from " + rank.getPrefix() + " &rto " + input));
        rank.setPrefix(input);
        return null;
    }

}

class SetSuffix extends StringPrompt {

    private Ranks rank;
    public SetSuffix(Ranks rank) {
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

class SetChatColor extends StringPrompt {

    private Ranks rank;
    public SetChatColor(Ranks rank) {
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

class SetNameColor extends StringPrompt {

    private Ranks rank;
    public SetNameColor(Ranks rank) {
        this.rank = rank;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return Chat.color("&b[SkyRanks] &fPlease enter the new name color code(s) into chat...");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (!rank.verifyColorCode(input)) {
            input = "&r";
        }
        context.getForWhom().sendRawMessage(Chat.color("&b[SkyRanks] &rChanged name color from " + rank.getNameColor() + "THIS" + " &rto " + input + "THIS"));
        rank.setNameColor(input);
        return null;
    }

}
