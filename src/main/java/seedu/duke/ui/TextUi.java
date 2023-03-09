package seedu.duke.ui;

import java.util.Scanner;

/**
 * UI class for reading user inputs and printing outputs
 */
public class TextUi {
    private static final String SEPARATOR = "-";
    private static final String INDENTATION_SPACES = "    ";

    /**
     * Read user's input command and return back the command string.<br/>
     *
     * @return user input command with leading/dangling whitespace being removed
     */
    public static String getCommand() {
        Scanner readInput = new Scanner(System.in);
        String inputLine;
        String userCommand;
        inputLine = readInput.nextLine();
        userCommand = inputLine.trim();
        return userCommand;
    }

    /**
     * Print line separators for output lines.<br/>
     * Each subclass inherited from this class can override this method to vary the interface.<br/>
     *
     * @param numOfChars determine the length of separation line to accommodate for different length of output
     */
    public static void printSeparator(int numOfChars) {
        for (int i=0; i<numOfChars; ++i) {
            System.out.print(SEPARATOR);
        }
        System.out.print(System.lineSeparator());
    };

    /**
     * Print spaces before output message for better formatting.
     */
    public static void printIndentation() {
        System.out.print(INDENTATION_SPACES);
    }

    /**
     * Print exception message.
     * Can override to accommodate to other customised error messages.
     *
     * @param exception the exception being thrown in the program
     */
    public static void printErrorFor(Exception exception) {
        printSeparator(40);
        printIndentation();
        System.out.println(exception.getMessage());
        printSeparator(40);
    }

    /**
     * Print exception message.
     * Can override to accommodate to other customised error messages.
     *
     * @param message the exception being thrown in the program
     */
    public static void printOutputMessage(String message) {
        printSeparator(40);
        printIndentation();
        System.out.println(message);
        printSeparator(40);
    }
}

