package wellnus.reflection;

import wellnus.exception.EmptyReflectionQuestionException;

/**
 * ReflectQuestion class is used to create reflect question objects.
 */
public class ReflectionQuestion {
    private static final int EMPTY_QUESTION = 0;
    private static final String EMPTY_QUESTION_MSG = "Question description cannot be empty.";
    private String questionDescription;

    public ReflectionQuestion(String questionDescription) throws EmptyReflectionQuestionException {
        if (questionDescription.length() == EMPTY_QUESTION) {
            throw new EmptyReflectionQuestionException(EMPTY_QUESTION_MSG);
        }
        assert questionDescription.length() > EMPTY_QUESTION : EMPTY_QUESTION_MSG;
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

