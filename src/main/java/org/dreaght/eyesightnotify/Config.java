package org.dreaght.eyesightnotify;

import lombok.Getter;
import org.dreaght.eyesightnotify.util.ParsePeriod;
import org.dreaght.eyesightnotify.util.ResourceUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Represents the config file access.
 */
@Getter
public class Config {
    private boolean playSound;
    private double soundVolume;
    private long notificationRate;
    private String notificationMessage;

    /**
     * Makes the Config object of config file.
     * @param filePath Resource path
     * @return Config object of config file.
     * @throws IOException
     */
    public static Config loadFromFile(String filePath) throws IOException {
        ResourceUtil resourceUtil = new ResourceUtil(filePath);
        FileInputStream fileInputStream = resourceUtil.getFileInputStreamOrNull();
        if (fileInputStream == null) {
            resourceUtil.saveDefaultResource();
            fileInputStream = resourceUtil.getFileInputStreamOrNull();
            if (fileInputStream == null) {
                throw new FileNotFoundException("Unable to load configuration file.");
            }
        }
        try (FileInputStream fis = fileInputStream) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = (Map<String, Object>) yaml.load(fis);
            if (!data.containsKey("play-sound") || !data.containsKey("notification-rate") || !data.containsKey("notification-message")) {
                throw new IOException("Invalid configuration file. Missing required fields.");
            }
            return createConfigFromData(data);
        }
    }

    private static Config createConfigFromData(Map<String, Object> data) {
        Config config = new Config();
        config.playSound = (boolean) data.get("play-sound");
        config.soundVolume = (double) data.get("sound-volume");
        config.notificationRate = ParsePeriod.getPeriodFromString((String) data.get("notification-rate"));
        config.notificationMessage = (String) data.get("notification-message");
        return config;
    }
}
