package seedu.duke.reflection;

import java.util.ArrayList;

/**
 * ReflectQuestion class is used to create reflect question objects.<br/>
 * The static attribute questions is used to keep track all the questions created. <br/>
 * Attribute isLike is to keep track of whether a user likes this question or not.
 */
public class ReflectQuestion {
    public static ArrayList<ReflectQuestion> getQuestions() {
        return questions;
    }

    private static ArrayList<ReflectQuestion> questions = new ArrayList<>();
    private String questionDescription;
    private boolean isLike;

    public ReflectQuestion (String questionDescription) {
        this.questionDescription = questionDescription;
        this.isLike = false;
    }

    public String getQuestionDescription() {
        return this.questionDescription;
    }

    public String getLikeStatus() {
        String likeStatus = (isLike ? "true" : "false");
        return likeStatus;
    }

    /**
     * Add new reflect question object into the list.<br/>
     * This method is more for developer usage at this stage as unders are not allowed to add their own questions. <br/>
     *
     * @param question newly created reflect question to be added into the list
     */
    public static void addReflectQuestion(ReflectQuestion question) {
        questions.add(question);
    }

    /**
     * Covert each reflect question to a string to be printed
     *
     * @return question description with its status
     */
    @Override
    public String toString() {
        return "[" + (isLike ? "LIKE" : " ") + "]" + questionDescription;
    }

}


