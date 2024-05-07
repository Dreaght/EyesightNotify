package org.dreaght.eyesightnotify.util;

import org.dreaght.eyesightnotify.EyesightNotify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the sound files util.
 */
public class SoundFilesUtil extends FileUtil {
    private final File soundFolder;

    public SoundFilesUtil(String folderPath) {
        this.soundFolder = getSoundDirectory(folderPath);
    }

    /**
     * Gets the sound directory File.
     * @param soundFolderName
     * @return Sound directory File.
     */
    public File getSoundDirectory(String soundFolderName) {
        File soundDirectory = new File(EyesightNotify.getProgramDirectoryPath() + File.separator + soundFolderName);
        soundDirectory.mkdirs();

        return soundDirectory;
    }

    /**
     * Gets the random sound file of loaded.
     * @return Sound File.
     */
    public File getRandomSoundFileOrNull() {
        List<File> soundFiles = listFilesInSoundFolder();

        if (soundFiles.isEmpty()) {
            EyesightNotify.getLogger().warning("No sound files found in the sounds folder!\n" +
                    "Sound folder: " + soundFolder.getAbsolutePath());
            return null;
        }
        return soundFiles.get((int) (Math.random() * soundFiles.size()));
    }

    /**
     * Gets the list of supported sound files.
     * @return List of supported sound files.
     */
    public List<File> listFilesInSoundFolder() {
        List<File> soundFiles = new ArrayList<>();

        for (File file : Objects.requireNonNull(soundFolder.listFiles())) {
            addIfSoundFile(soundFiles, file);
        }

        return soundFiles;
    }

    private void addIfSoundFile(List<File> soundFiles, File file) {
        if (hasMatchingExtension(file, "wav")) {
            soundFiles.add(file);
        }
    }
}
