package org.dreaght.eyesightnotify.manager;

import lombok.Getter;
import org.dreaght.eyesightnotify.task.NotifyTimerTask;

import java.util.Timer;

public class TimerManager {
    @Getter
    private static TimerManager instance;
    private final long period;

    private TimerManager(long period) {
        this.period = period;
    }

    public static void init(long period) {
        if (instance == null) {
            instance = new TimerManager(period);
        }
    }

    public void startTask() {
        Timer timer = new Timer();
        NotifyTimerTask timerTask = new NotifyTimerTask();

        timer.scheduleAtFixedRate(timerTask, 0, period);
    }
}
