package org.dreaght.eyesightnotify.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class StartupUtil {
    private static final String serviceName = "eyesight.service";

    public static void enableAutoStartup() {
        try {
            String username = System.getProperty("user.name");
            String jarPath = StartupUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            PrintWriter printWriter = getPrintWriter(username, jarPath);
            printWriter.close();

            Process process = Runtime.getRuntime().exec("sudo systemctl daemon-reload");
            process.waitFor();

            System.out.println("Auto startup enabled successfully.");
        } catch (Exception e) {
            System.err.println("Failed to enable auto startup: " + e.getMessage());
        }
    }

    private static PrintWriter getPrintWriter(String username, String jarPath) throws IOException {
        String serviceContent = "[Unit]\n" +
                "Description=Eye-safe 20/20/20 Linux application.\n" +
                "After=network.target\n\n" +
                "[Service]\n" +
                "Type=simple\n" +
                "User=" + username + "\n" +
                "ExecStart=/usr/bin/java -jar " + jarPath + "\n" +
                "Restart=on-failure\n\n" +
                "[Install]\n" +
                "WantedBy=multi-user.target\n";

        String serviceFilePath = "/etc/systemd/system/" + serviceName;

        FileWriter fileWriter = new FileWriter(serviceFilePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(serviceContent);
        return printWriter;
    }

    public static void disableAutoStartup() {
        try {
            String serviceFilePath = "/etc/systemd/system/" + serviceName;

            Process process1 = Runtime.getRuntime().exec("sudo rm " + serviceFilePath);
            process1.waitFor();

            Process process2 = Runtime.getRuntime().exec("sudo systemctl daemon-reload");
            process2.waitFor();

            Logger.getGlobal().info("Auto startup disabled successfully.");
        } catch (Exception e) {
            Logger.getGlobal().severe("Failed to disable auto startup: " + e.getMessage());
        }
    }
}
