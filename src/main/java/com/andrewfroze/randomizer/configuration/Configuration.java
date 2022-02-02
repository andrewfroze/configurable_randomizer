package com.andrewfroze.randomizer.configuration;

import java.awt.*;

import static com.andrewfroze.randomizer.util.PropertyReader.properties;

public class Configuration {
    //Random
    public static final int ROW_FROM = Integer.parseInt(properties.getProperty("FIRST_VALUE_FROM", "1"));;
    public static final int ROW_TO = Integer.parseInt(properties.getProperty("FIRST_VALUE_TO", "99"));;
    public static final int PLACE_FROM = Integer.parseInt(properties.getProperty("SECOND_VALUE_FROM", "1"));;
    public static final int PLACE_TO = Integer.parseInt(properties.getProperty("SECOND_VALUE_TO", "99"));;

    //screen
    public static final int START_WIDTH = 700;
    public static final int START_HEIGHT = 400;

    //style
    public static final String PATH_TO_BACKGROUND_IMAGE = properties.getProperty("PATH_TO_BACKGROUND_IMAGE", "src/main/resources/background.png");

    //text
    public static final String TWO_VARIABLES_TEXT_PATTERN = properties.getProperty("TWO_VARIABLES_TEXT_PATTERN", "Ряд: %s  /  Место: %s");
    public static final String PLACEHOLDER = properties.getProperty("PLACEHOLDER", "   ");
    public static final boolean ADD_ZERO_FOR_SINGLE_SIGNED_NUMBERS = Boolean.parseBoolean(properties.getProperty("ADD_ZERO_FOR_SINGLE_SIGNED_NUMBERS", "true"));



    public static final Color TEXT_COLOR;
    public static final Color SHADOW_COLOR;

    static {
        String colorString = properties.getProperty("TEXT_COLOR", "255/255/255");
        String [] colorRGBArray = colorString.split("\\D");
        TEXT_COLOR = new Color(Integer.parseInt(colorRGBArray[0]),Integer.parseInt(colorRGBArray[1]), Integer.parseInt(colorRGBArray[2]));

        colorString = properties.getProperty("SHADOW_COLOR", "0/0/0");
        colorRGBArray = colorString.split("\\D");
        SHADOW_COLOR = new Color(Integer.parseInt(colorRGBArray[0]),Integer.parseInt(colorRGBArray[1]), Integer.parseInt(colorRGBArray[2]));
    }

    public static final int SHADOW_SIZE_X = Integer.parseInt(properties.getProperty("SHADOW_SIZE_X", "3"));
    public static final int SHADOW_SIZE_Y = Integer.parseInt(properties.getProperty("SHADOW_SIZE_Y", "3"));
    public static final String FONT = properties.getProperty("FONT", "Georgia");
    public static final int FONT_STYLE = Integer.parseInt(properties.getProperty("FONT_STYLE", "1"));
    public static int START_FONT_SIZE = Integer.parseInt(properties.getProperty("FONT_SIZE", "25"));
    public static double TEXT_X_POSITION = Double.parseDouble(properties.getProperty("TEXT_X_POSITION", "0"));
    public static double TEXT_Y_POSITION = Double.parseDouble(properties.getProperty("TEXT_Y_POSITION", "0"));
    public static final double CHANGE_STEP = Double.parseDouble(properties.getProperty("CHANGE_STEP", "0.001"));
}
