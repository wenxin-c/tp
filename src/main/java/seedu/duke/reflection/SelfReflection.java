package seedu.duke.reflection;

import seedu.duke.exception.BadCommandException;
import seedu.duke.exception.InvalidCommandException;

import java.util.NoSuchElementException;

/**
 * The main body of self reflect section.
 */
public class SelfReflection {
    private static final String LOGO =
            "  _____ ______ _      ______   _____  ______ ______ _      ______ _____ _______ _____ ____  _   _ \n" +
            " / ____|  ____| |    |  ____| |  __ \\|  ____|  ____| |    |  ____/ ____|__   __|_   _/ __ \\| \\ | |\n" +
            "| (___ | |__  | |    | |__    | |__) | |__  | |__  | |    | |__ | |       | |    | || |  | |  \\| |\n" +
            " \\___ \\|  __| | |    |  __|   |  _  /|  __| |  __| | |    |  __|| |       | |    | || |  | | . ` |\n" +
            " ____) | |____| |____| |      | | \\ \\| |____| |    | |____| |___| |____   | |   _| || |__| | |\\  |\n" +
            "|_____/|______|______|_|      |_|  \\_\\______|_|    |______|______\\_____|  |_|  |_____\\____/|_| \\_|\n";
    private static final String GREETING_MESSAGE = "Welcome to WellNUS++ Self Reflection section:D"
            + System.lineSeparator() + "Feel very occupied and cannot find time to self reflect?"
            + System.lineSeparator() + "No worries, this section will give you the opportunity to reflect and improve on yourself!!";

    // Questions are adopted from website: https://www.usa.edu/blog/self-discovery-questions/
    private static final String[] QUESTIONS =
        {"What are three of my most cherished personal values?",
        "What is my purpose in life?",
        "What is my personality type?",
        "Did I make time for myself this week?",
        "Am I making time for my social life?",
        "What scares me the most right now?",
        "What is something I find inspiring?",
        "What is something that brings me joy?",
        "When is the last time I gave back to others?",
        "What matters to me most right now?"};

    private static final ReflectUi UI = new ReflectUi();

    public SelfReflection() {
        setUpQuestions();
    }

    /**
     * Load the questions list with pre-defined reflect questions.<br/>
     * This method is called in constructor so to create a new object to set up questions.
     */
    private static void setUpQuestions() {
        for (int i = 0; i < QUESTIONS.length; i += 1) {
            ReflectionQuestion newQuestion = new ReflectionQuestion(QUESTIONS[i]);
            QuestionManager.addReflectQuestion(newQuestion);
        }
    }

    /**
     * Print greeting logo and message.
     */
    public static void greet() {
        UI.printLogo(LOGO);
        UI.printOutputMessage(GREETING_MESSAGE);
    }
}






