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
     * This is for usage. <br/>
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
     * Print exception message.<br/>
     * Can override to accommodate to other customised error messages.
     *
     * @param exception the exception being thrown in the program
     */
    public void printErrorFor(Exception exception) {
        System.out.println(ALERT_SEPARATOR);
        printIndentation();
        System.out.println(exception.getMessage());
        System.out.println(ALERT_SEPARATOR);
    }

    /**
     * Print exception message.<br/>
     * Can override to accommodate to other customised error messages.
     *
     * @param message the exception being thrown in the program
     */
    public void printOutputMessage(String message) {
        printSeparator();
        printIndentation();
        System.out.println(message);
        printSeparator();
    }
}

