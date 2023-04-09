package wellnus.reflection.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test different tests for `ReflectionQuestion` Class utilising JUnit tests.
 * Test cases will involve expected outputs.
 */
class ReflectionQuestionTest {
    private static final String QUESTION = "How's today?";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test whether questions will be created and printed in a proper format.
     */
    @Test
    void createReflectionQuestion_printString_success() {
        ReflectionQuestion question = new ReflectionQuestion(QUESTION);
        System.out.print(question);
        assertEquals(QUESTION, outputStreamCaptor.toString().trim());
    }
}

