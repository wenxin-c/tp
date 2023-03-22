package wellnus;

import wellnus.common.MainManager;
import wellnus.manager.Manager;
import wellnus.ui.TextUi;

/**
 * Main class of our WellNUS++ application. main() is executed when the application is launched.<br/>
 * <p>
 * Control is then passed to MainManager.runEventDriver().
 *
 * @see MainManager#runEventDriver()
 */
public class WellNus {
    private static final String BYE_MESSAGE = "Thank you for using WellNUS++! See you again soon Dx";
    private static final String GREETING_MESSAGE = "Very good day to you! Welcome to ";
    private static final String NEWLINE = System.lineSeparator();
    private final TextUi textUi;
    private final MainManager mainManager;

    /**
     * Initialises an instance of WellNUS++, which needs <code>TextUi</code>
     * and <code>MainManager</code>.
     */
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

    /**
     * Calls MainManager to read and execute the user's commands.
     *
     * @see Manager#runEventDriver()
     */
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

    /**
     * Starts up WellNUS++: Greets the user, reads for commands until a exit command is given,
     * and bids the user goodbye.<br/>
     * <p>
     * The bulk of the work is done in executeUserCommands(), which delegates control to the
     * appropriate Manager.
     *
     * @see Manager#runEventDriver()
     */
    public void start() {
        this.greet();
        this.executeUserCommands();
        this.byeUser();
    }

}

