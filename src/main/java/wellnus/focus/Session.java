package wellnus.focus;


import java.util.ArrayList;

public class Session {
    private final ArrayList<Countdown> session;
    private int work = 1;
    private int brk = 1;
    private int longBrk = 1;
    private int cycle = 3;
    private int currentCountdownIndex = 0;

    public Session() {
        this.session = new ArrayList<>();
        fillSession();
    }

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

    public ArrayList<Countdown> getSession() {
        return this.session;
    }

    public int getCurrentCountdownIndex() {
        return this.currentCountdownIndex;
    }

    public void checkPrevCountdown() {
        if (session.get(currentCountdownIndex).getIsCompletedCountdown()) {
            currentCountdownIndex += 1;
        }
    }

    public void resetCurrentCountdownIndex() {
        currentCountdownIndex = 0;
    }


}
