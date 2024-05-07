package org.dreaght.eyesightnotify.util;

import java.io.File;

public class LogUtil extends FileUtil {
    private static final File logDir = new File(gerProgramDir(), "logs");

    /**
     * Get and create log folder if it does not exist yet.
     * @return Log folder.
     */
    public static File getLogFolder() {
        return logDir;
    }

    /**
     * Creates the log folder.
     * @return `true` if creates, `false` otherwise.
     */
    public static boolean createLogFolder() {
        return logDir.mkdirs();
    }
}
