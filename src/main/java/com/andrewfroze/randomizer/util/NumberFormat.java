package com.andrewfroze.randomizer.util;

import static com.andrewfroze.randomizer.configuration.Configuration.ADD_ZERO_FOR_SINGLE_SIGNED_NUMBERS;
import static com.andrewfroze.randomizer.configuration.Configuration.PLACEHOLDER;

public class NumberFormat {

    public static String convertNumberToString(int number) {
            return number == 0 ? PLACEHOLDER : ((number < 10 && ADD_ZERO_FOR_SINGLE_SIGNED_NUMBERS) ? "0" : "") + number;
    }
}
