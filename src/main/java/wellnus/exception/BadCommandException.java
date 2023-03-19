package wellnus.exception;

/**
 * BadCommandException is thrown when a conceptual/logical error occurs in Command (sub)classes <br>
 * <p>
 * This exception should be used in classes extending from the Command class.
 * It differentiates between WellNUS and regular Java exceptions,
 * allowing better pinpointing of errors to Command subclasses.
 */
public class BadCommandException extends WellNusException {
    public BadCommandException(String errorMessage) {
        super(errorMessage);
    }
}
