package org.dreaght.eyesightnotify;

import lombok.Getter;
import org.dreaght.eyesightnotify.util.ResourceUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

@Getter
public class Config {
    private boolean playSound;
    private String notificationRate;
    private String notificationMessage;

    public static Config loadFromFile(String filePath) throws IOException {
        ResourceUtil resourceUtil = new ResourceUtil(filePath);

        Yaml yaml = new Yaml();
        Map<String, Object> data = (Map<String, Object>) yaml.load(resourceUtil.makeAndGetFileInputStream());

        Config config = new Config();
        config.playSound = (boolean) data.get("play-sound");
        config.notificationRate = (String) data.get("notification-rate");
        config.notificationMessage = (String) data.get("notification-message");

        return config;
    }
}
