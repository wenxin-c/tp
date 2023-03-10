package seedu.duke.reflection;

import java.util.ArrayList;

public class QuestionManager {
    private static ArrayList<ReflectQuestion> questions = new ArrayList<>();

    /**
     * Get all the reflection questions in the list.
     *
     * @return All refection questions in ArrayList
     */
    public static ArrayList<ReflectQuestion> getQuestions() {
        return questions;
    }

    /**
     * Remove all questions in the list.
     */
    public static void clearQuestions() {
        questions.clear();
    }

    /**
     * Add new reflect question object into the list.<br/>
     * This method is more for developer usage at this stage as under are not allowed to add their own questions. <br/>
     *
     * @param question newly created reflect question to be added into the list
     */
    public static void addReflectQuestion(ReflectQuestion question) {
        questions.add(question);
    }
}


