package seedu.duke.reflection;

/**
 * Exception caused by invalid command.
 */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}


