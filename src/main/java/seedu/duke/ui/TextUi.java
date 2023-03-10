package seedu.duke.ui;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * TextUI class for reading user inputs and printing outputs.<br/>
 * Subclasses of TextUI class can override separator, printErrorFor and printOutputMessage.<br/>
 * This is to accommodate to the uniqueness of each feature.
 */
public class TextUi {
    private static final String ALERT_SEPARATOR = "!!!!!!----------!!!!!!!----------!!!!!!!";
    private static final String INDENTATION_SPACES = "    ";
    private static final int DEFAULT_SEPARATOR_LENGTH = 40;
    private static final Scanner SCANNER = new Scanner(System.in);

    private String separator = "-";

    /**
     * Print spaces before output message for better formatting.
     */
    public void printIndentation() {
        System.out.print(INDENTATION_SPACES);
    }

    /**
     * Read user's input command and return back the command string.<br/>
     *
     * @return user input command with leading/dangling whitespace being removed
     */
    public String getCommand() {
        String userCommand = "";
        try {
            String inputLine = SCANNER.nextLine();
            userCommand = inputLine.trim();
        } catch (BufferOverflowException bufferOverFlowException) {
            printErrorFor(bufferOverFlowException);
        } catch (NoSuchElementException noElementException) {
            printErrorFor(noElementException);
        }
        return userCommand;
    }

    /**
     * Customise separators for each feature.<br/>
     * At this moment we can only use length == 1 separator for consistency of length of line separator.
     * This will be improved on in the future to allow for more patterns.
     *
     * @param separator length == 1 stirng
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * Print line separators for output lines.<br/>
     * Each subclass inherited from this class can override this method to vary the interface.<br/>
     */
    public void printSeparator() {
        for (int i = 0; i < DEFAULT_SEPARATOR_LENGTH; i += 1) {
            System.out.print(separator);
        }
        System.out.print(System.lineSeparator());
    }

    /**
     * Split a message string to a string array using System.lineSeparator().<br/>
     * This is to help indent each new line during output.<br/>
     * Each line of string will be trimmed to remove leading/dangling whitespace.
     *
     * @param message message to be printed
     * @return messages that are split using lineSeparator
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
     * Output message with one line is also accepted.
     *
     * @param message output message to be printed
     */
    private void printMultiLineMessage(String message) {
        String[] newLineMessage = splitOutputMessage(message);
        for (String msg : newLineMessage) {
            printIndentation();
            System.out.println(msg);
        }
    }

    /**
     * Print exception message with length > 0.<br/>
     * 0 or more lines of messages are accepted but lineSeparator() must be added in message string.<br/>
     * Can override to accommodate to other customised error messages.
     *
     * @param exception the exception being thrown in the program
     */
    public void printErrorFor(Exception exception) {
        System.out.println(ALERT_SEPARATOR);
        String exceptionMsg = exception.getMessage();
        if (exceptionMsg.length() > 0) {
            printMultiLineMessage(exceptionMsg);
        }
        System.out.println(ALERT_SEPARATOR);
    }

    /**
     * Print output message with length > 0.<br/>
     * 0 or more lines of messages are accepted but lineSeparator() must be added in message string.<br/>
     * Can override to accommodate to other customised error messages.
     *
     * @param message the exception being thrown in the program
     */
    public void printOutputMessage(String message) {
        printSeparator();
        if (message.length() > 0) {
            printMultiLineMessage(message);
        }
        printSeparator();
    }
}
