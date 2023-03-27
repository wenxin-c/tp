package wellnus.reflection;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectionQuestion;

// @@author wenxin-c
class QuestionListTest {
    private static final int FULL_ARRAY_LENGTH = 10;
    // Test the correct number of questions are loaded into the list
    @Test
    void setUpQuestions_checkArrayLength_success() {
        QuestionList questionList = new QuestionList();
        ArrayList<ReflectionQuestion> questions = questionList.getAllQuestions();
        int fullArrayLength = questions.size();
        assertEquals(FULL_ARRAY_LENGTH, fullArrayLength);
    }
}

