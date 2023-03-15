package wellnus.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wellnus.exception.EmptyReflectionQuestionException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void createReflectionQuestion_printString_success() throws EmptyReflectionQuestionException {
        ReflectionQuestion question = new ReflectionQuestion(QUESTION);
        System.out.print(question);
        assertEquals(QUESTION, outputStreamCaptor.toString().trim());
    }

    // Test whether exception will be thrown for empty question description
    @Test
    void createReflectionQuestion_emptyInput_expectException() {
        assertThrows(EmptyReflectionQuestionException.class,
                () -> {
                    ReflectionQuestion question = new ReflectionQuestion(EMPTY_QUESTION);
                });
    }
}

