package wellnus.ui;

import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;

//@@author wenxin-c

/**
 * TextUi class for reading user inputs and printing outputs.<br/>
 * <br/>
 * Subclasses of TextUI class can override separator, printErrorFor and printOutputMessage.<br/>
 * This is to accommodate to the uniqueness of each feature.
 */
public class TextUi {
    private static final Logger LOGGER = WellNusLogger.getLogger("TextUiLogger");
    private static final String ALERT_SEPARATOR = "!!!!!!-------!!!!!--------!!!!!!!------!!!!!"
            + "---------!!!!!!!";
    private static final String INDENTATION_SPACES = "    ";
    private static final int DEFAULT_SEPARATOR_LENGTH = 60;
    private static final int EMPTY_MESSAGE = 0;
    private static final String ERROR_MESSAGE_LABEL = "Error Message:";
    private static final String ERROR_EMPTY_STRING = "The string argument should not be empty!";
    private static final String EXTRA_MESSAGE_LABEL = "Note:";
    private static final String NO_INPUT_ELEMENT_MSG = "There is no new line of element, "
            + "please key in input!";
    private static final String BUFFER_OVERFLOW_MSG = "Input is too long, please shorten your input!";
    private static final String CURSOR_CARET = "(%s):~$ ";
    private Scanner scanner;
    private String separator = "-";
    private int separatorLength;
    private String cursor = CURSOR_CARET;

    /**
     * Returns a new instance of TextUi that reads user input from the default
     * System.in InputStream.
     */
    public TextUi() {
        this(System.in);
    }

    /**
     * Returns a new instance of TextUi that reads user input from the given
     * InputStream.
     *
     * @param inputStream InputStream that WellNUS++ will read user input(commands) from
     */
    public TextUi(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.separatorLength = DEFAULT_SEPARATOR_LENGTH;
    }

    //@@author wenxin-c

    /**
     * Print spaces before output message for better formatting.
     */
    public void printIndentation() {
        System.out.print(INDENTATION_SPACES);
    }

    /**
     * Read user's input command and return back the command string.<br/>
     *
     * @return User input command with leading/dangling whitespace being removed
     */
    public String getCommand() {
        printCursor();
        String userCommand = "";
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
     * Customise separators for each feature.<br/>
     * <br/>
     * At this moment we can only use length == 1 separator for consistency of length of line separator.<br/>
     * This will be improved on in the future to allow for more patterns.
     *
     * @param separator Length == 1 string
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * Customises the length of the separator as needed by a particular feature's
     * unique style.
     *
     * @param separatorLength Number of characters to print in the feature's separator
     */
    public void setSeparatorLength(int separatorLength) {
        this.separatorLength = separatorLength;
    }

    /**
     * Print line separators for output lines.<br/>
     * <br/>
     * Each subclass inherited from this class can override this method to vary the interface.
     */
    public void printSeparator() {
        for (int i = 0; i < separatorLength; i += 1) {
            System.out.print(separator);
        }
        System.out.print(System.lineSeparator());
    }

    /**
     * Split a message string to a string array using System.lineSeparator().<br/>
     * <br/>
     * This is to help indent each new line during output.<br/>
     * Each line of string will be trimmed to remove leading/dangling whitespace.
     *
     * @param message Message to be printed
     * @return Messages that are split using lineSeparator
     */
    private String[] splitOutputMessage(String message) {
        String[] newLineMessages = message.trim().split(System.lineSeparator());
        for (int i = 0; i < newLineMessages.length; i += 1) {
            newLineMessages[i] = newLineMessages[i].trim();
        }
        return newLineMessages;
    }

    /**
     * Print each new line of message on a separate line with indentation being added.<br/>
     * <br/>
     * Output message with one line is also accepted.
     *
     * @param message Output message to be printed
     */
    private void printMultiLineMessage(String message) {
        String[] newLineMessages = splitOutputMessage(message);
        for (String msg : newLineMessages) {
            printIndentation();
            System.out.println(msg);
        }
    }

    /**
     * Print exception message with length > 0 and additional notes.<br/>
     * <br/>
     * 0 or more lines of messages are accepted, but lineSeparator() must be added
     * if you wish to have certain message to start on a new line.<br/>
     * Error messages and additional notes will be printed on separate lines with labels.<br/>
     * Can override to accommodate to other customised error messages.<br/>
     * Can improve on what will be printed for empty message in the future.
     *
     * @param exception         The exception being thrown in the program
     * @param additionalMessage Suggestions or notes that help users figure out what causes error
     */
    public void printErrorFor(Exception exception, String additionalMessage) {
        System.out.println(ALERT_SEPARATOR);
        String exceptionMsg = exception.getMessage();
        if (exceptionMsg.length() > EMPTY_MESSAGE) {
            System.out.println(ERROR_MESSAGE_LABEL);
            printMultiLineMessage(exceptionMsg);
            System.out.println(EXTRA_MESSAGE_LABEL);
            printMultiLineMessage(additionalMessage);
        }
        System.out.println(ALERT_SEPARATOR);
    }

    /**
     * Print output message with length > 0.<br/>
     * <br/>
     * 0 or more lines of messages are accepted, but lineSeparator() must be added
     * if you wish to have certain message to start on a new line.<br/>
     * Can override to accommodate to other customised error messages.
     *
     * @param message The exception being thrown in the program
     */
    public void printOutputMessage(String message) {
        printSeparator();
        if (message.length() > EMPTY_MESSAGE) {
            printMultiLineMessage(message);
        }
        printSeparator();
    }

    //@@author nichyjt

    /**
     * Prints a user-friendly cursor with the name of the feature the user is currently in.
     * <p>
     * Cursor format: ([featureName]):~$
     *
     * @param cursorName name of the feature to be tagged to the cursor
     */
    public void setCursorName(String cursorName) {
        assert cursorName != null && cursorName.length() > 0 : ERROR_EMPTY_STRING;
        cursor = String.format(CURSOR_CARET, cursorName);
    }

    /**
     * Utility function to print the cursor to screen
     */
    public void printCursor() {
        System.out.print(cursor);
    }

    /**
     * Utility function to print a newline
     */
    public void printNewline() {
        System.out.println(System.lineSeparator());
    }

    /**
     * Utility function to get the scanner belonging to TextUi.
     *
     * @return Scanner tagged to this instance of TextUi
     */
    public Scanner getScanner() {
        return scanner;
    }

}

