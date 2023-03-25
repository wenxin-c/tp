package wellnus.focus.feature;


import java.util.ArrayList;

/**
 * Represents a session of Countdown objects.
 * A session is a sequence of Countdown objects represented by an ArrayList.
 */
public class Session {
    private static final int INCREMENT = 1;
    private final ArrayList<Countdown> session;
    private String workDescription = "Task";
    private String breakDescription = "Break";
    private String longBreakDescription = "Long Break";
    private int work = 1;
    private int brk = 1;
    private int longBrk = 1;
    private int cycle = 2;
    private int currentCountdownIndex = 0;

    /**
     * Constructs a Session object.
     * Creates an ArrayList of Countdown objects.
     * Calls fillSession() to fill the session with Countdown objects.
     */
    public Session() {
        this.session = new ArrayList<>();
    }

    /**
     * Method to fill the session with Countdown objects in a specific sequence.
     */
    private void fillSession() {
        for (int i = 0; i < cycle; i++) {
            Countdown workCountDown = new Countdown(work, workDescription);
            Countdown breakCountDown = new Countdown(brk, breakDescription);
            session.add(workCountDown);
            session.add(breakCountDown);
        }
        Countdown longBreak = new Countdown(longBrk, longBreakDescription);
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
        if (session.size() == 0) {
            return;
        }
        if (session.get(currentCountdownIndex).getIsCompletedCountdown()
                && currentCountdownIndex < session.size() - INCREMENT) {
            currentCountdownIndex += INCREMENT;
        }
    }

    public void init() {
        fillSession();
    }

    public boolean hasAnyCountdown() {
        return session.size() > 0;
    }

    /**
     * Method to reset the current countdown index back to 0.
     * This method is called when the user wants to stop an ongoing session.
     */
    public void resetCurrentCountdownIndex() {
        currentCountdownIndex = 0;
    }
}
