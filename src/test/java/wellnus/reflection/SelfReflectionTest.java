package wellnus.reflection;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelfReflectionTest {
    private static final int FULL_ARRAY_LENGTH = 10;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Test the correct number of questions are loaded into the list
    @Test
    void setUpQuestions_checkArrayLength_success() {
        SelfReflection selfReflection = new SelfReflection();
        ArrayList<ReflectionQuestion> questions = selfReflection.getQuestions();
        int fullArrayLength = questions.size();
        assertEquals(FULL_ARRAY_LENGTH, fullArrayLength);
    }
}

