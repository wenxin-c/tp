package wellnus.reflection.feature;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

// @@author wenxin-c
/**
 * Class to test different tests for `QuestionList` Class utilising JUnit tests.
 * Test cases will involve expected outputs.
 */
class QuestionListTest {
    private static final int FULL_ARRAY_LENGTH = 10;

    /**
     * Test the correct number of reflection questions are loaded into the list
     * when `QuestionList` object is constructed.<br/>
     * Expect 10 questions.
     */
    @Test
    void setUpQuestions_checkArrayLength_success() {
        QuestionList questionList = new QuestionList();
        ArrayList<ReflectionQuestion> questions = questionList.getAllQuestions();
        int fullArrayLength = questions.size();
        assertEquals(FULL_ARRAY_LENGTH, fullArrayLength);
    }
}

