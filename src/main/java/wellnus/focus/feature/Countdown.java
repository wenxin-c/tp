package wellnus.focus.feature;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import wellnus.ui.TextUi;

/**
 * Class to represent a timer counting down given a specific minutes.
 * This class contains a Timer class which is used to simulate a clock counting down.
 * Atomic data type is used to communicate with the timer background thread.
 */
public class Countdown {

    private static final int ONE_SECOND = 1000;
    private static final int DELAY_TIME = 0;
    private static final int DEFAULT_STOP_TIME = 0;
    private static final int DEFAULT_SECONDS = 59;
    private static final int INITIAL_SECONDS = 0;
    private static final String MINUTES_INPUT_ASSERTION = "Minutes should be greater than 0";
    private static final String STOP_BEFORE_START_ASSERTION = "Timer should be started before trying to stop it";
    private static final String TIMER_NOT_RUNNING_ASSERTION = "Timer should not be running";
    private static final String TIMER_COMPLETE_MESSAGE = "Type start to begin the next countdown";
    private TextUi textUi;
    private Timer timer;
    private int minutes;
    private int inputMinutes;
    private int seconds;
    private final String description;
    private AtomicBoolean isCompletedCountdown;
    private AtomicBoolean isRunClock;

    /**
     * Constructor of Countdown.
     * This will initialise the private attributes of Countdown object.
     *
     * @param minutes     the number of minutes to countdown
     * @param description description of the current task user is focusing on
     */
    public Countdown(int minutes, String description) {
        assert minutes > 0 : MINUTES_INPUT_ASSERTION;
        this.minutes = minutes;
        this.inputMinutes = minutes;
        this.seconds = INITIAL_SECONDS;
        this.isCompletedCountdown = new AtomicBoolean(false);
        this.isRunClock = new AtomicBoolean(false);
        this.description = description;
        this.textUi = new TextUi();
    }

    /**
     * This method will execute the actions necessary when a countdown completes.
     * The timer will be stopped and a beep sound will be played.
     * A message will be printed to the user to notify them that the countdown has completed.
     */
    private void timerComplete() {
        setStop();
        java.awt.Toolkit.getDefaultToolkit().beep();
        textUi.printOutputMessage(TIMER_COMPLETE_MESSAGE);
        this.minutes = inputMinutes;
    }

    /**
     * This method will decrement the minutes by 1;
     */
    private void decrementMinutes() {
        minutes--;
    }

    /**
     * This method will decrement the seconds by 1;
     */
    private void decrementSeconds() {
        seconds--;
    }

    /**
     * This method will start the countdown timer.
     * Timer will notify user when the countdown timer has completed by playing a beep sound.
     * The timer will run in the background and will be stopped when the countdown minutes and seconds reaches 0.
     */
    public void start() {
        assert isRunClock.get() == false : TIMER_NOT_RUNNING_ASSERTION;
        /*
         * new Timer() results in a background Thread being created.
         * We lazy initialise this Thread by calling new Timer() inside start() instead of
         * Countdown's constructor.
         * This also reduces memory usage.
         */
        timer = new Timer();
        TimerTask countdownTask = new TimerTask() {
            @Override
            public void run() {
                if (isRunClock.get()) {
                    if (seconds == DEFAULT_STOP_TIME && minutes == DEFAULT_STOP_TIME) {
                        timerComplete();
                    } else if (seconds == DEFAULT_STOP_TIME) {
                        seconds = DEFAULT_SECONDS;
                        decrementMinutes();
                    } else {
                        decrementSeconds();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(countdownTask, DELAY_TIME, ONE_SECOND);

    }

    /**
     * This method will return the status of the countdown timer.
     *
     * @return true if the countdown timer has completed, false otherwise
     */
    public boolean getIsCompletedCountdown() {
        return isCompletedCountdown.get();
    }

    /**
     * This method will stop the countdown timer and stops the background thread.
     */
    public void setStop() {
        // timer is only initialised in start() method, so calling setStop() leads
        // to a crash. Catch this mistake with an assertion
        assert timer != null : STOP_BEFORE_START_ASSERTION;
        isCompletedCountdown.set(true);
        isRunClock.set(false);
        timer.cancel();
        timer.purge();
    }

    /**
     * This method will allow the countdown timer to count down.
     * It does so by allowing the minutes and seconds to be decremented.
     */
    public void setStart() {
        isRunClock.set(true);
    }

    /**
     * This method will pause the countdown timer.
     */
    public void setPause() {
        isRunClock.set(false);
    }

    /**
     * This method will return the status of the countdown timer.
     *
     * @return true if the countdown timer is running, false otherwise
     */
    public boolean getIsRunning() {
        return isRunClock.get();
    }

    /**
     * This method will return the current minutes of the countdown timer.
     *
     * @return the minutes of the countdown timer
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * This method will return the current seconds of the countdown timer.
     *
     * @return the seconds of the countdown timer
     */
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * This method will return the description of the countdown timer.
     *
     * @return the description of the countdown timer
     */
    public String getDescription() {
        return this.description;
    }

}
