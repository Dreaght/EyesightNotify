package org.dreaght.eyesightnotify.util;

import org.dreaght.eyesightnotify.Config;
import org.dreaght.eyesightnotify.EyesightNotify;

import java.io.*;
import java.util.logging.Logger;

public class ResourceUtil {
    public static void saveDefaultResource(String filePath) throws IOException {
        InputStream inputStream = getResourceStream(filePath);
        if (inputStream == null) {
            logResourceNotFound(filePath);
            return;
        }

        File targetFile = createTargetFile(filePath);
        copyResourceToFile(inputStream, targetFile);

        Logger.getGlobal().info("Config file saved to: " + targetFile.getAbsolutePath());
    }

    private static InputStream getResourceStream(String filePath) {
        return Config.class.getClassLoader().getResourceAsStream(filePath);
    }

    private static void logResourceNotFound(String filePath) {
        Logger.getGlobal().severe("Unable to find resource: " + filePath);
    }

    private static File createTargetFile(String filePath) {
        File programDir = new File(EyesightNotify.getProgramDirectoryPath());
        programDir.mkdirs();
        return new File(programDir, filePath);
    }

    private static void copyResourceToFile(InputStream inputStream, File targetFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
