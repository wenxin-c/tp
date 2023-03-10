package seedu.duke.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SelfReflectionTest {
    private static final String DEFAULT_SEPARATOR = "========================================";
    private static final String LOGO = "\n"
            + "  _____ ______ _      ______   _____  ______ ______ _      ______ _____ _______ _____ ____  _   _ \n" +
            " / ____|  ____| |    |  ____| |  __ \\|  ____|  ____| |    |  ____/ ____|__   __|_   _/ __ \\| \\ | |\n" +
            "| (___ | |__  | |    | |__    | |__) | |__  | |__  | |    | |__ | |       | |    | || |  | |  \\| |\n" +
            " \\___ \\|  __| | |    |  __|   |  _  /|  __| |  __| | |    |  __|| |       | |    | || |  | | . ` |\n" +
            " ____) | |____| |____| |      | | \\ \\| |____| |    | |____| |___| |____   | |   _| || |__| | |\\  |\n" +
            "|_____/|______|______|_|      |_|  \\_\\______|_|    |______|______\\_____|  |_|  |_____\\____/|_| \\_|\n";

    private static final String GREETING_MESSAGE = "Welcome to WellNUS++ Self Reflection section:D\n" +
            "Feel very occupied and cannot find time to self reflect?\n" +
            "No worries, this section will give you the opportunity to reflect and improve on yourself!!";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Test whether greeting message and logo are printed properly.
    @Test
    void greet_output_success() {
        SelfReflection.greet();
        assertEquals(DEFAULT_SEPARATOR + System.lineSeparator()
                + LOGO + System.lineSeparator() + DEFAULT_SEPARATOR + System.lineSeparator()
                + DEFAULT_SEPARATOR + System.lineSeparator() + GREETING_MESSAGE
                + System.lineSeparator() + DEFAULT_SEPARATOR,
                outputStreamCaptor.toString().trim());
    }

    // Test the correct number of questions are loaded into the list
    @Test
    void setUpQuestions_checkArrayLength_success() {
        QuestionManager.clearQuestions();
        ArrayList<ReflectQuestion> questions = QuestionManager.getQuestions();
        int emptyArrayLength = questions.size();
        SelfReflection.setUpQuestions();
        int fullArrayLength = questions.size();
        QuestionManager.clearQuestions();
        assertEquals(0, emptyArrayLength);
        assertEquals(10, fullArrayLength);
    }
}




