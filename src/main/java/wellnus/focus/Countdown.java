package wellnus.focus;

import wellnus.ui.TextUi;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Countdown {

    private TextUi textUi;
    private Timer timer;
    private int minutes;
    private int seconds;
    private final String description;
    private AtomicBoolean isCompletedCountdown;
    private AtomicBoolean isRunClock;

    public Countdown(int minutes, String description) {
        this.minutes = minutes;
        this.seconds = 0;
        this.timer = new Timer();
        this.isCompletedCountdown = new AtomicBoolean(false);
        this.isRunClock = new AtomicBoolean(false);
        this.description = description;
        this.textUi = new TextUi();
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isRunClock.get()) {
                    if (seconds == 0 && minutes == 0) {
                        isCompletedCountdown.set(true);
                        timer.cancel();
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        textUi.printOutputMessage("Type start for begin the next countdown");
                    } else if (seconds == 0) {
                        seconds = 59;
                        minutes--;
                    } else {
                        seconds--;
                    }
                }

            }
        }, 0, 1000);

    }

    public boolean getIsCompletedCountdown() {
        return isCompletedCountdown.get();
    }

    public void setStop() {
        timer.cancel();
        textUi.printOutputMessage("Timer has stopped");
    }

    public void setStart() {
        isRunClock.set(true);
    }


    public boolean getIsRunning() {
        return isRunClock.get();
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

}
