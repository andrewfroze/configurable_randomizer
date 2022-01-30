package com.andrewfroze.randomizer.util;

import java.io.*;
import java.util.Properties;

import static com.andrewfroze.randomizer.configuration.Configuration.*;

public class PropertyReader {
    private static final String PROPERTIES_PATH = "src/main/resources/config.properties";
    public static Properties properties = new Properties();

    static {
        try {
            new File(PROPERTIES_PATH).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProperties() {
        try (OutputStream output = new FileOutputStream(PROPERTIES_PATH)) {
            properties.setProperty("TEXT_X_POSITION", String.valueOf(TEXT_X_POSITION));
            properties.setProperty("TEXT_Y_POSITION", String.valueOf(TEXT_Y_POSITION));
            properties.setProperty("FONT_SIZE", String.valueOf(START_FONT_SIZE));
            properties.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
