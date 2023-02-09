package dev.micah.skyranks.util;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @Getter private Material material;
    @Getter private ItemMeta itemMeta;

    private ItemStack itemStack;

    public Item(Material material) {
        this.material = material;
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public Item setDisplayName(String displayName) {
        itemMeta.setDisplayName(Chat.color(displayName));
        return this;
    }

    public Item setLore(List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        lore.forEach(s -> coloredLore.add(Chat.color(s)));
        itemMeta.setLore(coloredLore);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
