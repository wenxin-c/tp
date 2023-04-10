package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.reflection.feature.QuestionList;

// @@author wenxin-c
/**
 * Class to test different tests for `FavoriteCommand` Class utilising JUnit tests.
 * Test cases will involve expected outputs and correct exception handling.
 */
class FavoriteCommandTest {
    private static final String LIKE_COMMAND = "like 1";
    private static final int MIN_QUESTION_LENGTH = 3;
    private static final boolean IS_CORRECT_LENGTH = true;
    private static final Integer[] ARR_INDEXES = { 5, 6, 7, 8, 1};
    private static final HashSet<Integer> RANDOM_INDEXES = new HashSet<>(Arrays.asList(ARR_INDEXES));
    private static final QuestionList QUESTION_LIST = new QuestionList();
    private static final String FAV_COMMAND_WRONG_PAYLOAD = "fav test";
    private static final String FAV_COMMAND_WRONG_ARGUMENT = "fav --test";

    /**
     * Test whether `fav` command is validated properly.<br/>
     * 'fav' without any payload and arguments, otherwise throw exception.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void validateCommand_getCommand_expectException() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> favCmdWrongPayload = commandParser.parseUserInput(FAV_COMMAND_WRONG_PAYLOAD);
        FavoriteCommand favWrongPayload = new FavoriteCommand(favCmdWrongPayload, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> favWrongPayload.validateCommand(favCmdWrongPayload));
        HashMap<String, String> favCmdWrongArgument = commandParser.parseUserInput(FAV_COMMAND_WRONG_ARGUMENT);
        FavoriteCommand favWrongArgument = new FavoriteCommand(favCmdWrongPayload, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> favWrongArgument.validateCommand(favCmdWrongArgument));
    }

    /**
     * Test whether fav list indexes are properly saved and returned.<br/>
     *
     * @throws BadCommandException If invalid command is given.
     */
    @Test
    void getFavQuestions_checkListLength_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes(RANDOM_INDEXES);
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayloadLikeCmd = commandParser.parseUserInput(LIKE_COMMAND);
        LikeCommand likeCmd = new LikeCommand(argumentPayloadLikeCmd, questionList);
        likeCmd.execute();
        String favQuestions = questionList.getFavQuestions();
        assertEquals(IS_CORRECT_LENGTH, favQuestions.length() >= MIN_QUESTION_LENGTH);
    }
}

