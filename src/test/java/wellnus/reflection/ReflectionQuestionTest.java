package wellnus.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectionQuestionTest {
    private static final String QUESTION = "How's today?";
    private static final String EMPTY_QUESTION = "";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Test whether questions will be created and printed in a proper format
    @Test
    void createReflectionQuestion_printString_success() {
        ReflectionQuestion question = new ReflectionQuestion(QUESTION);
        System.out.print(question);
        assertEquals(QUESTION, outputStreamCaptor.toString().trim());
    }
}

