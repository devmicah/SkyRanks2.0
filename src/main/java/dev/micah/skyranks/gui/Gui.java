package dev.micah.skyranks.gui;

import com.sun.tools.javac.util.Pair;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import dev.micah.skyranks.util.Item;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Gui {

    private List<Integer> getRange(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> getRange(List<Pair> values) {
        List<Integer> integers = new ArrayList<>();
        for (Pair pair : values) {
            integers.addAll(getRange((Integer) pair.fst, (Integer) pair.snd));
        }
        return integers;
    }

    protected void setOuterBorder(Inventory gui) {
        List<Integer> slots = getRange(Arrays
                .asList(
                        new Pair(0, 9),
                        new Pair(17, 18),
                        new Pair(26, 35)
                        ));
        for (int s : slots) {
            gui.setItem(s, build(" ", Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    protected ItemStack build(String name, Material material) {
        return new Item(material).setDisplayName(name).build();
    }

    protected ItemStack build(String name, Material material, String... lore) {
        return new Item(material).setDisplayName(name).setLore(Arrays.asList(lore)).build();
    }

    private List<String> getRankLore(String rankName, boolean display) {
        Ranks rank = new Ranks(null, rankName);
        return Arrays.asList(
                " ",
                Chat.color("&f- &bPrefix ➤ &f" + (rank.getPrefix().isEmpty() ? "&fNONE" : rank.getPrefix())),
                Chat.color("&f- &bSuffix ➤ &f" + (rank.getSuffix().isEmpty() ? "&fNONE" : rank.getSuffix())),
                Chat.color("&f- &bName Color ➤ &f" + rank.getNameColor() + "THIS"),
                Chat.color("&f- &bChat Color ➤ &f" + rank.getChatColor() + "THIS"),
                Chat.color("&f- &bNickable ➤ &f" + (rank.isNickable() ? "&aTrue" : "&cFalse")),
                rank.isDefault() ? Chat.color("&7This is the default rank") : " ",
                display ? Chat.color("&b&lShift + Click &r&bto edit this rank") : ""
                );
    }

    protected ItemStack createRankItem(String rankName, boolean display) {
        Ranks rank = new Ranks(null, rankName);
        return new Item(Material.NAME_TAG).setDisplayName("&b&l" + rank.getRankIdentifier()).setLore(getRankLore(rankName, display)).build();
    }

}
