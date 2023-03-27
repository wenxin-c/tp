package wellnus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.exception.BadCommandException;


public class CommandParserTest {

    private ArrayList<String> getValidCommandInputs() {
        ArrayList<String> validCommands = new ArrayList<>();
        validCommands.add("mainCommand");
        validCommands.add("mainCommand payload");
        validCommands.add("main --arg1 pay1");
        validCommands.add("main --arg1 pay1 --arg2");
        validCommands.add("main --arg1 pay1 --arg2 --arg3 pay3");
        return validCommands;
    }

    private ArrayList<String> getValidTrickyInputs() {
        ArrayList<String> validCommands = new ArrayList<>();
        validCommands.add("mainCommand pay--load");
        validCommands.add("mainCommand --argument1 payload--");
        validCommands.add("  mainCommand --arg--1 pay1 --arg2 pay2");
        return validCommands;
    }

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
                fail("CommandParser threw exception on valid input:\n" + exception);
            }
        }
        for (String validCommand : validTrickyInputs) {
            try {
                parser.parseUserInput(validCommand);
            } catch (BadCommandException exception) {
                fail("CommandParser threw exception on valid input:\n" + exception);
            }
        }
    }

    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        CommandParser parser = new CommandParser();
        // Test on empty user input
        String command = "";
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(command);
        }, "BadCommandException was expected for input:\n" + command);
    }

    @Test
    public void parseUserInput_emptyArgument_exceptionThrown() {
        CommandParser parser = new CommandParser();
        // Test on empty argument
        String commandEmptyArg = "mainCommand payload --";
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(commandEmptyArg);
        }, "BadCommandException was expected for input:\n" + commandEmptyArg);

        // Test on empty argument with padding
        String commandEmptyArgPadded = "mainCommand payload -- ";
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(commandEmptyArgPadded);
        }, "BadCommandException was expected for input:\n" + commandEmptyArgPadded);

        // Test on empty argument, payload exists
        String emptyCmdWithPayload = "mainCommand payload -- payload1";
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(emptyCmdWithPayload);
        }, "BadCommandException was expected for input:\n" + emptyCmdWithPayload);
    }

    @Test
    public void parseUserInput_noMainArgument_exceptionThrown() {
        CommandParser parser = new CommandParser();
        // Test on empty user input without padding
        String command = "--arg1 payload";
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(command);
        }, "BadCommandException was expected for input:\n" + command);

        // Test on empty user input with padding
        String commandPadded = " --arg1 payload";
        Assertions.assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput(commandPadded);
        }, "BadCommandException was expected for input:\n" + commandPadded);
    }

    @Test
    public void getMainArgumentTest() {
        CommandParser parser = new CommandParser();
        String target = "maincommand";
        String command = "mainCommand payload --argument payload1";
        try {
            String result1 = parser.getMainArgument(command);
            assertEquals(target, result1);
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
        String target = "maincommand";
        String command = "   mainCommand payload --argument payload1";
        try {
            String result1 = parser.getMainArgument(command);
            assertEquals(target, result1);
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
        String target = "maincommand";
        String command = "\n \t mainCommand payload --argument payload1";
        try {
            String result1 = parser.getMainArgument(command);
            assertEquals(target, result1);
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
            parser.getMainArgument("");
        }, "Expected error throw from empty user input");
    }

    /**
     * Test that getMainArgument throws exception for whitespace-only input
     */
    @Test
    public void getMainArgument_whiteSpacedInput_throwsException() {
        CommandParser parser = new CommandParser();
        assertThrows(BadCommandException.class, () -> {
            parser.getMainArgument(" \n \t ");
        }, "Expected error throw from white-spaced user input");
    }

    @Test
    public void parseUserInput_repeatedArgument_throwsException() {
        CommandParser parser = new CommandParser();
        assertThrows(BadCommandException.class, () -> {
            parser.parseUserInput("foo --bar payload --bar payload2");
        }, "Expected error throw from repeated arguments");
    }

}
