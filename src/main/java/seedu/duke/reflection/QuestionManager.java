package seedu.duke.reflection;

import java.util.ArrayList;

public class QuestionManager {
    private static ArrayList<ReflectionQuestion> questions = new ArrayList<>();

    /**
     * Get all the reflection questions in the list.
     *
     * @return All refection questions in ArrayList
     */
    public static ArrayList<ReflectionQuestion> getQuestions() {
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
    public static void addReflectQuestion(ReflectionQuestion question) {
        questions.add(question);
    }
}


