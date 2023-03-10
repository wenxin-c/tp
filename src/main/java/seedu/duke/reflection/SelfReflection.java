package seedu.duke.reflection;

import seedu.duke.command.BadCommandException;

import java.util.NoSuchElementException;

/**
 * The main body of self reflect section.
 */
public class SelfReflection {
    private static final String LOGO = "\n" +
            "╭━━━┳━━━┳╮╱╱╭━━━╮╭━━━┳━━━┳━━━┳╮╱╱╭━━━┳━━━┳━━━━┳━━┳━━━┳━╮╱╭╮\n" +
            "┃╭━╮┃╭━━┫┃╱╱┃╭━━╯┃╭━╮┃╭━━┫╭━━┫┃╱╱┃╭━━┫╭━╮┃╭╮╭╮┣┫┣┫╭━╮┃┃╰╮┃┃\n" +
            "┃╰━━┫╰━━┫┃╱╱┃╰━━╮┃╰━╯┃╰━━┫╰━━┫┃╱╱┃╰━━┫┃╱╰┻╯┃┃╰╯┃┃┃┃╱┃┃╭╮╰╯┃\n" +
            "╰━━╮┃╭━━┫┃╱╭┫╭━━╯┃╭╮╭┫╭━━┫╭━━┫┃╱╭┫╭━━┫┃╱╭╮╱┃┃╱╱┃┃┃┃╱┃┃┃╰╮┃┃\n" +
            "┃╰━╯┃╰━━┫╰━╯┃┃╱╱╱┃┃┃╰┫╰━━┫┃╱╱┃╰━╯┃╰━━┫╰━╯┃╱┃┃╱╭┫┣┫╰━╯┃┃╱┃┃┃\n" +
            "╰━━━┻━━━┻━━━┻╯╱╱╱╰╯╰━┻━━━┻╯╱╱╰━━━┻━━━┻━━━╯╱╰╯╱╰━━┻━━━┻╯╱╰━╯";
    private static final String GREETING_MESSAGE = "Welcome to WellNUS++ Self Reflection section:D\n" +
            "Feel very occupied and cannot find time to self reflect?\n" +
            "No worries, this section will give you the opportunity to reflect and improve on yourself!!\n";

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

    private static boolean isExit = false;

    /**
     * Method to be called to exit reflection section.
     */
    public static void setIsExit() {
        SelfReflection.isExit = true;
    }

    /**
     * Print greeting logo and message.
     */
    private static void greet() {
        UI.printOutputMessage(LOGO);
        UI.printOutputMessage(GREETING_MESSAGE);
    }

    /**
     * Load the questions list with pre-defined reflect questions.
     */
    private static void setUpQuestions() {
        for (int i = 0; i < QUESTIONS.length; i += 1) {
            ReflectQuestion newQuestion = new ReflectQuestion(QUESTIONS[i]);
            ReflectQuestion.addReflectQuestion(newQuestion);
        }
    }

    /**
     * High level framework of self reflection section.<br/>
     * It first prints out greeting messges.<br/>
     * Then setting up the reflection questions.<br/>
     * Finally listen to and execute user commands.
     */
    public void run() {
        greet();
        setUpQuestions();
        while (!isExit) {
            try {
                String inputCommand = UI.readInput();
                CommandManager.execute(inputCommand);
            } catch (NoSuchElementException noSuchElement) {
                // To be changed to UI printErrorFor
                System.out.println("No such element");
            } catch (BadCommandException badCommand) {
                System.out.println("Empty command");
            } catch (InvalidCommandException invalidCommand) {
                System.out.println("Invalid command");
            }
        }
    }
}



