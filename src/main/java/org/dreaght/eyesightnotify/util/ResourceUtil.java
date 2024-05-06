package org.dreaght.eyesightnotify.util;

import org.dreaght.eyesightnotify.Config;
import org.dreaght.eyesightnotify.EyesightNotify;

import java.io.*;

public class ResourceUtil {
    private final String resourcePath;

    public ResourceUtil(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public FileInputStream makeAndGetFileInputStream() throws IOException {
        File file = new File(EyesightNotify.getProgramDirectoryPath(), resourcePath);
        if (file.exists()) {
            return new FileInputStream(file);
        }

        if (saveDefaultResource()) {
            return makeAndGetFileInputStream();
        }

        EyesightNotify.getLogger().severe("No resource found: " + resourcePath);
        return (FileInputStream) getResourceStream();
    }

    public boolean saveDefaultResource() throws IOException {
        createProgramDirectoryIfNotExist();

        InputStream inputStream = getResourceStream();
        if (inputStream == null) {
            logResourceNotFound(resourcePath);
            return false;
        }

        File targetFile = getFile();
        FileUtil.copyInputStreamToFile(inputStream, targetFile);

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

    private void createProgramDirectoryIfNotExist() {
        new File(EyesightNotify.getProgramDirectoryPath()).mkdirs();
    }
}
