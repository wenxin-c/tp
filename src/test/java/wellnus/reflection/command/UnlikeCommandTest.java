package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.reflection.feature.QuestionList;

class UnlikeCommandTest {
    private static final String UNLIKE_COMMAND = "unlike 1";
    private static final String UNLIKE_CMD_OUT_BOUND_INDEX = "unlike -1";
    private static final String UNLIKE_CMD_WRONG_FORMAT = "unlike ab";
    private static final int EMPTY_LIST = 0;
    private static final int INDEX_ZERO = 0;

    // Test whether input validation works as expected
    @Test
    void validateUnlikeCommand_differentFormats_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentsUnlikeCmdOutBound = commandParser.parseUserInput(UNLIKE_CMD_OUT_BOUND_INDEX);
        UnlikeCommand unlikeCmd = new UnlikeCommand(argumentsUnlikeCmdOutBound, questionList);
        assertThrows(BadCommandException.class, (
        ) -> { unlikeCmd.validateCommand(argumentsUnlikeCmdOutBound); });
        HashMap<String, String> argumentsUnlikeCmdWrongFormat = commandParser.parseUserInput(UNLIKE_CMD_WRONG_FORMAT);
        assertThrows(NumberFormatException.class, (
        ) -> { unlikeCmd.validateCommand(argumentsUnlikeCmdWrongFormat); });
    }

    // Check whether empty fav list is caught
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
