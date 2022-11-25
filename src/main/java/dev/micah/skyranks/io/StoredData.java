package dev.micah.skyranks.io;

import dev.micah.skyranks.handler.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

public class StoredData {

    public StoredData() {
        playerHandlers = new ArrayList<>();
    }

    private static List<PlayerHandler> playerHandlers;

    public static List<PlayerHandler> getPlayerHandlers() {
        return playerHandlers;
    }

}
