package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.reflection.command.LikeCommand;
import wellnus.reflection.feature.QuestionList;

class FavoriteCommandTest {
    private static final String LIKE_COMMAND = "like 1";
    private static final int MIN_QUESTION_LENGTH = 3;
    private static final boolean IS_CORRECT_LENGTH = true;
    private static final Integer[] ARR_INDEXES = { 5, 6, 7, 8, 1};
    private static final HashSet<Integer> RANDOM_INDEXES = new HashSet<>(Arrays.asList(ARR_INDEXES));

    // Test whether fav list indexes are properly saved and returned
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

