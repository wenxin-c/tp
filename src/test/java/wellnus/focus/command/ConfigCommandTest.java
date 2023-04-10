package wellnus.focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.exception.WellNusException;
import wellnus.focus.feature.FocusUi;
import wellnus.focus.feature.Session;

//@@author nichyjt

/**
 * Test that ConfigCommand's public functions work as intended.
 * <p>
 * Only execute() is called for testing rather than the other public/protected method calls
 * as it covers almost all the main logic and branches.
 */
public class ConfigCommandTest {
    private static final String COMMAND_KEYWORD = "config";
    private static final String EMPTY_STRING = "";
    private static final String EXPECTED_ERROR_MAX_MINS = ""
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!"
            + System.lineSeparator()
            + "Error Message:"
            + System.lineSeparator()
            + "Invalid minutes payload given in 'config', the maximum time you can set is 60!"
            + System.lineSeparator()
            + "Note:"
            + System.lineSeparator()
            + "config command usage: config "
            + "[--cycle number] [--work minutes] [--break minutes] [--longbreak minutes]"
            + System.lineSeparator()
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!";

    private static final String EXPECTED_ERROR_MIN_MINS = ""
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!"
            + System.lineSeparator()
            + "Error Message:"
            + System.lineSeparator()
            + "Invalid minutes payload given in 'config', the minimum time you can set is 1!"
            + System.lineSeparator()
            + "Note:"
            + System.lineSeparator()
            + "config command usage: config "
            + "[--cycle number] [--work minutes] [--break minutes] [--longbreak minutes]"
            + System.lineSeparator()
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!";
    private static final String EXPECTED_ERROR_MAX_CYCLE = ""
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!"
            + System.lineSeparator()
            + "Error Message:"
            + System.lineSeparator()
            + "Invalid cycle payload given in 'config', the maximum cycles you can set is 5!"
            + System.lineSeparator()
            + "Note:"
            + System.lineSeparator()
            + "config command usage: config "
            + "[--cycle number] [--work minutes] [--break minutes] [--longbreak minutes]"
            + System.lineSeparator()
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!";

    private static final String EXPECTED_ERROR_MIN_CYCLE = ""
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!"
            + System.lineSeparator()
            + "Error Message:"
            + System.lineSeparator()
            + "Invalid cycle payload given in 'config', the minimum cycles you can set is 2!"
            + System.lineSeparator()
            + "Note:"
            + System.lineSeparator()
            + "config command usage: config "
            + "[--cycle number] [--work minutes] [--break minutes] [--longbreak minutes]"
            + System.lineSeparator()
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!";

    private static final String EXPECTED_ERROR_INVALID_PAYLOAD = ""
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!"
            + System.lineSeparator()
            + "Error Message:"
            + System.lineSeparator()
            + "Invalid payload given in 'config', expected a valid integer!"
            + System.lineSeparator()
            + "Note:"
            + System.lineSeparator()
            + "config command usage: config "
            + "[--cycle number] [--work minutes] [--break minutes] [--longbreak minutes]"
            + System.lineSeparator()
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!";
    private static final String EXPECTED_ERROR_INVALID_ARGS = ""
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!"
            + System.lineSeparator()
            + "Error Message:"
            + System.lineSeparator()
            + "Invalid arguments given to 'config'!"
            + System.lineSeparator()
            + "Note:"
            + System.lineSeparator()
            + "config command usage: config [--cycle number] [--work minutes] [--break minutes] [--longbreak minutes]"
            + System.lineSeparator()
            + "!!!!!!-------!!!!!--------!!!!!!!------!!!!!---------!!!!!!!";
    private static final String ERROR_EXPECTED_PASS = "Expected execution to pass but failed!";
    private static final String ERROR_UNEXPECTED_EXCEPTION = "No exception expected to throw!";
    private static final String VALID_CYCLE = "3";
    private static final int VALID_CYCLE_INT = 3;
    private static final String MIN_CYCLE = "2";
    private static final int MIN_CYCLE_INT = 2;
    private static final String MAX_CYCLE = "5";
    private static final int MAX_CYCLE_INT = 5;
    private static final String DEFAULT_TIME = "1";
    private static final int DEFAULT_TIME_INT = 1;
    private static final String VALID_TIME = "10";
    private static final int VALID_TIME_INT = 10;
    private static final String VALID_TIME_1 = "15";
    private static final int VALID_TIME_1_INT = 15;
    private static final String VALID_TIME_2 = "20";
    private static final int VALID_TIME_2_INT = 20;
    private static final String VALID_TIME_MAX = "60";
    private static final int VALID_TIME_MAX_INT = 60;
    private static final String INVALID_TIME_NEGATIVE = "-5";
    private static final String INVALID_TIME_MAX = "61";
    private static final String INVALID_CYCLE_MAX = "10";
    private static final String INVALID_CYCLE_NEGATIVE = "-5";
    private static final String INVALID_PAYLOAD = "foo";
    private static final String INVALID_PAYLOAD_1 = "bar";

    private String getMessageFrom(String uiOutput) {
        FocusUi ui = new FocusUi();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ui.printSeparator();
        String separator = outputStream.toString().trim();
        StringBuilder resultBuilder = new StringBuilder();
        String[] outputLines = uiOutput.split(System.lineSeparator());
        for (String outputLine : outputLines) {
            String trimmedOutputLine = outputLine.trim();
            if (!trimmedOutputLine.equals(separator)) {
                resultBuilder.append(trimmedOutputLine).append(System.lineSeparator());
            }
        }
        return resultBuilder.toString().trim();
    }

    private boolean isSessionCorrectlyUpdated(Session session, int cycle, int work, int brk, int longbrk) {
        if (session.getWork() != work) {
            return false;
        }
        if (session.getBrk() != brk || session.getLongBrk() != longbrk) {
            return false;
        }
        return session.getCycle() == cycle;
    }

    private HashMap<String, String> generateArguments(String cycle, String work, String brk, String longbrk) {
        HashMap<String, String> argumentPayload = new HashMap<>();
        argumentPayload.put(COMMAND_KEYWORD, EMPTY_STRING);
        if (cycle != null) {
            argumentPayload.put(ConfigCommand.ARGUMENT_CYCLE, cycle);
        }
        if (work != null) {
            argumentPayload.put(ConfigCommand.ARGUMENT_WORK, work);
        }
        if (brk != null) {
            argumentPayload.put(ConfigCommand.ARGUMENT_BREAK, brk);
        }
        if (longbrk != null) {
            argumentPayload.put(ConfigCommand.ARGUMENT_LONG_BREAK, longbrk);
        }
        return argumentPayload;
    }

    /**
     * Ensure that the command works with valid arguments which may not include all arguments
     */
    @Test
    public void executeTest_success() {
        ConfigCommand command;
        Session session = new Session();
        // Test with missing arguments
        HashMap<String, String> argumentPayload = generateArguments(VALID_CYCLE, VALID_TIME, null, null);
        command = new ConfigCommand(argumentPayload, session);
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_EXPECTED_PASS);
        }
        assertTrue(isSessionCorrectlyUpdated(session, VALID_CYCLE_INT, VALID_TIME_INT,
                DEFAULT_TIME_INT, DEFAULT_TIME_INT));

        // Test with numbers within range
        argumentPayload = generateArguments(VALID_CYCLE, VALID_TIME_2, VALID_TIME_1, VALID_TIME_2);
        command = new ConfigCommand(argumentPayload, session);
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_EXPECTED_PASS);
        }
        assertTrue(isSessionCorrectlyUpdated(session, VALID_CYCLE_INT, VALID_TIME_2_INT,
                VALID_TIME_1_INT, VALID_TIME_2_INT));
    }

    /**
     * Ensure that at extreme valid values, executes still works
     */
    @Test
    public void executeTest_minMaxRanges_success() {
        ConfigCommand command;
        Session session = new Session();
        // Test with edge values (max accepted values)
        HashMap<String, String> argumentPayload = generateArguments(MAX_CYCLE,
                VALID_TIME_MAX, VALID_TIME_MAX, VALID_TIME_MAX);
        command = new ConfigCommand(argumentPayload, session);
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_EXPECTED_PASS);
        }
        assertTrue(isSessionCorrectlyUpdated(session, MAX_CYCLE_INT,
                VALID_TIME_MAX_INT, VALID_TIME_MAX_INT, VALID_TIME_MAX_INT));

        // Test with edge values (min accepted values)
        argumentPayload = generateArguments(MIN_CYCLE, DEFAULT_TIME, DEFAULT_TIME, DEFAULT_TIME);
        command = new ConfigCommand(argumentPayload, session);
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_EXPECTED_PASS);
        }
        assertTrue(isSessionCorrectlyUpdated(session, MIN_CYCLE_INT, DEFAULT_TIME_INT,
                DEFAULT_TIME_INT, DEFAULT_TIME_INT));
    }

    /**
     * Ensure that negative numbers cause an error to be thrown
     */
    @Test
    public void executeTest_negativeNumbers_exceptionThrown() {
        ConfigCommand command;
        Session session = new Session();

        // Test with negative time values
        HashMap<String, String> argumentPayload = generateArguments(MAX_CYCLE, VALID_TIME,
                INVALID_TIME_NEGATIVE, VALID_TIME_2);
        command = new ConfigCommand(argumentPayload, session);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_MIN_MINS, getMessageFrom(outputStream.toString()));
        // Test with negative cycle values
        argumentPayload = generateArguments(INVALID_CYCLE_NEGATIVE, VALID_TIME,
                VALID_TIME, VALID_TIME);
        command = new ConfigCommand(argumentPayload, session);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_MIN_CYCLE, getMessageFrom(outputStream.toString()));
    }

    /**
     * Ensure that large numbers (in config's context) cause an error to be thrown
     */
    @Test
    public void executeTest_largeNumbers_exceptionThrown() {
        ConfigCommand command;
        Session session = new Session();
        // Test with large time values
        HashMap<String, String> argumentPayload = generateArguments(MAX_CYCLE, INVALID_TIME_MAX,
                VALID_TIME, VALID_TIME_2);
        command = new ConfigCommand(argumentPayload, session);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_MAX_MINS, getMessageFrom(outputStream.toString()));

        // Test with large cycle values
        outputStream = new ByteArrayOutputStream();
        argumentPayload = generateArguments(INVALID_CYCLE_MAX, VALID_TIME, VALID_TIME, VALID_TIME_2);
        command = new ConfigCommand(argumentPayload, session);
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_MAX_CYCLE, getMessageFrom(outputStream.toString()));
    }

    /**
     * Ensure that NaN values are correctly handled
     */
    @Test
    public void executeTest_notANumber_exceptionThrown() {
        ConfigCommand command;
        Session session = new Session();
        // Test with NaN time value
        HashMap<String, String> argumentPayload = generateArguments(MAX_CYCLE, INVALID_PAYLOAD,
                VALID_TIME, VALID_TIME_2);
        command = new ConfigCommand(argumentPayload, session);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_INVALID_PAYLOAD, getMessageFrom(outputStream.toString()));

        // Test with NaN cycle value
        argumentPayload = generateArguments(INVALID_PAYLOAD_1, VALID_TIME,
                VALID_TIME, VALID_TIME_2);
        command = new ConfigCommand(argumentPayload, session);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_INVALID_PAYLOAD, getMessageFrom(outputStream.toString()));
    }

    /**
     * Ensure that length of argument errors are caught (too few & too many)
     */
    @Test
    public void executeTest_invalidArguments_exceptionThrown() {
        ConfigCommand command;
        Session session = new Session();
        // Test with too many arguments
        HashMap<String, String> argumentPayload = generateArguments(MAX_CYCLE,
                VALID_TIME, VALID_TIME, VALID_TIME_2);
        argumentPayload.put(INVALID_PAYLOAD, INVALID_PAYLOAD_1);
        command = new ConfigCommand(argumentPayload, session);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            command.execute();
        } catch (WellNusException exception) {
            fail(ERROR_UNEXPECTED_EXCEPTION);
        }
        assertEquals(EXPECTED_ERROR_INVALID_ARGS, getMessageFrom(outputStream.toString()));
    }
}
