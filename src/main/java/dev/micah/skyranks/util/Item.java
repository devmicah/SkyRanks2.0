package dev.micah.skyranks.util;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public Item setLore(List<String> lore) {
        lore.forEach(s -> {
            lore.remove(s); lore.add(Chat.color(s));
        });
        itemMeta.setLore(lore);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }

}
