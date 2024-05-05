package org.dreaght.eyesightnotify;

import lombok.Getter;
import org.dreaght.eyesightnotify.manager.TimerManager;
import org.dreaght.eyesightnotify.util.ParsePeriod;
import org.dreaght.eyesightnotify.util.StartupUtil;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class EyesightNotify {
    private static final String programName = ".eyesight";
    private static final String userHome = System.getProperty("user.home");
    @Getter private static final String programDirectoryPath = userHome + File.separator + programName;

    @Getter private static Config config;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            if (args[0].equals("--enable-startup"))
                StartupUtil.enableAutoStartup();
            else if (args[0].equals("--disable-startup"))
                StartupUtil.disableAutoStartup();
            System.exit(0);
        }

        if (userHome.equals("/root")) {
            Logger.getGlobal().warning(
                        "It is not recommended to run this using `sudo`!\n" +
                            "If notifications does not shows, read README.md:\n" +
                            "https://github.com/Dreaght/EyesightNotify/blob/master/README.md");
        }

        config = Config.loadFromFile("config.yml");

        TimerManager.init(ParsePeriod.getPeriodFromString(config.getNotificationRate()));
        TimerManager timerManager = TimerManager.getInstance();

        timerManager.startTask();
    }
}
