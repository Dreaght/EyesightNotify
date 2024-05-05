package org.dreaght.eyesightnotify;

import org.dreaght.eyesightnotify.manager.TimerManager;

public class EyesightNotify {
    public static void main(String[] args) {
        TimerManager.init(20 * 1000 * 60);
        TimerManager timerManager = TimerManager.getInstance();

        timerManager.startTask();
    }
}
