package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.exception.ReflectionException;
import wellnus.reflection.feature.IndexMapper;
import wellnus.reflection.feature.QuestionList;

// @@author wenxin-c
/**
 * Class to test different tests for `LikeCommand` Class utilising JUnit tests.
 * Test cases will involve expected outputs and correct exception handling.
 */
class LikeCommandTest {
    private static final String LIKE_COMMAND_KEYWORD = "like";
    private static final String LIKE_COMMAND = "like 1";
    private static final String LIKE_COMMAND_MISSING_PARAM = "like";
    private static final String LIKE_COMMAND_WRONG_PARAM = "like ab";
    private static final String LIKE_COMMAND_OUT_OF_BOUND = "like 10";
    private static final int INPUT_INDEX = 2;
    private static final int INITIAL_INDEX = 1;
    private static final int INCREMENT_ONE = 1;
    private static final int INDEX_ZERO = 0;
    private static final boolean IS_ADDED = true;
    private static final Integer[] ARR_INDEXES = { 5, 6, 7, 8, 1};
    private static final HashSet<Integer> RANDOM_INDEXES = new HashSet<>(Arrays.asList(ARR_INDEXES));

    /**
     * Test whether `like` command is properly validated.<br/>
     * `like` keyword with a valid integer is expected, otherwise throw exception.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void validateLikeCommand_checkFormat_expectExceptions() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayloadMissingParam = commandParser.parseUserInput(LIKE_COMMAND_MISSING_PARAM);
        LikeCommand likeCmdMissingParam = new LikeCommand(argumentPayloadMissingParam, questionList);
        HashMap<String, String> argumentPayloadWrongParam = commandParser.parseUserInput(LIKE_COMMAND_WRONG_PARAM);
        LikeCommand likeCmdWrongParam = new LikeCommand(argumentPayloadWrongParam, questionList);
        HashMap<String, String> argumentPayloadOutBound = commandParser.parseUserInput(LIKE_COMMAND_OUT_OF_BOUND);
        LikeCommand likeCmdOutBound = new LikeCommand(argumentPayloadOutBound, questionList);
        assertThrows(NumberFormatException.class, (
        ) -> likeCmdMissingParam.addFavQuestion(argumentPayloadMissingParam.get(LIKE_COMMAND_KEYWORD)));
        assertThrows(NumberFormatException.class, (
        ) -> likeCmdWrongParam.addFavQuestion(argumentPayloadWrongParam.get(LIKE_COMMAND_KEYWORD)));
        assertThrows(ReflectionException.class, (
        ) -> likeCmdOutBound.addFavQuestion(argumentPayloadOutBound.get(LIKE_COMMAND_KEYWORD)));
    }

    /**
     * Test the mapping from user input to question index using HashMap.<br/>
     */
    @Test
    void addFavList_checkIndex_success() {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes(RANDOM_INDEXES);
        IndexMapper indexMapper = new IndexMapper(questionList.getRandomQuestionIndexes());
        HashMap<Integer, Integer> indexQuestionMap = indexMapper.mapIndex();
        int count = INITIAL_INDEX;
        int finalIndex = INITIAL_INDEX;
        for (int index : RANDOM_INDEXES) {
            if (count > INPUT_INDEX) {
                break;
            }
            count += INCREMENT_ONE;
            finalIndex = index;
        }
        int questionIndex = indexQuestionMap.get(INPUT_INDEX);
        assertEquals(finalIndex, questionIndex);
    }

    /**
     * Test whether liked questions are successfully added into the favorite list.<br/>
     *
     * @throws BadCommandException
     */
    @Test
    void addFavList_checkQuestionList_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes(RANDOM_INDEXES);
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayloadLikeCmd = commandParser.parseUserInput(LIKE_COMMAND);
        LikeCommand likeCmd = new LikeCommand(argumentPayloadLikeCmd, questionList);
        likeCmd.execute();
        Set<Integer> favList = questionList.getDataIndex().get(INDEX_ZERO);
        for (int index : favList) {
            System.out.println(index);
        }
        assertEquals(INCREMENT_ONE, favList.size());
        int index = Integer.parseInt(argumentPayloadLikeCmd.get(LIKE_COMMAND_KEYWORD));
        assertEquals(IS_ADDED, favList.contains(index));
    }
}

