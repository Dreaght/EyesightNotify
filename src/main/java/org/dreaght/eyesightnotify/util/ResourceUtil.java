package org.dreaght.eyesightnotify.util;

import org.dreaght.eyesightnotify.Config;
import org.dreaght.eyesightnotify.EyesightNotify;

import java.io.*;

/**
 * Represents the resources access util.
 */
public class ResourceUtil extends FileUtil {
    private final String resourcePath;

    public ResourceUtil(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * Gets the file input stream of resource path.
     * @return File input stream.
     * @throws IOException
     */
    public FileInputStream getFileInputStreamOrNull() throws IOException {
        File file = new File(EyesightNotify.getProgramDirectoryPath(), resourcePath);
        FileInputStream fileInputStream = null;

        if (file.exists()) {
            fileInputStream = new FileInputStream(file);
        }

        return fileInputStream;
    }

    /**
     * Save resource to file.
     * @return `true` if saved, `false` otherwise.
     * @throws IOException
     */
    public boolean saveDefaultResource() throws IOException {
        gerProgramDir();

        InputStream inputStream = getResourceStream();
        if (inputStream == null) {
            logResourceNotFound(resourcePath);
            return false;
        }

        File targetFile = getFile();
        copyInputStreamToFile(inputStream, targetFile);

        EyesightNotify.getLogger().info("Config file saved to: " + targetFile.getAbsolutePath());
        return true;
    }

    public InputStream getResourceStream() {
        return Config.class.getClassLoader().getResourceAsStream(resourcePath);
    }

    private void logResourceNotFound(String filePath) {
        EyesightNotify.getLogger().severe("Unable to find resource: " + filePath + "\n" +
                "Install the correct version of this program at: https://github.com/Dreaght/EyesightNotify");
    }

    public File getFile() {
        File programDir = new File(EyesightNotify.getProgramDirectoryPath());
        return new File(programDir, resourcePath);
    }
}
