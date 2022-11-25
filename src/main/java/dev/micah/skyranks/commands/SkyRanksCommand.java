package dev.micah.skyranks.commands;

import dev.micah.skyranks.gui.ranks.GuiRanks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkyRanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            new GuiRanks(player, 1);
            return false;
        }
        return false;
    }

}
