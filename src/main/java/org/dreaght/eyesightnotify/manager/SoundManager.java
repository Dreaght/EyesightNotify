package org.dreaght.eyesightnotify.manager;

import org.dreaght.eyesightnotify.util.SoundFilesUtil;

import javax.sound.sampled.*;
import java.io.*;

public class SoundManager {
    public static void playRandomSoundFromResources(String folderPath, double volume)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File randomSoundFile = new SoundFilesUtil(folderPath).getRandomSoundFileOrNull();

        if (randomSoundFile != null)
            playSound(randomSoundFile, volume);
    }

    private static void playSound(File soundFile, double volume)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);

        setVolume(clip, (float) volume);

        clip.start();

        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                event.getLine().close();
            }
        });
    }

    public static float getVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public static void setVolume(Clip clip, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
