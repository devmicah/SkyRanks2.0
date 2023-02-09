package dev.micah.skyranks.util;

import lombok.Getter;

public class Pair {

    @Getter
    private Object first;
    @Getter
    private Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

}
