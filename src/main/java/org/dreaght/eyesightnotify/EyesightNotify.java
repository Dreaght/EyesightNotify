package org.dreaght.eyesightnotify;

import lombok.Getter;
import org.dreaght.eyesightnotify.manager.TimerManager;
import org.dreaght.eyesightnotify.util.ParsePeriod;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class EyesightNotify {
    @Getter private static Logger logger = Logger.getLogger(EyesightNotify.class.getName());
    private static final String programName = ".eyesight";
    private static final String userHome = System.getProperty("user.home");
    @Getter private static final String programDirectoryPath = userHome + File.separator + programName;

    @Getter private static Config config;

    public static void main(String[] args) throws IOException {
        if (userHome.equals("/root")) {
            logger.warning("""
                    It is not recommended to run this using `sudo`!
                    If the notifications do not appear, see:
                    https://github.com/Dreaght/EyesightNotify?tab=readme-ov-file#problems""");
        }

        config = Config.loadFromFile("config.yml");

        TimerManager.init(ParsePeriod.getPeriodFromString(config.getNotificationRate()));
        TimerManager timerManager = TimerManager.getInstance();

        timerManager.startTask();
    }
}
