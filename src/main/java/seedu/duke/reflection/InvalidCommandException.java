package seedu.duke.reflection;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
