package seedu.duke.reflection;

/**
 * ReflectQuestion class is used to create reflect question objects.<br/>
 * <br/>
 * The static attribute questions is used to keep track all the questions created. <br/>
 * Attribute isLike is to keep track of whether a user likes this question or not.
 */
public class ReflectionQuestion {
    private String questionDescription;

    public ReflectionQuestion(String questionDescription) {
        this.questionDescription = questionDescription;
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
        return questionDescription;
    }
}

