package wellnus.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the functionality of methods associated with TextUi class.
 */
class TextUiTest {
    private static final String DEFAULT_SEPARATOR = "----------------------------------------"
            + "--------------------";
    private static final String ALERT_SEPARATOR = "!!!!!!-------!!!!!--------!!!!!!!------!!!!!"
            + "---------!!!!!!!";
    private static final String TEST_OUTPUT_MSG_ONE = "Hello ";
    private static final String TEST_OUTPUT_MSG_TWO = "World";
    private static final String OUTPUT_MSG_ONE = "Hello";
    private static final String INDENTATION = "    ";
    private static final String ARITHMETIC_EXCEPTION_MSG_ONE = "Please check your arithmetic equation!!";
    private static final String ARITHMETIC_EXCEPTION_MSG_TWO = "E.g. Denominator is 0 in division.";
    private static final String ERROR_MESSAGE_LABEL = "Error Message:";
    private static final String EXTRA_MESSAGE_LABEL = "Note:";
    private static final String GREET_MSG = "How are you?";
    private static final String OPERATION = "/ by zero";
    private static final String INPUT_WHITESPACE = " My string ";
    private static final String INPUT_WITHOUT_WHITESPACE = "My string";
    private static final TextUi UI = new TextUi();
    private static final int TEST_NUMERATOR = 2;
    private static final int TEST_DENOMINATOR = 0;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Read test input command and return back the command string.<br/>
     * For JUnit testing purpose only.
     *
     * @param readInput Scanner object with System.in being overwritten in test
     * @return User input command with leading/dangling whitespace being removed
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

    /**
     * Test whether removal of command leading/dangling space is successful.<br/>
     * Expect success.
     */
    @Test
    void getCommand_trimSpace_success() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream((INPUT_WHITESPACE
                + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner readLine = new Scanner(System.in);
        String command = getCommand(readLine);
        assertEquals(INPUT_WITHOUT_WHITESPACE, command);
        System.setIn(sysInBackup);
        readLine.close();
    }

    /**
     * Test whether default line separator is properly drawn.<br/>
     * Expect success.
     */
    @Test
    void printSeparator_defaultVersion_success() {
        UI.printSeparator();
        assertEquals(DEFAULT_SEPARATOR, outputStreamCaptor.toString().trim());
    }

    /**
     * Test whether exception message will be printed properly with correct format.<br/>
     * Expect both error message and error notes.
     */
    @Test
    void printErrorFor_arithmeticException_success() {
        String errorMsg = ARITHMETIC_EXCEPTION_MSG_ONE + System.lineSeparator() + ARITHMETIC_EXCEPTION_MSG_TWO;
        try {
            int result = TEST_NUMERATOR / TEST_DENOMINATOR;
        } catch (ArithmeticException exception) {
            UI.printErrorFor(exception, errorMsg);
        }
        assertEquals(ALERT_SEPARATOR + System.lineSeparator() + ERROR_MESSAGE_LABEL
                + System.lineSeparator() + INDENTATION + OPERATION + System.lineSeparator()
                + EXTRA_MESSAGE_LABEL + System.lineSeparator() + INDENTATION + ARITHMETIC_EXCEPTION_MSG_ONE
                + System.lineSeparator() + INDENTATION + ARITHMETIC_EXCEPTION_MSG_TWO + System.lineSeparator()
                + ALERT_SEPARATOR,
                outputStreamCaptor.toString().trim());
    }

    /**
     * Test whether messages will be properly printed with correct format.
     */
    @Test
    void printOutputMessage_greeting_success() {
        UI.printOutputMessage(GREET_MSG);
        assertEquals(DEFAULT_SEPARATOR + System.lineSeparator()
                + INDENTATION + GREET_MSG + System.lineSeparator() + DEFAULT_SEPARATOR,
                outputStreamCaptor.toString().trim());
    }

    /**
     * Test whether multi-line message can be printed with correct indentation.<br/>
     * Expect string split by lineSeparator() and indentation in front of each new line.
     */
    @Test
    void printMultiLineMessage_twoLines_success() {
        String multiLineOutput = TEST_OUTPUT_MSG_ONE + System.lineSeparator() + TEST_OUTPUT_MSG_TWO;
        UI.printOutputMessage(multiLineOutput);
        assertEquals((DEFAULT_SEPARATOR + System.lineSeparator() + INDENTATION + OUTPUT_MSG_ONE
                + System.lineSeparator() + INDENTATION + TEST_OUTPUT_MSG_TWO
                + System.lineSeparator() + DEFAULT_SEPARATOR),
                outputStreamCaptor.toString().trim());
    }
}

