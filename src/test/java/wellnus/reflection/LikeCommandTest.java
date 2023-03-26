package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;

// @@author wenxin-c
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

    // Test whether the wrong input format can be caught.
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
        ) -> likeCmdMissingParam.validateCommand(argumentPayloadMissingParam));
        assertThrows(NumberFormatException.class, (
        ) -> likeCmdWrongParam.validateCommand(argumentPayloadWrongParam));
        assertThrows(BadCommandException.class, (
        ) -> likeCmdOutBound.validateCommand(argumentPayloadOutBound));
    }

    // Test whether exceptions are thrown when executing like command before getting a previous set of questions.
    @Test
    void addFavList_noPrevQuestions_expectException() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayload = commandParser.parseUserInput(LIKE_COMMAND);
        LikeCommand likeCmd = new LikeCommand(argumentPayload, questionList);
        assertThrows(BadCommandException.class, (
        ) -> likeCmd.addFavQuestion(argumentPayload.get(LIKE_COMMAND_KEYWORD)));
    }

    // Test the mapping from user input to question index.
    @Test
    void addFavList_checkIndex_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes(RANDOM_INDEXES);
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayloadLikeCmd = commandParser.parseUserInput(LIKE_COMMAND);
        LikeCommand likeCmd = new LikeCommand(argumentPayloadLikeCmd, questionList);
        HashMap<Integer, Integer> indexQuestionMap = likeCmd.mapInputToQuestion();
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

    // Test whether like question is successfully added into fav list
    @Test
    void addFavList_checkQuestionList_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes(RANDOM_INDEXES);
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayloadLikeCmd = commandParser.parseUserInput(LIKE_COMMAND);
        LikeCommand likeCmd = new LikeCommand(argumentPayloadLikeCmd, questionList);
        likeCmd.execute();
        Set<Integer> favList = questionList.getDataIndex().get(INDEX_ZERO);
        assertEquals(INCREMENT_ONE, favList.size());
        int index = Integer.parseInt(argumentPayloadLikeCmd.get(LIKE_COMMAND_KEYWORD));
        assertEquals(IS_ADDED, favList.contains(index));
    }
}

