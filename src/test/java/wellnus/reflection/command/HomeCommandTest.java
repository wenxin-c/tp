package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectionManager;

//@@author wenxin-c
/**
 * Class to test different tests for HomeCommand Class utilising JUnit tests
 * Test cases will involve expected outputs and correct exception handling
 */
class HomeCommandTest {
    private static final String HOME_COMMAND = "home";
    private static final String HOME_COMMAND_WRONG_FORMAT = "home back";
    private static final String HOME_COMMAND_WITH_SPACES = "   home   ";
    private static final boolean IS_EXIT = true;
    private static final QuestionList questionList = new QuestionList();

    // Test whether ReturnCommand execute() method can terminate self reflection or not.
    @Test
    void execute_checkIsExit_expectTrue() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(HOME_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        HomeCommand homeCmd = new HomeCommand(returnArgumentPayload, questionList);
        homeCmd.execute();
        assertEquals(IS_EXIT, reflectionManager.getIsExit());
    }

    // Test whether wrong format command exception is caught or not.
    @Test
    void execute_checkWrongCmdFormat_expectException() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> homeArgumentPayload = commandParser.parseUserInput(HOME_COMMAND_WRONG_FORMAT);
        HomeCommand returnCmd = new HomeCommand(homeArgumentPayload, questionList);
        assertThrows(BadCommandException.class, () -> {
            returnCmd.validateCommand(homeArgumentPayload);
        });
    }

    // Test whether leading/dangling spaces will be removed.
    @Test
    void execute_checkSpaceRemoval_success() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setCommandType(HOME_COMMAND_WITH_SPACES);
        String homeCommand = reflectionManager.getCommandType();
        assertEquals(HOME_COMMAND, homeCommand);
    }
}

