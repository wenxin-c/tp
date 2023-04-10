package wellnus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.exception.BadCommandException;

//@@author nichyjt

/**
 * Test that CommandParser's public functions work as intended.[
 */
public class CommandParserTest {

    private static final String VALID_COMMAND = "mainCommand";
    private static final String VALID_COMMAND_1 = "mainCommand payload";
    private static final String VALID_COMMAND_2 = "mainCommand --arg1 pay1";
    private static final String VALID_COMMAND_3 = "mainCommand --arg1 pay1 --arg2";
    private static final String VALID_COMMAND_4 = "mainCommand --arg1 pay1 --arg2 --arg3 pay3";
    private static final String VALID_COMMAND_5 = "mainCommand pay--load";
    private static final String VALID_COMMAND_6 = "mainCommand --argument1 payload--";
    private static final String VALID_COMMAND_7 = "  mainCommand --arg--1 pay1 --arg2 pay2";
    private static final String VALID_COMMAND_SPECIAL_WHITESPACE = "\n \t mainCommand payload --argument payload1";
    private static final String INVALID_COMMAND_EMPTY_ARG = "mainCommand payload --";
    private static final String INVALID_COMMAND_EMPTY_ARG_WITH_PAYLOAD = "mainCommand payload -- payload1";
    private static final String INVALID_COMMAND_EMPTY_ARG_PADDED = "mainCommand payload -- ";
    private static final String INVALID_COMMAND_REPEATED_ARGS = "foo --bar payload --bar payload2";
    private static final String INVALID_COMMAND_NO_MAIN_COMMAND = "--arg1 payload";
    private static final String INVALID_COMMAND_NO_MAIN_COMMAND_PADDED = "  --arg1 payload";
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE_PAYLOAD = " \n \t ";
    private static final String TARGET_COMMAND = "maincommand";
    private static final String ERROR_EXCEPTION_THROWN = "CommandParser threw exception on valid input:";
    private static final String ERROR_EXPECTED_EXCEPTION = "Expected BadCommandException to be thrown. ";

    private ArrayList<String> getValidCommandInputs() {
        ArrayList<String> validCommands = new ArrayList<>();
        validCommands.add(VALID_COMMAND);
        validCommands.add(VALID_COMMAND_1);
        validCommands.add(VALID_COMMAND_2);
        validCommands.add(VALID_COMMAND_3);
        validCommands.add(VALID_COMMAND_4);
        return validCommands;
    }

    /**
     * Get tricky test strings that are 'adversarial' in nature but are otherwise syntatically correct
     * based on our definition of what counts as a correct input.
     * <p>
     * These tricky inputs test for whitespace padding and delimiter-based adversarial inputs.
     * These should be handled appropriately by the CommandParser.
     *
     * @return ArrayList of strings containing adversarial but valid commands
     */
    private ArrayList<String> getValidTrickyInputs() {
        ArrayList<String> validCommands = new ArrayList<>();
        validCommands.add(VALID_COMMAND_5);
        validCommands.add(VALID_COMMAND_6);
        validCommands.add(VALID_COMMAND_7);
        return validCommands;
    }

    /**
     * Test that all valid inputs defined pass the command parser's parseUserInput
     */
    @Test
    public void parseUserInput_validInput() {
        // The following commands should be able to pass
        ArrayList<String> validCommandInputs = getValidCommandInputs();

        // The following tests check if adversarial inputs are processed correctly
        ArrayList<String> validTrickyInputs = getValidTrickyInputs();

        CommandParser parser = new CommandParser();
        for (String validCommand : validCommandInputs) {
            try {
                parser.parseUserInput(validCommand);
            } catch (BadCommandException exception) {
                fail(ERROR_EXCEPTION_THROWN + exception);
            }
        }
        for (String validCommand : validTrickyInputs) {
            try {
                parser.parseUserInput(validCommand);
            } catch (BadCommandException exception) {
                fail(ERROR_EXCEPTION_THROWN + exception);
            }
        }
    }

    /**
     * Test that exception is thrown when an empty input is given
     */
    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        CommandParser parser = new CommandParser();
        // Test on empty user input
        String command = EMPTY_STRING;
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(command);
        }, ERROR_EXPECTED_EXCEPTION + command);
    }

    /**
     * Test that exception is thrown when an empty argument is given " -- ".
     * <p>
     * Account for different variations for empty argument with whitespace padding to ensure that
     * the CommandParser strips all whitespace
     */
    @Test
    public void parseUserInput_emptyArgument_exceptionThrown() {
        CommandParser parser = new CommandParser();
        // Test on empty argument
        String commandEmptyArg = INVALID_COMMAND_EMPTY_ARG;
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(commandEmptyArg);
        }, ERROR_EXPECTED_EXCEPTION + commandEmptyArg);

        // Test on empty argument with padding
        String commandEmptyArgPadded = INVALID_COMMAND_EMPTY_ARG_PADDED;
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(commandEmptyArgPadded);
        }, ERROR_EXPECTED_EXCEPTION + commandEmptyArgPadded);

        // Test on empty argument, payload exists
        String emptyCmdWithPayload = INVALID_COMMAND_EMPTY_ARG_WITH_PAYLOAD;
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(emptyCmdWithPayload);
        }, ERROR_EXPECTED_EXCEPTION + emptyCmdWithPayload);
    }

    /**
     * Test that exception is thrown when there is no main argument given
     */
    @Test
    public void parseUserInput_noMainArgument_exceptionThrown() {
        CommandParser parser = new CommandParser();
        // Test on empty user input without padding
        String command = INVALID_COMMAND_NO_MAIN_COMMAND;
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(command);
        }, ERROR_EXPECTED_EXCEPTION + command);

        // Test on empty user input with padding
        String commandPadded = INVALID_COMMAND_NO_MAIN_COMMAND_PADDED;
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(commandPadded);
        }, ERROR_EXPECTED_EXCEPTION + commandPadded);
    }

    /**
     * Test that getting the main argument (command) from user input works
     */
    @Test
    public void getMainArgumentTest() {
        CommandParser parser = new CommandParser();
        try {
            String result1 = parser.getMainArgument(VALID_COMMAND_4);
            assertEquals(TARGET_COMMAND, result1);
        } catch (BadCommandException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Test that getMainArgument works for valid whitespace padded input
     */
    @Test
    public void getMainArgumentTest_paddedInput_success() {
        CommandParser parser = new CommandParser();
        try {
            String result1 = parser.getMainArgument(VALID_COMMAND_7);
            assertEquals(TARGET_COMMAND, result1);
        } catch (BadCommandException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Test that getMainArgument works for valid \n, \t padded input
     */
    @Test
    public void getMainArgumentTest_specialWhitespace_success() {
        CommandParser parser = new CommandParser();
        try {
            String result1 = parser.getMainArgument(VALID_COMMAND_SPECIAL_WHITESPACE);
            assertEquals(TARGET_COMMAND, result1);
        } catch (BadCommandException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Test that getMainArgument throws exception for empty input
     */
    @Test
    public void getMainArgument_emptyInput_throwsException() {
        CommandParser parser = new CommandParser();
        assertThrows(BadCommandException.class, () -> {
            parser.getMainArgument(EMPTY_STRING);
        }, ERROR_EXPECTED_EXCEPTION);
    }

    /**
     * Test that getMainArgument throws exception for whitespace-only input
     */
    @Test
    public void getMainArgument_whiteSpacedInput_throwsException() {
        CommandParser parser = new CommandParser();
        assertThrows(BadCommandException.class, () -> {
            parser.getMainArgument(WHITESPACE_PAYLOAD);
        }, ERROR_EXPECTED_EXCEPTION);
    }

    /**
     * Test that repeated arguments throw an exception
     */
    @Test
    public void parseUserInput_repeatedArgument_throwsException() {
        CommandParser parser = new CommandParser();
        assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(INVALID_COMMAND_REPEATED_ARGS);
        }, ERROR_EXPECTED_EXCEPTION);
    }

}
