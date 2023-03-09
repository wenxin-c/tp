package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test methods associated with TextUi class
class TextUiTest {
    private static final String DEFAULT_SEPARATOR = "----------------------------------------";
    private static final String ALERT_SEPARATOR = "!!!!!!----------!!!!!!!----------!!!!!!!";
    private static final TextUi UI = new TextUi();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Read test input command and return back the command string.<br/>
     * For JUnit testing purpose only.
     *
     * @param readInput scanner object with System.in being overwritten in test
     * @return user input command with leading/dangling whitespace being removed
     */
    public static String getCommand(Scanner readInput) {
        String inputLine = readInput.nextLine();
        String userCommand = inputLine.trim();
        return userCommand;
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //Test whether removal of leading/dangling space is successful
    @Test
    void getCommand_trimSpace_success() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream((" My string " +
                System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner readLine = new Scanner(System.in);
        String command = getCommand(readLine);
        assertEquals("My string", command);
        System.setIn(sysInBackup);
        readLine.close();
    }

    //Test whether default line separator is properly drawn
    @Test
    void printSeparator_defaultVersion_success() {
        UI.printSeparator();
        assertEquals(DEFAULT_SEPARATOR, outputStreamCaptor.toString().trim());
    }

    //Test whether exception message will properly printed with correct format
    @Test
    void printErrorFor_arithmeticException_success() {
        try {
            int result = 2 / 0;
        } catch (ArithmeticException exception) {
            UI.printErrorFor(exception);
        }
        assertEquals(ALERT_SEPARATOR + System.lineSeparator() +
                "    / by zero" + System.lineSeparator() + ALERT_SEPARATOR,
                outputStreamCaptor.toString().trim());
    }

    //Test whether messages will be properly printed with correct format
    @Test
    void printOutputMessage_greeting_success() {
        String greeting = "How are you?";
        UI.printOutputMessage(greeting);
        assertEquals(DEFAULT_SEPARATOR + System.lineSeparator() +
                "    How are you?" + System.lineSeparator() + DEFAULT_SEPARATOR,
                outputStreamCaptor.toString().trim());
    }

}

