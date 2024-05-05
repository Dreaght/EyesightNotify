package org.dreaght.eyesightnotify.manager;

import java.io.IOException;

public class NotifyManager {
    public static void sendNotification(String appName, String message) {
        String[] cmd = {
                "/usr/bin/notify-send",
                "--hint", // to not show in notifications list
                "int:transient:1",
                "-t",
                "3000",
                "-a",
                appName,
                message
        };

        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
