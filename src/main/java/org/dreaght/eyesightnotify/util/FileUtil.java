package org.dreaght.eyesightnotify.util;

import java.io.*;
import java.util.Arrays;

public class FileUtil {
    public static void copyInputStreamToFile(InputStream inputStream, File targetFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(targetFile)) {
            inputStream.transferTo(outputStream);
        }
    }

    public static boolean hasMatchingExtension(File file, String... extensions) {
        String fileName = file.getName();
        return Arrays.stream(extensions).anyMatch(fileName::endsWith);
    }
}
