package org.dreaght.eyesightnotify.manager;

import org.dreaght.eyesightnotify.util.SoundFilesUtil;

import javax.sound.sampled.*;
import java.io.*;

public class SoundManager {
    public static void playRandomSoundFromResources(String folderPath, float volume)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File randomSoundFile = new SoundFilesUtil(folderPath).getRandomSoundFileOrNull();

        if (randomSoundFile != null)
            playSound(randomSoundFile, volume);
    }

    private static void playSound(File soundFile, float volume)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);

        clip.start();
    }
}
