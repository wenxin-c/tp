package seedu.duke.reflection;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandGet extends Command{
    private static final int NUM_OF_RANDOM_QUESTIONS = 5;
    private static final ReflectUi REFLECT_UI = new ReflectUi();

    private ArrayList<ReflectQuestion> getRandomQuestions() {
        ArrayList<ReflectQuestion> selectedQuestions = new ArrayList<>();
        ArrayList<ReflectQuestion> questions = ReflectQuestion.getQuestions();
        Set<Integer> fiveRandomNumbers = generateRandomNumbers(questions.size());
        for (int index : fiveRandomNumbers) {
            selectedQuestions.add(questions.get(index));
        }
        return selectedQuestions;
    }

    private Set<Integer> generateRandomNumbers(int maxSize) {
        Set<Integer> randomNumbers = new Random().ints(0, maxSize - 1)
                .distinct()
                .limit(NUM_OF_RANDOM_QUESTIONS)
                .boxed()
                .collect(Collectors.toSet());

        return randomNumbers;
    }

    private String convertQuestionsToString() {
        ArrayList selectedQuestions = getRandomQuestions();
        String questionString = "";
        for (int i = 0; i < selectedQuestions.size(); i += 1) {
            questionString += (Integer.toString(i+1) + selectedQuestions.get(i).toString() + System.lineSeparator());
        }
        return questionString;
    }

    public void printRandomReflectQuestions() {
        String outputString = convertQuestionsToString();
        REFLECT_UI.printOutputMessage(outputString);
    }
}

