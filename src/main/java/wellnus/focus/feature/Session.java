package wellnus.focus.feature;


import java.util.ArrayList;

/**
 * Represents a session of Countdown objects.
 * A session is a sequence of Countdown objects represented by an ArrayList.
 * We define a Session to have 4 states.
 * <ol>
 *     <li> Ready
 *          `config` and `start` are only legal here.*
 *     <li> Counting
 *          `pause` and `stop` are only legal here
 *     <li> Waiting
 *          `next` and `stop` are only legal here
 *     <li> Paused
 *          `resume` and `stop` are only legal here
 * </ol>
 * The first timer is a dummy timer
 */
//@@author YongbinWang
public class Session {
    private static final int INCREMENT = 1;
    private static final boolean IS_LAST_COUNTDOWN = true;
    private String workDescription = "Task";
    private final ArrayList<Countdown> session;
    private String breakDescription = "Break";
    private String longBreakDescription = "Long Break";
    private int work = 1;
    private int brk = 1;
    private int longBrk = 1;
    private int cycle = 2;
    private int currentCountdownIndex;

    /**
     * Constructs a Session object.
     * Creates an ArrayList of Countdown objects.
     * Calls fillSession() to fill the session with Countdown objects.
     */
    public Session() {
        this.session = new ArrayList<>();
        initialiseSession();
        this.currentCountdownIndex = session.size() - INCREMENT;
    }

    /**
     * Method to fill the session with Countdown objects in a specific sequence.
     */
    private void fillSession() {
        for (int i = 0; i < cycle; i++) {
            Countdown workCountDown = new Countdown(work, workDescription, !IS_LAST_COUNTDOWN);
            Countdown breakCountDown = new Countdown(brk, breakDescription, !IS_LAST_COUNTDOWN);
            session.add(workCountDown);
            session.add(breakCountDown);
        }
        Countdown longBreak = new Countdown(longBrk, longBreakDescription, IS_LAST_COUNTDOWN);
        int lastIndex = session.size() - 1;
        session.remove(lastIndex);
        session.add(longBreak);
    }

    /**
     * Method to get the session.
     *
     * @return arraylist of countdown objects
     */
    public ArrayList<Countdown> getSession() {
        return this.session;
    }

    /**
     * Method to get the current countdown index.
     *
     * @return the current countdown index
     */
    public int getCurrentCountdownIndex() {
        return this.currentCountdownIndex;
    }

    /**
     * Method to increment the current countdown index if the current countdown is completed.
     */
    public void checkPrevCountdown() {
        if (getCurrentCountdown().getIsReady()) {
            initialiseSession();
            currentCountdownIndex = 0;
            return;
        }
        currentCountdownIndex += INCREMENT;

    }

    public Countdown getCurrentCountdown() {
        return session.get(currentCountdownIndex);
    }

    public boolean isSessionReady() {
        return getCurrentCountdownIndex() == session.size() - INCREMENT && getCurrentCountdown().getIsReady();
    }

    public boolean isSessionCounting() {
        Countdown countdown = getCurrentCountdown();
        return countdown.getIsRunning() && !countdown.getIsCompletedCountdown();
    }

    public boolean isSessionWaiting() {
        Countdown countdown = getCurrentCountdown();
        return !countdown.getIsRunning() && countdown.getIsCompletedCountdown();
    }

    public boolean isSessionPaused() {
        Countdown countdown = getCurrentCountdown();
        return !countdown.getIsRunning() && !countdown.getIsCompletedCountdown() && !countdown.getIsReady();
    }

    public void startTimer() {
        checkPrevCountdown();
        getCurrentCountdown().setStart();
        getCurrentCountdown().start();
    }

    /**
     * Method to (re) initialise a session when start or stop command is executed.
     */
    public void initialiseSession() {
        session.clear();
        fillSession();
        primeSessionIsReady();
    }

    private void primeSessionIsReady() {
        int lastIndex = session.size() - INCREMENT;
        session.get(lastIndex).setIsReady(true);
    }

    public boolean hasAnyCountdown() {
        return session.size() > 0;
    }

    /**
     * Method to reset the current countdown index back to 0.
     * This method is called when the user wants to stop an ongoing session.
     */
    public void resetCurrentCountdownIndex() {
        currentCountdownIndex = session.size() - INCREMENT;
        primeSessionIsReady();
    }

    public int getWork() {
        return work;
    }

    public void setWork(int newWork) {
        this.work = newWork;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int newCycles) {
        this.cycle = newCycles;
        fillSession();
        currentCountdownIndex = session.size() - INCREMENT;
    }

    public int getBrk() {
        return brk;
    }

    public void setBrk(int newBrk) {
        this.brk = newBrk;
    }

    public int getLongBrk() {
        return longBrk;
    }

    public void setLongBrk(int newLongBrk) {
        this.longBrk = newLongBrk;
    }

}
