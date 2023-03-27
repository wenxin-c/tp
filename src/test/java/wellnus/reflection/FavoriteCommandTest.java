package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;

class FavoriteCommandTest {
    private static final String LIKE_COMMAND = "like 1";
    private static final int MIN_QUESTION_LENGTH = 3;
    private static final boolean IS_CORRECT_LENGTH = true;

    // Test whether fav list indexes are properly saved and returned
    @Test
    void getFavQuestions_checkListLength_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes();
        questionList.getRandomQuestionIndexes();
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> argumentPayloadLikeCmd = commandParser.parseUserInput(LIKE_COMMAND);
        LikeCommand likeCmd = new LikeCommand(argumentPayloadLikeCmd, questionList);
        likeCmd.execute();
        String favQuestions = questionList.getFavQuestions();
        assertEquals(IS_CORRECT_LENGTH, favQuestions.length() >= MIN_QUESTION_LENGTH);
    }
}

