package dev.micah.skyranks.gui;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Page {

    public static List<ItemStack> getPageItems(List<ItemStack> all, int page, int spaces) {
        int upper = page*spaces; int lower = upper-spaces;
        List<ItemStack> finalList = new ArrayList<>();
        for (int i = lower; i < upper; i++) {
            if (i >= all.size()) {
                return finalList;
            }
            finalList.add(all.get(i));
        }
        return finalList;
    }

    public static boolean isPageValid(List<ItemStack> all, int page, int spaces) {
        if (page <= 0) return false;
        int upper = page*spaces; int lower = upper-spaces;
        return all.size() > lower;
    }

}
