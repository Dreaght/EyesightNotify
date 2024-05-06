package org.dreaght.eyesightnotify.util;

import org.dreaght.eyesightnotify.EyesightNotify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoundFilesUtil {
    private final File soundFolder;

    public SoundFilesUtil(String folderPath) {
        this.soundFolder = getSoundDirectory(folderPath);
    }

    public File getSoundDirectory(String soundFolderName) {
        File soundDirectory = new File(EyesightNotify.getProgramDirectoryPath() + File.separator + soundFolderName);
        soundDirectory.mkdirs();

        return soundDirectory;
    }

    public File getRandomSoundFileOrNull() {
        List<File> soundFiles = listFilesInSoundFolder();

        if (soundFiles.isEmpty()) {
            EyesightNotify.getLogger().warning("No sound files found in the sounds folder!\n" +
                    "Sound folder: " + soundFolder.getAbsolutePath());
            return null;
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
        if (FileUtil.hasMatchingExtension(file, "wav")) {
            soundFiles.add(file);
        }
    }
}
