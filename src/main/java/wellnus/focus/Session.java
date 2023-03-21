package wellnus.focus;


import java.util.ArrayList;

public class Session {
    private final ArrayList<Countdown> session;
    private int work = 1;
    private int brk = 1;
    private int longBrk = 1;
    private int cycle = 3;
    private int currentCountdownIndex = 0;

    /**
     * Constructs a Session object.
     * Creates an ArrayList of Countdown objects.
     * Calls fillSession() to fill the session with Countdown objects.
     */
    public Session() {
        this.session = new ArrayList<>();
        fillSession();
    }

    /**
     * Method to fill the session with Countdown objects in a specific sequence.
     */
    private void fillSession() {
        for (int i = 0; i < cycle; i++) {
            Countdown workCountDown = new Countdown(work, "Task");
            Countdown breakCountDown = new Countdown(brk, "Break");
            session.add(workCountDown);
            session.add(breakCountDown);
        }
        Countdown longBreak = new Countdown(longBrk, "Long Break");
        session.remove((cycle * 2) - 1);
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
     * Method to get the current countdown.
     *
     * @return current index to show which countdown is running
     */
    public void checkPrevCountdown() {
        if (session.get(currentCountdownIndex).getIsCompletedCountdown()) {
            currentCountdownIndex += 1;
        }
    }

    /**
     * Method to reset the current countdown index back to 0.
     * This method is called when the user wants to stop an ongoing session.
     */
    public void resetCurrentCountdownIndex() {
        currentCountdownIndex = 0;
    }


}
