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
 * Class to test different tests for `HomeCommand` Class utilising JUnit tests
 * Test cases will involve expected outputs and correct exception handling
 */
class HomeCommandTest {
    private static final String HOME_COMMAND = "home";
    private static final String HOME_COMMAND_WRONG_PAYLOAD = "home test";
    private static final String HOME_COMMAND_WRONG_ARGUMENT = "home --test";
    private static final String HOME_COMMAND_WITH_SPACES = "   home   ";
    private static final boolean IS_EXIT = true;
    private static final QuestionList QUESTION_LIST = new QuestionList();

    /**
     * Test whether ReturnCommand execute() method can terminate self reflection or not.<br/>
     * Set isExit to true.
     *
     * @throws BadCommandException If invalid command is given.
     */
    @Test
    void execute_checkIsExit_expectTrue() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(HOME_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        HomeCommand homeCmd = new HomeCommand(returnArgumentPayload, QUESTION_LIST);
        homeCmd.execute();
        assertEquals(IS_EXIT, reflectionManager.getIsExit());
    }

    /**
     * Test whether `home` command is validated properly.<br/>
     * 'home' without any payload and arguments, otherwise throw exception.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void execute_checkWrongCmdFormat_expectException() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> homeCmdWrongPayload = commandParser.parseUserInput(HOME_COMMAND_WRONG_PAYLOAD);
        HomeCommand homeWrongPayload = new HomeCommand(homeCmdWrongPayload, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> homeWrongPayload.validateCommand(homeCmdWrongPayload));
        HashMap<String, String> homeCmdWrongArgument = commandParser.parseUserInput(HOME_COMMAND_WRONG_ARGUMENT);
        HomeCommand homeWrongArgument = new HomeCommand(homeCmdWrongArgument, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> homeWrongArgument.validateCommand(homeCmdWrongArgument));
    }

    /**
     * Test whether command leading/dangling spaces will be removed properly.<br/>
     * Command keyword whitespace should be trimmed.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void execute_checkSpaceRemoval_success() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setCommandType(HOME_COMMAND_WITH_SPACES);
        String homeCommand = reflectionManager.getCommandType();
        assertEquals(HOME_COMMAND, homeCommand);
    }
}

