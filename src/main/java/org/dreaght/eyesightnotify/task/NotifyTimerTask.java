package org.dreaght.eyesightnotify.task;

import org.dreaght.eyesightnotify.manager.NotifyManager;
import org.dreaght.eyesightnotify.manager.SoundManager;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.TimerTask;

public class NotifyTimerTask extends TimerTask {
    private static final String APP_NAME = "Eyesight";
    private static final String notificationMessage = "Go away for 20 Secs!";
    private static final String soundFolderName = "sounds";
    private static final Float soundVolume = 6.0f;

    @Override
    public void run() {
        NotifyManager.sendNotification(APP_NAME, notificationMessage);
        try {
            SoundManager.playRandomSoundFromResources(soundFolderName, soundVolume);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
}
