package seedu.duke.reflection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetCommandTest {
    private static final int EXPECTED_ARRAY_LENGTH = 5;

    // Test the number of questions being generated
    @Test
    void getRandomQuestions_checkLength_expectFive() {
        GetCommand get = new GetCommand();
        SelfReflection.setUpQuestions();
        ArrayList<ReflectQuestion> selectedQuestions = get.getRandomQuestions();
        assertEquals(EXPECTED_ARRAY_LENGTH, selectedQuestions.size());
        QuestionManager.clearQuestions();
    }
}
