package seedu.duke.reflection;

import java.util.ArrayList;

/**
 * ReflectQuestion class is used to create reflect question objects.<br/>
 * <br/>
 * The static attribute questions is used to keep track all the questions created. <br/>
 * Attribute isLike is to keep track of whether a user likes this question or not.
 */
public class ReflectQuestion {

    public ReflectQuestion (String questionDescription) {
        this.questionDescription = questionDescription;
        this.isLike = false;
    }

    /**
     * Change like status to true.
     */
    public void setLike() {
        isLike = true;
    }

    /**
     * Return a string of whether a particular question is liked by user.<br/>
     * If true, then "yes"<br/>
     * Else, then "no"<br/>
     *
     * @return string "true" or "false"
     */
    public String getLikeStatus() {
        String likeStatus = (isLike ? "true" : "false");
        return likeStatus;
    }

    /**
     * Get the description of question.
     *
     * @return Question description
     */
    public String getQuestionDescription() {
        return this.questionDescription;
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

    private String questionDescription;
    private boolean isLike;
}





