package org.dreaght.eyesightnotify;

import lombok.Getter;
import org.dreaght.eyesightnotify.manager.TimerManager;
import org.dreaght.eyesightnotify.util.AutoStart;
import org.dreaght.eyesightnotify.util.LogUtil;
import org.dreaght.eyesightnotify.util.ParsePeriod;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Represents the program entrypoint.
 */
public class EyesightNotify {
    @Getter private static Logger logger = Logger.getLogger(EyesightNotify.class.getName());
    private static final String programName = ".eyesight";
    private static final String userHome = System.getProperty("user.home");
    @Getter private static final String programDirectoryPath = userHome + File.separator + programName;

    @Getter private static Config config;

    /**
     * Launches all necessary modules.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        initializeLogs();

        if (userHome.equals("/root")) {
            logger.warning("""
                    It is not recommended to run this using `sudo`!
                    If the notifications do not appear, see:
                    https://github.com/Dreaght/EyesightNotify?tab=readme-ov-file#problems""");
        }

        config = Config.loadFromFile("config.yml");

        TimerManager.init(config.getNotificationRate());
        TimerManager timerManager = TimerManager.getInstance();

        timerManager.startTask();

        if (config.isStartup()) {
            AutoStart.addToStartup();
        } else {
            AutoStart.removeFromStartup();
        }
    }

    /**
     * Initializes the logs file.
     * @throws IOException
     */
    public static void initializeLogs() throws IOException {
        LogUtil.createLogFolder();
        Handler handler = new FileHandler(
                LogUtil.getLogFolder().getAbsolutePath()
                        + File.separator + new Date().getTime()
                , 5 * 1024 * 1024, 10);
        logger.addHandler(handler);
    }
}
