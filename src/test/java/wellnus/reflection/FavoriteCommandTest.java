package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.exception.BadCommandException;

class FavoriteCommandTest {
    private static final String FAV_COMMAND = "fav";
    private static final String LIKE_COMMAND = "like 1";
    private static final int MIN_QUESTION_LENGTH = 3;
    private static final boolean IS_CORRECT_LENGTH = true;

    // Test whether fav list indexes are properly saved and returned
    @Test
    void getFavQuestions_checkListLength_success() throws BadCommandException {
        QuestionList questionList = new QuestionList();
        questionList.setRandomQuestionIndexes();
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(LIKE_COMMAND);
        HashMap<String, String> argumentPayloadLikeCmd = reflectionManager.getArgumentPayload();
        LikeCommand likeCmd = new LikeCommand(argumentPayloadLikeCmd, questionList);
        likeCmd.execute();
        reflectionManager.setArgumentPayload(FAV_COMMAND);
        HashMap<String, String> argumentPayloadFavCmd = reflectionManager.getArgumentPayload();
        FavoriteCommand favCmd = new FavoriteCommand(argumentPayloadFavCmd, questionList);
        String favQuestions = favCmd.getFavQuestions();
        assertEquals(IS_CORRECT_LENGTH, favQuestions.length() >= MIN_QUESTION_LENGTH);
    }

}

