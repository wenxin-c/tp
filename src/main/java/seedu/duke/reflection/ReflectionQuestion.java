package seedu.duke.reflection;

/**
 * ReflectQuestion class is used to create reflect question objects.
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
     * Convert each reflect question to a string to be printed
     *
     * @return Question description with its status
     */
    @Override
    public String toString() {
        return questionDescription;
    }
}

