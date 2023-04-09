package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.exception.ReflectionException;
import wellnus.reflection.feature.QuestionList;

// @@author wenxin-c
/**
 * Class to test different tests for `UnlikeCommand` Class utilising JUnit tests.
 * Test cases will involve expected outputs and correct exception handling.
 */
class UnlikeCommandTest {
    private static final String UNLIKE_KEYWORD = "unlike";
    private static final String UNLIKE_COMMAND = "unlike 1";
    private static final String UNLIKE_CMD_OUT_BOUND_INDEX = "unlike -1";
    private static final String UNLIKE_CMD_WRONG_FORMAT = "unlike ab";
    private static final int EMPTY_LIST = 0;
    private static final int INDEX_ZERO = 0;

    /**
     * Test whether `unlike` command is properly validated.<br/>
     * `unlike` keyword with a valid integer is expected, otherwise throw exception.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void validateUnlikeCommand_differentFormats_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentsUnlikeCmdOutBound = commandParser.parseUserInput(UNLIKE_CMD_OUT_BOUND_INDEX);
        UnlikeCommand unlikeCmd = new UnlikeCommand(argumentsUnlikeCmdOutBound, questionList);
        if (questionList.hasFavQuestions()) {
            assertThrows(ReflectionException.class, (
            ) -> unlikeCmd.removeFavQuestion(argumentsUnlikeCmdOutBound.get(UNLIKE_KEYWORD)));
        }
        HashMap<String, String> argumentsUnlikeCmdWrongFormat = commandParser.parseUserInput(UNLIKE_CMD_WRONG_FORMAT);
        assertThrows(NumberFormatException.class, (
        ) -> unlikeCmd.removeFavQuestion(argumentsUnlikeCmdWrongFormat.get(UNLIKE_KEYWORD)));
    }

    /**
     * Test whether empty fav list is caught properly when executing `unlike` command.<br/>
     *
     * @throws BadCommandException
     */
    @Test
    void checkFavListLength_emptyList_expectZero() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentsUnlikeCmdOutBound = commandParser.parseUserInput(UNLIKE_COMMAND);
        UnlikeCommand unlikeCmd = new UnlikeCommand(argumentsUnlikeCmdOutBound, questionList);
        unlikeCmd.execute();
        assertEquals(EMPTY_LIST, questionList.getDataIndex().get(INDEX_ZERO).size());
    }
}
