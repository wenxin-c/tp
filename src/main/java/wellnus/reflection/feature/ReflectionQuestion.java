package wellnus.reflection.feature;

//@@author wenxin-c
/**
 * ReflectQuestion class is used to create reflect question objects.
 */
public class ReflectionQuestion {
    private static final int EMPTY_QUESTION = 0;
    private static final String EMPTY_QUESTION_MSG = "Question description cannot be empty.";
    private String questionDescription;

    /**
     * Constructor to create a ReflectionQuestion object and initialise question description.
     *
     * @param questionDescription The reflection question description
     */
    public ReflectionQuestion(String questionDescription) {
        assert questionDescription.length() > EMPTY_QUESTION : EMPTY_QUESTION_MSG;
        this.questionDescription = questionDescription;
    }

    /**
     * Convert each reflect question to a string to be printed.
     *
     * @return Question description with its status
     */
    @Override
    public String toString() {
        return questionDescription;
    }
}

