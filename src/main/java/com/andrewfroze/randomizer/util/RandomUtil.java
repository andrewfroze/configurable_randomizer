package com.andrewfroze.randomizer.util;

import java.util.Random;

import static com.andrewfroze.randomizer.configuration.Configuration.*;

public class RandomUtil {

    public static int getRandomRow() {
        return getRandom(ROW_FROM, ROW_TO);
    }

    public static int getRandomPlace() {
        return getRandom(PLACE_FROM, PLACE_TO);
    }

    public static int getRandom(int from, int to) {
        return new Random().nextInt(to - from + 1) + from;
    }
}
