package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.reflection.feature.QuestionList;

// @@author wenxin-c
/**
 * Class to test different tests for `PrevCommand` Class utilising JUnit tests.
 * Test cases will involve correct exception handling.
 */
public class PrevCommandTest {
    private static final String PREV_COMMAND_WRONG_PAYLOAD = "prev test";
    private static final String PREV_COMMAND_WRONG_ARGUMENT = "prev --test";
    private static final QuestionList QUESTION_LIST = new QuestionList();

    /**
     * Test whether `prev` command is validated properly.<br/>
     * 'prev' without any payload and arguments, otherwise throw exception.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void validateCommand_getCommand_expectException() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> prevCmdWrongPayload = commandParser.parseUserInput(PREV_COMMAND_WRONG_PAYLOAD);
        GetCommand prevWrongPayload = new GetCommand(prevCmdWrongPayload, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> prevWrongPayload.validateCommand(prevCmdWrongPayload));
        HashMap<String, String> prevCmdWrongArgument = commandParser.parseUserInput(PREV_COMMAND_WRONG_ARGUMENT);
        GetCommand prevWrongArgument = new GetCommand(prevCmdWrongArgument, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> prevWrongArgument.validateCommand(prevCmdWrongArgument));
    }
}
