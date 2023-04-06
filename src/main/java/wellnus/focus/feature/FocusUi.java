package wellnus.focus.feature;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;
import wellnus.ui.TextUi;

/**
 * FocusUi contains extra logic to handle special cursor-printing logic and reading of stdin
 * due to the need to block all user input when the countdown timer starts counting down from
 * (10,9,...,1).
 */
public class FocusUi extends TextUi {
    private static final Logger LOGGER = WellNusLogger.getLogger("FocusUiLogger");
    private static final String NO_INPUT_ELEMENT_MSG = "There is no new line of element,"
            + "please key in your input!!";
    private static final String BUFFER_OVERFLOW_MSG = "Your input is too long,"
            + "please shorten it!!";
    private static final String SEPARATOR = "*";
    private static final int FLUSH_DELAY_TIMING = 1000;
    private static final boolean NO_PRINT_CURSOR = false;
    private static final boolean PRINT_CURSOR = true;


    /**
     * Constructs a FocusUi variant of TextUi
     */
    public FocusUi() {
        super();
        setSeparator(SEPARATOR);
    }

    /**
     * FocusUi specific getCommand that accounts for proper printing of
     * the cursor and accepting user input when the countdown printing phase (10,9,...,1)
     * is not ongoing.
     * <p>
     * In this scenario, we define invalid phase to be the point where
     * the countdown prints (10,9,...,1) on screen.
     * Otherwise, it is valid.
     *
     * @return User input command with leading/dangling whitespace being removed
     */
    public String getCommand(Session session) {
        Scanner scanner = getScanner();
        // User tries to input a command in the invalid phase
        if (isBlocking(session)) {
            return waitAndGetCommand(session, scanner);
        }
        // User entered command during valid phase
        String userCommand = getCommandWithCursor(PRINT_CURSOR, scanner);
        // Edge case: User maliciously waits until the countdown print starts before pressing enter.
        // In this case, the command should NOT be processed until the countdown phase is over.
        if (isBlocking(session)) {
            return waitAndGetCommand(session, scanner);
        }
        return userCommand;
    }

    private void printLogo(String logo) {
        System.out.print(logo);
    }

    protected void printLogoWithSeparator(String logo) {
        printSeparator();
        printLogo(logo);
    }

    /**
     * User has entered a command whilst the countdown printing phase is ongoing.
     * <p>
     * Waits for the printing phase to end, flush stdin and then gets the command without printing a cursor
     * as the Countdown will automatically print a cursor on completion.
     *
     * @param session the session containing all the countdowns
     * @param scanner the scanner reading user input
     * @return String User input command with leading/dangling whitespace being removed
     */
    private String waitAndGetCommand(Session session, Scanner scanner) {
        waitForCountdownPrint(session);
        flushInput(scanner);
        // Get command without printing a cursor
        return getCommandWithCursor(NO_PRINT_CURSOR, scanner);
    }

    /**
     * This method is the lowest abstraction layer, talking to
     *
     * @param isPrintCursor the session containing all the countdowns
     * @param scanner       the scanner reading user input
     * @return User input command with leading/dangling whitespace being removed
     */
    private String getCommandWithCursor(boolean isPrintCursor, Scanner scanner) {
        String userCommand = "";
        if (isPrintCursor) {
            printCursor();
        }
        try {
            String inputLine = scanner.nextLine();
            userCommand = inputLine.trim();
        } catch (BufferOverflowException bufferOverFlowException) {
            LOGGER.log(Level.INFO, BUFFER_OVERFLOW_MSG);
            printErrorFor(bufferOverFlowException, BUFFER_OVERFLOW_MSG);
        } catch (NoSuchElementException noElementException) {
            LOGGER.log(Level.INFO, NO_INPUT_ELEMENT_MSG);
            printErrorFor(noElementException, NO_INPUT_ELEMENT_MSG);
        }
        return userCommand;
    }

    /**
     * Checks if the countTimer is in an invalid phase which
     * means that all stdin should be blocked.
     *
     * @param session the session containing all the countdowns
     * @return boolean Representing the countdown state
     */
    public boolean isBlocking(Session session) {
        return session.getCurrentCountdown().isCountdownPrinting() && session.isSessionCounting();
    }

    /**
     * If the countdown is in its printing phase, wait for it to finish first
     * The while loop is designed as such for checkstyle correctness (no empty body)
     */
    private void waitForCountdownPrint(Session session) {
        while (true) {
            if (!(isBlocking(session))) {
                break;
            }
        }
    }

    /**
     * Flush all input from Stdin.
     * <p>
     * Gracefully rejects all overzealous input from users, cleaning the input buffer.
     *
     * @param scanner Reference to the scanner being used by the UI
     */
    private void flushInput(Scanner scanner) {
        // Implement a timeout to avoid hanging
        long flushStartTime = System.currentTimeMillis();
        while (scanner.hasNextLine()
                && System.currentTimeMillis() - flushStartTime < FLUSH_DELAY_TIMING) {
            // Discard extraneous input
            scanner.nextLine();
        }
    }

}
