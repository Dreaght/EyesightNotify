package org.dreaght.eyesightnotify.util;

import org.dreaght.eyesightnotify.EyesightNotify;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class AutoStart {
    private static final String userHome = System.getProperty("user.home");
    private static final String autostartPath = userHome + "/.config/autostart/eyesight.desktop";

    public static void addToStartup() {
        String jarPath = getRunningJarPath();
        String execCmd = "java -jar " + jarPath;

        String desktopEntry =
                "[Desktop Entry]\n" +
                        "Name=EyesightNotify\n" +
                        "Exec=" + execCmd + "\n" +
                        "Terminal=false\n" +
                        "Type=Application\n" +
                        "Categories=Utility;Health;\n";

        try {
            File file = new File(autostartPath);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(desktopEntry);
            }
            EyesightNotify.getLogger().info("Autostart entry created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromStartup() {
        File file = new File(autostartPath);

        if (file.exists()) {
            file.delete();
        }

        EyesightNotify.getLogger().info("Autostart entry removed successfully.");
    }

    private static String getRunningJarPath() {
        try {
            CodeSource codeSource = AutoStart.class.getProtectionDomain().getCodeSource();
            if (codeSource != null) {
                return new File(codeSource.getLocation().toURI()).getPath();
            } else {
                throw new RuntimeException("Unable to determine the JAR file path.");
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while determining the JAR file path.", e);
        }
    }
}
