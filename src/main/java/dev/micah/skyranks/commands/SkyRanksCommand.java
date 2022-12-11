package dev.micah.skyranks.commands;

import dev.micah.skyranks.gui.ranks.GuiRanks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SkyRanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("skyranks.admin.rank")) {
                new GuiRanks(player, 1);
            }
        } else {
            Arrays.asList(
                    "&bSkyRanks Console",
                    "&f- &bsr setrank <user> <rank>",
                    "&f- &bsr getrank <user>"
                    )
                    .forEach(s -> Bukkit.getLogger().info(Chat.color(s)));
        }
        return false;
    }

}
