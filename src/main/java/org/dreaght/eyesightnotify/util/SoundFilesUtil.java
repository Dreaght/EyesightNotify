package org.dreaght.eyesightnotify.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoundFilesUtil {
    private static final String programName = ".eyesight";
    private static final String userHome = System.getProperty("user.home");
    private static final String programDirectoryPath = userHome + File.separator + programName;
    private final File soundFolder;

    public SoundFilesUtil(String folderPath) {
        this.soundFolder = getSoundDirectory(folderPath);
    }

    public File getSoundDirectory(String soundFolderName) {
        File soundDirectory = new File(programDirectoryPath + File.separator + soundFolderName);
        soundDirectory.mkdirs();

        return soundDirectory;
    }

    public File getRandomSoundFile() {
        List<File> soundFiles = listFilesInSoundFolder();

        if (soundFiles.isEmpty()) {
            throw new RuntimeException("No sound files found in the sounds folder");
        }
        return soundFiles.get((int) (Math.random() * soundFiles.size()));
    }

    public List<File> listFilesInSoundFolder() {
        List<File> soundFiles = new ArrayList<>();

        for (File file : Objects.requireNonNull(soundFolder.listFiles())) {
            addIfSoundFile(soundFiles, file);
        }

        return soundFiles;
    }

    private void addIfSoundFile(List<File> soundFiles, File file) {
        String fileName = file.getName();

        if (fileName.endsWith(".wav")) {
            soundFiles.add(file);
        }
    }
}
