package wellnus;

import wellnus.common.MainManager;
import wellnus.exception.BadCommandException;
import wellnus.ui.TextUi;


public class WellNus {
    private static final String BYE_MESSAGE = "Thank you for using WellNUS++! See you again soon Dx";
    private static final String GREETING_MESSAGE = "Very good day to you! Welcome to ";
    private static final String NEWLINE = System.lineSeparator();
    private final TextUi textUi;
    private final MainManager mainManager;

    public WellNus() {
        this.textUi = new TextUi();
        this.mainManager = new MainManager();
    }

    private static String getWellNusLogo() {
        return NEWLINE
                + ",--.   ,--.       ,--.,--.,--.  ,--.,--. ,--. ,---.     |  |        |  |     " + NEWLINE
                + "|  |   |  | ,---. |  ||  ||  ,'.|  ||  | |  |'   .-',---|  |---.,---|  |---. " + NEWLINE
                + "|  |.'.|  || .-. :|  ||  ||  |' '  ||  | |  |`.  `-.'---|  |---''---|  |---' " + NEWLINE
                + "|   ,'.   |\\   --.|  ||  ||  | `   |'  '-'  '.-'    |   |  |        |  |     " + NEWLINE
                + "'--'   '--' `----'`--'`--'`--'  `--' `-----' `-----'    `--'        `--'     " + NEWLINE;
    }

    private void byeUser() {
        this.getTextUi().printOutputMessage(WellNus.BYE_MESSAGE);
    }

    private void executeUserCommands() {
        this.getMainManager().runEventDriver();
    }

    private MainManager getMainManager() {
        return this.mainManager;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private void greet() {
        this.getTextUi().printOutputMessage(WellNus.GREETING_MESSAGE + WellNus.NEWLINE
                + WellNus.getWellNusLogo());
    }

    /**
     * Executes the WellNus application and provides the user with our features.
     *
     * @param args Commandline arguments passed to the WellNus Java ARchive
     */
    public static void main(String[] args) {
        new WellNus().start();
    }

    public void start() {
        this.greet();
        this.executeUserCommands();
        this.byeUser();
    }

}

