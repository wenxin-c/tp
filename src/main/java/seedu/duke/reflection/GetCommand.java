package seedu.duke.reflection;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GetCommand extends Command{
    private static final int NUM_OF_RANDOM_QUESTIONS = 5;
    private static final ReflectUi REFLECT_UI = new ReflectUi();

    /**
     * Get a random set of 5 reflection questions.
     *
     * @return the selected sets of random questions
     */
    private ArrayList<ReflectQuestion> getRandomQuestions() {
        ArrayList<ReflectQuestion> selectedQuestions = new ArrayList<>();
        ArrayList<ReflectQuestion> questions = ReflectQuestion.getQuestions();
        Set<Integer> fiveRandomNumbers = generateRandomNumbers(questions.size());
        for (int index : fiveRandomNumbers) {
            selectedQuestions.add(questions.get(index));
        }
        return selectedQuestions;
    }

    /**
     * Generate an array of 5 random numbers, <br/>
     * each number num: num >= 0 and num <= (maxSize - 1)
     *
     * @param maxSize number of questions available to be chosen
     * @return array of 5 random numbers
     */
    private Set<Integer> generateRandomNumbers(int maxSize) {
        Set<Integer> randomNumbers = new Random().ints(0, maxSize - 1)
                .distinct()
                .limit(NUM_OF_RANDOM_QUESTIONS)
                .boxed()
                .collect(Collectors.toSet());
        return randomNumbers;
    }

    /**
     * Convert all five questions to a single string to be printed.
     *
     * @return single string that consists of all questions
     */
    private String convertQuestionsToString() {
        ArrayList selectedQuestions = getRandomQuestions();
        String questionString = "";
        for (int i = 0; i < selectedQuestions.size(); i += 1) {
            questionString += (Integer.toString(i+1) + selectedQuestions.get(i).toString() + System.lineSeparator());
        }
        return questionString;
    }

    /**
     * Entry point to this command.<br/>
     * Trigger the generation of five random questions and print to users.<br/>
     */
    @Override
    public void execute() {
        String outputString = convertQuestionsToString();
        REFLECT_UI.printOutputMessage(outputString);
    }
}

