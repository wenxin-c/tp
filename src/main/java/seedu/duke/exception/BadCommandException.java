package seedu.duke.exception;

public class BadCommandException extends WellNusException {
    public BadCommandException(String errorMessage) {
        super(errorMessage);
    }
}
